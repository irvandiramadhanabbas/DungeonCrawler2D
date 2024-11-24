package tes;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class DungeonGame extends JFrame {
    private final int TILE_SIZE = 64; // Ukuran tile
    private String[][] mapData; // Data peta
    private int playerX = 1; // Posisi awal pemain (x)
    private int playerY = 1; // Posisi awal pemain (y)
    private final JPanel mapPanel;

    public DungeonGame() {
        setTitle("Dungeon Game");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Membuat panel peta
        mapPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                renderMap(g);
            }
        };
        add(mapPanel, BorderLayout.CENTER);

        // Membuat panel kontrol
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(2, 3));

        JButton upButton = new JButton("↑");
        JButton downButton = new JButton("↓");
        JButton leftButton = new JButton("←");
        JButton rightButton = new JButton("→");
        JButton actionButton = new JButton("🔍");

        controlPanel.add(new JLabel());
        controlPanel.add(upButton);
        controlPanel.add(new JLabel());
        controlPanel.add(leftButton);
        controlPanel.add(actionButton);
        controlPanel.add(rightButton);
        controlPanel.add(new JLabel());
        controlPanel.add(downButton);
        controlPanel.add(new JLabel());

        add(controlPanel, BorderLayout.SOUTH);

        // Event listener untuk navigasi tombol
        upButton.addActionListener(e -> movePlayer(0, -1));
        downButton.addActionListener(e -> movePlayer(0, 1));
        leftButton.addActionListener(e -> movePlayer(-1, 0));
        rightButton.addActionListener(e -> movePlayer(1, 0));

        // Muat data peta
        loadMapData();

        setVisible(true);
    }

    private void loadMapData() {
        // Contoh peta sederhana
        String[] mapLines = {
                "WWWWWWWWWW",
                "WP......WW",
                "W.......WW",
                "W.......WW",
                "WWWWWWWWWW"
        };

        mapData = new String[mapLines.length][];
        for (int i = 0; i < mapLines.length; i++) {
            mapData[i] = mapLines[i].split("");
        }
    }

    private void renderMap(Graphics g) {
        // Render peta dalam first-person perspective
        int width = mapData[0].length;
        int height = mapData.length;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (mapData[y][x].equals("W")) {
                    g.setColor(Color.DARK_GRAY);
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                } else if (mapData[y][x].equals("P")) {
                    g.setColor(Color.BLUE);
                    g.fillOval(playerX * TILE_SIZE, playerY * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    g.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
                }
            }
        }
    }

    private void movePlayer(int dx, int dy) {
        int newX = playerX + dx;
        int newY = playerY + dy;

        if (newX >= 0 && newX < mapData[0].length && newY >= 0 && newY < mapData.length) {
            if (!mapData[newY][newX].equals("W")) {
                playerX = newX;
                playerY = newY;

                // Perbarui posisi pemain di peta
                Arrays.stream(mapData).forEach(row -> Arrays.fill(row, "."));
                mapData[playerY][playerX] = "P";

                repaint();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(DungeonGame::new);
    }
}
