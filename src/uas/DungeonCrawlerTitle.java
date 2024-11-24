package uas;

import javax.swing.*;
import java.awt.*;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class DungeonCrawlerTitle {
    private JFrame frame;

    public DungeonCrawlerTitle() {

        playMusic("uas/backgroundmusic.wav");
        // Membuat frame utama
        frame = new JFrame("Dungeon Crawler RPG");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.getContentPane().setBackground(Color.BLACK); // Latar belakang hitam

        // Label untuk judul game
        JLabel titleLabel = new JLabel("Fogos");
        titleLabel.setFont(new Font("algerian", Font.BOLD, 50));
        titleLabel.setForeground(Color.WHITE); // Teks warna putih
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        titleLabel.setBounds(150, 100, 500, 100); // Posisi dan ukuran label
        frame.add(titleLabel);

        // Tombol Start
        JButton startButton = new JButton("START");
        startButton.setFont(new Font("Serif", Font.PLAIN, 24));
        startButton.setForeground(Color.WHITE);
        startButton.setBackground(Color.DARK_GRAY);
        startButton.setFocusPainted(false);
        startButton.setBounds(325, 300, 150, 50); // Posisi dan ukuran tombol
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Pindah ke layar utama game
                frame.dispose(); // Menutup layar title
                new DungeonCrawlerGame(); // Membuka layar utama game
            }
        });
        frame.add(startButton);

        // Menampilkan frame
        frame.setVisible(true);
    }

    private void playMusic(String filePath) {
        try {
            File musicFile = new File(filePath);
            if (musicFile.exists()) {
                AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicFile);
                Clip clip = AudioSystem.getClip();
                clip.open(audioStream);
                clip.start();
                clip.loop(Clip.LOOP_CONTINUOUSLY); // Musik diputar terus menerus
            } else {
                System.out.println("File musik tidak ditemukan!");
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new DungeonCrawlerTitle();
    }
}

// Layar utama game (contoh sederhana)
class DungeonCrawlerGame {
    private JFrame frame;

    public DungeonCrawlerGame() {
        // Membuat frame untuk layar utama
        frame = new JFrame("Dungeon Crawler RPG");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(Color.BLACK);

        // Area teks utama
        JTextArea mainTextArea = new JTextArea("selamat datang");
        mainTextArea.setEditable(false);
        mainTextArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
        mainTextArea.setForeground(Color.WHITE);
        mainTextArea.setBackground(Color.BLACK);
        mainTextArea.setBounds(300, 100, 150, 50);
        frame.add(mainTextArea);

        // Menampilkan frame
        frame.setVisible(true);
    }
}

