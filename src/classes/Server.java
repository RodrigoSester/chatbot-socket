package classes;

import java.io.*;
import java.net.*;
import java.time.LocalDateTime;
import java.util.Random;

public class Server { 
	private static final int PORT = 4567;

    public static void main(String[] args) { 
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is listening on port " + PORT);
 
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("New client connected!");
                    
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);

                    String clientMessage;
                   
                    do {
                        clientMessage = reader.readLine();

                        System.out.println("Client sent Message: " + clientMessage);

                        if (!clientMessage.equals("Sair")) {                 
                           String response = getMessageResponse(clientMessage);

                           writer.println(response);
                        } else {
	                        writer.println("Closing server...");
                        }
                    } while (!clientMessage.equals("Sair"));

                    System.out.println("Connection closed on address " + socket.getLocalAddress() + ":" + socket.getLocalPort());
                    socket.close();

                } catch (IOException e) {
                	if (e.getMessage().equals("Connection reset")) {                		
                		System.out.println("The client closed the connection!");
                		continue;
                	}

                	System.out.println("An error occured: " + e.getMessage());
                	continue;
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
    
    private static String getMessageResponse(String message) {
    	Random random = new Random();

    	if (message.equals("Que horas sao?")) {
    		LocalDateTime now = LocalDateTime.now();
     	   return ("Hora de agora: " + now.getHour() + ":" + now.getMinute() + ":" + now.getSecond());
        } else if (message.equals("Que horas o sol se poe?")) {                        	   
     	   return ("O sol se poe perto das 18h");
        } else if (message.equals("Vai chover?")) {
     	    int num = random.nextInt(2);
   			if (num == 1) {
   				return ("Sim, chovera hoje!");
   			} else {                           				
   				return ("Nao, nao chovera hoje!");
   			}                        	   
        } else if (message.equals("Qual a temperatura maxima de hoje?")) {                        	   
     	   int temperature = random.nextInt(20, 40);
     	   return ("Temperatura maxima de hoje: " + temperature);
        } else if (message.equals("Qual a temperatura minima de hoje?")) {                        	   
      	   int temperature = random.nextInt(0, 20);
      	   return ("Temperatura minima de hoje: " + temperature);
        } else {
     	   return ("I don't recongnize this command!");
        }
    }
}