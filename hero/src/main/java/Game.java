import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {

    private boolean playing;
    private Screen screen;
    private Arena arena;

    public Game(){
        try {
            playing = true;
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            arena = new Arena(this, screen.getTerminalSize().getRows(), screen.getTerminalSize().getColumns());

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void draw() throws IOException{
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run(){
        while (playing) {
            try {
                draw();
                KeyStroke key = screen.readInput();
                arena.processKey(key);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void exitGame(){
        try {
            screen.close();
            playing = false;
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
