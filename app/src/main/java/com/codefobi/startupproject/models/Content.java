package com.codefobi.startupproject.models;

/**
 * Created by tosantechnolocal on 11/3/2016.
 */
public class Content {

    private int ID;
    private String Specifier, title, body ,whodoyou;


    public Content() {
        //Empty constructor
    }

    public Content(String Specifier, String title, String body) {
        this.Specifier = Specifier;
        this.title = title;
        this.body = body;
    }

    public String getWhodoyou() {
        return whodoyou;
    }

    public void setWhodoyou(String whodoyou) {
        this.whodoyou = whodoyou;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getSpecifier() {
        return Specifier;
    }

    public void setSpecifier(String specifier) {
        Specifier = specifier;
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
}
