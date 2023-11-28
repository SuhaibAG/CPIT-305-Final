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

            boolean login = false;
            User user;
            while (true) {
                String message = "";

                //login condition
                if (!login) {
                    System.out.println("enter your username");
                    message = "0" + userInput.next();
                    out.println(message);
                    String response = in.nextLine();

                    System.out.println(response);
                    if(response.equals("logged in")){
                        user = new User(0, message.substring(1));
                        login = true;
                    }
                }

                else{
                    int command = printcommands(userInput);

                    //log out condition
                    if (command == 0){
                        login = false;
                    }

                    //command interpreter
                    else if (command > 1 && command < 9){

                    }


                }





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

    public static int printcommands(Scanner userInput){
        boolean done = false;
        System.out.println("Enter 1 if you want to do thing");
        System.out.println("Enter 2 if you want to do thing");
        System.out.println("Enter 3 if you want to do thing");
        System.out.println("Enter 4 if you want to do thing");
        System.out.println("Enter 5 if you want to do thing");
        System.out.println("Enter 6 if you want to do thing");
        System.out.println("Enter 7 if you want to do thing");
        System.out.println("Enter 8 if you want to log out");
        int command = -1;
        while(!done){
            command = userInput.nextInt();
            if (command > 0 && command < 9){
                done = true;
            }
        }
        return command;

    }
}
