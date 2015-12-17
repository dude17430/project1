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
        this.x = path.get(0).get(0);
        this.y = path.get(0).get(1);
        speed = strength*2;
        dead = false;
        radius = (5 + (strength*2));


    }

    public void update(){
        speed = strength*2;
        radius = 5 + strength;
        //move
        if(pathLocation + speed > (path.size()-2)){dead = true;} //Checks if the enemies next position in the path is greater then the position of the last availabe coordinates in the path

        else { //If more coordinates are avilable in the path, the enemy adds the speed to the path location, reads coodinates from path at that position, and sets its coordinates to the ones provided in the path
            pathLocation += speed;
            x = path.get(pathLocation).get(0);
            y = path.get(pathLocation).get(1);

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
    public boolean getDead() { return dead; }
    public void setDead(Boolean b) { dead = b; }
    public int getRadius() { return radius; }
}
