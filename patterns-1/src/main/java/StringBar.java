import java.util.ArrayList;
import java.util.List;

public class StringBar extends Bar {
    public StringBar(List<BarObserver> observers) {
        super(observers);
    }

    public StringBar() {

    }

    public StringRecipe getRecipe(StringDrink drink) {
        StringInverter si = new StringInverter(drink);
        StringCaseChanger cc = new StringCaseChanger(drink);
        StringReplacer sr = new StringReplacer(drink, 'A', 'X');

        List<StringTransformer> transformers = new ArrayList<>();
        transformers.add(si);
        transformers.add(cc);
        transformers.add(sr);

        StringRecipe recipe = new StringRecipe(transformers);
        return recipe;
    }

    public void order(StringRecipe recipe){
        recipe.mix();
    }
}
