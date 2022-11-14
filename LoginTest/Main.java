import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.util.Scanner;
import java.util.HashMap;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main{
    HashMap<String, User> users = new HashMap<String, User>();
    Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        new Main();
    }
    public Main() {
        ObjectInputStream inputStream;
        try {
            inputStream = new ObjectInputStream(new FileInputStream("LoginSystem\\Users.ser"));
            users = (HashMap<String, User>) inputStream.readObject();
        } catch (FileNotFoundException e) {
            System.err.println("FileNotFoundException caught");
            e.printStackTrace();
            System.exit(1);
        } catch (IOException e) {
            System.err.println("IOException caught, program will continue with empty user list");
        } catch (ClassNotFoundException e) {
            System.err.println("ClassNotFoundException caught");
            e.printStackTrace();
            System.exit(1);
        }

        System.out.println("Do you wish to login or sign up");
        String toSwitch = sc.nextLine().toLowerCase().trim();
        switch(toSwitch){
            case "login":
                login();
                break;
            case "sign up":
                signUp();
                break;   
        }
    }
    private void login(){
        String userIn, passIn;
        byte[] passInHashed;
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            System.err.println("No such algorithm found");
            e.printStackTrace();
        }
        System.out.println("Input your username");
        userIn = sc.next();
        if(!users.containsKey(userIn)){
            System.err.println("No such user found");
            return;
        }
        System.out.println("Input your password");
        passIn = sc.next();
        passInHashed = md.digest(passIn.getBytes());
        if(MessageDigest.isEqual(passInHashed, users.get(userIn).getPassHash()))
            System.out.println("You have logged in. Thank you for using this learning project.");
        else System.err.println("Incorrect password");

    }
    private void signUp(){
        ObjectOutputStream outputStream = null;
        try {
            outputStream = new ObjectOutputStream(new FileOutputStream("LoginSystem\\Users.ser"));
        } catch (IOException e) {
            System.err.println("IOException caught");
            e.printStackTrace();
            System.exit(1);
        }
        String user, ageStr, pass, passConf;
        System.out.println("Input a username");
        user = sc.next();
        System.out.println("Input an age");
        ageStr = sc.next();
        System.out.println("Input a password");
        pass = sc.next();
        System.out.println("confirm password");
        passConf = sc.next();
        if(!pass.equals(passConf)) {
            System.err.println("Passwords not equal");
            return;
        }
        int age = toInt(ageStr);
        users.put(user, new User(user, age, pass));
        try {
            outputStream.writeObject(users);
        } catch (IOException e) {
            System.err.println("Caught IOException while writing users to file");
            e.printStackTrace();
            System.exit(1);
        }

    }
    int toInt(String str){
        try{
            return(Integer.parseInt(str));
        }catch(NumberFormatException e){
            System.err.println("Input an integer");
            return -1;
        }
    }
}