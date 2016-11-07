package com.codefobi.startupproject.models;

/**
 * Created by tosantechnolocal on 11/3/2016.
 */
public class ReadContent {

    private int ID;
    private String  title, body, specifier;
    private String imagePath , whodoyou;

    /**
     * Specifier values
     *   0 : no image
     *   1 : body
     *   2 : image only
     *   3 : full
     */

    public ReadContent() {
        //empty constructor
    }

    public String getWhodoyou() {
        return whodoyou;
    }

    public void setWhodoyou(String whodoyou) {
        this.whodoyou = whodoyou;
    }

    public String getSpecifier() {
        return specifier;
    }

    public void setSpecifier(String specifier) {
        this.specifier = specifier;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
