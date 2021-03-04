package daw.urjc.ayuntamiento.modules;

import java.nio.file.Path;

public class Events {
    private String name;
    private Path image;
    private String activities;
    private String description;
    //Foto

    public Events(String name, Path image, String activities, String description) { //Constructor
        this.name = name;
        this.image = image;
        this.activities = activities;
        this.description = description
    }

    //Getters and setters

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getImage() {
        return image;
    }

    public void setImage(Path image) {
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
}

