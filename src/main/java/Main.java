import Back.DataBase.DBService;
import Back.DataBase.DBServiceInt;
import Back.BL.Profile.Profile;
public class Main {
    public static void main(String[] args) {
        DBServiceInt db = new DBService();
        db.printConnectInfo();
        try {
            long userId = db.addProfile("Aureys","1234","fox@gmail.com");
            System.out.println("Added user id: " + userId);
            long userId2 = db.addProfile("Aureys2","1234","fox2@gmail.com");
            System.out.println("Added user id: " + userId2);
            Profile profile = db.getProfile(userId);
            System.out.println("User profile: " + profile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
