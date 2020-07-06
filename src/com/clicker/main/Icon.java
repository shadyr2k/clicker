package com.clicker.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

class Icon {

    private int x, y, picX, picY;
    private int toggled;
    private long price;
    private String tag;
    private Rectangle r;

    Icon(int x, int y, long price, String tag){
        this.x = x;
        this.y = y;
        picX = x + 5;
        picY = y + 5;
        this.price = price;
        this.tag = tag;
        r = new Rectangle(x, y, 40, 40);
    }

    void render(Graphics g){
        try {
            BufferedImage rectangle = ImageIO.read(new File("assets/sprites/border" + toggled + ".png"));
            BufferedImage icon = ImageIO.read(new File("assets/sprites/" + tag.replaceAll("\\s", "_").replaceAll("'", "") + ".png"));
            g.drawImage(rectangle, x, y, null);
            g.drawImage(icon, picX, picY, null);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    int getToggled(){
       return toggled;
    }
    long getPrice(){
        return price;
    }
    void setToggled(int toggled){
        this.toggled = toggled;
    }
    String getTag() {
        return tag;
    }
    Rectangle getHitbox(){
        return r;
    }
}
