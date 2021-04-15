package daw.urjc.ayuntamiento.api;

import com.fasterxml.jackson.annotation.JsonIgnore;
import daw.urjc.ayuntamiento.modules.Comment;
import org.springframework.web.multipart.MultipartFile;
import javax.persistence.*;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;


public class StoreDTO {
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

    private String length;

    private MultipartFile imageField1;

    private MultipartFile imageField2;

    private long id;

    public StoreDTO(String name, String description, String frontdescription, String services, String openDay, String closeDay, String openHour, String closeHour, String street, String latitude, String length, MultipartFile imageField1, MultipartFile imageField2, long id) {
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
        this.length = length;
        this.imageField1 = imageField1;
        this.imageField2 = imageField2;
        this.id = id;
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

    public String getFrontdescription() {
        return frontdescription;
    }

    public void setFrontdescription(String frontdescription) {
        this.frontdescription = frontdescription;
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

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public MultipartFile getImageField1() {
        return imageField1;
    }

    public void setImageField1(MultipartFile imageField1) {
        this.imageField1 = imageField1;
    }

    public MultipartFile getImageField2() {
        return imageField2;
    }

    public void setImageField2(MultipartFile imageField2) {
        this.imageField2 = imageField2;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
