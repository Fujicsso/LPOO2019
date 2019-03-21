public class Triangle implements AreaShape {

    int base, height;

    public Triangle(int b, int h){
        base = b;
        height = h;
    }

    @Override
    public double getArea() {
        return height * base / 2.0;
    }

    @Override
    public void draw() {
        System.out.println("Triangle");
    }
}
