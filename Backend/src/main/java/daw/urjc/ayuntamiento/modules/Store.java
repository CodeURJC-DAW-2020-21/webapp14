package daw.urjc.ayuntamiento.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;
import java.util.List;

@Entity
public class Store {

    private String name;
    private String description;
    private String services;
    private String openDay;
    private String closeDay;
    private String openHour;
    private String closeHour;
    private String street;
    private String primaryImage;
    private String secondaryImage;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany
    private List<Comments> comment;

    protected Store(){}

    public Store(String name, String description, String services, String openDay, String closeDay, String openHour, String closeHour, String street, String primaryImage, String secondaryImage) {
        this.name = name;
        this.description = description;
        this.services = services;
        this.openDay = openDay;
        this.closeDay = closeDay;
        this.openHour = openHour;
        this.closeHour = closeHour;
        this.street = street;
        this.primaryImage = primaryImage;
        this.secondaryImage = secondaryImage;
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

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public String getSecondaryImage() {
        return secondaryImage;
    }

    public void setSecondaryImage(String secondaryImage) {
        this.secondaryImage = secondaryImage;
    }
}
