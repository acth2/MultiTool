package fr.acth2.mt.components;

import fr.acth2.mt.utils.Refs;
import javax.swing.*;
import java.awt.*;

import static fr.acth2.mt.utils.Refs.*;

public class MainWindow extends JFrame {
    public MainWindow(int id) {
        super("India tech");
        setResizable(false);

        Image background = new ImageIcon(
                getClass().getResource("/icons/background.png")
        ).getImage();
        JPanel panel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(background, 0, 0, getWidth(), getHeight(), this);
            }
        };
        setContentPane(panel);


        Image image = new ImageIcon(
                getClass().getResource("/icons/cursor.png")
        ).getImage();
        Cursor c = getToolkit().createCustomCursor(image , new Point(getX(),
                getY()), "img");
        setCursor(c);

        setIconImage(new ImageIcon(
                getClass().getResource("/icons/mts.png")
        ).getImage());


        if (id == Refs.MAIN_WINDOW) {
            setLayout(null);
            setSize(1000, 800);
            setDefaultCloseOperation(EXIT_ON_CLOSE);
            setVisible(true);

            Image mainIcon = new ImageIcon(
                    getClass().getResource("/icons/logo.png")
            ).getImage();
            JLabel mainLabel = new JLabel(new ImageIcon(mainIcon));
            mainLabel.setBounds(245, 0, 500, 250);
            add(mainLabel);

            Image musicIcon = new ImageIcon(
                    getClass().getResource("/icons/music.png")
            ).getImage();
            JLabel musicLabel = new JLabel(new ImageIcon(musicIcon));
            musicLabel.setBounds(85 + GLOBALMOVER_TOP, 313, 84, 84);
            add(musicLabel);

            JButton m = new JButton("");
            m.setBackground(Color.RED);
            m.setBounds(43 + GLOBALMOVER_TOP, 300, 170, 120);
            add(m);

            Image imgIcon = new ImageIcon(
                    getClass().getResource("/icons/img.png")
            ).getImage();
            JLabel imgLabel = new JLabel(new ImageIcon(imgIcon));
            imgLabel.setBounds(265 + GLOBALMOVER_TOP, 313, 84, 84);
            add(imgLabel);

            JButton r = new JButton("");
            r.setBackground(Color.BLUE);
            r.setBounds(220 + GLOBALMOVER_TOP, 300, 170, 120);
            add(r);

            Image downIcon = new ImageIcon(
                    getClass().getResource("/icons/mts-dl.png")
            ).getImage();
            JLabel downLabel = new JLabel(new ImageIcon(downIcon));
            downLabel.setBounds(5, 655, 400, 100);
            add(downLabel);
        }

        repaint();
    }
}
