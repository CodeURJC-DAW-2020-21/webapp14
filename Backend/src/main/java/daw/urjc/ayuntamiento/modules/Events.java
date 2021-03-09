package daw.urjc.ayuntamiento.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;
import java.sql.Time;
import java.util.Date;
import java.util.List;

@Entity
public class Events {
    private String name;
    private String image;
    private String activities;
    private String description;
    private Date date;
    private String place;
    private Time hora;
    private String reward;
    private String people;
    private String price;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Comments> comment;

    protected Events(){}

    public Events(String name, String image, String activities, String description, Date date, String place, Time hora, String reward, String people, String price) {
        this.name = name;
        this.image = image;
        this.activities = activities;
        this.description = description;
        this.date = date;
        this.place = place;
        this.hora = hora;
        this.reward = reward;
        this.people = people;
        this.price = price;
    }


    //Getters and setters


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
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
}

