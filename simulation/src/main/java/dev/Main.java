package dev;

import dev.engine.Window;

import java.awt.image.BufferedImage;

import dev.engine.Engine;
import dev.particles.Carbon;

class Main {

    public static void main(String[] args) {

        System.out.println("Hello Gradle!");

        Window window = new Window(500, 500);

        Engine engine = new Engine(window.getWidth(), window.getHeight());

        engine.addAtom(new Carbon(0, 0, 10));

        window.updateBuffer(engine.getFrame());

        BufferedImage[] video = new BufferedImage[60];

        for (int i = 0; i < 60; i++) {
            engine.stepSim();
            video[i] = engine.getFrame();
            System.out.printf("Frame: %d\n", i + 1);
            /*
             * if (i > 0) {
             * System.out.printf("Same Check %b\n", video[i] == video[i - 1]);
             * }
             */
        }

        window.startWindow();

        long startTime = System.nanoTime();

        int videoFrame = 0;
        while (true) {
            if (System.nanoTime() - startTime >= 75000000) {
                startTime = System.nanoTime();
                window.updateBuffer(video[videoFrame]);
                videoFrame++;
                if (videoFrame >= 60) {
                    videoFrame = 0;
                    window.clearBuffer();
                    window.updateBuffer(video[videoFrame]);

                }
            }
        }
        /*
         * int ticker = 0;
         * while (System.nanoTime() - startTime > 50000000) {
         * if (System.nanoTime() % 5000 == 0) {
         * engine.castRays();
         * window.updateBuffer(engine.getBuffer().getBuffer());
         * }
         * }
         */
    }
}
