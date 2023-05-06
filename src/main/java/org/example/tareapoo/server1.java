package org.example.tareapoo;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class server1 {
    public static int PORT = 9888;
    public static final String FILE_NAME = "archivorecibido.txt";
    public static final String SUCCESS_MESSAGE = "Archivo recibido exitosamente";
    public static final String STOP_COMMAND = "stop";
    public static void main(String[] args) {
            try (ServerSocket server = new ServerSocket(PORT);
                 Socket socket = server.accept();
                 InputStream inputStream = socket.getInputStream();
                 DataInputStream din = new DataInputStream(inputStream);
                 DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                 OutputStream outputStream = socket.getOutputStream();
                 BufferedReader buff = new BufferedReader(new InputStreamReader(System.in, StandardCharsets.UTF_8))) {

                receiveFile(inputStream);

                String clientMessage, serverResponse;
                do {
                    // lee los bytes entrantes de la conexión como caracteres unicode,
                    // el flujo de la aplicación se detiene esperando la llegada de datos
                    clientMessage = din.readUTF();
                    System.out.println("Client says: " + clientMessage);

                    System.out.print("Response: ");
                    // lee la entrada del usuario, el flujo de la aplicación se detiene
                    serverResponse = buff.readLine();

                    // la entrada del usuario es codificada a caracteres unicode,
                    // y coloca en el buffer de salida de la conexión
                    dos.writeUTF(serverResponse);
                    dos.flush(); // envía los bytes pendientes de salida
                } while (!clientMessage.equals(STOP_COMMAND));

                // notificamos al cliente que hemos recibido el archivo exitosamente
                outputStream.write(SUCCESS_MESSAGE.getBytes(StandardCharsets.UTF_8));
                outputStream.flush();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        private static void receiveFile(InputStream inputStream) throws IOException {
            // leemos la información del archivo del cliente y lo escribimos en un nuevo archivo en el servidor
            try (FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    fileOutputStream.write(buffer, 0, bytesRead);
                }
            }
        }

    }
