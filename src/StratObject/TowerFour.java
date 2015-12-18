package StratObject;

import main.StratUtil;

/**
 * Created by Dude on 12/13/2015.
 */
public class TowerFour {

    private int x;
    private int y;
    private StratUtil su;
    private long lastShot;
    private int checkRadius;

    public TowerFour(int x, int y, StratUtil su){
        this.x = x;
        this.y = y;
        this.su = su;
        checkRadius = 300;
        lastShot = System.currentTimeMillis();
    }

    public void update(){
        if(su.getEnemyList().size()>0){
            if(System.currentTimeMillis()-lastShot>500){
                lastShot = System.currentTimeMillis();
                su.newProjectile("t4",x+30,y+30);
            }
        }
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getCheckRadius(){
        return checkRadius;
    }
}
