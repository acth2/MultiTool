package fr.acth2.mt.components;

import fr.acth2.mt.handlers.FileManagerHandler;
import fr.acth2.mt.handlers.WriterHandler;
import fr.acth2.mt.utils.Refs;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

            Image vidIcon = new ImageIcon(
                    getClass().getResource("/icons/video.png")
            ).getImage();
            JLabel vidLabel = new JLabel(new ImageIcon(vidIcon));
            vidLabel.setBounds(442 + GLOBALMOVER_TOP, 313, 84, 84);
            add(vidLabel);

            JButton v = new JButton("");
            v.setBackground(Color.ORANGE);
            v.setBounds(397 + GLOBALMOVER_TOP, 300, 170, 120);
            add(v);

            Image recIcon = new ImageIcon(
                    getClass().getResource("/icons/recorder.png")
            ).getImage();
            JLabel recLabel = new JLabel(new ImageIcon(recIcon));
            recLabel.setBounds(85 + GLOBALMOVER_DOWN, 438, 84, 84);
            add(recLabel);

            JButton rec = new JButton("");
            rec.setBackground(Color.GRAY);
            rec.setBounds(43 + GLOBALMOVER_DOWN, 425, 170, 120);
            add(rec);

            Image wIcon = new ImageIcon(
                    getClass().getResource("/icons/writer.png")
            ).getImage();
            JLabel wLabel = new JLabel(new ImageIcon(wIcon));
            wLabel.setBounds(265 + GLOBALMOVER_DOWN, 438, 84, 84);
            add(wLabel);

            JButton w = new JButton("");
            w.setBackground(Color.WHITE);
            w.setBounds(220 + GLOBALMOVER_DOWN, 425, 170, 120);
            w.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(WriterHandler::new);
                }
            });
            add(w);

            Image gIcon = new ImageIcon(
                    getClass().getResource("/icons/game.png")
            ).getImage();
            JLabel gLabel = new JLabel(new ImageIcon(gIcon));
            gLabel.setBounds(442 + GLOBALMOVER_DOWN, 438, 84, 84);
            add(gLabel);

            JButton g = new JButton("");
            g.setBackground(new Color(127, 0, 255));
            g.setBounds(397 + GLOBALMOVER_DOWN, 425, 170, 120);
            add(g);

            Image fmIcon = new ImageIcon(
                    getClass().getResource("/icons/filemanager.png")
            ).getImage();
            JLabel fmLabel = new JLabel(new ImageIcon(fmIcon));
            fmLabel.setBounds(265 + GLOBALMOVER_DOWN, 556, 84, 84);
            add(fmLabel);

            JButton fm = new JButton("");
            fm.setBackground(Color.GREEN);
            fm.setBounds(220 + GLOBALMOVER_DOWN, 550, 170, 120);
            fm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(FileManagerHandler::new);
                }
            });
            add(fm);
        }

        repaint();
    }
}
