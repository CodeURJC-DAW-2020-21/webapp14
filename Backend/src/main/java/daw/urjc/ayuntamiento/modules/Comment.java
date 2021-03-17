package daw.urjc.ayuntamiento.modules;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.nio.file.Path;
import java.sql.Blob;

@Entity
public class Comment {
    private String name;
    private String comment;
    private String date;

    @Lob
    @JsonIgnore
    private Blob imageFile;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Event event;
    @OneToOne
    private Store local;

    protected Comment(){}


    public Comment(String name, String coment, String date) { //Constructor
        this.name = name;
        this.comment = coment;
        this.date = date;
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

}
