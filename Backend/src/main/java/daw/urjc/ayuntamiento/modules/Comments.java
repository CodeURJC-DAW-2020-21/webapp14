package daw.urjc.ayuntamiento.modules;

import java.nio.file.Path;

public class Comments {
    private String name;
    private String comment;
    private String date;
    private Path Picture;

    public Comments(String name, String coment, String fecha, Path Picture) { //Constructor
        this.name = name;
        this.comment = coment;
        this.date = date;
        this.Picture = Picture;
    }

    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Path getPicture() {
        return Picture;
    }

    public void setPicture(Path picture) {
        Picture = picture;
    }
}
