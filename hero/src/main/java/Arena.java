import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int height;
    private int width;
    private Hero hero;
    private Monster monster;
    private List<Wall> walls;
    private List<Coin> coins;

    public Arena(int height, int width){
        this.height = height;
        this.width = width;
        this.hero = new Hero(10, 10);
        this.monster = new Monster(25, 15);
        this.walls = createWalls();
        this.coins = createCoins();
    }

    boolean processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowDown){
            return moveHero(hero.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowUp){
            return moveHero(hero.moveUp());
        } else if (key.getKeyType() == KeyType.ArrowLeft){
            return moveHero(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight){
            return moveHero(hero.moveRight());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            return true;
        }
        return key.getKeyType() == KeyType.EOF;
    }

    void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        hero.draw(graphics);
        monster.draw(graphics);
    }

    private boolean moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
        moveMonsters();
        retrieveCoin();
        return verifyMonsterCollisions();
    }



    private boolean canHeroMove(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height;
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

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Coin c = new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            for (int j = 0; j < i; j++){
                if (c.position.equals(coins.get(j).position)){
                    c.position = hero.position;
                    i--;
                    break;
                }
            }
            if (c.position.equals(hero.position)) {
                i--;
                continue;
            }
            coins.add(c);
        }
        return coins;
    }

    private void retrieveCoin() {
        for (Coin coin : coins){
            if (hero.position.equals(coin.position)) {
                coins.remove(coin);
                break;
            }
        }
    }

    private void moveMonsters(){
        monster.setPosition(monster.move(hero.getPosition()));
    }

    private boolean verifyMonsterCollisions(){
        boolean res = monster.position.equals(hero.position);
        if (res)
            System.out.println("Lost game");
        return res;
    }
}
