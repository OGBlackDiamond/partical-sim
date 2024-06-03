package dev.shapes;

public abstract class ShapesAbstractBaseClass {

    // define color values for the shape
    protected int r;
    protected int g;
    protected int b;

    // the 3d x, y, z values of the object
    protected double x;
    protected double y;
    protected double z;

    // optional transparency value
    protected int a;

    /**
     * Constructs a new shape with color values and a transparency value.
     * 
     * @param r - red color value
     * @param g - green color value
     * @param b - blue color value
     * @param x - the x position
     * @param y - the y position
     * @param z - the z position
     * @param a - the transparency value
     */
    public ShapesAbstractBaseClass(int r, int g, int b, int a, double x, double y, double z) {
        this.r = r;
        this.g = g;
        this.b = b;

        this.x = x;
        this.y = y;
        this.z = z;

        this.a = a;
    }

    /**
     * Constructs a new shape with color values and transparency defaulted to
     * opaque.
     * 
     * @param r - red color value
     * @param g - green color value
     * @param b - blue color value
     * @param x - the x position
     * @param y - the y position
     * @param z - the z position
     */
    public ShapesAbstractBaseClass(int r, int g, int b, double x, double y, double z) {
        this(r, g, b, 255, x, y, z);
    }

    /**
     * Constructs a black shape at 0, 0, 0, fully opaque.
     */
    public ShapesAbstractBaseClass() {
        this(0, 0, 0, 0, 0, 255);
    }

    protected int[] getRGB() {
        return new int[] { r, g, b };
    }

    public int[] getRGBA() {
        return new int[] { r, g, b, a };
    }

    public abstract boolean checkInBounds(double x, double y, double z);
}
