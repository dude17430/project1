package physics;

import StratObject.Enemy;
import StratObject.TowerProjectile;
import main.StratUtil;

import StratObject.*; // This imports all the objects into class




import java.util.ArrayList;

/**
 * Created by Dude on 12/15/2015.
 */
public class StratCalc {
    private StratUtil su;

    public StratCalc( StratUtil su) {//---------------------------
        this.su = su;
    }

    public void checkPCollision(TowerProjectile tp) {
        ArrayList<Enemy> elist = su.getEnemyList();
        for (Enemy e: elist){
            int temp1 = tp.getRadius();
            int temp2 = tp.getRadius();
            double dx = (e.getX()+e.getRadius()) - (tp.getX()+temp1);
            double dy = (e.getY()+e.getRadius()) - (tp.getY()+temp2);
            double distance = Math.sqrt((dx*dx)+(dy*dy));
            if (distance < e.getRadius() + e.getRadius()){
                e.collided();//strength-- OR kill
                tp.setDead(true);
                System.out.println("projectile collided with enemy!");
            }
        }
    }

    public double[] projectilePather(TowerProjectile tp){


        //Find the absolute closest enemy

//        Enemy e = su.getEnemyList().get(0);
//        double dx = (e.getX() - towerX);//gets the x distance between the tower and enemy
//        double dy = (e.getY() - towerY);//gets the y distance between the tower and enemy
//        double distance = Math.sqrt((dx*dx)+(dy*dy)); //gets the absolute distance between the tower and enemy
//        double newDistance;
//
//        if(su.getEnemyList().size()>1) {
//            for (int i = 1; i < su.getEnemyList().size(); i++) {
//                dx = (su.getEnemyList().get(i).getX() - towerX);//gets the x distance between the tower and the next enemy
//                dy = (su.getEnemyList().get(i).getY() - towerY);//gets the y distance between the tower and the next enemy
//                newDistance = Math.sqrt((dx * dx) + (dy * dy)); //gets the absolute distance between the tower the next and enemy
//                if (newDistance < distance) {// if the old distance
//                    e= su.getEnemyList().get(i);
//                }
//            }
//        }
//System.out.println(distance);

        //Find closest enemy that's farthest in the path



        Enemy e = su.getEnemyList().get(0); //get first enemy






        //FROM
        double px = tp.getX();
        double py = tp.getY();
        //TO
        double ex = e.getX()+e.getRadius();
        double ey = e.getY()+e.getRadius();
        //DIFFERENCE
        double dx = ex-px; //difference = to-from (enemy x - current x)
        double dy = ey-py; //difference = to-from (enemy y - current y)
        //DISTANCE
        double dis = Math.sqrt(Math.pow(dx,2)+Math.pow(dy,2));
        //DIRECTIONALITY
        double dirX = dx/dis;
        double diry = dy/dis;
        //returning shit
        double[] i = {dirX,diry};
        return i;
    }

    public boolean checkMouseOverT1(TowerOne go){
        int towerX = go.getX();
        int towerY = go.getY();
        int mouseX = su.getGame().getMouseX();
        int mouseY = su.getGame().getMouseY();
        if(mouseX>towerX && mouseY>towerY && mouseX<towerX+60 && mouseY<towerY+60){
            return true;
        }
        return false;
    }
    public boolean checkMouseOverT2(TowerTwo go){
        int towerX = go.getX();
        int towerY = go.getY();
        int mouseX = su.getGame().getMouseX();
        int mouseY = su.getGame().getMouseY();
        if(mouseX>towerX && mouseY>towerY && mouseX<towerX+60 && mouseY<towerY+60){
            return true;
        }
        return false;
    }
    public boolean checkMouseOverT3(TowerThree go){
        int towerX = go.getX();
        int towerY = go.getY();
        int mouseX = su.getGame().getMouseX();
        int mouseY = su.getGame().getMouseY();
        if(mouseX>towerX && mouseY>towerY && mouseX<towerX+60 && mouseY<towerY+60){
            return true;
        }
        return false;
    }
    public boolean checkMouseOverT4(TowerFour go){
        int towerX = go.getX();
        int towerY = go.getY();
        int mouseX = su.getGame().getMouseX();
        int mouseY = su.getGame().getMouseY();
        if(mouseX>towerX && mouseY>towerY && mouseX<towerX+60 && mouseY<towerY+60){
            return true;
        }
        return false;
    }

}