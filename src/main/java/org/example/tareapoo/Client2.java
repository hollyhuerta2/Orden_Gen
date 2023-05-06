package org.example.tareapoo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client2 {
    public static String SERVER_IP = "Localhost";
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

            // enviamos los datos del archivo al servidor en bloques de 1024 bytes
            int bytesRead;
            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                dataOutputStream.write(buffer, 0, bytesRead);
            }

            // notificamos al servidor que hemos terminado de enviar el archivo
            dataOutputStream.flush();
            socket.shutdownOutput();

            // leemos la respuesta del servidor
            InputStream inputStream = socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            String response = bufferedReader.readLine();
            System.out.println("Server response: " + response);

            // cerramos los flujos de entrada y salida
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
