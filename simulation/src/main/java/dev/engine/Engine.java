package dev.engine;

import java.awt.Color;
import java.awt.image.BufferedImage;

public class Engine {

    BufferedImage imgBuffer;

    int width;
    int height;

    double FOV;
    double checkRate;

    Camera cam;
    BuffWrapper buffer;

    public Engine(int width, int height, double FOV, double checkRate) {
        this.width = width;
        this.height = height;
        this.FOV = FOV;
        this.checkRate = checkRate;
        cam = new Camera(0, 0, 0);
        buffer = new BuffWrapper(width, height, width / 2, height / 2, FOV);

    }

    private void castRays() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                double[] camPos = cam.getPos();
                Vector ray = new Vector(camPos[0], camPos[1], camPos[2], x, y);



            }
        }
    }

    public BuffWrapper getBuffer() {
        return buffer;
    }

    class Camera {

        private double x;
        private double y;
        private double z;

        public Camera(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        public double[] getPos() {
            return new double[] {x, y, z};
        }
    }

    public class BuffWrapper {

        private BufferedImage buffer;

        private double x;
        private double y;
        private double z;

        public BuffWrapper(int width, int height, double camX, double camY, double camZ) {
            generateBuffer(width, height, true);
            fill(0, 0, 0, 0);
        }

        /**
         * Generates a fresh buffer at the specified dimensions with optional
         * transparency support.
         * 
         * @param width       - the width of the buffer
         * @param height      - the hight of the buffer
         * @param transparent - whether to support transparency in the image
         */
        public void generateBuffer(int width, int height, boolean transparent) {
            imgBuffer = new BufferedImage(width, height,
                    transparent ? BufferedImage.TYPE_INT_ARGB : BufferedImage.TYPE_INT_RGB);
        }

        private void setPixel(int x, int y, int r, int g, int b, int a) {
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

    class Vector {

        private double startX;
        private double startY;
        private double startZ;

        private double incrimentX;
        private double incrimentY;
        private double incrimentZ;

        public Vector(double startX, double startY, double startZ, int buffX, int buffY) { 
            this.startX = startX;
            this.startY = startY;
            this.startZ = startZ;

            this.incrimentX = startX - (width / 2) + buffX;
            this.incrimentY = startY - (height / 2) + buffY;
            this.incrimentZ = FOV / checkRate;
        }

        public Vector() {
            this(0, 0, 0, 0, 0);
        }

        public double[] getPos() {
            return new double[] { startX, startY, startZ };
        }

        public void incriment() {
            startX += incrimentX;
            startY += incrimentY;
            startZ += incrimentZ;
        }
    }
}
