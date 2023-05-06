package org.example;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.*;

public class Cliente3 {
    public static final int PORT = 8080;
    public static final String SERVER_ADDRESS = "localhost";
    private static Socket socket;

    public static void main(String[] args) {
        try {
            socket = new Socket(SERVER_ADDRESS, PORT);
            System.out.println("Conexión establecida con el servidor: " + socket.getInetAddress());

            DataInputStream din = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(in);

            String line1 = "", line2 = "";

            boolean receivingFile = false;
            String receivedFileName = "received_file.txt";
            FileOutputStream fos = null;

            while (!line1.equals("stop")) {
                if (din.available() > 0) {
                    if (receivingFile) {
                        byte[] buffer = new byte[4096];
                        int bytesRead = din.read(buffer);

                        if (bytesRead > 0) {
                            fos.write(buffer, 0, bytesRead);
                            fos.close();

                            System.out.println("Archivo recibido y guardado como: " + receivedFileName);

                            receivingFile = false;
                        }
                    } else {
                        line1 = din.readUTF();

                        if (line1.equals("sending_file")) {
                            fos = new FileOutputStream(receivedFileName);
                            receivingFile = true;
                        } else if (line1.equals("file_sent")) {
                            // No es necesario hacer nada aquí
                        } else {
                            System.out.println("Mensaje recibido del servidor: " + line1);
                        }
                    }
                }

                if (buff.ready()) {
                    line2 = buff.readLine();

                    if (line2.startsWith("file=")) {
                        dos.writeUTF(line2);
                        dos.flush();

                        String fileName = line2.substring(5);
                        File file = new File(fileName);

                        if (file.exists() && (fileName.endsWith(".txt") || fileName.endsWith(".json"))) {
                            dos.writeUTF("sending_file");
                            dos.flush();

                            FileInputStream fileInputStream = new FileInputStream(file);

                            byte[] buffer = new byte[4096];
                            int bytesRead = -1;

                            while ((bytesRead = fileInputStream.read(buffer)) != -1) {
                                dos.write(buffer, 0, bytesRead);
                            }

                            System.out.println("Archivo enviado");
                            fileInputStream.close();
                        } else {
                            System.out.println("Error: El archivo no existe o no es compatible.");
                        }
                    } else {
                        dos.writeUTF(line2);
                        dos.flush();

                        System.out.println("Mensaje enviado al servidor: " + line2);
                    }
                }
            }

            din.close();
            dos.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
