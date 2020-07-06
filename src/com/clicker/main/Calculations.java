package com.clicker.main;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;

class Calculations extends Tech {

    Calculations(int x, int y) {
        super(x, y);
    }

    @Override
    void render(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);

        try {
            Font font = Font.createFont(Font.TRUETYPE_FONT, new File("assets/fonts/poppins.otf"));
            ge.registerFont(font);
            g2d.setFont(font.deriveFont(18.0f));
            String heavenlyChipsNeeded = BigInteger.valueOf(Game.price).subtract(Game.currentPrestigeLevel).compareTo(BigInteger.ZERO) < 0
                    ? "0"
                    : String.format("%,d", BigInteger.valueOf(Game.price).subtract(Game.currentPrestigeLevel));

            g2d.setColor(Color.YELLOW);
            g2d.drawString(String.format("%,d", Game.price), 11 + g2d.getFontMetrics().stringWidth("Grand Total: "), 480);
            g2d.drawString(heavenlyChipsNeeded, 11 + g2d.getFontMetrics().stringWidth("Heavenly Chips Needed: "), 510);
            g2d.drawString(convertToWords(heavenlyChipsToCookies(BigInteger.valueOf(Game.price), Game.currentPrestigeLevel.add(Game.prestigeLevel))), 11 + g2d.getFontMetrics().stringWidth("Cookies Needed: "), 540);
            g2d.drawString(heavenlyChipsToCookiesAllTime(BigInteger.valueOf(Game.price)), 11 + g2d.getFontMetrics().stringWidth("Cookies Needed (all time): "), 570);
            g2d.drawString(timeBake(Game.currentCpS, heavenlyChipsToCookies(BigInteger.valueOf(Game.price), Game.currentPrestigeLevel.add(Game.prestigeLevel))), 11 + g2d.getFontMetrics().stringWidth("Approximate time needed: "), 600);

            g2d.setColor(Color.WHITE);
            g2d.drawString("Grand Total: ", 11, 480);
            g2d.drawString("Heavenly Chips Needed: ", 11, 510);
            g2d.drawString("Cookies Needed: ", 11, 540);
            g2d.drawString("Cookies Needed (all time): ", 11, 570);
            g2d.drawString("Approximate time needed: ", 11, 600);

            g2d.setFont(font.deriveFont(14.0f));
            String[] next7 = next7(Game.currentPrestigeLevel, Game.prestigeLevel, "7");
            String[] next777 = next7(Game.currentPrestigeLevel, Game.prestigeLevel, "777");
            String[] next777777 = next7(Game.currentPrestigeLevel, Game.prestigeLevel, "777777");
            g2d.drawString("Next 7: ", 11, 635);
            g2d.drawString("Next 777: ", 11, 655);
            g2d.drawString("Next 777777: ", 11, 675);

            g2d.setColor(Color.YELLOW);
            g2d.drawString("end in " + next7[2] + ", ", 11 + g2d.getFontMetrics().stringWidth("Next 7: "), 635);
            g2d.drawString("end in " + next777[2] + ", ", 11 + g2d.getFontMetrics().stringWidth("Next 777: "), 655);
            g2d.drawString("end in " + next777777[2] + ", ", 11 + g2d.getFontMetrics().stringWidth("Next 777777: "), 675);

            g2d.setColor(Color.CYAN);
            g2d.drawString(next7[1] + " cookies away", 11 + g2d.getFontMetrics().stringWidth("Next 7: end in " + next7[2] + ", "), 635);
            g2d.drawString(next777[1] + " cookies away", 11 + g2d.getFontMetrics().stringWidth("Next 777: end in " + next777[2] + ", "), 655);
            g2d.drawString(next777777[1] + " cookies away", 11 + g2d.getFontMetrics().stringWidth("Next 777777: end in " + next777777[2] + ", "), 675);

            g2d.setColor(Color.WHITE);
            g2d.setFont(font.deriveFont(10.0f));
            g2d.drawString("cars10 - v" + Game.version, 1184, 700);

            g2d.setFont(font.deriveFont(22.0f));
            g2d.drawString(Clicked.tagName, 839, 55);
            String price = Clicked.tagPrice.isEmpty() ? "" : String.format("%,d", Long.parseLong(Clicked.tagPrice));
            g2d.setColor(Color.ORANGE);
            g2d.drawString(price, 863, 85);

            if (Clicked.found)
                g2d.drawImage(new ImageIcon("assets/sprites/heavenlyChip.png").getImage(), 839, 69, null);
            else
                g2d.drawImage(new ImageIcon("assets/sprites/blank.png").getImage(), 839, 69, null);

        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }
    }

    private static String[] next7(BigInteger current, BigInteger level, String end) {
        BigInteger b = current.add(level);
        BigInteger goal;
        BigInteger loop = b.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0 ? b.add(BigInteger.ONE) : b;
        for (BigInteger i = loop; ; i = i.add(BigInteger.TWO)) {
            if (i.toString().endsWith(end)) {
                goal = i;
                break;
            }
        }

        return new String[]{goal.subtract(level) + "", goal.subtract(level).subtract(current) + "", goal.subtract(level).toString().substring(goal.subtract(level).toString().length() - end.length())};
    }

    private BigInteger heavenlyChipsToCookies(BigInteger price, BigInteger level) {
        BigInteger first = level.add(price.subtract(level)).pow(3);
        BigInteger second = level.pow(3);
        return BigInteger.TEN.pow(12).multiply(first.subtract(second)).compareTo(BigInteger.ZERO) >= 0 ? BigInteger.TEN.pow(12).multiply(first.subtract(second)) : BigInteger.ZERO;
    }

    private String heavenlyChipsToCookiesAllTime(BigInteger price) {
        return convertToWords(price.pow(3).multiply(BigInteger.TEN.pow(12)));
    }

    private String timeBake(BigInteger cps, BigInteger goal) {
        if (cps.compareTo(BigInteger.ZERO) == 0) return "Enter CpS!";
        BigInteger seconds = goal.divide(cps.multiply(BigInteger.valueOf(34)));

        if (seconds.compareTo(BigInteger.valueOf(3153600000L)) > 0) return "Over a century.";
        BigInteger years = seconds.divide(BigInteger.valueOf(31536000));
        seconds = seconds.subtract(years.multiply(BigInteger.valueOf(31536000)));
        BigInteger days = seconds.divide(BigInteger.valueOf(86400));
        seconds = seconds.subtract(days.multiply(BigInteger.valueOf(86400)));
        BigInteger hours = seconds.divide(BigInteger.valueOf(3600));
        seconds = seconds.subtract(hours.multiply(BigInteger.valueOf(3600)));
        BigInteger minutes = seconds.divide(BigInteger.valueOf(60));
        seconds = seconds.subtract(minutes.multiply(BigInteger.valueOf(60)));

        String s = "";
        s += years.compareTo(BigInteger.ZERO) == 0 ? "" : years + "y ";
        s += days.compareTo(BigInteger.ZERO) == 0 ? "" : days + "d ";
        s += hours.compareTo(BigInteger.ZERO) == 0 ? "" : hours + "h ";
        s += minutes.compareTo(BigInteger.ZERO) == 0 ? "" : minutes + "m ";
        s += seconds.compareTo(BigInteger.ZERO) == 0 ? "" : seconds + "s ";

        return s.isEmpty() ? "0" : s;
    }

    private String convertToWords(BigInteger b) {
        String s = b.toString();
        if (s.length() <= 6) return s;

        int power = s.length() - 1;
        int decimalPlace = power % 3 + 1;

        int wordConvert = power / 3;
        s = s.substring(0, decimalPlace) + "." + s.substring(decimalPlace, 4);
        switch (wordConvert) {
            case 2:
                s += " million";
                break;
            case 3:
                s += " billion";
                break;
            case 4:
                s += " trillion";
                break;
            case 5:
                s += " quadrillion";
                break;
            case 6:
                s += " quintillion";
                break;
            case 7:
                s += " sextillion";
                break;
            case 8:
                s += " septillion";
                break;
            case 9:
                s += " octillion";
                break;
            case 10:
                s += " nonillion";
                break;
            case 11:
                s += " decillion";
                break;
            case 12:
                s += " undecillion";
                break;
            case 13:
                s += " duodecillion";
                break;
            case 14:
                s += " tredecillion";
                break;
            case 15:
                s += " quattuordecillion";
                break;
            case 16:
                s += " quindecillion";
                break;
            case 17:
                s += " sexdecillion";
                break;
            case 18:
                s += " septendecillion";
                break;
            case 19:
                s += " octodecillion";
                break;
            case 20:
                s += " novemdecillion";
                break;
            case 21:
                s += " vigintillion";
                break;
        }
        return s;
    }
}
