public class Line implements Shape{

    int length;

    public Line(int l){
        length = l;
    }

    @Override
    public void draw() {
        System.out.println("Line");
    }
}
