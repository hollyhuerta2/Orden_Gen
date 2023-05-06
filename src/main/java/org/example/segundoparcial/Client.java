package org.example.segundoparcial;

//package mx.uv.fiee.iinf.poo.demos.clientsocketv2;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * En esta versión del cliente se permite una conexión uno a uno
 * recibiendo mensajes del servidor y dando la capacidad al usuario de
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
public class Client {
    public static String ADDRESS = "localhost";
    public static int PORT = 9888;

    public static void main(String [] args) {
        try {
            // creamos el socket cliente, utilizando la dirección remota y el mismo puerto de escucha del servidor
            Socket socket = new Socket (ADDRESS, PORT);

            // creamos los objetos DataInputStream y DataOutputStream, usando el flujo de entrada
            // y salida del socket
            DataInputStream din = new DataInputStream (socket.getInputStream ());
            DataOutputStream dos = new DataOutputStream (socket.getOutputStream ());

            // la entrada del usuario se referencía de la entrada por consola utilizamos un objeto InputStreamReader
            // para leer los bytes entrantes y los convierta a su representación unicode
            InputStreamReader in = new InputStreamReader (System.in, StandardCharsets.UTF_8);

            // La clase Reader lee byte a byte e interpreta cada byte de acuerdo a la codificación establecidad,
            // pero lo que necesitamos es leer bloques (líneas) completas y se almacen en memoria
            // para ser enviadas, para ellos utilizamos un BufferedReader
            BufferedReader buff = new BufferedReader (in);


            // Variables de control
            // line1, almacena la entrada del usuario
            // line2, lee el mensaje remoto entrante
            String line1 = "", line2 = "";
            File file = new File("Interstellar.txt");
            byte[] fileBytes = new byte[(int) file.length()];
            FileInputStream fileInputStream = new FileInputStream(file);


            /**
             * Para realizar lectura y envíos recurrentes, utilizamos un ciclo.
             *
             * La aplicación se detiene cuando el usuario teclea "stop"
             */
            while (!line1.equals ("stop")) {
                if(line2.equals("stop")) {
                    fileInputStream.read(fileBytes);
                    dos.write(fileBytes);
                    dos.flush();
                }
                    System.out.print("Message: ");

                    // lee la entrada del usuario mediante la consola del sistema
                    line1 = buff.readLine();

                    // escribe la entradad del usuario en el buffer del flujo de salida
                    dos.writeUTF(line1);
                    dos.flush(); // envía los bytes pendientes alojados en el flujo de salida

                    // espera la respuesta del equipo remoto
                    // el flujo de la aplicación se bloquea en este punto, hasta que llega algún dato
                    line2 = din.readUTF();
                    System.out.println("Server says: " + line2); // coloca el mensaje entrante en la consola

            }

            // cerramos los flujos de entrada y salida
            din.close ();
            dos.close ();
            fileInputStream.close();
            socket.close (); // y terminamos la conexión

        } catch (IOException ex) {
            ex.printStackTrace ();
        }

    }
}