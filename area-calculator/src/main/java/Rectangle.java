public class Rectangle implements AreaShape {

    int width, height;

    public Rectangle(int w, int h){
        width = w;
        height = h;
    }

    @Override
    public double getArea() {
        return width * height;
    }

    @Override
    public void draw() {
        System.out.println("Rectangle");
    }

}
