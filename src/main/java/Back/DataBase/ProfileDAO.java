package Back.DataBase;

import Back.BL.Profile.Profile;
import org.hibernate.HibernateException;
import org.hibernate.Session;

import javax.persistence.*;
import javax.persistence.criteria.*;

public class ProfileDAO {
    private Session session;
    public ProfileDAO (Session session) { this.session=session; }
    public Profile get(long id) throws HibernateException{
        return session.get(Profile.class,id);
    }
    public long getId (String name){
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<Profile> criteriaQuery = builder.createQuery(Profile.class);
        Root<Profile> root = criteriaQuery.from(Profile.class);
        criteriaQuery.select(root);
        criteriaQuery.where(builder.equal(root.get("name"),name));
        Query q = session.createQuery(criteriaQuery);
        Profile result = (Profile)q.getSingleResult();
        return result.getId();
    }
    public String getEmail (long id) throws HibernateException{
        Profile profile = this.get(id);
        return profile.getEmail();
    }
    public String getImage (long id) throws HibernateException{
        Profile profile = this.get(id);
        return profile.getImage();
    }
    public String getPass(long id) throws HibernateException{
        String password = this.get(id).getPass();
        return password;
    }
    public long CreateProfile(String name, String password, String email) throws HibernateException{
        Profile profile = new Profile(name,password,email);
        return (long)session.save(profile);
    }

}
