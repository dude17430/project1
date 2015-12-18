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
    private int fireRadius;

    public TowerTwo(int x, int y, StratUtil su, int towerTwoCheckRadius){
        this.x = x;
        this.y = y;
        this.su = su;
        this.fireRadius = towerTwoCheckRadius; //sets the radius of the tower to be checked
        lastShot = System.currentTimeMillis();
    }

    public void update(){
        if(!(su.getEnemyList().isEmpty()) && checkForEnemies()){
            if(System.currentTimeMillis()-lastShot>1000){
                lastShot = System.currentTimeMillis();
                su.newProjectile("t2",x+30,y+30);
            }
        }
    }

    public boolean checkForEnemies(){  //checks to see if enemy is within a towers radius
        if(!(su.getEnemyList().isEmpty())) { //if enemy list is not emepty
            for (int i = 0; i < su.getEnemyList().size(); i++) {
                double distance = su.getEnemyList().get(i).getDistance(x,y);
                if (distance < fireRadius + su.getEnemyList().get(i).getRadius()){//checks if the radius to be checked plus the enemies radius is greater than the absolute distance between the enemy and tower
                    return true;
                }
            }
        }
        return false;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public int getCheckRadius(){
        return fireRadius;
    }
}

