package com.pragyamutluru.news;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by suresh on 27/2/18.
 */

public class NewsCategory {
    private String name;
    private int imageResource;

    public NewsCategory() {
    }

    //getters
    public String getName(){
        return name;
    }
    public int getImageResource(){
        return imageResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
