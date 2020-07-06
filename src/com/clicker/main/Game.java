package com.clicker.main;

import com.clicker.icons.*;

import java.awt.*;
import java.awt.image.BufferStrategy;
import java.io.*;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Game extends Canvas implements Runnable {

    static final int WIDTH = 1280, HEIGHT = 770;
    private Thread thread;
    private boolean running = false;
    private Clicked clicked;

    static BigInteger prestigeLevel;
    static BigInteger currentPrestigeLevel;
    static BigInteger currentCpS;
    static long price;

    static String CpSplaceholder;

    static boolean[] data = new boolean[82];

    private Handler handler;
    private TechHandler techHandler;

    static ArrayList<Icon> icons = new ArrayList<>();


    synchronized void start(){
        thread = new Thread(this);
        thread.start();
        running = true;
    }

    private synchronized void stop(){
        try {
            thread.join();
            running = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void run(){
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }

    private void render(){
        BufferStrategy b = this.getBufferStrategy();
        if(b == null){
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = b.getDrawGraphics();
        techHandler.render(g);
        handler.render(g);

        g.dispose();
        b.show();
    }

    public Game() {

        currentPrestigeLevel = BigInteger.ZERO;
        prestigeLevel = BigInteger.ZERO;
        currentCpS = BigInteger.ZERO;

        ArrayList<String> fileData = new ArrayList<>();
        readTxt(fileData);

        System.out.println(currentPrestigeLevel);
        try {
            if(fileData.get(0).equalsIgnoreCase("true")){
                File file = new File("resources/save-data.txt");
                if(file.delete())
                    System.out.println("deleted file: resources/save-data.txt");
                if(file.createNewFile())
                    System.out.println("created file: resources/save-data.txt");

                PrintWriter pw = new PrintWriter(new FileWriter("resources/save-data.txt", true));
                String s = "false" +
                        "\n" +
                        "prestige-levels=0" +
                        "\n" +
                        "current-levels=0" +
                        "\n" +
                        "current-cps=0" +
                        "\n" +
                        Arrays.toString(data).replaceAll("[\\[\\]]", "");
                pw.write(s);
                pw.close();
                readTxt(fileData);
            }
        } catch(IOException e){
            e.printStackTrace();
        }

        prestigeLevel = new BigInteger(fileData.get(1).split("=")[1]);
        currentPrestigeLevel = new BigInteger(fileData.get(2).split("=")[1]);
        currentCpS = new BigInteger(fileData.get(3).split("=")[1]);
        CpSplaceholder = currentCpS.toString();
        String[] toConvertBoolean = fileData.get(4).split(", ");
        for(int i = 0; i < data.length; ++i)
            data[i] = Boolean.parseBoolean(toConvertBoolean[i]);


        new Window(WIDTH, HEIGHT, "Ascension Upgrade Calculator", this);

        handler = new Handler();
        techHandler = new TechHandler();

        techHandler.addObject(new BG(0, 0, ID.BG));
        techHandler.addObject(new Calculations(0, 0, ID.Calculations));

        Twin_Gates_of_Transcendence twin_gates_of_transcendence = new Twin_Gates_of_Transcendence(759, 191, 1, ID.Twin_Gates_of_Transcendence, "Twin Gates of Transcendence");
        Angels angels = new Angels(715, 120, 7, ID.Angels, "Angels");
        Abaddon abaddon = new Abaddon(596, 217, 343, ID.Abaddon, "Abaddon");
        Archangels archangels = new Archangels(668,112,49, ID.Archangels, "Archangels");
        Asmodeus asmodeus = new Asmodeus(500, 188, 16807, ID.Asmodeus, "Asmodeus");
        Basic_wallpaper basic_wallpaper = new Basic_wallpaper(746, 470, 99, ID.Basic_wallpaper, "Basic Wallpaper Assortment");
        Beelzebub beelzebub = new Beelzebub(452, 169, 117649, ID.Beelzebub, "Beelzebub");
        Belphegor belphegor = new Belphegor(693, 239, 7, ID.Belphegor, "Belphegor");
        Box_of_brand_biscuits box_of_brand_biscuits = new Box_of_brand_biscuits(448, 394, 25, ID.Box_of_brand_biscuits, "Box of Brand Biscuits");
        Box_of_macarons box_of_macarons = new Box_of_macarons(448, 448, 25, ID.Box_of_macarons, "Box of Macarons");
        Box_of_maybe_cookies box_of_maybe_cookies = new Box_of_maybe_cookies(60, 73, 333000000000L, ID.Box_of_maybe_cookies, "Box of Maybe Cookies");
        Box_of_not_cookies box_of_not_cookies = new Box_of_not_cookies(60, 133, 333000000000L, ID.Box_of_not_cookies, "Box of Not Cookies");
        Box_of_pastries box_of_pastries = new Box_of_pastries(60, 15, 333000000000L, ID.Box_of_pastries, "Box of Pastries");
        Cherubim cherubim = new Cherubim(524, 81, 16807, ID.Cherubim, "Cherubim");
        Chimera chimera = new Chimera(367, 85, 40353607, ID.Chimera, "Chimera");
        Cosmic_beginners_luck cosmic_beginners_luck = new Cosmic_beginners_luck(1014, 202, 14999999985L, ID.Cosmic_beginners_luck, "Cosmic Beginner's Luck");
        Cosmic_milk cosmic_milk = new Cosmic_milk(746, 381, 1000000, ID.Cosmic_milk, "Fanciful Dairy Selection");
        Diabetica_Daemonicus diabetica_daemonicus = new Diabetica_Daemonicus(297, 132, 300000000, ID.Diabetica_Daemonicus, "Diabetica Daemonicus");
        Distilled_Essence_of_Redoubled_Luck distilled_essence_of_redoubled_luck = new Distilled_Essence_of_Redoubled_Luck(1080, 407, 7777777, ID.Distilled_Essence_of_Redoubled_Luck, "Distilled Essence of Redoubled Luck");
        Divine_bakeries divine_bakeries = new Divine_bakeries(1096, 576, 399999, ID.Divine_bakeries, "Divine Bakeries");
        Divine_discount divine_discount = new Divine_discount(1028, 613, 99999, ID.Divine_discount, "Divine Discount");
        Divine_sales divine_sales = new Divine_sales(1028, 541, 99999, ID.Divine_sales, "Divine Sales");
        Dominions dominions = new Dominions(572, 92, 2401, ID.Dominions, "Dominions");
        Eye_of_the_wrinkler eye_of_the_wrinkler = new Eye_of_the_wrinkler(106, 356, 99999999, ID.Eye_of_the_wrinkler, "Eye of the Wrinkler");
        Five_finger_discount five_finger_discount = new Five_finger_discount(393, 229, 555555, ID.Five_finger_discount, "Five Finger Discount");
        Fortune_cookies fortune_cookies = new Fortune_cookies(1164, 355, 77777777777L, ID.Fortune_cookies, "Fortune Cookies");
        Four_leaf_cookie four_leaf_cookie = new Four_leaf_cookie(899, 459, 99999, ID.Four_leaf_cookie, "Residual Luck");
        Genius_accounting genius_accounting = new Genius_accounting(880, 155, 2000000, ID.Genius_accounting, "Genius Accounting");
        GhostCookieNew ghostCookieNew = new GhostCookieNew(604, 605, 111111, ID.GhostCookieNew, "Starterror");
        God god = new God(430, 55, 823543, ID.God, "God");
        GoldCookieNew goldCookieNew = new GoldCookieNew(930, 574, 7777, ID.GoldCookieNew, "Decisive Fate");
        GoldCursorNew goldCursorNew = new GoldCursorNew(367, 366, 50, ID.GoldCursorNew, "Starter Kit");
        Golden_cookie_alert_sound golden_cookie_alert_sound = new Golden_cookie_alert_sound(962, 501, 9999, ID.Golden_cookie_alert_sound, "Golden Cookie Alert Sound");
        Golden_switch golden_switch = new Golden_switch(825, 492, 999, ID.Golden_switch, "Golden Switch");
        GoldRollingPinNew goldRollingPinNew = new GoldRollingPinNew(302, 343, 5000, ID.GoldRollingPinNew, "Starter Kitchen");
        Halo_gloves halo_gloves = new Halo_gloves(355, 293, 55555, ID.Halo_gloves, "Halo Gloves");
        Heavenly_cookies heavenly_cookies = new Heavenly_cookies(538, 366, 3, ID.Heavenly_cookies, "Heavenly Cookies");
        Heavenly_luck heavenly_luck = new Heavenly_luck(769, 559, 77, ID.Heavenly_luck, "Heavenly Luck");
        Heralds heralds = new Heralds(602, 456, 100, ID.Heralds, "Heralds");
        How_to_bake_your_dragon how_to_bake_your_dragon = new How_to_bake_your_dragon(552, 430, 9, ID.How_to_bake_your_dragon, "How to bake your dragon");
        Inspired_checklist inspired_checklist = new Inspired_checklist(850, 231, 900000, ID.Inspired_checklist, "Inspired Checklist");
        I one = new I(819, 346, 100, ID.I, "Permanent Upgrade Slot I");
        II two = new II(842, 409, 2000, ID.II, "Permanent Upgrade Slot II");
        III three = new III(904, 373, 30000, ID.III, "Permanent Upgrade Slot III");
        IV four = new IV(886, 306, 400000, ID.IV, "Permanent Upgrade Slot IV");
        V five = new V(956, 273, 5000000, ID.V, "Permanent Upgrade Slot V");
        Keepsakes keepsakes = new Keepsakes(604, 660, 1111111111, ID.Keepsakes, "Keepsakes");
        Kitten_angels kitten_angels = new Kitten_angels(558, 9, 9000, ID.Kitten_angels, "Kitten Angels");
        Label_printer label_printer = new Label_printer(280, 415, 999999, ID.Label_printer, "Label Printer");
        Lasting_fortune lasting_fortune = new Lasting_fortune(847, 567, 777, ID.Lasting_fortune, "Lasting Fortune");
        Legacy legacy = new Legacy(611, 357, 1, ID.Legacy, "Legacy");
        Lucifer lucifer = new Lucifer(406, 152, 823543, ID.Lucifer, "Lucifer");
        Lucky_Digit_Number_Payout lucky_digit_number_payout = new Lucky_Digit_Number_Payout(789, 625, 777, ID.Lucky_Digit_Number_Payout, "Lucky Digit");
        Lucky_Digit_Number_Payout_ lucky_digit_number_payout_ = new Lucky_Digit_Number_Payout_(862, 634, 77777, ID.Lucky_Digit_Number_Payout_, "Lucky Number");
        Lucky_Digit_Number_Payout__ lucky_digit_number_payout__ = new Lucky_Digit_Number_Payout__(939, 642, 77777777, ID.Lucky_Digit_Number_Payout__, "Lucky Payout");
        Mammon mammon = new Mammon(644, 229, 49, ID.Mammon, "Mammon");
        Persistent_memory persistent_memory = new Persistent_memory(777, 283, 500, ID.Persistent_memory, "Persistent Memory");
        Plain_milk plain_milk = new Plain_milk(671, 420, 9, ID.Plain_milk, "Classic Dairy Selection");
        Reindeer reindeer = new Reindeer(525, 534, 111111, ID.Reindeer, "Starsnow");
        Reinforced_membrane reinforced_membrane = new Reinforced_membrane(1097, 202, 14999999985L, ID.Reinforced_membrane, "Reinforced Membrane");
        Satan satan = new Satan(548, 204, 2401, ID.Satan, "Satan");
        SeasonsNew seasonsNew = new SeasonsNew(603, 528, 1111, ID.SeasonsNew, "Season Switcher");
        Seraphim seraphim = new Seraphim(476, 69, 117649, ID.Seraphim, "Seraphim");
        Shimmering_veil shimmering_veil = new Shimmering_veil(1061, 296, 999999999, ID.Shimmering_veil, "Shimmering Veil");
        Starlove starlove = new Starlove(550, 588, 111111, ID.Starlove, "Starlove");
        Starspawn starspawn = new Starspawn(677, 534, 111111, ID.Starspawn, "Starspawn");
        Startrade startrade = new Startrade(657, 588, 111111, ID.Startrade, "Startrade");
        Stevia_Caelestis stevia_caelestis = new Stevia_Caelestis(172, 161, 100000000, ID.Stevia_Caelestis, "Stevia Caelestis");
        Sucralosia_Inutilis sucralosia_inutilis = new Sucralosia_Inutilis(299, 204, 1000000000, ID.Sucralosia_Inutilis, "Sucralosia Inutilis");
        Sugar_aging_process sugar_aging_process = new Sugar_aging_process(327, 20, 600000000, ID.Sugar_aging_process, "Sugar Aging Process");
        Sugar_baking sugar_baking = new Sugar_baking(212, 96, 200000000, ID.Sugar_baking, "Sugar Baking");
        Sugar_craving sugar_craving = new Sugar_craving(257, 35, 400000000, ID.Sugar_craving, "Sugar Craving");
        Sugar_crystal_cookies sugar_crystal_cookies = new Sugar_crystal_cookies(128, 73, 1000000000, ID.Sugar_crystal_cookies, "Sugar Crystal Cookies");
        Synergies synergies = new Synergies(570, 150, 222222, ID.Synergies, "Synergies Vol I");
        SynergiesII synergiesII = new SynergiesII(471, 119, 2222222, ID.SynergiesII, "Synergies Vol II");
        Tin_of_british_tea_biscuits tin_of_british_tea_biscuits = new Tin_of_british_tea_biscuits(448, 287, 25, ID.Tin_of_british_tea_biscuits, "Tin of British Tea Biscuits");
        Tin_of_butter_cookies tin_of_butter_cookies = new Tin_of_butter_cookies(448, 340, 25, ID.Tin_of_butter_cookies, "Tin of Butter Cookies");
        Unholy_bait unholy_bait = new Unholy_bait(271, 274, 44444, ID.Unholy_bait, "Unholy Bait");
        Virtues virtues = new Virtues(620, 102, 343, ID.Virtues, "Virtues");
        WrinklerLarvaNew wrinklerLarvaNew = new WrinklerLarvaNew(209, 241, 444444, ID.WrinklerLarvaNew, "Elder Spice");
        WrinklerLarvaNew_ wrinklerLarvaNew_ = new WrinklerLarvaNew_(209, 304, 444444, ID.WrinklerLarvaNew_, "Sacrilegious Corruption");
        Wrinkly_cookies wrinkly_cookies = new Wrinkly_cookies(130, 257, 6666666, ID.Wrinkly_cookies, "Wrinkly Cookies");

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
        for(int i = 0; i < icons.size(); ++i){
            if(data[i]) {
                System.out.println("true " + i);
                icons.get(i).setToggled(-1);
            }
        }
        clicked = new Clicked();
        this.addMouseListener(clicked);
        this.addMouseMotionListener(clicked);
    }

    public static void save(){
        System.out.println("test");
        for(int i = 0; i < icons.size(); ++i){
            if(icons.get(i).getToggled() == -1)
                data[i] = true;
            else data[i] = false;
        }
        System.out.println(Arrays.toString(data));

        try {
            PrintWriter pw = new PrintWriter(new FileWriter("resources/save-data.txt"));
            String s = "false" +
                    "\n" +
                    "prestige-levels=" + prestigeLevel +
                    "\n" +
                    "current-levels=" + currentPrestigeLevel +
                    "\n" +
                    "current-cps=" + currentCpS +
                    "\n" +
                    Arrays.toString(data).replaceAll("[\\[\\]]", "");
            pw.write(s);
            pw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void readTxt(ArrayList<String> arrayList){
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
            while((line = bufferedReader.readLine()) != null)
                arrayList.add(line);
        } catch(IOException e){
            e.printStackTrace();
        } finally {
            if(bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(inputStream != null){
                try {
                    inputStream.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args){
        new Game();
    }
}
