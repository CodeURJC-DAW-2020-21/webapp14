package daw.urjc.ayuntamiento.api;

import org.springframework.web.multipart.MultipartFile;


public class EventDTO {

    private String name;
    private String activities;
    private String description;
    private String date;
    private String place;
    private String reward;
    private String people;
    private String price;
    private String tag1;

    private MultipartFile imageFile;

    private long id;

    public EventDTO(String name, String activities, String description, String date, String place, String reward, String people, String price, String tag1, MultipartFile imageFile, long id) {
        this.name = name;
        this.activities = activities;
        this.description = description;
        this.date = date;
        this.place = place;
        this.reward = reward;
        this.people = people;
        this.price = price;
        this.tag1 = tag1;
        this.imageFile = imageFile;
        this.id = id;
    }


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

    public String getTag1() {
        return tag1;
    }

    public void setTag1(String tag1) {
        this.tag1 = tag1;
    }

    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
