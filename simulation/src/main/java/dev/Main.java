package dev;

import dev.engine.Window;
import dev.shapes.Sphere;
import dev.engine.Engine;


class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        System.out.println("Hello Gradle!");
        
        Window window = new Window(1000, 1000);

        Engine engine = new Engine(window.getWidth(), window.getHeight());

        engine.addShape(new Sphere());
        engine.addShape(new Sphere(255, 100, 100, 200, 70, 70, 90, 70));


        window.updateBuffer(engine.getBuffer().getBuffer());
   
        int ticker = 0;
        while (System.nanoTime() - startTime > 50000000) {
            if (System.nanoTime() % 5000 == 0) {
                engine.castRays();
                window.updateBuffer(engine.getBuffer().getBuffer());
            }
        }
    }
}
