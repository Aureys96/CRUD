package Back.DataBase;

import Back.BL.Profile.Profile;

public interface DBServiceInt {
    Profile getProfile(long id);

    long addProfile(String name, String password, String email);

    void printConnectInfo();
}
