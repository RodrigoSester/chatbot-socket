package classes;

import java.net.*;
import java.io.*;

public class Client {
 
    public static void main(String[] args) {
 
        String hostname = "localhost";
        int port = 4567;
 
        try (Socket socket = new Socket(hostname, port)) {
            
            System.out.println("Server is connected");
            OutputStream output = socket.getOutputStream();
            PrintWriter writer = new PrintWriter(output, true);
            
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String name;
            
            do {
            	System.out.println("Enter the Message to the Server:");
            	name = br.readLine();
            
                
                writer.println(name);
 
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                
                String time = reader.readLine();
 
                System.out.println(time);
            } while (!name.equals("Sair"));

            socket.close();

        } catch (UnknownHostException ex) {
            System.out.println("Server not found: " + ex.getMessage());
        } catch (IOException ex) {
            System.out.println("I/O error: " + ex.getMessage());
        }
    }
}