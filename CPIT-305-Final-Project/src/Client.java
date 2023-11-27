import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;


public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        try {
            Socket client = new Socket("127.0.0.1", 8800);

            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);

            Scanner userInput = new Scanner(System.in);


            while (true) {
                String message = "";

                message = in.nextLine();
                System.out.printf("Server says: " + message + "\n");

                String send = userInput.next();
                out.println(send);



                if (message.trim().equals("BYE")) {
                    break;
                }
            }
            client.close();
        } catch (UnknownHostException e) {
            System.err.println("Host not found");
        } catch (java.net.ConnectException e) {
            System.err.println("There are no connection at this port");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void printcommands(Scanner in){
        String message = in.nextLine();
        System.out.printf(message);
    }
}
