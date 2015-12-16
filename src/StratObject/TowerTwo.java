package StratObject;

import main.StratUtil;

/**
 * Created by Dude on 12/13/2015.
 */
public class TowerTwo {

    private int x;
    private int y;
    private StratUtil su;
    private long lastShot;

    public TowerTwo(int x, int y, StratUtil su){
        this.x = x;
        this.y = y;
        this.su = su;
        lastShot = System.currentTimeMillis();
    }

    public void update(){
        if(System.currentTimeMillis()-lastShot>500){
            su.newProjectile("t2",x,y);
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
