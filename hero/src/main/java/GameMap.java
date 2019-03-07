public class GameMap {

    Game game;
    private int height;
    private int width;
    Arena currentArena;
    Hero hero;

    GameMap(Game game, int height, int width){
        this.game = game;
        this.height = height;
        this.width = width;
        this.hero = new Hero(10, 10);
        this.currentArena = new Arena(this, hero, height, width);
    }

    void changeArena(Hero hero){
        this.hero = hero;
        this.currentArena = new Arena(this, hero, height, width);
    }


    void leaveGame(){
        game.exitGame();
    }

}
