package com.company;
import java.net.*;
import java.io.*;

public class Client {
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        try {
            try {
                Socket clientSocket = new Socket(HOST, Server.PORT);
                System.out.println("You connected to the server on port" + Server.PORT);
                System.out.println("Send \"" + Server.DISCONNECT_KEYWORD + "\" to disconnect the server");

                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

                BufferedReader inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter outputStream = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()));

                System.out.print("Message: ");

                String outputMessage = "";
                while (!outputMessage.equals(Server.DISCONNECT_KEYWORD)) {
                    String inputMessage = reader.readLine();
                    outputStream.write(inputMessage + "\n");
                    outputStream.flush();
                    outputMessage = inputStream.readLine();
                    if (outputMessage == null) {
                        throw new NullPointerException();
                    }
                    System.out.println(outputMessage);
                }

                System.out.println("Goodbye!");
                clientSocket.close();
                inputStream.close();
                outputStream.close();
            } catch (ConnectException ignored) {
                System.out.println("Cannot connect to the server!");
            } catch (NullPointerException ignored) {
                System.out.println("Seems like the server was disconnected!");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}