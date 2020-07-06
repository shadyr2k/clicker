package com.clicker.main;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class BG extends Tech {

    BG(int x, int y) {
        super(x, y);
    }

    @Override
    public void render(Graphics g) {
        try {
            BufferedImage GameBG = ImageIO.read(new File("assets/background/background.png"));
            g.drawImage(GameBG, x, y, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
