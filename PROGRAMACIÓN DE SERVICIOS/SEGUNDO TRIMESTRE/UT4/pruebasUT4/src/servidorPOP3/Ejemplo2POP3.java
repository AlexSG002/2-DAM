package servidorPOP3;

import java.io.BufferedReader;
import java.io.IOException;
import org.apache.commons.net.pop3.POP3MessageInfo;
import org.apache.commons.net.pop3.POP3SClient;

public class Ejemplo2POP3 {

    public static void main(String[] args) {
        String server = "localhost", username = "usu1", password = "usu1";
        int puerto = 110;

        POP3SClient pop3 = new POP3SClient();

        try {
            pop3.connect(server, puerto);
            System.out.println("Conexión realizada al servidor POP3 " + server);
            // nos logueamos
            if (!pop3.login(username, password))
                System.err.println("Error al hacer login");
            else {
                POP3MessageInfo[] men = pop3.listMessages();
                if (men == null)
                    System.out.println("Imposible recuperar mensajes.");
                else {
                    System.out.println("Nº de mensajes: " + men.length);
                    if (men.length > 0)
                        RecuperaMensajes(men, pop3);
                }

                // Finalizar sesión
                pop3.logout();
            }
            // Nos desconectamos
            pop3.disconnect();

        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }

    private static void RecuperaMensajes(POP3MessageInfo[] men, POP3SClient pop3) 
        throws IOException {
        for (int i = 0; i < men.length; i++) {
            // Por cada mensaje en messages[]
            System.out.println("Mensaje: " + (i + 1));
            POP3MessageInfo msginfo = men[i]; // Lista de mensajes
            System.out.println("Identificación: " + msginfo.identifier
                + ", Número: " + msginfo.number + ", Tamaño: "
                + msginfo.size);

            System.out.println("Prueba de listUniqueIdentifier:");
            POP3MessageInfo pni = pop3.listUniqueIdentifier(i + 1); // Un mensaje
            System.out.println("Identificador: " + pni.identifier
                + ", Número: " + pni.number + ", Tamaño: " + pni.size);

            // Solo recupera cabecera
            System.out.println("Cabecera del mensaje:");
            BufferedReader reader = (BufferedReader) pop3.retrieveMessageTop(
                msginfo.number, 0);

            String linea;
            while ((linea = reader.readLine()) != null)
                System.out.println(linea.toString());
            reader.close();

            // Recupera todo
            BufferedReader reader2 = (BufferedReader) pop3.retrieveMessage(
                msginfo.number);

            while ((linea = reader2.readLine()) != null)
                System.out.println(linea.toString());
            reader2.close();
        }
    }
} // Fin Ejemplo2POP3

