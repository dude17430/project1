import main.Game;
import physics.MapFunction;

import java.io.IOException;

/**
 * Created by Dude on 12/1/2015.
 */
public class Launcher {

    public static void main(String[] args) throws IOException {
        new Launcher();
    }

    public  Launcher() throws IOException {
        Game game = new Game();//pass perams from launcher if needed here
        game.launch();
    }

}
