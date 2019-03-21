import java.util.ArrayList;
import java.util.List;

public class AreaAggregator implements SumProvider {
    private List<HasArea> objs = new ArrayList<>();

    public void addShape(HasArea obj) {
        objs.add(obj);
    }

    public double sum() {
        double sum = 0;
        for (HasArea obj: objs) {
            sum += obj.getArea();
        }
        return sum;
    }
}