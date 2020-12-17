package com.titowareglobal.humerusrehab.Model;

public class Exercise {
    private int image_id;
    private String name;

    // my Trial code
    private String myDescription;



    public Exercise(int image_id, String name, String myDescription) {
        this.image_id = image_id;
        this.name = name;

        // my trial code
        this.myDescription = myDescription;
    }

    public int getImage_id() {
        return image_id;
    }

    public void setImage_id(int image_id) {
        this.image_id = image_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyDescription() {
        return myDescription;
    }

    public void setMyDescription(String myDescription) {
        this.myDescription = myDescription;
    }
}
