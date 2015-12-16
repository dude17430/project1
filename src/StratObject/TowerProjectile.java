package StratObject;

import main.StratUtil;

/**
 * Created by Dude on 12/13/2015.
 */
public class TowerProjectile {

    private int x;
    private int y;
    private int radius;
    private StratUtil su;

    public TowerProjectile(int x, int y, int radius, String s, StratUtil su){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.su = su;
    }

    public void update(){
        //movement
        su.getSC().checkPCollision(this);
    }

    public int getRadius() {
        return radius;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
//(where at-going to)