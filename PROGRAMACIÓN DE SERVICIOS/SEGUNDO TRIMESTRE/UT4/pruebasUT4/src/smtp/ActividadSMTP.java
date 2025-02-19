package smtp;

import java.io.IOException;
import java.io.Writer;
import java.security.InvalidKeyException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.spec.InvalidKeySpecException;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class ActividadSMTP {
    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
        if (args.length < 5) {
            System.out.println("Uso: java ActividadSMTPModificado <servidorSMTP> <puerto> <usuario> <clave> <destinatario>");
            return;
        }
        
        String servidorSMTP = args[0];
        int puerto = Integer.parseInt(args[1]);
        String usuario = args[2];
        String clave = args[3];
        String destinatario = args[4];
        
        String cuerpoMensaje = "Mensaje.";
        
        AuthenticatingSMTPClient cliente = new AuthenticatingSMTPClient();
        
        try {
            KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
            kmf.init(null, null);
            KeyManager km = kmf.getKeyManagers()[0];
            cliente.setKeyManager(km);

            cliente.connect(servidorSMTP, puerto);
            System.out.println("Conexión inicial: " + cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(cliente.getReplyCode())) {
                cliente.disconnect();
                System.err.println("Conexión rechazada.");
                return;
            }
            
            int ehloReply = cliente.sendCommand("EHLO", "localhost");
            System.out.println("EHLO: " + cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(ehloReply)) {
                cliente.disconnect();
                System.err.println("Fallo en el comando EHLO.");
                return;
            }
            
            if (!cliente.execTLS()) {
                cliente.disconnect();
                System.err.println("No se pudo iniciar TLS (STARTTLS).");
                return;
            }
            System.out.println("TLS iniciado: " + cliente.getReplyString());
            
            ehloReply = cliente.sendCommand("EHLO", "localhost");
            System.out.println("EHLO post-TLS: " + cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(ehloReply)) {
                cliente.disconnect();
                System.err.println("Fallo en EHLO después de TLS.");
                return;
            }
            
            if (!cliente.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, usuario, clave)) {
                cliente.disconnect();
                System.err.println("Fallo en la autenticación.");
                return;
            }
            System.out.println("Autenticación exitosa: " + cliente.getReplyString());
            
            cliente.setSender(usuario);
            cliente.addRecipient(destinatario);
            
            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(usuario, destinatario, "Asunto del correo");
            String mensajeCompleto = cabecera.toString() + "\r\n" + cuerpoMensaje + "\r\n";
            
            Writer writer = cliente.sendMessageData();
            if (writer != null) {
                writer.write(mensajeCompleto);
                writer.flush();
                writer.close();
                if (cliente.completePendingCommand()) {
                    System.out.println("Mensaje enviado a " + destinatario);
                } else {
                    System.err.println("Error al finalizar el envío del mensaje.");
                }
            } else {
                System.err.println("No se pudo iniciar el envío de datos.");
            }
            
            cliente.logout();
            cliente.disconnect();
            System.out.println("Desconectado del servidor.");
            
        } catch (IOException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException e) {
            System.err.println("Ocurrió un error inesperado:");
            e.printStackTrace();
        }
    }
}
