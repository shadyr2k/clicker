package com.clicker.main;

import java.awt.*;

abstract class Tech {
    int x, y;

    Tech(int x, int y) {
        this.x = x;
        this.y = y;
    }

    abstract void render(Graphics g);
}
