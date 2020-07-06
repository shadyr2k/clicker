package com.clicker.main;

import java.awt.*;
import java.util.LinkedList;

class Handler {

    private LinkedList<Icon> object = new LinkedList<>();

    void render(Graphics g) {
        for (Icon temp : object) {
            temp.render(g);
        }
    }

    void addObject(Icon obj) {
        this.object.add(obj);
    }

}
