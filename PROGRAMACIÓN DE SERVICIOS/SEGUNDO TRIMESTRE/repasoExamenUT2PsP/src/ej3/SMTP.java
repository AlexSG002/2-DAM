package ej3;

import java.io.*;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;

import org.apache.commons.net.smtp.AuthenticatingSMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.apache.commons.net.smtp.SimpleSMTPHeader;

public class SMTP {
    private static final String CLIENTES_FILE = "clientes.txt"; // Archivo con los correos
    private List<String> correos; // Lista de correos a los que se enviará el email

    public SMTP() {
        this.correos = new ArrayList<>();
    }

    /**
     * Lee los correos electrónicos del archivo clientes.txt y los almacena en la lista.
     */
    public void cargarCorreos() {
        try (BufferedReader br = new BufferedReader(new FileReader(CLIENTES_FILE))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    correos.add(linea.trim()); // Agregar el correo a la lista
                }
            }
            System.out.println("Correos cargados correctamente.");
        } catch (IOException e) {
            System.err.println("Error al leer el archivo de clientes: " + e.getMessage());
        }
    }

    /**
     * Envía un correo a cada dirección de la lista.
     * @throws InvalidKeySpecException 
     * @throws NoSuchAlgorithmException 
     * @throws InvalidKeyException 
     */
    public void enviarCorreos(String servidorSMTP, int puerto, String usuario, String clave, String asunto, String cuerpoMensaje) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (correos.isEmpty()) {
            System.err.println("No hay correos para enviar.");
            return;
        }

        for (String destinatario : correos) {
            enviarCorreo(servidorSMTP, puerto, usuario, clave, destinatario, asunto, cuerpoMensaje);
        }
    }

    private void enviarCorreo(String servidorSMTP, int puerto, String usuario, String clave, String destinatario, String asunto, String cuerpoMensaje) throws NoSuchAlgorithmException, InvalidKeyException, InvalidKeySpecException {
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

            if (!cliente.execTLS()) {
                cliente.disconnect();
                System.err.println("No se pudo iniciar TLS.");
                return;
            }

            if (!cliente.auth(AuthenticatingSMTPClient.AUTH_METHOD.PLAIN, usuario, clave)) {
                cliente.disconnect();
                System.err.println("Fallo en la autenticación.");
                return;
            }

            cliente.setSender(usuario);
            cliente.addRecipient(destinatario);

            SimpleSMTPHeader cabecera = new SimpleSMTPHeader(usuario, destinatario, asunto);
            String mensajeCompleto = cabecera.toString() + "\r\n" + cuerpoMensaje + "\r\n";

            Writer writer = cliente.sendMessageData();
            if (writer != null) {
                writer.write(mensajeCompleto);
                writer.flush();
                writer.close();
                if (cliente.completePendingCommand()) {
                    System.out.println("✔ Mensaje enviado a " + destinatario);
                } else {
                    System.err.println("✖ Error al finalizar el envío del mensaje a " + destinatario);
                }
            } else {
                System.err.println("✖ No se pudo iniciar el envío de datos para " + destinatario);
            }

            cliente.logout();
            cliente.disconnect();
        } catch (IOException | NoSuchAlgorithmException | UnrecoverableKeyException | KeyStoreException e) {
            System.err.println("Ocurrió un error con " + destinatario + ": " + e.getMessage());
        }
    }

    public static void main(String[] args) throws InvalidKeyException, NoSuchAlgorithmException, InvalidKeySpecException {
        if (args.length < 4) {
            System.out.println("Uso: java EmailSender <servidorSMTP> <puerto> <usuario> <clave>");
            return;
        }

        String servidorSMTP = args[0];
        int puerto = Integer.parseInt(args[1]);
        String usuario = args[2];
        String clave = args[3];

        String asunto = "Asunto del correo";
        String cuerpoMensaje = "Este es un mensaje de prueba.";

        SMTP emailSender = new SMTP();
        emailSender.cargarCorreos();
        emailSender.enviarCorreos(servidorSMTP, puerto, usuario, clave, asunto, cuerpoMensaje);
    }
}
