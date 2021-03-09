package daw.urjc.ayuntamiento.modules;

import java.nio.file.Path;

public class Store {

    private String name;
    private String description;
    private String services;
    private String openDay;
    private String closeDay;
    private String openHour;
    private String closeHour;
    private String street;
    private Path primaryImage;
    private Path secondaryImage;

    public Store(String name, String description, String services, String openDay, String closeDay, String openHour, String closeHour, String street, Path primaryImage, Path secondaryImage) {
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

    public Path getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(Path primaryImage) {
        this.primaryImage = primaryImage;
    }

    public Path getSecondaryImage() {
        return secondaryImage;
    }

    public void setSecondaryImage(Path secondaryImage) {
        this.secondaryImage = secondaryImage;
    }
}
