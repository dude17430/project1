package StratObject;

import com.sun.org.apache.xpath.internal.operations.Bool;

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

    public Enemy(int x, int y, int strength){
        this.strength = strength;
        this.x = x;
        this.y = y;
        speed = strength*2;
        dead = false;
        radius = 5 + strength;
    }

    public void update(){
        speed = strength*2;
        radius = 5 + strength;
        //move
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
