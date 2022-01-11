package com.company;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static final int PORT = 8001;
    public static final String DISCONNECT_KEYWORD = "close";
    private static ServerSocket server;
    private static BufferedReader inputStream;
    private static BufferedWriter outputStream;

    public static void main(String[] args) {
        try {
            try  {
                server = new ServerSocket(PORT);
                System.out.println("Server is started on port " + PORT);
                System.out.println("Send \"" + DISCONNECT_KEYWORD + "\" to disconnect the client");

                try (Socket clientSocket = server.accept()) {
                    inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    outputStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));
                    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                    while (true) {
                        String outputMessage = inputStream.readLine();
                        if (outputMessage == null) {
                            throw new NullPointerException();
                        }
                        if (outputMessage.equals(Server.DISCONNECT_KEYWORD)) {
                            break;
                        }
                        System.out.println("Message: " + outputMessage);
                        System.out.print("Return: ");
                        outputStream.write(reader.readLine() + "\n");
                        outputStream.flush();
                    }
                } catch (NullPointerException ignored) {
                    System.out.println("Seems like the client was disconnected!");
                } finally {
                    inputStream.close();
                    outputStream.close();
                }
            } finally {
                server.close();
                System.out.println("Server was stopped");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}