package physics;

import StratObject.Enemy;
import StratObject.TowerProjectile;
import main.StratUtil;

import java.util.ArrayList;

/**
 * Created by Dude on 12/15/2015.
 */
public class StratCalc {
    private StratUtil su;

    public StratCalc(StratUtil su) {
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
                e.setDead(true);
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
            double ex = e.getX();
            double ey = e.getY();
            //DIFFERENCE
            double dx = px-ex; //difference = from-to (current x - enemy x)
            double dy = py-ey; //difference = from-to (current y - enemy y)

            double[] i = {dx,dy};

            return i;

            /*//DISTANCE
            double distance = Math.sqrt( Math.pow(ex-px,2) + Math.pow(ey-py,2) );
            //RESULTANT
            double x = dx/distance;
            double y = dy/distance;*/
        }
        double[] i = {0,0};
        return i;
    }

    /*
    * deleted it :P
    */

}
/*
* KEEP CONSTANT SPEED
* if (!(slopeX + slopY == speed))
* slopX + SlopY > speed
*   SlopX - (speed - (SlopX + SlopeY) /2 )
*   SlopY - (speed - (SlopX + SlopeY) /2 )
* else
*   SlopX + ((SlopX + SlopeY) - speed /2 )
*   SlopY + ((SlopX + SlopeY) - speed /2 )
*   -Devide by 2 is to split the diff such
*       that keeps the ratio of diff between
*       x and y
*/