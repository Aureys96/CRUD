package Back.DataBase;

import Back.BL.Profile.Profile;

public interface DBServiceInt {
    Profile getProfile(long id);
    long addProfile(String name, String password, String email);
    void printConnectInfo();
    boolean isValid(String name, String password);
    Profile getProfileByName(String name);
    void delete(String name);
}
