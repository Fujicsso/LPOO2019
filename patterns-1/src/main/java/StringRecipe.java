import java.util.List;

public class StringRecipe {

    List<StringTransformer> transformers;

    StringRecipe(List<StringTransformer> transformers){
        this.transformers = transformers;
    }

    public void mix(){
        for (StringTransformer st : transformers){
            st.execute();
        }
    }

    public void undo(){
        for (int i = transformers.size()-1; i >= 0; i--)
            transformers.get(i).undo();
    }
}
