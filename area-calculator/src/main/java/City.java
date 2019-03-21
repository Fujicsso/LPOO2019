import java.util.List;

public class City implements SumProvider {
    private List<House> houses;

    public City(List<House> housesList){
        houses = housesList;
    }

    @Override
    public double sum() {
        double sum = 0;
        for (House h: houses) {
            sum += h.getArea();
        }
        return sum;
    }
}
