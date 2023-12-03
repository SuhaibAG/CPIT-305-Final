import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                System.out.println("x");
                String message = "";

                //login condition
                if (!login) {
                    System.out.println("enter your username");
                    message = "0" + userInput.next();
                    out.println(message);
                    String response = in.nextLine();

                    System.out.println(response);
                    if (response.equals("logged in")) {
                        int idLocation = message.length() -1 ;
                        user = new User(Integer.parseInt(response.substring(idLocation)), message.substring(1));
                        login = true;
                        System.out.println(user.getId());
                        System.out.println(user.getUsername());

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
                        System.out.println(response);
                        login = false;
                    }

                    //making a request
                    else if (command == 1) {
                        System.out.println("enter the name of the other user");
                        String otherUser = userInput.next();
                        System.out.println("enter the amount you want to request");
                        int amount = userInput.nextInt();
                        user.makeRequest(otherUser, amount);
                    }

                    //removing a request
                    else if (command == 2) {
                        System.out.println("enter the name of the other user");
                        String otherUser = userInput.next();
                        user.removeRequest(otherUser);
                    }

                    //view all requests
                    else if (command == 3) {
                        String sql = user.viewRequests();
                        out.println(sql);
                        Object obj = objectIn.readObject();
                        ResultSet rs = (ResultSet) obj;

                        while(rs.next()){
                            System.out.println("Id: " + rs.getInt(1) + " Name: " + rs.getString(2));
                        }


                    }


                    else if (command == 4) {
                        System.out.println("enter the name of the user you want to accept requests from");
                        String otherUser = userInput.next();
                        user.acceptRequests(otherUser);
                    }

                    else if (command == 5) {
                        System.out.println("enter the name of the user you want to give to");
                        String otherUser = userInput.next();
                        user.giveMoney(otherUser);
                    }

                    else if (command == 6) {
                        user.owedList();
                    }

                    else if (command == 7) {
                        user.owingList();
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }



    public static void commands (){
        //prints out commands and takes in the input of user
        System.out.println("Choose a feature that you want to use");
        System.out.println("type 1 to make a request");
        System.out.println("type 2 to make remove a reques");
        System.out.println("type 3 to view requests");
        System.out.println("type 4 to accept requests");
        System.out.println("type 5 to give money");
        System.out.println("type 6 to show owed list");
        System.out.println("type 7 to show owing list");
        System.out.println("type 8 to log out");
    }
}