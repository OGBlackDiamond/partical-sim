package dev.shapes;

import java.lang.Math;

public class Sphere extends ShapesAbstractBaseClass {

    private double radius;

    public Sphere() {
        this(100, 100, 100, 100, 0, 0, 25, 25);
    }

    public Sphere(int r, int g, int b, int a, double x, double y, double z, double radius) {
        super(r, g, b, a, x, y, z); 
        this.radius = radius;
    }

    @Override
    public boolean checkInBounds(double x, double y, double z) {
        return Math.pow(x - this.x, 2) + Math.pow(y - this.y, 2) + Math.pow(z - this.z, 2) <= Math.pow(radius, 2);
    }

    public void updateCoords(double[] coords) {
        x = coords[0];
        y = coords[1];
        z = coords[2];
    }
}
