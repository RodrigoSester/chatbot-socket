package classes;

import java.io.*;
import java.net.*;

public class Server { 
    public static void main(String[] args) {
        int port = 4567;
 
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
 
            while (true) {
                try (Socket socket = serverSocket.accept()) {
                    System.out.println("New client connected");
                    
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output, true);
                    
                    
                    String text;
                   
                    do {
                        text = reader.readLine();

                        System.out.println("Client sent Message: "+text);

                        if(!text.equals("Sair")){
                           writer.println("Reply from Server: Ok");
                        }else{
                        String reply = "Encerrando execução";
                        writer.println("Reply from Server: " +reply);
                        }
                        
                    } while (!text.equals("Sair"));
                    System.out.println("Connection Closed");
                    socket.close();
                }
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
        }
    }
}