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
    private double lastDirX;
    private double lastDirY;

    public TowerProjectile(double x, double y, int radius, String s, StratUtil su, StratCalc sc){
        this.x = x;
        this.y = y;
        this.su = su;
        this.sc = sc;
        speed = 8;
        this.radius = radius;
        dead = false;
        lastDirX = 0;
        lastDirY = 0;
    }

    public void update(){
        if(!(su.getEnemyList().isEmpty())) {
            double[] dir = sc.projectilePather(this);
            x = (dir[0] * speed) + x;
            y = (dir[1] * speed) + y;
            lastDirX = dir[0];
            lastDirY = dir[1];
        } else {
            x = (lastDirX * speed) + x;
            y = (lastDirY * speed) + y;
            if(x < 0 || y < 0 || x > su.getGame().getWindowWidth() || y > su.getGame().getWindowHeight()){
                dead=true;
            }
        }
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
