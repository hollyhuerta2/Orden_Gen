package org.example.segundoparcial;

//package mx.uv.fiee.iinf.poo.demos.serversocketv2;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;


/**
 * En esta versión del servidor se permite una conexión uno a uno
 * recibiendo mensajes del cliente y dando la capacidad al usuario de
 * responderlos.
 *
 * Los puntos principales son, el uso de un ciclo para controlar el flujo
 * de la aplicación, terminando hasta que se lee de la consola la palabra "stop".
 *
 * Para enviar y recibir mensajes es necesario utilizar ambos flujos proporcionados
 * por el socket, entrada mediante InputStream y salida mediante OutputStream.
 *
 * La entrada del usuario es manejado mediante el flujo de entrada de la consola
 * referenciado por método "in", de la clase System.
 *
 */
public class Server {
    public static int PORT = 9888;

    public static void main(String[] args) {
        try {
            // creamos al socket tipo servidor usando el puerto definido
            ServerSocket server = new ServerSocket (PORT);
            // esperamos a que se realice una conexión, bloqueando el flujo de la aplicación
            Socket socket = server.accept ();
            byte[] buffer = new byte[1024];

            // creamos los objetos DataInputStream y DataOutputStream, usando el flujo de entrada
            // y salida del socket
            DataInputStream din = new DataInputStream (socket.getInputStream ());
            DataOutputStream dos = new DataOutputStream (socket.getOutputStream ());

            // la entrada del usuario se referencía de la entrada por consola
            // Utilizamos un objeto InputStreamReader para leer los bytes
            // entrantes y los convierta a su representación unicode
            InputStreamReader in = new InputStreamReader (System.in, StandardCharsets.UTF_8);

            // La clase Reader lee byte a byte e interpreta cada byte de acuerdo a la codificación establecidad,
            // pero lo que necesitamos es leer bloques (líneas) completas y se almacen en memoria
            // para ser enviadas, para ellos utilizamos un BufferedReader
            BufferedReader buff = new BufferedReader (in);


            // variables de control
            // line1, lee el mensaje remoto entrante
            // line2, almacena la entrada del usuario
            String line1 = "", line2 = "";
            FileOutputStream fileOutputStream = new FileOutputStream("archivoCliente.txt");

                /**
                 * Para realizar lectura y envíos recurrentes, utilizamos un ciclo.
                 *
                 * La aplicación se detiene cuando el usuario teclea "stop"
                 */
                while (!line1.equals("stop")) {
                    if(line2.equals("stop")) {
                        int bytesRead;
                        while ((bytesRead = din.read(buffer)) != -1) {
                            fileOutputStream.write(buffer, 0, bytesRead);
                        }
                    }
                        // lee los bytes entrantes de la conexión como caracteres unicode,
                        // el flujo de la aplicación se detiene esperando la llegada de datos
                        line1 = din.readUTF();
                        System.out.println("Client says: " + line1);

                        System.out.print("Response: ");
                        // lee la entrada del usuario, el flujo de la aplicación se detiene
                        line2 = buff.readLine();

                        // la entrada del usuario es codificada a caracteres unicode,
                        // y coloca en el buffer de salida de la conexión
                        dos.writeUTF(line2);
                        dos.flush(); // envía los bytes pendientes de salida

                }

            fileOutputStream.close();
            OutputStream outputStream = socket.getOutputStream();
            outputStream.write("Archivo recibido exitosamente".getBytes(StandardCharsets.UTF_8));
            outputStream.flush();

            // cerramos los flujos de entrada y salida

            dos.close ();
            din.close ();
            outputStream.close();
            socket.close();
            server.close (); // y finalmente la conexión
        } catch (IOException ex) {
            ex.printStackTrace ();
        }

    }

}
