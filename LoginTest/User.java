import java.io.Serializable;
import java.util.UUID;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable{
    String userName;
    byte[] passHash;
    final String UID;
    int age;
    boolean loggedIn = false;

    public User(String userName, int age, String pass) {
        UID = UUID.randomUUID().toString();
        if(age == -1){
            System.err.println("invalid age");
            return;
        }
        this.age = age;
        this.userName = userName;
        try {
            this.passHash = MessageDigest.getInstance("MD5").digest(pass.getBytes());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
    public boolean isLoggedIn() {
        return loggedIn;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserName() {
        return userName;
    }
    public int getAge() {
        return age;
    }
    public byte[] getPassHash() {
        return passHash;
    }
}