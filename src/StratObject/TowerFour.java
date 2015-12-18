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

    public TowerFour(int x, int y, StratUtil su, int towerFourCheckRadius){
        this.x = x;
        this.y = y;
        this.su = su;
        this.checkRadius = towerFourCheckRadius;
        lastShot = System.currentTimeMillis();
    }

    public void update(){
        if(su.getEnemyList().size()>0 && checkForEnemies()){
            if(System.currentTimeMillis()-lastShot>500){
                lastShot = System.currentTimeMillis();
                su.newProjectile("t4",x+30,y+30);
            }
        }
    }

    public boolean checkForEnemies(){  //checks to see if enemy is within a towers radius
        if(!(su.getEnemyList().isEmpty())) { //if enemy list is not emepty
            for (int i = 0; i < su.getEnemyList().size(); i++) {
                double dx = (su.getEnemyList().get(i).getX() - getX());//gets the x distance between the tower and enemy
                double dy = (su.getEnemyList().get(i).getY() - getY());//gets the y distance between the tower and enemy
                double distance = Math.sqrt((dx*dx)+(dy*dy)); //gets the absolute distance between the tower and enemy
                if (distance < checkRadius + su.getEnemyList().get(i).getRadius()){//checks if the radius to be checked plus the enemies radius is greater than the absolute distance between the enemy and tower
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
        return checkRadius;
    }
}
