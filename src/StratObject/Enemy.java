package StratObject;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.org.apache.xpath.internal.operations.Bool;
import physics.MapFunction;

import java.util.ArrayList;
/**
 * Created by Dude on 12/15/2015.
 */
public class Enemy {

    private int x;
    private int y;
    private int radius;
    private int strength;//tells how to render, judges speed
    private int speed;
    private boolean dead;
    private ArrayList<ArrayList<Integer>> path;
    private int pathLocation;


    public Enemy(ArrayList<ArrayList<Integer>> path, int strength){
        this.strength = strength;
        this.path = path;
        radius = ((strength*10)); // sets the enemies radius
        this.x = path.get(0).get(0)-radius;
        this.y = path.get(0).get(1)-radius;
        speed = strength*2;
        dead = false;



    }
//    public void update() {
//        x = 200;
//        y = 200;
//    }

    public void update(){
        radius = ((strength*5));
        speed = strength*2;
        //move
        if(pathLocation + speed > (path.size()-2)){dead = true;} //Checks if the enemies next position in the path is greater then the position of the last availabe coordinates in the path

        else { //If more coordinates are avilable in the path, the enemy adds the speed to the path location, reads coodinates from path at that position, and sets its coordinates to the ones provided in the path
            pathLocation += speed;
            x = path.get(pathLocation).get(0)-radius;
            y = path.get(pathLocation).get(1)-radius;

        }
    }

    public void collided(){
        if(strength>1){
            strength--;
        } else {
            dead = true;
        }
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public boolean getDead() { return dead; }//so can be removed in game.update()
    public void setDead(Boolean b) { dead = b; }//insta-kill.... for use l8r
    public int getRadius() { return radius; }//collision/graphics
    public int getStrength() { return strength; }//for graphics
}
