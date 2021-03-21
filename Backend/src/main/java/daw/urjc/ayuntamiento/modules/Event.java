package daw.urjc.ayuntamiento.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Event {
    private String name;
    private String activities;
    private String description;
   // @Temporal(TemporalType.TIMESTAMP)
    private String date;
    private String place;
    private String reward;
    private String people;
    private String price;
    private String tag1;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany
    private List<Comment> comment = new ArrayList<>();



    protected Event(){}

    public Event(String name, String activities, String description, String date, String place, String reward, String people, String price,String tag1) {
        super();
        this.name = name;
        this.activities = activities;
        this.description = description;
        this.date = date;
        this.place = place;
        this.reward = reward;
        this.people = people;
        this.price = price;
        this.tag1 = tag1;
    }


    //Getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }


    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public String getPeople() {
        return people;
    }

    public void setPeople(String people) {
        this.people = people;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Blob getImageFile() {
        return imageFile;
    }

    public void setImageFile(Blob imageFile) {
        this.imageFile = imageFile;
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

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }
}

