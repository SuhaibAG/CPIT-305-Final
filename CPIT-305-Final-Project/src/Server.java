import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args) throws Exception {
        ServerSocket server = new ServerSocket(8800);

        System.out.println("Server waiting Connection...");
        while (true) {
            Socket client = server.accept();

            myThread m = new myThread(client);
            m.start();
        }
        //server.close();

    }
}

class myThread extends Thread {

    Socket client;

    public myThread(Socket client) {
        this.client = client;
    }


    @Override
    public void run() {


        System.out.println("Client connect via: " + client.getInetAddress().getHostAddress());

        try {
            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);


            OutputStream fis = new FileOutputStream(".\\users.txt");
            ObjectOutputStream output = new ObjectOutputStream(fis);



            while (in.hasNextLine()) {

                //logging in
                String message = in.next();
                if(message.charAt(0) == '0' ) {
                    if(message.endsWith("suhaib")) {
                        out.println("logged in");
                    }
                    else{
                        out.println("try again");
                    }
                }


                out.println("Enter your name!");
                String name = in.next();
                out.println("Client says: " + name);


            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }



    public void responder(PrintWriter out, Scanner in, int response, User user){
        switch(response){
            case 1:
                out.println("Enter the name of the ussader you want to request from");
                String otherUser = in.next();
                out.println("enter the amount of money you want to request");
                int amount = in.nextInt();
                out.println("if you want to request money type 0, if the you want to give money type 1");
                boolean type = in.nextBoolean();
                User.makeRequest(otherUser, amount, type);

            case 2:

        }


    }

}

