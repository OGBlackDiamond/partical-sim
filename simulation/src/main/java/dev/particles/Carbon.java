package dev.particles;

public class Carbon extends Atom {

    public Carbon() {
        this(0, 0, 0);
    }

    public Carbon(double x, double y, double z) {
        super(x, y, z);
        radius = 35;
        amu = 12.0107;
        electronegativity = 2.55;
        finishConstruction();
    }

}
