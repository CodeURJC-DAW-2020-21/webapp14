package daw.urjc.ayuntamiento.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Store {

    private String name;
    private String description;
    private String frontdescription;
    private String services;
    private String openDay;
    private String closeDay;
    private String openHour;
    private String closeHour;
    private String street;
    private String latitude;
    private String lenght;


    @Lob
    @JsonIgnore
    private Blob imageField1;

    @Lob
    @JsonIgnore
    private Blob imageField2;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Comment> comment = new ArrayList<>();

    protected Store(){}

    public Store(String name, String description,String frontdescription, String services, String openDay, String closeDay, String openHour, String closeHour, String street,String latitude, String lenght) {
        super();
        this.name = name;
        this.description = description;
        this.frontdescription = frontdescription;
        this.services = services;
        this.openDay = openDay;
        this.closeDay = closeDay;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.street = street;
        this.latitude = latitude;
        this.lenght = lenght;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getServices() {
        return services;
    }

    public void setServices(String services) {
        this.services = services;
    }

    public String getOpenDay() {
        return openDay;
    }

    public void setOpenDay(String openDay) {
        this.openDay = openDay;
    }

    public String getCloseDay() {
        return closeDay;
    }

    public void setCloseDay(String closeDay) {
        this.closeDay = closeDay;
    }

    public String getOpenHour() {
        return openHour;
    }

    public void setOpenHour(String openHour) {
        this.openHour = openHour;
    }

    public String getCloseHour() {
        return closeHour;
    }

    public void setCloseHour(String closeHour) {
        this.closeHour = closeHour;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Blob getImageField1() {
        return imageField1;
    }

    public void setImageField1(Blob imageField1) {
        this.imageField1 = imageField1;
    }

    public Blob getImageField2() {
        return imageField2;
    }

    public void setImageField2(Blob imageField2) {
        this.imageField2 = imageField2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public List<Comment> getComment() {
        return comment;
    }

    public void setComment(List<Comment> comment) {
        this.comment = comment;
    }

    public String getFrontdescription() {
        return frontdescription;
    }

    public void setFrontdescription(String frontdescription) {
        this.frontdescription = frontdescription;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLength() {
        return lenght;
    }

    public void setLength(String lenght) {
        this.lenght = lenght;
    }
}
