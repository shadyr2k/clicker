package com.clicker.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Icon {

    private int x, y;
    private int toggled;
    private long price;
    private String tag;
    private Rectangle r;

    Icon(int x, int y, long price, String tag) {
        this.x = x;
        this.y = y;
        this.price = price;
        this.tag = tag;
        r = new Rectangle(x, y, 40, 40);
    }

    void render(Graphics g) {
        if (toggled != 0) {
            try {
                BufferedImage rectangle = ImageIO.read(new File("assets/sprites/border" + toggled + ".png"));
                g.drawImage(rectangle, x, y, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    int getToggled() {
        return toggled;
    }

    void setToggled(int toggled) {
        this.toggled = toggled;
    }

    long getPrice() {
        return price;
    }

    String getTag() {
        return tag;
    }

    Rectangle getHitbox() {
        return r;
    }
}
