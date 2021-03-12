package daw.urjc.ayuntamiento.modules;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {

    private String name;
    private String mail;
    private String description;
    private String DNI;
    private String password;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Comment> comment;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    protected User(){}




    public User(String name, String mail, String description, String DNI, String password, String... roles) {
        this.name = name;
        this.mail = mail;
        this.description = description;
        this.DNI = DNI;
        this.password = password;
        this.roles = List.of(roles);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }





    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
