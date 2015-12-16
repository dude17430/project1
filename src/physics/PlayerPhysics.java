package physics;

import main.Game;
import AgilObject.Player;

/**
 * Created by Dude on 12/4/2015.
 */
public class PlayerPhysics {

    private Game game;
    private Player p;

    public  PlayerPhysics(Game game, Player p){
        this.game = game;
        this.p = p;
    }

    public void checkBounds(){
        if(p.getX()<0){
            p.moveRight();
        }
        if(p.getY()+p.getHeight()>600){
            p.moveUp();
        }
        if(p.getX()+p.getWidth() > game.getWindowWidth()){
            p.moveLeft();
        }
        if(p.getY()<0){
            p.moveDown();
        }
    }
}
