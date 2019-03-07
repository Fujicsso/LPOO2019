import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Arena {
    private GameMap gameMap;
    private int height;
    private int width;
    private Hero hero;
    private List<Monster> monsters;
    private List<Wall> walls;
    private List<Coin> coins;

    Arena(GameMap gameMap, Hero hero, int height, int width){
        this.gameMap = gameMap;
        this.height = height;
        this.width = width;
        this.hero = hero;
        this.monsters = createMonsters();
        this.walls = createWalls();
        this.coins = createCoins();
    }

    void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.ArrowDown){
            moveHero(hero.moveDown());
        } else if (key.getKeyType() == KeyType.ArrowUp){
            moveHero(hero.moveUp());
        } else if (key.getKeyType() == KeyType.ArrowLeft){
            moveHero(hero.moveLeft());
        } else if (key.getKeyType() == KeyType.ArrowRight){
            moveHero(hero.moveRight());
        } else if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q'){
            gameMap.leaveGame();
        } else if (key.getKeyType() == KeyType.EOF)
            gameMap.leaveGame();
    }

    void draw(TextGraphics graphics){
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        hero.draw(graphics);
        hero.drawLives(graphics);
        hero.drawScore(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
    }

    private void moveHero(Position position) {
        if (canHeroMove(position))
            hero.setPosition(position);
        moveMonsters();
        retrieveCoin();
        verifyMonsterCollisions();
        enterDoor();
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

    private List<Monster> createMonsters(){
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Monster m = new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1);
            for (int j = 0; j < i; j++){
                if (m.position.equals(monsters.get(j).position)){
                    m.position = hero.position;
                    i--;
                    break;
                }
            }
            if (m.position.equals(hero.position)) {
                i--;
                continue;
            }
            monsters.add(m);
        }
        return monsters;
    }

    private void retrieveCoin() {
        for (Coin coin : coins){
            if (hero.position.equals(coin.position)) {
                coins.remove(coin);
                hero.increaseScore();
                if (coins.size() == 0)
                    openDoors();
                break;
            }
        }
    }

    private void moveMonsters(){
        for (Monster monster : monsters)
            monster.setPosition(monster.move(hero.getPosition()));
    }

    private void verifyMonsterCollisions(){
        for (Monster monster : monsters)
            if (monster.position.equals(hero.position)) {
                hero.takeDamage();
                break;
            }
        if (!hero.isAlive()) {
            System.out.println("Lost game");
            gameMap.leaveGame();
        }
    }

    private void enterDoor(){
        if (hero.getPosition().getX() == 0 || hero.getPosition().getX() == width-1){
            hero.setPosition(new Position(Math.abs(width -2 - hero.getPosition().getX()), hero.getPosition().getY()));
            gameMap.changeArena(hero);
        }
        if (hero.getPosition().getY() == 0 || hero.getPosition().getY() == height-1){
            hero.setPosition(new Position(hero.getPosition().getX(), Math.abs(height -2 - hero.getPosition().getY())));
            gameMap.changeArena(hero);
        }
    }

    private void openDoors(){
        for (int i = 0; i < walls.size(); i++){
            if (walls.get(i).getPosition().getX() == width/2 || walls.get(i).getPosition().getY() == height/2){
                walls.remove(i--);
            }
        }
    }
}
