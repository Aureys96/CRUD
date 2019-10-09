import Back.DataBase.DBService;
import Back.DataBase.DBServiceInt;

public class Main {
    public static void main(String[] args) {
        DBServiceInt db = new DBService();
        db.printConnectInfo();
    }
}
