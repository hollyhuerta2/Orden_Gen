package org.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server3 {
    public static final int PORT = 8080;
    private static Socket clientSocket;

    public static void main(String[] args) {
        try {
            ServerSocket server = new ServerSocket(PORT);

            System.out.println("Esperando conexión...");
            clientSocket = server.accept();
            System.out.println("Conexión establecida con el cliente: " + clientSocket.getInetAddress());

            DataInputStream din = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            InputStreamReader in = new InputStreamReader(System.in);
            BufferedReader buff = new BufferedReader(in);

            String line1 = "", line2 = "";

            while (!line1.equals("stop")) {
                if (din.available() > 0) {
                    line1 = din.readUTF();
                    System.out.println("Cliente dice: " + line1);

                    // Lógica para recibir y guardar el archivo en el servidor
                    if (line1.equals("sending_file")) {
                        File receivedFile = new File("server_received_file.txt");
                        FileOutputStream fos = new FileOutputStream(receivedFile);

                        byte[] buffer = new byte[4096];
                        int bytesRead = -1;

                        while (din.available() > 0 && (bytesRead = din.read(buffer)) != -1) {
                            fos.write(buffer, 0, bytesRead);
                        }

                        fos.close();
                        System.out.println("Archivo recibido y guardado en el servidor.");
                    }
                }

                if (buff.ready()) {
                    line2 = buff.readLine();

                    if (line2.startsWith("file=")) {
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
                        System.out.println("Mensaje enviado al cliente: " + line2);
                    }
                }
            }

            din.close();
            dos.close();
            clientSocket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
