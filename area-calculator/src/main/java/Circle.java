public class Circle implements AreaShape {

    private int radius;

    public Circle(int r){
        radius = r;
    }

    public int getRadius(){
        return radius;
    }

    @Override
    public double getArea() {
        return Math.PI * Math.pow(radius, 2);
    }

    @Override
    public void draw() {
        System.out.println("Circle");
    }
}
