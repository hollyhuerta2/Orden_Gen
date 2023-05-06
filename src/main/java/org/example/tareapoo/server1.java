package org.example.tareapoo;
import java.io.*;
import java.net.ServerSocket;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class server1 {
    public static int PORT = 9888;

    public static void main(String[] args) {
        try {
            // creamos al socket tipo servidor usando el puerto definido
            ServerSocket server = new ServerSocket(PORT);
            // esperamos a que se realice una conexión, bloqueando el flujo de la aplicación
            Socket socket = server.accept();

            // creamos el objeto InputStream para recibir datos binarios del cliente
            InputStream inputStream = socket.getInputStream();

            // creamos un buffer de bytes para leer los datos del archivo
            byte[] buffer = new byte[1024];

            // leemos la información del archivo del cliente y lo escribimos en un nuevo archivo en el servidor
            FileOutputStream fileOutputStream = new FileOutputStream("archivorecibido.txt");
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                fileOutputStream.write(buffer, 0, bytesRead);
            }

            // notificamos al cliente que hemos recibido el archivo exitosamente
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Archivo recibido exitosamente".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            // cerramos los flujos de entrada y salida
            inputStream.close();
            outputStream.close();
            socket.close();
            server.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

}
