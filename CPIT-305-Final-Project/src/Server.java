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

                //logging out
                else if(message.charAt(0) == '8'){
                    out.println("logged out");
                }

                //making a request
                else if(message.charAt(0) == '1'){
                    out.println();
                }

                //removing a request
                else if(message.charAt(0) == '2'){
                    out.println();
                }

                //view all requests
                else if(message.charAt(0) == '3'){
                    out.println();
                }

                //accept requests
                else if(message.charAt(0) == '4'){
                    out.println();
                }

                else if(message.charAt(0) == '5'){
                    out.println();
                }

                else if(message.charAt(0) == '6'){
                    out.println();
                }

                else if(message.charAt(0) == '7'){
                    out.println();
                }



            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

}



