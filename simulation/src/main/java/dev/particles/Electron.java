package dev.particles;

import java.awt.Color;

import dev.shapes.Sphere;

public class Electron extends ParticleAbstractBaseClass {

    private double distanceFromAtom;
    private double azimuth;
    private double theta;

    private double atomX;
    private double atomY;
    private double atomZ;

	private double thetaIncriment;
	private double azimuthIncriment;

    public Electron(double atomX, double atomY, double atomZ, double atomR) {
        radius = 10;
        amu = 0.000548;
        electronegativity = 0;
        distanceFromAtom = atomR + 15;

        // temporary, these should be set to some distance
        this.atomX = x = atomX;
        this.atomY = y = atomY;
        this.atomZ = z = atomZ;


        theta = Math.random() * 2 * Math.PI;
        azimuth = Math.random() * 2 * Math.PI;

        thetaIncriment = /*Math.random() */ Math.PI / 32;
        azimuthIncriment = /*Math.random() */ Math.PI / 32;



        // all electrons will be grey
        color = new Color(100, 100, 100, 255);


    }

    public void step() {
        theta += thetaIncriment;
        azimuth += azimuthIncriment;

        theta %= (2 * Math.PI);
        azimuth %= (2 * Math.PI);

        calculateCoords();
        shape.updateCoords(new double[] {x, y, z});
    }

    public void calculateCoords() {
        x = atomX + distanceFromAtom * Math.sin(azimuth) * Math.cos(theta);
        y = atomY + distanceFromAtom * Math.sin(azimuth) * Math.sin(theta);
        z = atomZ + distanceFromAtom * Math.cos(azimuth);

        // System.out.printf("X: %f\nY: %f\nZ: %f\n\n\n", x, y, z);
    }

    public void makeShape() {
        generateSphere();
    }


    public void setDistFromAtom(double distance) {
        distanceFromAtom = distance;
    }
}
