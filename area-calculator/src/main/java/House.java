public class House implements HasArea {

    double area;

    public House(double a){
        area = a;
    }

    @Override
    public double getArea() {
        return area;
    }
}
