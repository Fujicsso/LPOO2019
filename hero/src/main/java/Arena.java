import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Arena {
    private int height;
    private int width;
    private Hero hero;
    private List<Wall> walls;

    public Arena(int height, int width){
        this.height = height;
        this.width = width;
        this.hero = new Hero(10, 10);
        this.walls = createWalls();
    }

    public boolean processKey(KeyStroke key) throws IOException {
        System.out.println(key);
        if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        } else if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            return true;
        } else if (key.getKeyType() == KeyType.EOF){
            return true;
        }
        return false;
    }

    public void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
    }

    public void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
    }

    private boolean canHeroMove(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().getY() == position.getY() && wall.getPosition().getX() == position.getX())
                return false;
        if (position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height)
            return true;
        return false;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }
}
