package org.example.tareapoo;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class Client2 {
    public static String SERVER_IP = "localhost"; // "localhost" en minúsculas
    public static int SERVER_PORT = 9888;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, SERVER_PORT);
             FileInputStream fileInputStream = new FileInputStream("Interstellar.txt");
             OutputStream outputStream = socket.getOutputStream();
             DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
             InputStream inputStream = socket.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
             InputStreamReader in = new InputStreamReader(System.in, StandardCharsets.UTF_8);
             BufferedReader buff = new BufferedReader(in)) {

            sendFileToServer(fileInputStream, dataOutputStream);

            String response = receiveResponseFromServer(socket, bufferedReader);

            handleUserInputAndServerResponse(socket, buff, dataOutputStream, bufferedReader);

            System.out.println("Server response: " + response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private static String receiveResponseFromServer(Socket socket, BufferedReader bufferedReader) throws IOException {
        String response = bufferedReader.readLine();
        return response;
    }

    private static void sendFileToServer(FileInputStream fileInputStream, DataOutputStream dataOutputStream) throws IOException {
        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fileInputStream.read(buffer)) != -1) {
            dataOutputStream.write(buffer, 0, bytesRead);
        }
        dataOutputStream.flush();
        // notificamos al servidor que hemos terminado de enviar el archivo
       // socket.shutdownOutput();
    }



    private static void handleUserInputAndServerResponse(Socket socket, BufferedReader buff, DataOutputStream dataOutputStream, BufferedReader bufferedReader) throws IOException {
        String userInput = "";
        String serverResponse = "";
        while (!userInput.equals("stop")) {
            System.out.print("Message: ");
            userInput = buff.readLine();
            dataOutputStream.writeUTF(userInput);
            dataOutputStream.flush();
            serverResponse = bufferedReader.readLine();
            System.out.println("Server says: " + serverResponse);
        }
    }
}
