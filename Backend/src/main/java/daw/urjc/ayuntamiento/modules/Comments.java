package daw.urjc.ayuntamiento.modules;

public class Comments {
    private String name;
    private String comment;
    private String date;
    //Foto

    public Comments(String name, String coment, String fecha) { //Constructor
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
