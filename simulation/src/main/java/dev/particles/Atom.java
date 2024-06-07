package dev.particles;

import java.awt.Color;

public class Atom extends ParticleAbstractBaseClass {

    protected Electron[] electrons;

    protected Atom(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    protected void generateColor() {
        color = new Color((int) electronegativity % 255, (int) radius % 255,  (int) amu % 255, 255);
    }

    protected void finishConstruction() {
        electrons = new Electron[(int) amu / 2];

        generateColor();
        generateSphere();
        configureElectrons();

    }

    protected void configureElectrons() {
        
        // handles setting electrons to different engery levels
        for (int i = 0; i < electrons.length; i++) {
            electrons[i] = new Electron(x, y, z, radius);
            if (10 > i && i >= 2) {
                electrons[i].setDistFromAtom(30 + radius);        
            } else {
                electrons[i].setDistFromAtom(45 + radius);
            }

            electrons[i].calculateCoords();
            electrons[i].generateSphere();
        }
    }

    public Electron[] getElectrons() {
        return electrons;
    }

}
