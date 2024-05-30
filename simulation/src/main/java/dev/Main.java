package dev;

import dev.engine.Window;
import dev.engine.Engine;


class Main {

    public static void main(String[] args) {

        long startTime = System.nanoTime();

        System.out.println("Hello Gradle!");
        
        Window window = new Window(1000, 1000);

        Engine engine = new Engine(window.getWidth(), window.getHeight(), 1, 1);


        window.updateBuffer(engine.getBuffer().getBuffer());
   
        int ticker = 0;
        while (System.nanoTime() - startTime > 50000000) {
            if (System.nanoTime() % 5000 == 0) {
                engine.getBuffer().fill(ticker, ticker, ticker, 255);
                if (ticker < 255)
                    ticker++;
                window.updateBuffer(engine.getBuffer().getBuffer());
            }
        }
    }
}
