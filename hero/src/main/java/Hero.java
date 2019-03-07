import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends Element{

    private int lives;
    private int score;

    public Hero(int x, int y){
        super(x, y);
        lives = 3;
        score = 0;
    }

    public void draw(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFF33"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "X");
    }

    public void drawLives(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition( graphics.getSize().getColumns() - 7, 2), lives + " <3");
    }

    public void drawScore(TextGraphics graphics){
        graphics.setForegroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition( 5, 2), score + "");
    }

    void takeDamage(){
        lives--;
    }

    boolean isAlive(){
        return lives >= 1;
    }

    void increaseScore(){
        score++;
    }
}
