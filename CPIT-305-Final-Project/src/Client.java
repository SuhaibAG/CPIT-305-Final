import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;



public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {

        try {
            Socket client = new Socket("127.0.0.2", 8800);

            Scanner in = new Scanner(client.getInputStream());
            PrintWriter out = new PrintWriter(client.getOutputStream(), true);
            Scanner userInput = new Scanner(System.in);
            ObjectInputStream objectIn = new ObjectInputStream(client.getInputStream());


            boolean login = false;
            User user = null;
            while (true) {
                System.out.println("=============================================");
                String message = "";




                //login condition
                if (!login) {
                    System.out.println("enter your username");
                    String username = userInput.next();
                    message = "0" + username;
                    out.println(message);
                    String response = in.nextLine();

                    System.out.println(response);
                    if (response.startsWith("logged in")) {

                        int id = Integer.parseInt(response.substring(response.length() - 1));
                        user = new User(id, username);
                        System.out.println(id);
                        login = true;


                    }
                }





                //interpreting all the commands
                else {
                    commands();
                    int command = userInput.nextInt();

                    //log out command
                    if (command == 8) {
                        out.println("8");
                        String response = in.nextLine();
                        login = false;
                    }





                    //making a request
                    else if (command == 1) {
                        System.out.println("enter the name of the other user");
                        String otherUser = userInput.next();
                        System.out.println("enter the id of the other user");
                        int id = userInput.nextInt();
                        System.out.println("enter the amount you want to request");
                        int amount = userInput.nextInt();

                        String query  = user.makeRequest(otherUser, id, amount);
                        out.println("1" + query);
                        System.out.println("executed");;

                    }





                    //removing a request
                    else if (command == 2) {
                        System.out.println("enter the request number");
                        int reqNumber = userInput.nextInt();
                        String query = user.removeRequest(reqNumber);
                        out.println("2" + query);
                        System.out.println("deleted");
                    }





                    //view all requests
                    else if (command == 3) {
                        String sql = user.viewRequests();
                        out.println("3" + sql);
                        ArrayList<String> results = (ArrayList<String>) objectIn.readObject();

                        for (int i = 0; i < results.size(); i++) {
                            System.out.println(results.get(i));
                        }
                    }




                    //accept a request
                    else if (command == 4) {
                        System.out.println("enter the name of the user you want to accept requests from");
                        String otherUser = userInput.next();
                        String query = user.acceptRequests(otherUser);
                        out.println(4 + query);
                        System.out.println("Accepted the request");
                    }





                    //give money
                    else if (command == 5) {
                        System.out.println("enter the request number you want to settle");
                        int requestNumber = userInput.nextInt();
                        String query = user.giveMoney(requestNumber);
                        out.println(4 + query);
                        System.out.println("Debt payed off");
                    }





                    //show owed list
                    else if (command == 6) {
                        user.owedList();
                        String sql = user.owedList();
                        out.println("6" + sql);
                        ArrayList<String> results = (ArrayList<String>) objectIn.readObject();

                        for (int i = 0; i < results.size(); i++) {
                            System.out.println(results.get(i));
                        }

                    }




                    //show owing list
                    else if (command == 7) {
                        user.owedList();
                        String sql = user.owingList();
                        out.println("7" + sql);
                        ArrayList<String> results = (ArrayList<String>) objectIn.readObject();

                        for (int i = 0; i < results.size(); i++) {
                            System.out.println(results.get(i));
                        }
                    }



                    else{
                        System.out.println("wrong command");
                    }
                }


                if(message == "bye"){
                    client.close();
                }






            }

        } catch (UnknownHostException e) {
            System.err.println("Host not found");
        } catch (java.net.ConnectException e) {
            System.err.println("There are no connection at this port");
        } catch (IOException e) {
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public static void commands (){
        //prints out commands and takes in the input of user
        System.out.println("Choose a feature that you want to use");
        System.out.println("type 1 to make a request");
        System.out.println("type 2 to make remove a request");
        System.out.println("type 3 to view requests");
        System.out.println("type 4 to accept requests");
        System.out.println("type 5 to Settle debt");
        System.out.println("type 6 to show owed list");
        System.out.println("type 7 to show owing list");
        System.out.println("type 8 to log out");
    }

}