package smtp;

import java.io.IOException;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;

public class ClienteSMTP {
	public static void main(String[] args) {
        SMTPClient client = new SMTPClient();
        try {
            int respuesta;
            client.connect("localhost");
            System.out.print(client.getReplyString());
            respuesta = client.getReplyCode();

            if (!SMTPReply.isPositiveCompletion(respuesta)) {
                client.disconnect();
                System.err.println("CONEXION RECHAZADA.");
                System.exit(1);
            }
            
            // REALIZAR ACCIONES

        } catch (IOException e) {
            if (client.isConnected()) {
                try {
                    client.disconnect();
                } catch (IOException ex) {
                    // NO HAGO NADA
                }
            }
            System.err.println("NO SE PUEDE CONECTAR AL SERVIDOR.");
            e.printStackTrace();
            System.exit(1);
        }

        // NOS DESCONECTAMOS
        try {
            client.disconnect();
        } catch (IOException e) {
            System.err.println("ERROR AL DESCONECTAR DEL SERVIDOR.");
            e.printStackTrace();
        }

        System.exit(0);
    }
}
