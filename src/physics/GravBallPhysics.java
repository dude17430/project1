package physics;

import main.AgilUtil;
import main.Game;
import AgilObject.*;

import java.io.FileNotFoundException;

/**
 * Created by Dude on 12/4/2015.
 */
public class GravBallPhysics {

    private final AgilUtil au;
    private Game _game;
    private Player p;

    public GravBallPhysics(Game game, Player p, AgilUtil au){
        this._game = game;
        this.p = p;
        this.au = au;
    }

    public void checkBounds(GravBallRed go){
        if(go.getX()<0){
            go.invertSpeedX();
        }
        if(go.getY()+go.getHeight()>600){
            go.invertSpeedY();
        }
        if(go.getX()+go.getWidth() > _game.getWindowWidth()){
            go.invertSpeedX();
        }
        if(go.getY()<0){
            go.invertSpeedY();
        }
    }

    public void checkPcollided(GravBallRed gb) throws FileNotFoundException {
        int temp1 = gb.getWidth()/2;
        int temp2 = gb.getHeight()/2;
        double dx = (p.getX()+p.getWidth()/2) - (gb.getX()+temp1);
        double dy = (p.getY()+p.getHeight()/2) - (gb.getY()+temp2);
        double distance = Math.sqrt((dx*dx)+(dy*dy));
        if (distance < p.getWidth()/2 + gb.getWidth()/2){
            au.collision("red", gb.getID(), _game);
//            System.out.println("collided: "+gb.getID());
        }
    }

    public void checkBounds(GravBallBlue gb) {
        if(gb.getX()<0){
            gb.invertSpeedX();
        }
        if(gb.getY()+gb.getHeight()>600){
            gb.invertSpeedY();
        }
        if(gb.getX()+gb.getWidth() > _game.getWindowWidth()){
            gb.invertSpeedX();
        }
        if(gb.getY()<0){
            gb.invertSpeedY();
        }
    }

    public void checkPcollided(GravBallBlue gb) throws FileNotFoundException {
        int temp1 = gb.getWidth()/2;
        int temp2 = gb.getHeight()/2;
        double dx = (p.getX()+p.getWidth()/2) - (gb.getX()+temp1);
        double dy = (p.getY()+p.getHeight()/2) - (gb.getY()+temp2);
        double distance = Math.sqrt((dx*dx)+(dy*dy));
        if (distance < p.getWidth()/2 + gb.getWidth()/2){
            au.collision("blue", gb.getID(), _game);
//            System.out.println("collided: "+gb.getID());
        }
    }

    public void checkBounds(GravBallGreen gb) {
        if(gb.getX()<0){
            gb.invertSpeedX();
        }
        if(gb.getY()+gb.getHeight()>600){
            gb.invertSpeedY();
        }
        if(gb.getX()+gb.getWidth() > _game.getWindowWidth()){
            gb.invertSpeedX();
        }
        if(gb.getY()<0){
            gb.invertSpeedY();
        }

    }

    public void checkPcollided(GravBallGreen gb) throws FileNotFoundException {
        int temp1 = gb.getWidth()/2;
        int temp2 = gb.getHeight()/2;
        double dx = (p.getX()+p.getWidth()/2) - (gb.getX()+temp1);
        double dy = (p.getY()+p.getHeight()/2) - (gb.getY()+temp2);
        double distance = Math.sqrt((dx*dx)+(dy*dy));
        if (distance < p.getWidth()/2 + gb.getWidth()/2){
            au.collision("green", gb.getID(), _game);
//            System.out.println("collided: "+gb.getID());
        }
    }

    public void checkBounds(GravBallYellow gb) {
        if(gb.getX()<0){
            gb.invertSpeedX();
        }
        if(gb.getY()+gb.getHeight()>600){
            gb.invertSpeedY();
        }
        if(gb.getX()+gb.getWidth() > _game.getWindowWidth()){
            gb.invertSpeedX();
        }
        if(gb.getY()<0){
            gb.invertSpeedY();
        }
    }

    public void checkPcollided(GravBallYellow gb) throws FileNotFoundException {
        int temp1 = gb.getWidth()/2;
        int temp2 = gb.getHeight()/2;
        double dx = (p.getX()+p.getWidth()/2) - (gb.getX()+temp1);
        double dy = (p.getY()+p.getHeight()/2) - (gb.getY()+temp2);
        double distance = Math.sqrt((dx*dx)+(dy*dy));
        if (distance < p.getWidth()/2 + gb.getWidth()/2){
            au.collision("yellow", gb.getID(), _game);
//            System.out.println("collided: "+gb.getID());
        }
    }

    public void checkBounds(GravBallPurple gb) {
        if(gb.getX()<0){
            gb.invertSpeedX();
        }
        if(gb.getY()+gb.getHeight()>600){
            gb.invertSpeedY();
        }
        if(gb.getX()+gb.getWidth() > _game.getWindowWidth()){
            gb.invertSpeedX();
        }
        if(gb.getY()<0){
            gb.invertSpeedY();
        }
    }

    public void checkPcollided(GravBallPurple gb) throws FileNotFoundException {
        int temp1 = gb.getWidth()/2;
        int temp2 = gb.getHeight()/2;
        double dx = (p.getX()+p.getWidth()/2) - (gb.getX()+temp1);
        double dy = (p.getY()+p.getHeight()/2) - (gb.getY()+temp2);
        double distance = Math.sqrt((dx*dx)+(dy*dy));
        if (distance < p.getWidth()/2 + gb.getWidth()/2){
            au.collision("purple", gb.getID(), _game);
//            System.out.println("collided: "+gb.getID());
        }
    }
}
