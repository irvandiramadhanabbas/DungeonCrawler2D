package simpan;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DungeonGame extends JFrame {
    private static final int GRID_SIZE = 20; // Ukuran grid (20x20)
    private JLabel[][] gridLabels; // Label untuk menampilkan grid
    private String[][] mapData; // Data peta dari file
    private int playerX, playerY; // Posisi pemain

    public DungeonGame() {
        setTitle("Dungeon Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(600, 600);
        setLayout(new GridLayout(GRID_SIZE, GRID_SIZE));

        gridLabels = new JLabel[GRID_SIZE][GRID_SIZE];
        mapData = new String[GRID_SIZE][GRID_SIZE];

        // Membuat grid dengan JLabel
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                gridLabels[i][j] = new JLabel();
                gridLabels[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                gridLabels[i][j].setOpaque(true);
                gridLabels[i][j].setBackground(Color.LIGHT_GRAY);
                add(gridLabels[i][j]);
            }
        }

        // Muat peta dari file
        loadMap();

        // Cari posisi pemain awal (default di (0,0) jika tidak ada 'P')
        boolean foundPlayer = false;
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                if (mapData[i][j].equals("P")) {
                    playerX = i;
                    playerY = j;
                    foundPlayer = true;
                    break;
                }
            }
            if (foundPlayer) break;
        }

        if (!foundPlayer) {
            mapData[5][5] = "P"; // Default posisi pemain
            playerX = 5;
            playerY = 5;
        }

        updateGrid();

        // Tambahkan KeyListener untuk menggerakkan pemain
        addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                int newX = playerX, newY = playerY;

                switch (e.getKeyCode()) {
                    case KeyEvent.VK_UP -> newX = playerX - 1;
                    case KeyEvent.VK_DOWN -> newX = playerX + 1;
                    case KeyEvent.VK_LEFT -> newY = playerY - 1;
                    case KeyEvent.VK_RIGHT -> newY = playerY + 1;
                }

                if (isValidMove(newX, newY)) {
                    // Update posisi pemain
                    mapData[playerX][playerY] = " ";
                    playerX = newX;
                    playerY = newY;
                    mapData[playerX][playerY] = "P";

                    updateGrid();
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
        });

        setFocusable(true);
        setVisible(true);
    }

    private void loadMap() {
        // Inisialisasi grid dengan nilai kosong
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                mapData[i][j] = " "; // Default: kosong
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader("map.txt"))) {
            String line;
            int row = 0;

            while ((line = reader.readLine()) != null && row < GRID_SIZE) {
                String[] cells = line.split(",");
                for (int col = 0; col < cells.length && col < GRID_SIZE; col++) {
                    mapData[row][col] = cells[col].trim(); // Isi mapData dari file
                }
                row++;
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Error loading map: " + e.getMessage());
        }
    }


    private void updateGrid() {
        for (int i = 0; i < GRID_SIZE; i++) {
            for (int j = 0; j < GRID_SIZE; j++) {
                String cell = mapData[i][j] != null ? mapData[i][j] : " "; // Default jika null
                JLabel label = gridLabels[i][j];

                switch (cell) {
                    case "X" -> {
                        label.setText("X");
                        label.setBackground(Color.BLACK); // Dinding
                    }
                    case "D" -> {
                        label.setText("D");
                        label.setBackground(Color.YELLOW); // Pintu
                    }
                    case "P" -> {
                        label.setText("P");
                        label.setBackground(Color.GREEN); // Pemain
                    }
                    default -> {
                        label.setText("");
                        label.setBackground(Color.LIGHT_GRAY); // Kosong
                    }
                }
            }
        }
    }


    private boolean isValidMove(int x, int y) {
        // Cek apakah gerakan valid (dalam batas grid dan tidak menabrak dinding)
        if (x < 0 || x >= GRID_SIZE || y < 0 || y >= GRID_SIZE) {
            return false;
        }
        return !mapData[x][y].equals("X"); // Tidak boleh menabrak dinding
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DungeonGame::new);
    }
}
