package daw.urjc.ayuntamiento.api;

import daw.urjc.ayuntamiento.modules.Comment;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class UserDTO {


    private String name;

    private String mail;

    private String description;

    private String DNI;

    private String password;

    private MultipartFile imageField;

    private long id;

    private List<Long> eventSuscribe;

    private List<String> commentPlaces;

    private List<String> events;

    private List<Comment> comment;

    private List<String> roles;


    public UserDTO(String name, String mail, String description, String DNI, String password, MultipartFile imageField, long id) {
        this.name = name;
        this.mail = mail;
        this.description = description;
        this.DNI = DNI;
        this.password = password;
        this.imageField = imageField;
        this.id = id;
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

    public MultipartFile getImageField() {
        return imageField;
    }

    public void setImageField(MultipartFile imageField) {
        this.imageField = imageField;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Long> getEventSuscribe() {
        return eventSuscribe;
    }

    public void setEventSuscribe(List<Long> eventSuscribe) {
        this.eventSuscribe = eventSuscribe;
    }

    public List<String> getCommentPlaces() {
        return commentPlaces;
    }

    public void setCommentPlaces(List<String> commentPlaces) {
        this.commentPlaces = commentPlaces;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
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
