public class StringReplacer implements StringTransformer {

    private StringDrink stringDrink;
    private char before, after;

    StringReplacer(StringDrink stringDrink, char before, char after){
        this.stringDrink = stringDrink;
        this.before = before;
        this.after = after;
    }

    @Override
    public void execute() {
        stringDrink.setText(stringDrink.getText().replace(before, after));
    }

    @Override
    public void undo() {
        stringDrink.setText(stringDrink.getText().replace(after, before));
    }
}
