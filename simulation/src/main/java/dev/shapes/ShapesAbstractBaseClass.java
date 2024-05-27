
abstract class ShapesAbstractBaseClass {


    protected int r;
    protected int g;
    protected int b;

    protected int x;
    protected int y;
    protected int z;

    public ShapesAbstractBaseClass(int r, int g, int b, int x, int y, int z) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public ShapesAbstractBaseClass() {
        this(0, 0, 0, 0, 0, 0);
    }
}
