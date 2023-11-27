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

            out.printf("welcome to debt organiser, Enter your username");

            while (in.hasNextLine()) {
                //getting the user to login
                boolean login = false;
                int id = 0;
              /*  while (!login) {
                    out.println("Enter your name!");
                    String name = in.next();
                     if (username in db ){
                          login = true
                          id = username's id from the db
                          }
                    System.out.println("Client says: " + name);
                }*/

                //this is temporary code for now

                String username = in.next();
                //


                User user = new User(id, username);
                out.println("logged in as " +  username);
                boolean loggedin = true;
                while(loggedin) {
                    int response = commands(out, in); // getting the response of the user

                    if (response == 8){
                        loggedin = false;
                    }
                    else{
                        responder(out, in, response, user);
                    }



                }


            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public int commands(PrintWriter out, Scanner in){
        //prints out commands and takes in the input of user
        out.println("Choose a feature that you want to use /n" +
                "type 1 to make a request /n" +
                "type 2 to make remove a reques /n" +
                "type 3 to view requests /n" +
                "type 4 to accept requests /n" +
                "type 5 to give money /n" +
                "type 6 to show owed list /n" +
                "type 7 to showing list /n" +
                "type 8 to log out");

        int answer = in.nextInt();

        if (answer > 8 || answer < 1){
            out.println("Please choose something from the list");
            return commands(out, in);
        }
        return answer;
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

