package tes;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Arrays;

public class DungeonMapEditor extends JFrame {
    private static final int GRID_SIZE = 20; // Ukuran grid 20x20
    private JButton[][] gridButtons;
    private String[][] mapData;

    public DungeonMapEditor() {
        setTitle("Dungeon Map Editor");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 700);
        setLayout(new BorderLayout());

        // Inisialisasi data peta
        mapData = new String[GRID_SIZE][GRID_SIZE];
        for (String[] row : mapData) {
            Arrays.fill(row, " "); // Default kosong
        }

        // Panel untuk grid
        JPanel gridPanel = new JPanel();
        gridPanel.setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));
        gridButtons = new JButton[GRID_SIZE][GRID_SIZE];

        // Membuat tombol untuk grid
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                JButton button = new JButton();
                button.setBackground(Color.LIGHT_GRAY);
                int x = i, y = j;

                button.addActionListener(e -> {
                    // Toggle antara "kosong", "X" (dinding), dan "D" (pintu)
                    if (mapData[x][y].equals(" ")) {
                        mapData[x][y] = "X";  // Menandakan dinding
                        button.setBackground(Color.BLACK);
                    } else if (mapData[x][y].equals("X")) {
                        mapData[x][y] = "D";  // Menandakan pintu
                        button.setBackground(Color.YELLOW); // Warna pintu
                    } else if (mapData[x][y].equals("D")) {
                        mapData[x][y] = " ";  // Kembali ke kosong
                        button.setBackground(Color.LIGHT_GRAY);
                    }
                });

                gridButtons[i][j] = button;
                gridPanel.add(button);
            }
        }

        // Panel untuk tombol Save/Load
        JPanel controlPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton loadButton = new JButton("Load");

        saveButton.addActionListener(e -> saveMap());
        loadButton.addActionListener(e -> loadMap());

        controlPanel.add(saveButton);
        controlPanel.add(loadButton);

        // Tambahkan panel ke frame
        add(gridPanel, BorderLayout.CENTER);
        add(controlPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    public static String[][] loadMap(String file) {
        return new String[0][];
    }

    private void saveMap() {
        try (FileWriter writer = new FileWriter("map.txt")) {
            for (String[] row : mapData) {
                writer.write(String.join(",", row) + "\n");
            }
            JOptionPane.showMessageDialog(this, "Map saved successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error saving map: " + e.getMessage());
        }
    }

    private void loadMap() {
        try (BufferedReader reader = new BufferedReader(new FileReader("map.txt"))) {
            for (int i = 0; i < GRID_SIZE; i++) {
                String[] row = reader.readLine().split(",");
                System.arraycopy(row, 0, mapData[i], 0, row.length);

                for (int j = 0; j < GRID_SIZE; j++) {
                    if (mapData[i][j].equals("X")) {
                        gridButtons[i][j].setBackground(Color.BLACK); // Dinding
                    } else if (mapData[i][j].equals("D")) {
                        gridButtons[i][j].setBackground(Color.YELLOW); // Pintu
                    } else {
                        gridButtons[i][j].setBackground(Color.LIGHT_GRAY); // Kosong
                    }
                }
            }
            JOptionPane.showMessageDialog(this, "Map loaded successfully!");
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading map: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DungeonMapEditor::new);
    }
}

