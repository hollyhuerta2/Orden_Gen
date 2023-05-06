package org.example.tareapoo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client2 {
    public static String SERVER_IP = "localhost"; // "localhost" en minúsculas
    public static int SERVER_PORT = 9888;

    public static void main(String[] args) {
        try {
            // creamos el socket tipo cliente
            Socket socket = new Socket(SERVER_IP, SERVER_PORT);

            // creamos el objeto OutputStream para enviar datos binarios al servidor
            OutputStream outputStream = socket.getOutputStream();
            DataOutputStream dataOutputStream = new DataOutputStream(outputStream);

            // leemos el archivo que queremos enviar al servidor
            FileInputStream fileInputStream = new FileInputStream("Interstellar.txt");
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            // notificamos al servidor que hemos terminado de enviar el archivo
            dataOutputStream.flush();
            socket.shutdownOutput();

            // creamos los flujos de entrada para recibir mensajes del servidor
            DataInputStream din = new DataInputStream(socket.getInputStream());
            InputStreamReader in = new InputStreamReader(System.in, StandardCharsets.UTF_8);
            BufferedReader buff = new BufferedReader(in);

            String line1 = "", line2 = "";
            while (!line1.equals("stop")) {
                System.out.print("Message: ");

                // lee la entrada del usuario mediante la consola del sistema
                line1 = buff.readLine();

                // escribe la entrada del usuario en el buffer del flujo de salida
                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(line1);
                dos.flush(); // envía los bytes pendientes alojados en el flujo de salida

                // espera la respuesta del equipo remoto
                // el flujo de la aplicación se bloquea en este punto, hasta que llega algún dato
                line2 = din.readUTF();
                System.out.println("Server says: " + line2); // coloca el mensaje entrante en la consola
            }

            // leemos la respuesta del servidor
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String response = bufferedReader.readLine();
            System.out.println("Server response: " + response);

            // cerramos los flujos de entrada y salida
            din.close();
            dataOutputStream.close();
            outputStream.close();
            fileInputStream.close();
            bufferedReader.close();
            inputStream.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
