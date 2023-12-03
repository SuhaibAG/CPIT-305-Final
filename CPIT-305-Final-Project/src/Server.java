import java.io.*;
import java.net.Socket;
import java.sql.*;
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
        String url = "jdbc:sqlserver://project305database.database.windows.net:1433;" +
                "database=database-cpit305;" +
                "user=Saud@project305database;" +
                "password=Duzu26\\5;" +
                "encrypt=true;" +
                "trustServerCertificate=false;" +
                "hostNameInCertificate=*.database.windows.net;" +
                "loginTimeout=30;";

        System.out.println("Client connect via: " + client.getInetAddress().getHostAddress());
        try(Connection con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();) {
            try {
                Scanner in = new Scanner(client.getInputStream());
                PrintWriter out = new PrintWriter(client.getOutputStream(), true);
                ObjectOutputStream objectOut = new ObjectOutputStream(client.getOutputStream());


                PrintWriter logs = new PrintWriter(".\\logs.txt");


                while (in.hasNextLine()) {
                    //logging in
                    String message = in.next();

                    if (message.charAt(0) == '0') {
                        String username = message.substring(1);
                        int id = login(statement, username);
                        if (id != -1) {
                            out.println("logged in as " + username + id);
                            logs.println("logged in as " + username);
                        } else {
                            out.println("try again");
                        }
                    }

                    //logging out
                    else if (message.charAt(0) == '8') {
                        out.println("logged out");
                        logs.println("logged out from " + message);
                    }

                    //making a request
                    else if (message.charAt(0) == '1') {

                    }

                    //removing a request
                    else if (message.charAt(0) == '2') {
                        out.println();
                    }

                    //view all requests
                    else if (message.charAt(0) == '3') {
                        out.println();
                    }

                    //accept requests
                    else if (message.charAt(0) == '4') {
                        out.println();
                    } else if (message.charAt(0) == '5') {
                        out.println();
                    } else if (message.charAt(0) == '6') {
                        out.println();
                    } else if (message.charAt(0) == '7') {
                        out.println();
                    }
                    logs.flush();


                }
            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int login(Statement statement, String username) throws SQLException {
        String sql = "Select * from Usersinfo";
        ResultSet rs = statement.executeQuery(sql);
        while(rs.next()){
            if(rs.getString(2) == username){
                return rs.getInt(1);
            }
        }


        return -1;
    }

}



