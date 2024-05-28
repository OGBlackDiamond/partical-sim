package dev.engine;

import java.awt.image.BufferedImage;
import javax.swing.JFrame;

class Engine {

    BufferedImage imgBuffer;

    public Engine() {
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

}
