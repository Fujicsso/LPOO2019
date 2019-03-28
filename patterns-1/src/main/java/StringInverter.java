public class StringInverter implements StringTransformer {

    private StringDrink stringDrink;

    StringInverter(StringDrink stringDrink){
        this.stringDrink = stringDrink;
    }

    @Override
    public void execute() {
        String text = stringDrink.getText();
        String ans = "";
        for (int i = text.length()-1; i >= 0; i--){
            ans += text.charAt(i);
        }
        stringDrink.setText(ans);
    }

    @Override
    public void undo() {
        execute();
    }
}
