package StratObject;

import main.StratUtil;
import physics.StratCalc;

/**
 * Created by Dude on 12/13/2015.
 */
public class TowerProjectile {

    private double x;
    private double y;
    private int radius;
    private StratUtil su;
    private StratCalc sc;
    private double speed;
    private boolean dead;

    public TowerProjectile(double x, double y, int radius, String s, StratUtil su, StratCalc sc){
        this.x = x;
        this.y = y;
        this.su = su;
        this.sc = sc;
        speed = 8;
        this.radius = radius;
        dead = false;
    }

    public void update(){
        double[] dir = sc.projectilePather(this);
        x = (dir[0]*speed)+x;
        y = (dir[1]*speed)+y;
        su.getSC().checkPCollision(this);
    }

    public int getRadius() {
        return radius;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setDead(boolean dead) { this.dead = dead; }
    public boolean getDead() { return dead; }
}
