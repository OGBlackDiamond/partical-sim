package dev.engine;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.image.BufferedImage;
import java.awt.BorderLayout;
import java.awt.Graphics2D;

public class Window {

    private JFrame window;
    private JLabel panel;

    private int width;
    private int height;

    public Window(int width, int height) {
        window = new JFrame("Particle Simulation =D");
        window.setSize(width, height);
        this.width = width;
        this.height = height;
        window.setVisible(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        panel = new JLabel();
    }

    public void startWindow() {
        window.setVisible(true);
    }

    public void updateBuffer(BufferedImage image) {
        panel.setIcon(new ImageIcon(image));
        window.getContentPane().add(panel, BorderLayout.CENTER);
        window.pack();
    }

    public void clearBuffer() {
        window.getContentPane().removeAll();
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
