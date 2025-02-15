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

        if (id == Refs.MAIN_WINDOW) {

            Image musicIcon = getToolkit().getImage("icons/music.png");
            JLabel picLabel = new JLabel(new ImageIcon(musicIcon));
            picLabel.setBounds(85, 60, 64, 64);
            add(picLabel);

            JButton m = new JButton("");
            m.setBounds(43, 50, 150, 100);
            add(m);

            JButton j = new JButton("Jeux");
            add(j);

            setLayout(null);
            setSize(800, 600);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

        }
    }
}
