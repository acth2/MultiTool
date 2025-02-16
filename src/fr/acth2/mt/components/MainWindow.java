package fr.acth2.mt.components;

import fr.acth2.mt.utils.Refs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(int id) {
        super("India tech");

        Image image = getToolkit().getImage("icons/img.png");
        Cursor c = getToolkit().createCustomCursor(image , new Point(getX(),
                getY()), "img");
        setCursor(c);

        setIconImage(getToolkit().getImage("icons/mts.png"));


        if (id == Refs.MAIN_WINDOW) {
            setLayout(null);
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

            Image musicIcon = getToolkit().getImage("icons/music.png");
            JLabel musicLabel = new JLabel(new ImageIcon(musicIcon));
            musicLabel.setBounds(85, 60, 64, 64);
            add(musicLabel);

            Image downIcon = getToolkit().getImage("icons/mts-dl.png");
            JLabel downLabel = new JLabel(new ImageIcon(downIcon));
            downLabel.setBounds(5, 445, 400, 100);
            add(downLabel);

            JButton m = new JButton("");
            m.setBounds(43, 50, 150, 100);
            add(m);

            JButton j = new JButton("Jeux");
            add(j);
        }
    }
}
