public class AreaXMLOutputter {

    private SumProvider areaProvider;

    AreaXMLOutputter(SumProvider provider){
        areaProvider = provider;
    }

    public String output() {
        return "<area>" + areaProvider.sum() + "</area>";
    }

}
