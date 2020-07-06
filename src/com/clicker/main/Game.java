package com.clicker.main;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Game extends Canvas implements Runnable {

    static final int WIDTH = 1280, HEIGHT = 770;
    static BigInteger prestigeLevel;
    static BigInteger currentPrestigeLevel;
    static BigInteger currentCpS;
    static long price;
    static String CpSplaceholder;
    static boolean[] data = new boolean[82];
    static ArrayList<Icon> icons = new ArrayList<>();
    private Thread thread;
    private boolean running = false;
    private Clicked clicked;
    private Handler handler;
    private TechHandler techHandler;


    private Game() {

        currentPrestigeLevel = BigInteger.ZERO;
        prestigeLevel = BigInteger.ZERO;
        currentCpS = BigInteger.ZERO;

        ArrayList<String> fileData = new ArrayList<>();
        readTxt(fileData);

        System.out.println(currentPrestigeLevel);

        try {
            if (fileData.get(0).equalsIgnoreCase("true")) {
                File file = new File("resources/save-data.txt");
                if (file.delete())
                    System.out.println("deleted file: resources/save-data.txt");
                if (file.createNewFile())
                    System.out.println("created file: resources/save-data.txt");

                PrintWriter pw = new PrintWriter(new FileWriter("resources/save-data.txt", true));
                StringBuilder sb = new StringBuilder();
                sb.append("false");
                sb.append("\n");
                sb.append("prestige-levels=0");
                sb.append("\n");
                sb.append("current-levels=0");
                sb.append("\n");
                sb.append("current-cps=0");
                sb.append("\n");
                sb.append(Arrays.toString(data).replaceAll("[\\[\\]]", ""));
                pw.write(sb.toString());
                pw.close();
                readTxt(fileData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        prestigeLevel = new BigInteger(fileData.get(1).split("=")[1]);
        currentPrestigeLevel = new BigInteger(fileData.get(2).split("=")[1]);
        currentCpS = new BigInteger(fileData.get(3).split("=")[1]);
        CpSplaceholder = currentCpS.toString();
        String[] toConvertBoolean = fileData.get(4).split(", ");
        for (int i = 0; i < data.length; ++i)
            data[i] = Boolean.parseBoolean(toConvertBoolean[i]);


        new Window(WIDTH, HEIGHT, "Ascension Upgrade Calculator", this);

        handler = new Handler();

        techHandler = new TechHandler();

        techHandler.addObject(new BG(0, 0));
        techHandler.addObject(new Calculations(0, 0));

        Icon twin_gates_of_transcendence = new Icon(759, 191, 1, "Twin Gates of Transcendence");
        Icon angels = new Icon(715, 120, 7, "Angels");
        Icon abaddon = new Icon(596, 217, 343, "Abaddon");
        Icon archangels = new Icon(668, 112, 49, "Archangels");
        Icon asmodeus = new Icon(500, 188, 16807, "Asmodeus");
        Icon basic_wallpaper = new Icon(746, 470, 99, "Basic Wallpaper Assortment");
        Icon beelzebub = new Icon(452, 169, 117649, "Beelzebub");
        Icon belphegor = new Icon(693, 239, 7, "Belphegor");
        Icon box_of_brand_biscuits = new Icon(448, 394, 25, "Box of Brand Biscuits");
        Icon box_of_macarons = new Icon(448, 448, 25, "Box of Macarons");
        Icon box_of_maybe_cookies = new Icon(60, 73, 333000000000L, "Box of Maybe Cookies");
        Icon box_of_not_cookies = new Icon(60, 133, 333000000000L, "Box of Not Cookies");
        Icon box_of_pastries = new Icon(60, 15, 333000000000L, "Box of Pastries");
        Icon cherubim = new Icon(524, 81, 16807, "Cherubim");
        Icon chimera = new Icon(367, 85, 40353607, "Chimera");
        Icon cosmic_beginners_luck = new Icon(1014, 202, 14999999985L, "Cosmic Beginner's Luck");
        Icon cosmic_milk = new Icon(746, 381, 1000000, "Fanciful Dairy Selection");
        Icon diabetica_daemonicus = new Icon(297, 132, 300000000, "Diabetica Daemonicus");
        Icon distilled_essence_of_redoubled_luck = new Icon(1080, 407, 7777777, "Distilled Essence of Redoubled Luck");
        Icon divine_bakeries = new Icon(1096, 576, 399999, "Divine Bakeries");
        Icon divine_discount = new Icon(1028, 613, 99999, "Divine Discount");
        Icon divine_sales = new Icon(1028, 541, 99999, "Divine Sales");
        Icon dominions = new Icon(572, 92, 2401, "Dominions");
        Icon eye_of_the_wrinkler = new Icon(106, 356, 99999999, "Eye of the Wrinkler");
        Icon five_finger_discount = new Icon(393, 229, 555555, "Five Finger Discount");
        Icon fortune_cookies = new Icon(1164, 355, 77777777777L, "Fortune Cookies");
        Icon four_leaf_cookie = new Icon(899, 459, 99999, "Residual Luck");
        Icon genius_accounting = new Icon(880, 155, 2000000, "Genius Accounting");
        Icon ghostCookieNew = new Icon(604, 605, 111111, "Starterror");
        Icon god = new Icon(430, 55, 823543, "God");
        Icon goldCookieNew = new Icon(930, 574, 7777, "Decisive Fate");
        Icon goldCursorNew = new Icon(367, 366, 50, "Starter Kit");
        Icon golden_cookie_alert_sound = new Icon(962, 501, 9999, "Golden Cookie Alert Sound");
        Icon golden_switch = new Icon(825, 492, 999, "Golden Switch");
        Icon goldRollingPinNew = new Icon(302, 343, 5000, "Starter Kitchen");
        Icon halo_gloves = new Icon(355, 293, 55555, "Halo Gloves");
        Icon heavenly_cookies = new Icon(538, 366, 3, "Heavenly Cookies");
        Icon heavenly_luck = new Icon(769, 559, 77, "Heavenly Luck");
        Icon heralds = new Icon(602, 456, 100, "Heralds");
        Icon how_to_bake_your_dragon = new Icon(552, 430, 9, "How to bake your dragon");
        Icon inspired_checklist = new Icon(850, 231, 900000, "Inspired Checklist");
        Icon one = new Icon(819, 346, 100, "Permanent Upgrade Slot I");
        Icon two = new Icon(842, 409, 2000, "Permanent Upgrade Slot II");
        Icon three = new Icon(904, 373, 30000, "Permanent Upgrade Slot III");
        Icon four = new Icon(886, 306, 400000, "Permanent Upgrade Slot IV");
        Icon five = new Icon(956, 273, 5000000, "Permanent Upgrade Slot V");
        Icon keepsakes = new Icon(604, 660, 1111111111, "Keepsakes");
        Icon kitten_angels = new Icon(558, 9, 9000, "Kitten Angels");
        Icon label_printer = new Icon(280, 415, 999999, "Label Printer");
        Icon lasting_fortune = new Icon(847, 567, 777, "Lasting Fortune");
        Icon legacy = new Icon(611, 357, 1, "Legacy");
        Icon lucifer = new Icon(406, 152, 823543, "Lucifer");
        Icon lucky_digit_number_payout = new Icon(789, 625, 777, "Lucky Digit");
        Icon lucky_digit_number_payout_ = new Icon(862, 634, 77777, "Lucky Number");
        Icon lucky_digit_number_payout__ = new Icon(939, 642, 77777777, "Lucky Payout");
        Icon mammon = new Icon(644, 229, 49, "Mammon");
        Icon persistent_memory = new Icon(777, 283, 500, "Persistent Memory");
        Icon plain_milk = new Icon(671, 420, 9, "Classic Dairy Selection");
        Icon reindeer = new Icon(525, 534, 111111, "Starsnow");
        Icon reinforced_membrane = new Icon(1097, 202, 14999999985L, "Reinforced Membrane");
        Icon satan = new Icon(548, 204, 2401, "Satan");
        Icon seasonsNew = new Icon(603, 528, 1111, "Season Switcher");
        Icon seraphim = new Icon(476, 69, 117649, "Seraphim");
        Icon shimmering_veil = new Icon(1061, 296, 999999999, "Shimmering Veil");
        Icon starlove = new Icon(550, 588, 111111, "Starlove");
        Icon starspawn = new Icon(677, 534, 111111, "Starspawn");
        Icon startrade = new Icon(657, 588, 111111, "Startrade");
        Icon stevia_caelestis = new Icon(172, 161, 100000000, "Stevia Caelestis");
        Icon sucralosia_inutilis = new Icon(299, 204, 1000000000, "Sucralosia Inutilis");
        Icon sugar_aging_process = new Icon(327, 20, 600000000, "Sugar Aging Process");
        Icon sugar_baking = new Icon(212, 96, 200000000, "Sugar Baking");
        Icon sugar_craving = new Icon(257, 35, 400000000, "Sugar Craving");
        Icon sugar_crystal_cookies = new Icon(128, 73, 1000000000, "Sugar Crystal Cookies");
        Icon synergies = new Icon(570, 150, 222222, "Synergies Vol I");
        Icon synergiesII = new Icon(471, 119, 2222222, "Synergies Vol II");
        Icon tin_of_british_tea_biscuits = new Icon(448, 287, 25, "Tin of British Tea Biscuits");
        Icon tin_of_butter_cookies = new Icon(448, 340, 25, "Tin of Butter Cookies");
        Icon unholy_bait = new Icon(271, 274, 44444, "Unholy Bait");
        Icon virtues = new Icon(620, 102, 343, "Virtues");
        Icon wrinklerLarvaNew = new Icon(209, 241, 444444, "Elder Spice");
        Icon wrinklerLarvaNew_ = new Icon(209, 304, 444444, "Sacrilegious Corruption");
        Icon wrinkly_cookies = new Icon(130, 257, 6666666, "Wrinkly Cookies");

        icons.add(twin_gates_of_transcendence);
        icons.add(angels);
        icons.add(abaddon);
        icons.add(archangels);
        icons.add(asmodeus);
        icons.add(basic_wallpaper);
        icons.add(beelzebub);
        icons.add(belphegor);
        icons.add(box_of_brand_biscuits);
        icons.add(box_of_macarons);
        icons.add(box_of_maybe_cookies);
        icons.add(box_of_not_cookies);
        icons.add(box_of_pastries);
        icons.add(cherubim);
        icons.add(chimera);
        icons.add(cosmic_beginners_luck);
        icons.add(cosmic_milk);
        icons.add(diabetica_daemonicus);
        icons.add(distilled_essence_of_redoubled_luck);
        icons.add(divine_bakeries);
        icons.add(divine_discount);
        icons.add(divine_sales);
        icons.add(dominions);
        icons.add(eye_of_the_wrinkler);
        icons.add(five_finger_discount);
        icons.add(fortune_cookies);
        icons.add(four_leaf_cookie);
        icons.add(genius_accounting);
        icons.add(ghostCookieNew);
        icons.add(god);
        icons.add(goldCookieNew);
        icons.add(goldCursorNew);
        icons.add(golden_cookie_alert_sound);
        icons.add(golden_switch);
        icons.add(goldRollingPinNew);
        icons.add(halo_gloves);
        icons.add(heavenly_cookies);
        icons.add(heavenly_luck);
        icons.add(heralds);
        icons.add(how_to_bake_your_dragon);
        icons.add(inspired_checklist);
        icons.add(one);
        icons.add(two);
        icons.add(three);
        icons.add(four);
        icons.add(five);
        icons.add(keepsakes);
        icons.add(kitten_angels);
        icons.add(label_printer);
        icons.add(lasting_fortune);
        icons.add(legacy);
        icons.add(lucifer);
        icons.add(lucky_digit_number_payout);
        icons.add(lucky_digit_number_payout_);
        icons.add(lucky_digit_number_payout__);
        icons.add(mammon);
        icons.add(persistent_memory);
        icons.add(plain_milk);
        icons.add(reindeer);
        icons.add(reinforced_membrane);
        icons.add(satan);
        icons.add(seasonsNew);
        icons.add(seraphim);
        icons.add(shimmering_veil);
        icons.add(starlove);
        icons.add(starspawn);
        icons.add(startrade);
        icons.add(stevia_caelestis);
        icons.add(sucralosia_inutilis);
        icons.add(sugar_aging_process);
        icons.add(sugar_baking);
        icons.add(sugar_craving);
        icons.add(sugar_crystal_cookies);
        icons.add(synergies);
        icons.add(synergiesII);
        icons.add(tin_of_british_tea_biscuits);
        icons.add(tin_of_butter_cookies);
        icons.add(unholy_bait);
        icons.add(virtues);
        icons.add(wrinklerLarvaNew);
        icons.add(wrinklerLarvaNew_);
        icons.add(wrinkly_cookies);

        for (Icon icon : icons) {
            handler.addObject(icon);
        }

        System.out.println(icons.size());
        for (int i = 0; i < icons.size(); ++i) {
            if (data[i]) {
                icons.get(i).setToggled(-1);
            }
        }
        clicked = new Clicked();
        this.addMouseListener(clicked);
        this.addMouseMotionListener(clicked);
    }

    static void save() {
        System.out.println("test");
        for (int i = 0; i < icons.size(); ++i) {
            data[i] = icons.get(i).getToggled() == -1;
        }
        System.out.println(Arrays.toString(data));

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("resources/save-data.txt"));
            StringBuilder sb = new StringBuilder();
            sb.append("false");
            sb.append("\n");
            sb.append("prestige-levels=");
            sb.append(prestigeLevel);
            sb.append("\n");
            sb.append("current-levels=");
            sb.append(currentPrestigeLevel);
            sb.append("\n");
            sb.append("current-cps=");
            sb.append(currentCpS);
            sb.append("\n");
            sb.append(Arrays.toString(data).replaceAll("[\\[\\]]", ""));
            pw.write(sb.toString());
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new Game();
    }

    synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop() {
        try {
            thread.join();
            running = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        long timer = System.currentTimeMillis();
        int frames = 0;
        while (running) {
            render();
            frames++;

            if (System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render() {
        BufferStrategy b = this.getBufferStrategy();
        if (b == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = b.getDrawGraphics();
        techHandler.render(g);
        handler.render(g);

        g.dispose();
        b.show();
    }

    private void readTxt(ArrayList<String> arrayList) {
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("resources/save-data.txt");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        arrayList.clear();
        BufferedReader bufferedReader = null;
        try {
            String line;
            bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            while ((line = bufferedReader.readLine()) != null)
                arrayList.add(line);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
