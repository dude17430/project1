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

    public StratCalc(ArrayList<Enemy> enemyList, StratUtil su) {//---------------------------
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
        if(!(su.getEnemyList().isEmpty())){ //if enemy list is not emepty
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
        double[] i = {0,0};
        return i;
    }

}