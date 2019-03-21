public class Square implements AreaShape {

    private int side;

    public Square(int s){
        side = s;
    }

    public int getSide(){
        return side;
    }

    @Override
    public double getArea() {
        return Math.pow(side, 2);
    }

    @Override
    public void draw() {
        System.out.println("Square");
    }

}
