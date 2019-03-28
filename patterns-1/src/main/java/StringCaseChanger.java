public class StringCaseChanger implements StringTransformer {

    private StringDrink stringDrink;

    StringCaseChanger(StringDrink stringDrink){
        this.stringDrink = stringDrink;
    }

    @Override
    public void execute() {
        String text = stringDrink.getText();
        String ans = "";
        for (int i = 0; i < text.length(); i++){
            if (Character.isUpperCase(text.charAt(i)))
                ans += Character.toLowerCase(text.charAt(i));
            else
                ans += Character.toUpperCase(text.charAt(i));
        }
        stringDrink.setText(ans);
    }

    @Override
    public void undo() {
        execute();
    }
}
