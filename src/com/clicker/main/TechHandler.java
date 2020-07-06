package com.clicker.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class TechHandler {

    private LinkedList<Tech> object = new LinkedList<>();

    void render(Graphics g){
        for (Tech temp : object) {
            temp.render(g);
        }
    }

    void addObject(Tech obj){
        this.object.add(obj);
    }

    public void removeObject(Tech obj){
        this.object.remove(obj);
    }

}
