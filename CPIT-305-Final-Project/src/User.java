public class User {
    private int id;
    private String username;

    public User(int id, String username) {
        //if the user is in the database fetch info through the database
        this.id = id;
        this.username = username;

    }

    public static void makeRequest(String user, int amount, boolean give){
        //sql code to make a request if the boolean give is false then the user is requesting money

        //sql code to make a request if the boolean give is true then the user is giving money
    }

    public void removeRequest(String username){
        //sql code to remove all requests by username
    }

    public void viewRequests(){
        //sql code to view all requests and print them in method
    }

    public void acceptRequests(String username){
        //sql code to accept the request
    }

    public void giveMoney(int amount){
        //code using sql to give money
    }

    public void owedList(){
        //code using sql to retrieve all users who owe the user money
    }

    public void owingList(){
        //code using sql retrieve all users who the user owes money to
    }

}
