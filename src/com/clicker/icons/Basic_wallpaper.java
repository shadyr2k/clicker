package com.clicker.icons;

import com.clicker.main.ID;
import com.clicker.main.Icon;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Basic_wallpaper extends Icon {

    static int picX, picY;
    static Rectangle r;

    public Basic_wallpaper(int x, int y, long price, ID id, String tag){
        super(x,y,price,id,tag);
        picX = x + 5;
        picY = y + 5;
    }

    @Override
    public void render(Graphics g) {
        //System.out.println(toggled);
        try {
            BufferedImage rectangle = ImageIO.read(new File("assets//sprites/border" + toggled + ".png"));
            BufferedImage icon = ImageIO.read(new File("assets//sprites/Basic_wallpaper.png"));
            g.drawImage(rectangle, x, y, null);
            g.drawImage(icon, picX, picY, null);
        } catch(IOException e) {
            e.printStackTrace();
        }

    }
}
