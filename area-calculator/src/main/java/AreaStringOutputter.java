public class AreaStringOutputter {

    private SumProvider areaProvider;

    AreaStringOutputter(SumProvider provider){
        areaProvider = provider;
    }

    public String output() {
        return "Sum of areas: " + areaProvider.sum();
    }

}
