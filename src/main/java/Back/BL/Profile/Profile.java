package Back.BL.Profile;
import javax.persistence.*;

@Entity
@Table(name="profiles")
public class Profile {
    @SuppressWarnings("UnusedDeclaration")
    public Profile(){

    }
    public Profile(String name, String password, String email){
        this.name = name;
        this.password = password;
        this.email = email;
    }
    @Column (name="name", unique = true, updatable = true)
   private String name;
    @Column (name="email",unique = true)
   private String email;
    @Column (name="password")
   private String password;
    @Column (name="image",updatable = true)
   private String image;
    @Id
    @Column (name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEmail() {
        return email;
    }
    public String getImage(){
        return image;
    }

    public void setName(String s) {
        this.name = s;
    }

    public void setEmail(String s) {
        this.email = s;
    }

    public void setId(long id) {this.id = id;}
    @Override
    public String toString() {
        return "Profile {name: " + name + " id= " + id +" e-mail: "+ email +'\''+'}';
    }
}
