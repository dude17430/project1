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
    private int speed;
    private long lastmoved;

    public TowerProjectile(double x, double y, int radius, String s, StratUtil su, StratCalc sc){
        this.x = x;
        this.y = y;
        this.su = su;
        this.sc = sc;
        speed = 2;
        this.radius = radius;
        lastmoved = System.currentTimeMillis();
    }

    public void update(){
        //TODO:movement
        if(System.currentTimeMillis() - lastmoved >1000){
            lastmoved = System.currentTimeMillis();
            double[] slope = sc.projectilePather(this);
            if(slope[0]+slope[1] != speed){
                if(slope[0]+slope[1] > speed){
                    double diff = (slope[0]+slope[1])-speed;
                    slope[0] -= diff/2;
                    slope[1] -= diff/2;
                }
                else if (slope[0]+slope[1] < speed){
                    double diff = speed-(slope[0]+slope[1]);
                    slope[0] += diff/2;
                    slope[1] += diff/2;
                }
            }
            x += slope[0];
            y -= slope[1];
        }
//        su.getSC().checkPCollision(this);
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
}
//(where at-going to)