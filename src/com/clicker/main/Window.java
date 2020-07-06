package com.clicker.main;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.*;

class Window extends Canvas {
    private static final long serialVersionUID = -6930025877466834394L;
    Window(int width, int height, String title, Game game){
        JFrame f = new JFrame(title);
        Clicked clicked = new Clicked();
        f.setPreferredSize(new Dimension(width, height));
        f.setMaximumSize(new Dimension(width, height));
        f.setMinimumSize(new Dimension(width, height));
        f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        f.setResizable(false);
        f.setLocationRelativeTo(null);
        f.add(game);

        JMenuBar mb = new JMenuBar();
        JMenu apl = new JMenu("Calculator");
        JMenu edit = new JMenu("Edit");

        JMenuItem save = new JMenuItem("Save");
        JMenuItem quit = new JMenuItem("Quit");
        JMenuItem level = new JMenuItem("Prestige Level");
        JMenuItem currentLevel = new JMenuItem("Current Level (this ascension)");
        JMenuItem cps = new JMenuItem("CpS");

        apl.add(save);
        apl.add(quit);
        edit.add(level);
        edit.add(currentLevel);
        edit.add(cps);
        mb.add(apl);
        mb.add(edit);

        save.addActionListener(clicked);
        quit.addActionListener(clicked);
        level.addActionListener(clicked);
        currentLevel.addActionListener(clicked);
        cps.addActionListener(clicked);

        f.setJMenuBar(mb);
        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(f, "Close Window?", "Make sure to save your changes!", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION)
                    System.exit(0);
            }
        });
        f.setVisible(true);
        game.start();
    }
}