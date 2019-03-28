public class SmartStrategy implements OrderingStrategy {

    StringRecipe recipe;
    StringBar bar;

    @Override
    public void wants(StringRecipe recipe, StringBar bar) {
        if (bar.isHappyHour())
            bar.order(recipe);
        else {
            this.recipe = recipe;
            this.bar = bar;
        }
    }

    @Override
    public void happyHourStarted(StringBar bar) {
        if (recipe != null){
            bar.order(recipe);
        }
        recipe = null;
    }

    @Override
    public void happyHourEnded(StringBar bar) {

    }
}
