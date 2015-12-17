package StratObject;

import main.StratUtil;
import physics.StratCalc;

/**
 * Created by Dude on 12/13/2015.
 */
public class TowerOne {

    private int x;
    private int y;
    private StratUtil su;
    private long lastShot;

    public TowerOne(int x, int y, StratUtil su){
        this.x = x;
        this.y = y;
        this.su = su;
        lastShot = System.currentTimeMillis();
    }

    public void update(){
        if(su.getEnemyList().size()>0){
            if(System.currentTimeMillis()-lastShot>2000){
                lastShot = System.currentTimeMillis();
                su.newProjectile("t1",x+30,y+30);
            }
        }

    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
