package com.example.project.Domain;

import java.io.Serializable;

public class CategoryDomain implements Serializable {
    private String title;
    private String pic;
    private String background;

    public CategoryDomain(String title, String pic, String background) {
        this.title = title;
        this.pic = pic;
        this.background = background;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic() {
        return pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }

    public String getBackground() { return background; }
    public void setBackground(String background) { this.background = background; }
}
