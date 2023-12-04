import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class User {
    private int id;
    private String username;

    public User(int id, String username) {
        //if the user is in the database fetch info through the database
        this.id = id;
        this.username = username;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String makeRequest(String user2, int id2,int amount){
        //sql code to make a request if the boolean give
        String query = "INSERT INTO requests VALUES(" + this.id + "," + id2 + "," + this.username + "," + user2 + "," + amount + false + ")";
        System.out.println("checkpoint");

        return query;
    }

    public String removeRequest(String username){
        //sql code to remove all requests by username
        String query = "DELETE FROM requests where name1 = " + this.username + " AND name2 = " + username;
        return query;
    }

    public String viewRequests(){
        //sql code to get all the requests
        String sql = "SELECT * FROM Usersinfo WHERE id1 = " + this.id;
        //code to print out all the requests
        return sql;
    }

    public void acceptRequests(String username){
        //sql code to accept the request
        String sql = "UPDATE requests SET condition = True WHERE name1 =" + this.username + " AND name2 = " + username;
    }

    public void giveMoney(String username){
        //code using sql to give money
        String query = "Delete";
    }

    public void owedList(){
        //code using sql to retrieve all users who owe the user money
    }

    public void owingList(){
        //code using sql retrieve all users who the user owes money to
    }

}
