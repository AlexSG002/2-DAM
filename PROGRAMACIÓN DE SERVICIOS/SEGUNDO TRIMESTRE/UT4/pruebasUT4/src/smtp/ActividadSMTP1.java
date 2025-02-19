package smtp;

import java.io.IOException;
import java.io.Writer;
import java.util.Base64;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SMTPSClient;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class ActividadSMTP1 {
    public static void main(String[] args) {
        if (args.length < 5) {
            System.out.println("Uso: java ActividadSMTPS <servidorSMTP> <puerto> <usuario> <clave> <destinatario>");
            return;
        }
        
        String servidorSMTP = args[0];
        int puerto = Integer.parseInt(args[1]);
        String usuario = args[2];
        String clave = args[3];
        String destinatario = args[4];
        
        String cuerpoMensaje = "Mensajote.";
        
        // Crear SMTPSClient en modo NO implícito para usar STARTTLS
        SMTPSClient cliente = new SMTPSClient("TLS", false);
        try {
            // Conectar al servidor (smtp.gmail.com, puerto 587)
            cliente.connect(servidorSMTP, puerto);
            System.out.print(cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(cliente.getReplyCode())) {
                cliente.disconnect();
                System.err.println("Conexión rechazada.");
                return;
            }
            
            // Enviar EHLO antes de STARTTLS
            int ehloReply = cliente.sendCommand("EHLO", "localhost");
            System.out.println("EHLO reply: " + cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(ehloReply)) {
                cliente.disconnect();
                System.err.println("EHLO command failed.");
                return;
            }
            
            // Actualizar la conexión a TLS usando STARTTLS
            if (!cliente.execTLS()) {
                cliente.disconnect();
                System.err.println("No se pudo iniciar TLS (STARTTLS).");
                return;
            }
            
            // Reenviar EHLO después de la actualización a TLS
            ehloReply = cliente.sendCommand("EHLO", "localhost");
            System.out.println("EHLO after TLS reply: " + cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(ehloReply)) {
                cliente.disconnect();
                System.err.println("EHLO command failed after TLS.");
                return;
            }
            
            // Autenticación AUTH PLAIN:
            // Formato: base64("\0" + usuario + "\0" + clave)
            String authString = "\0" + usuario + "\0" + clave;
            String base64Auth = Base64.getEncoder().encodeToString(authString.getBytes("UTF-8"));
            int resp = cliente.sendCommand("AUTH", "PLAIN " + base64Auth);
            System.out.println("Respuesta AUTH: " + cliente.getReplyString());
            if (!SMTPReply.isPositiveCompletion(resp)) {
                cliente.disconnect();
                System.err.println("Fallo en la autenticación.");
                return;
            }
            
            System.out.println("Conexión y autenticación exitosa.");
            
            // Configurar remitente y destinatario
            cliente.setSender(usuario);
            cliente.addRecipient(destinatario);
            
            // Crear el encabezado SMTP y formar el mensaje completo
            SimpleSMTPHeader header = new SimpleSMTPHeader(usuario, destinatario, "Asunto del correo");
            String mensajeCompleto = header.toString() + "\r\n" + cuerpoMensaje + "\r\n";
            
            // Enviar el mensaje mediante el comando DATA
            Writer writer = cliente.sendMessageData();
            if (writer != null) {
                writer.write(mensajeCompleto);
                writer.flush();
                writer.close();
                // Finalizar la transacción DATA
                if (cliente.completePendingCommand()) {
                    System.out.println("Mensaje enviado a " + destinatario);
                } else {
                    System.out.println("Error al enviar el mensaje.");
                }
            } else {
                System.out.println("No se ha podido iniciar el envío de datos.");
            }
            
            cliente.logout();
            cliente.disconnect();
            System.out.println("Desconectado del servidor.");
            
        } catch (IOException e) {
            System.err.println("Ocurrió un error inesperado.");
            e.printStackTrace();
        }
    }
}
