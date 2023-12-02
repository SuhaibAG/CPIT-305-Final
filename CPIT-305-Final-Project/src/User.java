import java.sql.PreparedStatement;

public class User {
    private int id;
    private String username;

    public User(int id, String username) {
        //if the user is in the database fetch info through the database
        this.id = id;
        this.username = username;

    }

    public  void makeRequest(String user, int amount){
        //sql code to make a request if the boolean give
        String query = "add into x where name1 = " + username + ", name2 = " + user + " , id1 = " + id;

    }

    public void removeRequest(String username){
        //sql code to remove all requests by username
    }

    public void viewRequests(){
        //sql code to get all the requests

        //code to print out all the requests

    }

    public void acceptRequests(String username){
        //sql code to accept the request
    }

    public void giveMoney(String username){
        //code using sql to give money
    }

    public void owedList(){
        //code using sql to retrieve all users who owe the user money
    }

    public void owingList(){
        //code using sql retrieve all users who the user owes money to
    }

}
