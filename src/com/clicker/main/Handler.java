package com.clicker.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {

    private LinkedList<Icon> object = new LinkedList<>();

    void render(Graphics g){
        for (Icon temp : object) {
            temp.render(g);
        }
    }

    void addObject(Icon obj){
        this.object.add(obj);
    }

    public void removeObject(Icon obj){
        this.object.remove(obj);
    }

}
