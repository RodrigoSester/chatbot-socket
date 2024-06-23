package classes;

import java.net.*;
import java.io.*;

public class Client {
	private static final String HOST = "localhost";
	private static final int PORT = 4567;
	
    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT)) {
            System.out.println("Server is connected");
            System.out.println("Welcome to Brele Chatbot!!");
            System.out.println("You can do a few questions to me, there are them: ");
            System.out.println(
            		"- Que horas sao?\n"
            		+ "- Vai chover?\n"
            		+ "- Que horas o sol se poe?\n"
            		+ "- Qual a temperatura maxima de hoje?\n"
            		+ "- Qual a temperatura minima de hoje?\n"
            );
            
            System.out.println("And if you want to exit from the chat, type 'Sair'");

            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            BufferedReader systemReader = new BufferedReader(new InputStreamReader(System.in));
            String name;
            
            do {
            	System.out.println("--------------------------------");
            	System.out.println("Enter the Message to Brele:");
            	name = systemReader.readLine();

                writer.println(name);
 
                InputStream input = socket.getInputStream();
                BufferedReader inputReader = new BufferedReader(new InputStreamReader(input));
                
                String serverResponse = inputReader.readLine();
 
                System.out.println("Here's the Brele's response:");
                System.out.println("-> " + serverResponse);
            } while (!name.equals("Sair"));

            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
        	if (ex.getMessage().equals("Connection reset")) {
        		System.out.println("The server closed!");
        		System.out.println("Try to connect again");
        		return;
        	}
            System.out.println("I/O error: " + ex.getMessage());
            return;
        }
    }
}