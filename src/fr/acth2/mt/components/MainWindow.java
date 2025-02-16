package fr.acth2.mt.components;

import fr.acth2.mt.utils.Refs;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;

public class MainWindow extends JFrame {
    public MainWindow(int id) {
        super("India tech");
        setResizable(false);

        Image background = new ImageIcon("icons/background.png").getImage();
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(panel);


        Image image = getToolkit().getImage("icons/img.png");
        Cursor c = getToolkit().createCustomCursor(image , new Point(getX(),
                getY()), "img");
        setCursor(c);

        setIconImage(getToolkit().getImage("icons/mts.png"));


        if (id == Refs.MAIN_WINDOW) {
            setLayout(null);
            setSize(1000, 800);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

            Image mainIcon = getToolkit().getImage("icons/logo.png");
            JLabel mainLabel = new JLabel(new ImageIcon(mainIcon));
            mainLabel.setBounds(245, 0, 500, 250);
            add(mainLabel);

            Image musicIcon = getToolkit().getImage("icons/music.png");
            JLabel musicLabel = new JLabel(new ImageIcon(musicIcon));
            musicLabel.setBounds(85, 313, 64, 64);
            add(musicLabel);

            JButton m = new JButton("");
            m.setBackground(Color.RED);
            m.setBounds(43, 300, 150, 100);
            add(m);

            Image downIcon = getToolkit().getImage("icons/mts-dl.png");
            JLabel downLabel = new JLabel(new ImageIcon(downIcon));
            downLabel.setBounds(5, 655, 400, 100);
            add(downLabel);
        }

        repaint();
    }
}
