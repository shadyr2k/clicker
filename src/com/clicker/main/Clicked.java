package com.clicker.main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

public class Clicked implements MouseListener, ActionListener, MouseMotionListener {

    static String tagName = "";
    static String tagPrice = "";
    static boolean found = false;

    Clicked() {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        for (int i = 0; i < Game.icons.size(); ++i) {
            Icon icon = Game.icons.get(i);
            if (mouseOver(mouseX, mouseY, icon.getHitbox())) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    if (icon.getToggled() != -1) {
                        icon.setToggled(icon.getToggled() == 1 ? 0 : 1);
                    }
                } else {
                    if (icon.getToggled() != 1)
                        icon.setToggled(icon.getToggled() == -1 ? 0 : -1);
                }
                if (icon.getToggled() == 1 && e.getButton() == MouseEvent.BUTTON1) Game.price += icon.getPrice();
                else if (icon.getToggled() == 0 && e.getButton() == MouseEvent.BUTTON1)
                    Game.price -= icon.getPrice();

                break;
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        int mouseX = e.getX();
        int mouseY = e.getY();

        found = false;
        for (int i = 0; i < Game.icons.size(); ++i) {
            Icon icon = Game.icons.get(i);
            if (mouseOver(mouseX, mouseY, icon.getHitbox())) {
                tagName = icon.getTag();
                tagPrice = icon.getPrice() + "";
                found = true;
                //System.out.println(tagName + " " + tagPrice);
            }
        }
        if (!found) {
            tagName = "";
            tagPrice = "";
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        String toInt;
        switch (action) {
            case "Prestige Level":
                toInt = JOptionPane.showInputDialog("Enter Current Prestige Level", Game.prestigeLevel);
                Game.prestigeLevel = checkInt(toInt, Game.prestigeLevel);
                System.out.println(Game.prestigeLevel);
                break;
            case "Current Level (this ascension)":
                toInt = JOptionPane.showInputDialog("Enter Current Amount of Levels (next to the plus, long numbers)", Game.currentPrestigeLevel);
                Game.currentPrestigeLevel = checkInt(toInt, Game.currentPrestigeLevel);
                System.out.println(Game.currentPrestigeLevel);
                break;
            case "CpS":
                toInt = JOptionPane.showInputDialog("Enter Rate of Cookies per Second (short numbers)", Game.CpSplaceholder);
                if (convertNum(toInt, Game.currentCpS).compareTo(BigInteger.ZERO) > 0) {
                    Game.currentCpS = convertNum(toInt, Game.currentCpS);
                    Game.CpSplaceholder = convertNum(toInt, Game.currentCpS).toString();
                }
                System.out.println(Game.currentCpS);
                break;
            case "Save":
                Game.save();
                break;
            case "Quit":
                Game.save();
                System.exit(0);
                break;
        }
    }


    private boolean mouseOver(int mouseX, int mouseY, Rectangle r) {
        if (mouseX > r.getX() && mouseX < r.getX() + r.getWidth())
            return mouseY > r.getY() && mouseY < r.getY() + r.getHeight();
        return false;
    }

    private BigInteger checkInt(String s, BigInteger b) {
        if (s == null) return b;
        BigInteger i;
        try {
            i = new BigInteger(s);
        } catch (NumberFormatException e) {
            return BigInteger.ZERO;
        }
        return i.compareTo(BigInteger.ZERO) >= 0 ? i : BigInteger.ZERO;
    }

    private BigInteger convertNum(String s, BigInteger b) {
        if (s == null) return b;
        s = s.replaceAll(",", "").trim();
        String[] split = s.split(" ");
        if (split.length <= 1) {
            return checkInt(s, b);
        }

        double d;
        try {
            d = Double.valueOf(split[0]);
        } catch (NumberFormatException e) {
            return BigInteger.ZERO;
        }

        BigInteger base = new BigInteger(String.valueOf(Integer.valueOf((int) (d * 1000))));
        String number = split[1];
        BigInteger power;

        switch (number) {
            case "million":
                power = BigInteger.TEN.pow(3);
                break;
            case "billion":
                power = BigInteger.TEN.pow(6);
                break;
            case "trillion":
                power = BigInteger.TEN.pow(9);
                break;
            case "quadrillion":
                power = BigInteger.TEN.pow(12);
                break;
            case "quintillion":
                power = BigInteger.TEN.pow(15);
                break;
            case "sextillion":
                power = BigInteger.TEN.pow(18);
                break;
            case "septillion":
                power = BigInteger.TEN.pow(21);
                break;
            case "octillion":
                power = BigInteger.TEN.pow(24);
                break;
            case "nonillion":
                power = BigInteger.TEN.pow(27);
                break;
            case "decillion":
                power = BigInteger.TEN.pow(30);
                break;
            case "undecillion":
                power = BigInteger.TEN.pow(33);
                break;
            case "duodecillion":
                power = BigInteger.TEN.pow(36);
                break;
            case "tredecillion":
                power = BigInteger.TEN.pow(39);
                break;
            case "quattuordecillion":
                power = BigInteger.TEN.pow(42);
                break;
            case "quindecillion":
                power = BigInteger.TEN.pow(45);
                break;
            case "sexdecillion":
                power = BigInteger.TEN.pow(48);
                break;
            case "septendecillion":
                power = BigInteger.TEN.pow(51);
                break;
            case "octodecillion":
                power = BigInteger.TEN.pow(54);
                break;
            case "novemdecillion":
                power = BigInteger.TEN.pow(57);
                break;
            case "vigintillion":
                power = BigInteger.TEN.pow(60);
                break;
            default:
                throw new IllegalStateException("unexpected value: " + number);
        }
        return base.multiply(power);
    }

    public void mouseClicked(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}
