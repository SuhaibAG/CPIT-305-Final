import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

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
        Random rand = new Random();
        int requestNum = (int) (Math.random() * 1000);
        //sql code to make a request if the boolean give
        String query = "insert into Owe " +
                "(RequestNumber,ID1,ID2,Name1,Name2,Amount,Accepted)" +
                " values  ("+requestNum+","+this.id+","+id2+",'"+this.username+"','"+user2+"',"+amount+",'"+"false');";

        return query;
    }

    public String removeRequest(int requestnNumber){
        //sql code to remove all requests by username
        String query = "delete from Owe where RequestNumber=" + requestnNumber + ";";
        return query;
    }

    public String viewRequests(){
        //sql code to get all the requests
        String sql = "select * from Owe where Name2 ='" + this.username + "' AND Accepted = 'false';";
        //code to print out all the requests
        return sql;
    }

    public String acceptRequests(String username){
        //sql code to accept the request
        String sql = "update Owe set Accepted = 'true'  where Name2 = '"+this.username+"' And Name1='"+username+"';";
        return sql;
    }

    public String giveMoney(int requestnNumber){
        //code using sql to give money
        String query = "delete from Owe where RequestNumber=" + requestnNumber + ";";
        return query;
    }

    public String owedList(){
        //code using sql to retrieve all users who owe the user money
        String sql = "Select * from Owe where Name2= '" + this.username +"' AND Accepted= 'true';";
        return sql;
    }

    public String owingList(){
        //code using sql retrieve all users who the user owes money to
        String sql = "Select * from Owe where Name1= '" + this.username +"' AND Accepted = 'true';";
        return sql;
    }

}
