package com.clicker.main;

import java.awt.*;

public abstract class Icon {

    protected int x, y;
    protected ID id;
    protected int toggled;
    protected long price;
    protected String tag;
    protected Rectangle r;

    public Icon(int x, int y, long price, ID id, String tag){
        this.x = x;
        this.y = y;
        this.id = id;
        this.price = price;
        this.tag = tag;
        r = new Rectangle(x, y, 40, 40);
    }

    public abstract void render(Graphics g);

    public int getToggled(){
       return toggled;
    }
    public long getPrice(){
        return price;
    }
    public void setToggled(int toggled){
        this.toggled = toggled;
    }
    public void setPrice(long price){
        this.price = price;
    }
    public String getTag() {
        return tag;
    }
    public Rectangle getHitbox(){
        return r;
    }
}
