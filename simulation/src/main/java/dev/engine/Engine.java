package dev.engine;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import dev.particles.Atom;
import dev.particles.Electron;

public class Engine {

    BufferedImage imgBuffer;

    int width;
    int height;

    double FOV;
    double checkRate;

    int renderLimit;

    ArrayList<Atom> atoms;

    Camera cam;
    BuffWrapper buffer;


    public Engine(int width, int height) {
        this.width = width;
        this.height = height;

        FOV = 3000;

        renderLimit = 300;

        checkRate = 0.01;

        this.atoms = new ArrayList<Atom>();

        cam = new Camera(0, 0, -FOV);
        buffer = new BuffWrapper(width, height, -width / 2, height / 2, 0);
    }

    public BufferedImage getFrame() {
        castRays();
        return buffer.getBuffer();
    }


    private void castRays() {
        buffer = new BuffWrapper(width, height, -width / 2, height / 2, 0);
        double[] camPos = cam.getPos();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Vector ray = new Vector(camPos[0], camPos[1], camPos[2], x, y, buffer);

                buffer.setPixel(x, y, 0, 0, 0, 255);

                rayloop:
                while (ray.getCounter() < renderLimit / checkRate) {

                    double[] vectorPos = ray.getPos();

                    for (Atom atom: atoms) {
                        if (atom.getShape().checkInBounds(vectorPos[0], vectorPos[1], vectorPos[2])) {
                            int[] shapeColor = atom.getShape().getRGBA();
                            buffer.setPixel(x, y, shapeColor[0], shapeColor[1], shapeColor[2], shapeColor[3]);
                            break rayloop;
                        }
                        for (Electron electron : atom.getElectrons()) {
                            if (electron.getShape().checkInBounds(vectorPos[0], vectorPos[1], vectorPos[2])) {
                                buffer.setPixel(x, y, 100, 100, 100, 255);
                                break rayloop;
                            }
                        }
                    }

                    ray.incriment();
                }

            }
        }
    }

    public void addAtom(Atom atom) {
        atoms.add(atom);
    }

    public void stepSim() {
        for (Atom atom : atoms) {
            for (Electron electron : atom.getElectrons()) {
                electron.step();
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
            return new double[] { x, y, z };
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

            x = camX;
            y = camY;
            z = camZ;
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

        public double[] getPos() {
            return new double[] { x, y, z };
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

        private int incrimentCounter;

        public Vector(double startX, double startY, double startZ, int buffX, int buffY, BuffWrapper buffer) {
            double[] buffPos = buffer != null ? buffer.getPos() : new double[] { 0, 0, 0 };

            this.startX = startX;
            this.startY = startY;
            this.startZ = startZ;

            incrimentCounter = 0;


            this.incrimentX = (buffPos[0] + buffX + startX) * checkRate;
            this.incrimentY = (buffPos[1] - buffY + startY) * checkRate;
            this.incrimentZ = FOV * checkRate;
        }

        public Vector() {
            this(0, 0, 0, 0, 0, null);
        }

        public double[] getPos() {
            return new double[] { startX, startY, startZ };
        }

        public int getCounter() {
            return incrimentCounter;
        }

        public void incriment() {
            startX += incrimentX;
            startY += incrimentY;
            startZ += incrimentZ;
            incrimentCounter++;
        }
    }
}
