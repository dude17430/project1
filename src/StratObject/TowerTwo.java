package StratObject;

import main.StratUtil;
import physics.StratCalc;
/**
 * Created by Dude on 12/13/2015.
 */
public class TowerTwo {

    private int x;
    private int y;
    private StratUtil su;
    private long lastShot;
    private int checkRadius;


    public TowerTwo(int x, int y, StratUtil su){

        this.x = x;
        this.y = y;
        this.su = su;
        this.checkRadius = 300;
        lastShot = System.currentTimeMillis();
    }

    public void update(){


        if(su.getEnemyList().size()>0 && this.checkForEnemies()){




            if(System.currentTimeMillis()-lastShot>1000){
                lastShot = System.currentTimeMillis();
                su.newProjectile("t2",x+30,y+30);
            }
        }

    }

    public boolean checkForEnemies(){  //checks to see if enemy is within a towers radius

        if(!(su.getEnemyList().isEmpty())) { //if enemy list is not emepty

            for (int i = 0; i < su.getEnemyList().size(); i++) {

                double dx = (su.getEnemyList().get(i).getX()+su.getEnemyList().get(i).getRadius()) - (getX()+getCheckRadius());//dx = (tower_x_coordinate + (tower radius)) - (enemy_x_coordinate + (enemy radius))---------------
                double dy = (su.getEnemyList().get(i).getY()+su.getEnemyList().get(i).getRadius()) - (getY()+getCheckRadius());
                double distance = Math.sqrt((dx*dx)+(dy*dy));

                System.out.println(dx+" , "+dy+" , "+distance);

                if (distance < checkRadius + su.getEnemyList().get(i).getRadius() ){
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

