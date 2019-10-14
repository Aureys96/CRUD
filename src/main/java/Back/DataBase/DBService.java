package Back.DataBase;

import Back.BL.Profile.Profile;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;



public class DBService implements DBServiceInt {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "create";
    private final SessionFactory sessionFactory;

    public DBService(){
        Configuration configuration = getConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Profile.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://127.0.0.1:3306/webserver?serverTimezone=UTC&useLegacyDatetimeCode=false");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "3872264");
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;

    }
    @Override
    public Profile getProfile(long id){
        Session session = sessionFactory.openSession();
        ProfileDAO dao = new ProfileDAO(session);
        Profile profile = dao.get(id);
        session.close();
        return profile;
    }
    @Override
    public long addProfile(String name, String password, String email){
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        ProfileDAO dao = new ProfileDAO(session);
        long id = dao.CreateProfile(name,password,email);
        transaction.commit();
        session.close();
        return id;
    }
    @Override
    public void printConnectInfo() {
        Session session = sessionFactory.openSession();
        session.doWork(connection -> {
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        });
        session.close();
    }

    @Override
    public boolean isValid(String name, String password) {
        Session session = sessionFactory.openSession();
        ProfileDAO dao = new ProfileDAO(session);
        long id = dao.getId(name);
        if(name.equals(dao.get(id).getName())&&password.equals(dao.get(id).getPass()))
            return true;
        else
            return false;
    }

    private static SessionFactory createSessionFactory(Configuration configuration){
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }
    public Profile getProfileByName(String name){
        Session session = sessionFactory.openSession();
        ProfileDAO dao = new ProfileDAO(session);
        long id = dao.getId(name);
        Profile response = getProfile(id);
        session.close();
        return response;
    }
}
