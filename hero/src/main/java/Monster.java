import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Monster extends Element {

    Monster(int x, int y){
        super(x, y);
    }

    @Override
    void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF0000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "M");
    }

    Position move(Position heroPos){
        if (position.getX() < heroPos.getX())
            return moveRight();
        else if (position.getY() < heroPos.getY())
            return moveDown();
        else if (position.getX() > heroPos.getX())
            return moveLeft();
        else if (position.getY() > heroPos.getY())
            return moveUp();
        return position;
    }
}
