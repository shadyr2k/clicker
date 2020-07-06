package com.clicker.main;

import java.awt.*;

public abstract class Tech {
    protected int x, y;
    protected ID id;

    Tech(int x, int y, ID id){
        this.x = x;
        this.y = y;
        this.id = id;
    }

    abstract void render(Graphics g);
}
