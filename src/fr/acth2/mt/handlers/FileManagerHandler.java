package fr.acth2.mt.handlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;

public class FileManagerHandler extends JFrame {

    private static final long serialVersionUID = 1L;

    private static final String APPDATA = System.getenv("APPDATA");
    private static final File MULTITOOL_DIR = new File(APPDATA, ".multitool");
    private static final File BUTTONS_FILE = new File(MULTITOOL_DIR, "buttons.properties");

    private Properties buttonProperties;
    private JPanel buttonPanel;

    public FileManagerHandler() {
        super("Multi-Tools FileManager");
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        if (!MULTITOOL_DIR.exists()) {
            MULTITOOL_DIR.mkdirs();
        }

        buttonProperties = new Properties();
        loadButtonProperties();

        buttonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        JScrollPane scrollPane = new JScrollPane(buttonPanel);
        add(scrollPane, BorderLayout.CENTER);

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton addButton = new JButton("Add Button");
        addButton.addActionListener(e -> openCreateButtonDialog());
        topPanel.add(addButton);
        add(topPanel, BorderLayout.NORTH);

        populateButtons();

        setVisible(true);
    }

    private void loadButtonProperties() {
        if (BUTTONS_FILE.exists()) {
            try (FileInputStream fis = new FileInputStream(BUTTONS_FILE)) {
                buttonProperties.load(fis);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveButtonProperties() {
        try (FileOutputStream fos = new FileOutputStream(BUTTONS_FILE)) {
            buttonProperties.store(fos, "Button Configurations");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void populateButtons() {
        buttonPanel.removeAll();

        Set<String> sortedKeys = new TreeSet<>(buttonProperties.stringPropertyNames());
        for (String key : sortedKeys) {
            if (key.startsWith("button")) {
                String combinedValue = buttonProperties.getProperty(key, "");
                String[] parts = combinedValue.split("\\|", 2);
                String imagePath = (parts.length > 0) ? parts[0] : "";
                String directoryPath = (parts.length > 1) ? parts[1] : "";

                JButton btn = createButton(key, imagePath, directoryPath);
                buttonPanel.add(btn);
            }
        }

        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private JButton createButton(String key, String imagePath, String directoryPath) {
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(100, 100));
        btn.setMaximumSize(new Dimension(100, 100));
        btn.setMinimumSize(new Dimension(100, 100));
        btn.setHorizontalTextPosition(SwingConstants.CENTER);
        btn.setVerticalTextPosition(SwingConstants.BOTTOM);

        if (imagePath != null && !imagePath.isEmpty()) {
            File imgFile = new File(imagePath);
            if (imgFile.exists()) {
                ImageIcon originalIcon = new ImageIcon(imagePath);
                Image scaledImage = originalIcon.getImage().getScaledInstance(
                        80, 80, Image.SCALE_SMOOTH
                );
                btn.setIcon(new ImageIcon(scaledImage));
            } else {
                btn.setText("Img Not Found");
            }
        } else {
            btn.setText("No Image");
        }

        btn.addActionListener(e -> {
            if (directoryPath == null || directoryPath.isEmpty()) {
                JOptionPane.showMessageDialog(
                        this,
                        "No directory path specified for this button.",
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            File dir = new File(directoryPath);
            if (!dir.exists() || !dir.isDirectory()) {
                JOptionPane.showMessageDialog(
                        this,
                        "Directory does not exist or is not a valid folder:\n" + directoryPath,
                        "Invalid Directory",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }
            try {
                Desktop.getDesktop().open(dir);
            } catch (IOException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(
                        this,
                        "Failed to open directory:\n" + directoryPath,
                        "Error",
                        JOptionPane.ERROR_MESSAGE
                );
            }
        });

        JPopupMenu popupMenu = new JPopupMenu();
        JMenuItem deleteItem = new JMenuItem("Delete Button");
        deleteItem.addActionListener(e -> {
            buttonProperties.remove(key);
            saveButtonProperties();
            populateButtons();
        });
        popupMenu.add(deleteItem);

        btn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                checkPopup(e);
            }
            @Override
            public void mouseReleased(MouseEvent e) {
                checkPopup(e);
            }
            private void checkPopup(MouseEvent e) {
                if (e.isPopupTrigger()) {
                    popupMenu.show(btn, e.getX(), e.getY());
                }
            }
        });

        return btn;
    }

    private void openCreateButtonDialog() {
        JDialog dialog = new JDialog(this, "Create New Button", true);
        dialog.setSize(450, 200);
        dialog.setLayout(new BorderLayout());
        dialog.setLocationRelativeTo(this);

        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 5, 5));

        JLabel imageLabel = new JLabel("Image Path:");
        JTextField imageField = new JTextField();

        JLabel directoryLabel = new JLabel("Directory to Open:");
        JTextField directoryField = new JTextField();

        inputPanel.add(imageLabel);
        inputPanel.add(imageField);
        inputPanel.add(directoryLabel);
        inputPanel.add(directoryField);

        JPanel browsePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton pictureButton = new JButton("Browse Image");
        pictureButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile();
                imageField.setText(selectedFile.getAbsolutePath());
            }
        });

        JButton directoryButton = new JButton("Browse Folder");
        directoryButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedDir = fileChooser.getSelectedFile();
                directoryField.setText(selectedDir.getAbsolutePath());
            }
        });
        browsePanel.add(pictureButton);
        browsePanel.add(directoryButton);

        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton okButton = new JButton("OK");
        JButton cancelButton = new JButton("Cancel");

        okButton.addActionListener(e -> {
            String imgPath = imageField.getText().trim();
            String dirPath = directoryField.getText().trim();

            if (dirPath.isEmpty()) {
                JOptionPane.showMessageDialog(
                        dialog,
                        "Please specify a directory to open.",
                        "Invalid Input",
                        JOptionPane.WARNING_MESSAGE
                );
                return;
            }

            String newKey = getNextButtonKey();
            String combinedValue = imgPath + "|" + dirPath;
            buttonProperties.setProperty(newKey, combinedValue);

            saveButtonProperties();
            populateButtons();
            dialog.dispose();
        });

        cancelButton.addActionListener(e -> dialog.dispose());

        bottomPanel.add(okButton);
        bottomPanel.add(cancelButton);

        dialog.add(inputPanel, BorderLayout.CENTER);
        dialog.add(browsePanel, BorderLayout.NORTH);
        dialog.add(bottomPanel, BorderLayout.SOUTH);

        dialog.setVisible(true);
    }

    private String getNextButtonKey() {
        int maxIndex = -1;
        for (String key : buttonProperties.stringPropertyNames()) {
            if (key.startsWith("button")) {
                try {
                    int idx = Integer.parseInt(key.replace("button", ""));
                    if (idx > maxIndex) {
                        maxIndex = idx;
                    }
                } catch (NumberFormatException ignored) {}
            }
        }
        return "button" + (maxIndex + 1);
    }
}
