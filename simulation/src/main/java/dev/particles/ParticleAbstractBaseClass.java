package dev.particles;

import java.awt.Color;

import dev.shapes.Sphere;

class ParticleAbstractBaseClass {
    
    protected double radius;
    protected double amu;
    protected double electronegativity;

    protected double x;
    protected double y;
    protected double z;

    // electrons exists in Atom class

    protected Color color;

    protected Sphere shape;

    protected void generateSphere() {
        shape = new Sphere(color.getRed() * 5, color.getGreen() * 5, color.getBlue() * 5, color.getAlpha(), x, y, z, radius);
    }

    // generate color method exists in Atom class

    public double getRadius() {
        return radius;
    }

    public double getAMU() {
        return amu;
    }
    
    public double getEN() {
        return electronegativity;
    }

    public Color getColor() {
        return color;
    }

    public Sphere getShape() {
        return shape;
    }
}
