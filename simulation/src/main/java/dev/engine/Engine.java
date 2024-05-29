package dev.engine;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Engine {

    BufferedImage imgBuffer;



    int width;
    int height;

    public Engine(int width, int height) {
        generateBuffer(width, height, true);
        this.width = width;
        this.height = height;
        fill(0, 0, 0, 0);
    }

    /**
     * Generates a fresh buffer at the specified dimensions with optional
     * transparency support.

     * @param width       - the width of the buffer
     * @param height      - the hight of the buffer
     * @param transparent - whether to support transparency in the image
     */
    public void generateBuffer(int width, int height, boolean transparent) {
        imgBuffer = new BufferedImage(width, height,
                transparent ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
    }
    

    private void setPixel (int x, int y, int r, int g, int b, int a) {
        imgBuffer.setRGB(x, y, new Color(r, g, b, a).getRGB());
    }

    public void fill(int r, int g, int b, int a) {
        for (int x = 0; x < imgBuffer.getWidth(); x++) {
            for (int y = 0; y < imgBuffer.getHeight(); y++) {
                setPixel(x, y, r, g, b, a);
            }
        }
    }

    public BufferedImage getBuffer() {
        return imgBuffer;
    }
}

class Camera {

    private int X;
    private int Y;
    private int Z;
    
    public Camera(int X, int Y, int Z) {
        this.X = X;
        this.Y = Y;
        this.Z = Z;
    }
}
