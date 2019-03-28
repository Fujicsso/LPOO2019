public class HumanClient implements Client {

    private OrderingStrategy orderingStrategy;

    HumanClient(OrderingStrategy orderingStrategy){
        this.orderingStrategy = orderingStrategy;
    }

    @Override
    public void wants(StringRecipe recipe, StringBar bar) {
        orderingStrategy.wants(recipe, bar);
    }

    @Override
    public void happyHourStarted(Bar bar) {
        orderingStrategy.happyHourStarted((StringBar) bar);
    }

    @Override
    public void happyHourEnded(Bar bar) {

    }
}
