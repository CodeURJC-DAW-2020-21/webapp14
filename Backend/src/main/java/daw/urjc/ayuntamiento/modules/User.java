package daw.urjc.ayuntamiento.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Blob;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Entity
public class User {

    private String name;
    private String mail;
    private String description;
    private String DNI;
    @JsonIgnore
    private String password;

    @ElementCollection(fetch = FetchType.EAGER)
    private Map<String,Integer> map = new HashMap<>();

    @ElementCollection
    private List<Long> eventSuscribe;

    @ElementCollection
    private List<String> events;

    @ElementCollection
    private List<String> commentPlaces;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Comment> comment;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    protected User(){}


    public User(String name, String mail, String description, String DNI, String password) {
        map.put("Deporte",0);
        map.put("Cultura",0);
        map.put("Musica",0);
        map.put("Videojuegos",0);
        this.name = name;
        this.mail = mail;
        this.description = description;
        this.DNI = DNI;
        this.password = password;
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
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

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    public List<Long> getEventSuscribe() {
        return eventSuscribe;
    }

    public void setEventSuscribe(List<Long> eventSuscribe) {
        this.eventSuscribe = eventSuscribe;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<String> getCommentPlaces() {
        return commentPlaces;
    }

    public void setCommentPlaces(List<String> commentPlaces) {
        this.commentPlaces = commentPlaces;
    }
}
