public class Ellipse implements AreaShape {

    int x_radius, y_radius;

    Ellipse(int x, int y){
        x_radius = x;
        y_radius = y;
    }

    @Override
    public double getArea() {
        return Math.PI * y_radius * x_radius;
    }

    @Override
    public void draw() {
        System.out.println("Ellipse");
    }

}
