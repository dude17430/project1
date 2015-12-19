package StratObject;

import main.StratUtil;

import java.util.ArrayList;

/**
 * Created by dude1_000 on 12/18/2015.
 */
public class RoundManager {

    private StratUtil su;
    private int round;
    private long lastSpawnTime;
    private long spawnLimiter;
    private ArrayList<Integer> spawnArray;
    private boolean moveOn;

    public RoundManager(StratUtil su){
        this.su = su;
        round = 1;
        spawnLimiter = 5000;
        lastSpawnTime = System.currentTimeMillis();
        spawnArray = new ArrayList<Integer>();
        calcNewRound();
        moveOn = false;
    }

    public int update(){
        spawnLimiter = 500;
        if (System.currentTimeMillis()-lastSpawnTime>spawnLimiter){
            lastSpawnTime = System.currentTimeMillis();
            if(!spawnArray.isEmpty()){
                su.spawnNewEnemy(spawnArray.get(0));
                spawnArray.remove(0);
            } else {
                if(su.getEnemyList().size()  == 0 && moveOn == true){
                    moveOn = false;
                    round++;
                    calcNewRound();
                }
            }
        }
        return round;
    }

    public int resetRound(){
        round = 1;
        return round;
    }

    public void calcNewRound(){
        for(int i = 0; i<round*4; i++){
            if(round > 1) {
                double j = ((round/3)+1);
                System.out.println("j: "+j);
                System.out.println("calc: "+((round/3)+1));
                spawnArray.add((int) j);
            } else { spawnArray.add(1); }
        }
    }

    public void nextRound() { moveOn = true; }
}
