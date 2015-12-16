package AgilObject;

import main.AgilUtil;
import main.Game;
import physics.GravBallPhysics;

/**
 * Created by Dude on 12/6/2015.
 */
public class EntityMaker {

    private Game game;
    private int numrgb;
    private int numbgb;
    private int numggb;
    private int numygb;
    private GravBallPhysics gbp;
    private int numpgb;
    private AgilUtil au;

    public EntityMaker(Game game, GravBallPhysics gbp, AgilUtil au){
        this.game = game;
        this.gbp = gbp;
        this.au = au;
        numrgb = 0;
        numbgb = 0;
        numggb = 0;
        numygb = 0;
        numpgb = 0;
    }

    public void newSpawnManager() {
        if(au.getScore() == (au.getprevLifeAwardScore()+60)){
            au.setprevLifeAwardScore(au.getScore());
            au.incrementLives();
        }
        if(au.getScore() == (au.getprevSpawnScore()+20)){
            au.setprevSpawnScore(au.getScore());
            newEntityRandomer();
        }
    }

    public void newEntityRandomer(){
        double r = game.randomNum(100,1);
//        System.out.println("random gen num: "+r);
        newEntityRandomSpeed("red");
        if(r >= 1 && r <= 30){
            newEntityRandomSpeed("green");
        } if ( r >= 25 && r <=45 ){
            newEntityRandomSpeed("blue");
        } if ( r >= 40 && r <=60 ){
            newEntityRandomSpeed("yelllow");
        } if ( r >= 55 && r <=75 ){
            newEntityRandomSpeed("purple");
        }
    }

    private void newEntityRandomSpeed(String s) {
        int tempsx = 0;
        int tempsy = 0;
        while (tempsx == tempsy){
            tempsx = game.randomNum(5,0);
            tempsy = game.randomNum(5,0);
        }
        spawnNewEntity(tempsx,tempsy, s);
    }

    private void spawnNewEntity(double tempsx, double tempsy, String s) {
//        System.out.println("spawning: "+s+" GravBall x/y speeds: "+ tempsx +"/"+tempsy);
        int x = ((int) tempsx);
        int y = ((int) tempsy);
        switch (s){
            case "red":
                numrgb++;
                au.getRedGravBallList().add(new GravBallRed("red"+numrgb,game.getWindowWidth()/2, game.getWindowHeight()/2, 16,16,x,y,gbp, false));
                break;
            case "blue":
                numbgb++;
                au.getBlueGravBallList().add(new GravBallBlue("blue"+numbgb,game.getWindowWidth()/2, game.getWindowHeight()/2, 16,16,x,y,gbp, false));
                break;
            case "green":
                numggb++;
                au.getGreenGravBallList().add(new GravBallGreen("green"+numggb,game.getWindowWidth()/2, game.getWindowHeight()/2, 16,16,x,y,gbp, false));
                break;
            case "yellow":
                numygb++;
                au.getYellowGravBallList().add(new GravBallYellow("yellow"+numygb,game.getWindowWidth()/2, game.getWindowHeight()/2, 16,16,x,y,gbp, false));
                break;
            case "purple":
                numpgb++;
                au.getPurpleGravBallList().add(new GravBallPurple("purple"+numpgb,game.getWindowWidth()/2, game.getWindowHeight()/2, 16,16,x,y,gbp, false));
                break;
        }
    }

}
