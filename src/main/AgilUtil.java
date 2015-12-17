package main;

import AgilObject.*;
import physics.GravBallPhysics;
import physics.PlayerPhysics;

import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Dude on 12/9/2015.
 */
public class AgilUtil {

    private Game game;
    private Player p;

    private ArrayList<GravBallRed> gravballredList;
    private ArrayList<GravBallBlue> gravballblueList;
    private ArrayList<GravBallGreen> gravballgreenList;
    private ArrayList<GravBallYellow> gravballyellowList;
    private ArrayList<GravBallPurple> gravballpurpleList;
    private EntityMaker em;
    private PlayerPhysics pPhyscics;
    private JPanel agilSidePane;
    private long score;
    private long startTime;
    private int lives;
    private JLabel agilScoreL;
    private JLabel agilLivesL;
    private JLabel agilShieldL;
    private long prevLifeAwardScore;
    private long prevSpawnScore;
    private int greenScore;
    private long scoreAddon;
    private long scoreAddonTest;
    private Shield shield;
    private int pOrigSpeed;
    private FileManager fm;

    public AgilUtil(Game game, FileManager fm){
        this.game = game;
        this.p = makePlayer();
        this.fm = fm;

        pOrigSpeed = 5;
        GravBallPhysics gbp = new GravBallPhysics(game, p, this);
        em = new EntityMaker(game, gbp,this);
        pPhyscics = new PlayerPhysics(game, p);
        shield = new Shield(37);
        score = 0;
        greenScore = 0;
        scoreAddon = 0;
        agilSidePane = makeAgilSidePane();
        gravballredList = new ArrayList<>();
        gravballblueList = new ArrayList<>();
        gravballgreenList = new ArrayList<>();
        gravballyellowList = new ArrayList<>();
        gravballpurpleList = new ArrayList<>();
        scoreAddonTest=0;
    }

    public long calcScore() {
        long temp;
        temp = (System.currentTimeMillis()- startTime)/1000;
        temp += greenScore;
        temp += scoreAddon;
        return temp;
    }

    private Player makePlayer(){
        Player p = new Player("player", 200,200,60,60,pOrigSpeed, game, this);
        return p;
    }

    public void updateStats(){
        score = calcScore();
        agilScoreLSetText("Score: "+ score);
        agilLivesLSetText("Extra Lives: "+ lives);
        agilShieldLSetText("Currently Shielded: "+ shield.getEnabled());
    }

    public void collision(String s, String id, Game game) throws IOException {
        if(s.equals("red")){
            if(!shield.getEnabled()){
                if(lives > 0){
                    lives--;
                    p.resetPos();
                    shield.turnOn(1);
                }else{
                    System.out.println(score);
                    if(Integer.valueOf(fm.fetchVal("AgilHS"))<score){
                        try { game.getFileManager().setVal("AgilHS",Integer.toString((int) score)); } catch (IOException e) { e.printStackTrace(); }
                    }
                    game.changeGameState(2);
                }
            }
        } else if(s.equals("green")){//points
            for(Iterator<GravBallGreen> it2 = gravballgreenList.iterator(); it2.hasNext();){
                GravBallGreen temp = it2.next();
                if(temp.getID().equals(id)){
                    temp.setNeedRemoval(true);
                    greenScore+=10;
                }
            }
        }
        else if(s.equals("blue")){//shield
            for(Iterator<GravBallBlue> it2 = gravballblueList.iterator(); it2.hasNext();){
                GravBallBlue temp = it2.next();
                if(temp.getID().equals(id)){
                    temp.setNeedRemoval(true);
                    shield.turnOn(0);
                }
            }
        }
        else if(s.equals("yellow")){//tele
            for(Iterator<GravBallYellow> it2 = gravballyellowList.iterator(); it2.hasNext();){
                GravBallYellow temp = it2.next();
                if(temp.getID().equals(id)){
                    temp.setNeedRemoval(true);
                    p.teleport();
                }
            }
        }
        else if(s.equals("purple")){//addative speed boost
            for(Iterator<GravBallPurple> it2 = gravballpurpleList.iterator(); it2.hasNext();){
                GravBallPurple temp = it2.next();
                if(temp.getID().equals(id)){
                    temp.setNeedRemoval(true);
                    p.incrementSpeed();
                }
            }
        }
    }

    public void update() throws IOException {
        pPhyscics.checkBounds();
        shield.update();
        for(Iterator<GravBallRed> it = gravballredList.iterator(); it.hasNext();) {
            GravBallRed go = it.next();
            go.update();
        }
        for(Iterator<GravBallBlue> it = gravballblueList.iterator(); it.hasNext();) {
            GravBallBlue go = it.next();
            go.update();
            if(go.getNeedRemoval() == true) {
                it.remove();
            }
        }
        for(Iterator<GravBallGreen> it = gravballgreenList.iterator(); it.hasNext();) {
            GravBallGreen go = it.next();
            go.update();
            if(go.getNeedRemoval() == true) {
                it.remove();
            }
        }
        for(Iterator<GravBallYellow> it = gravballyellowList.iterator(); it.hasNext();) {
            GravBallYellow go = it.next();
            go.update();
            if(go.getNeedRemoval() == true) {
                it.remove();
            }
        }
        for(Iterator<GravBallPurple> it = gravballpurpleList.iterator(); it.hasNext();) {
            GravBallPurple go = it.next();
            go.update();
            if(go.getNeedRemoval() == true) {
                it.remove();
            }
        }
        updateStats();
        em.newSpawnManager();
    }

    public Player getPlayer() {
        return p;
    }

    private JPanel makeAgilSidePane(){
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(200,550));
        jp.setLayout(new BorderLayout(0,0));

        JPanel jpTop = new JPanel(new GridLayout(4,1));
        JLabel agilTit = new JLabel("Agil Info Pane");
        agilTit.setHorizontalAlignment(SwingConstants.CENTER);
        agilTit.setFont(agilTit.getFont().deriveFont(24.0f));
        agilScoreL = new JLabel("Score: ");
        agilScoreL.setFont(agilTit.getFont().deriveFont(18.0f));
        agilLivesL = new JLabel("Extra Lives: ");
        agilLivesL.setFont(agilTit.getFont().deriveFont(18.0f));
        agilShieldL = new JLabel("Currently Shielded: ");
        agilShieldL.setFont(agilTit.getFont().deriveFont(14.0f));
        jpTop.add(agilTit);
        jpTop.add(agilScoreL);
        jpTop.add(agilLivesL);
        jpTop.add(agilShieldL);

        JPanel jpCenter = new JPanel(new GridLayout(7,1));
        JLabel controlslabel = new JLabel("Controls: ");
        controlslabel.setFont(controlslabel.getFont().deriveFont(30.0f));
        controlslabel.setHorizontalAlignment(SwingConstants.CENTER);
        jpCenter.add(controlslabel);
        jpCenter.add(new JLabel("W = Move Up"));
        jpCenter.add(new JLabel("A = Move Left"));
        jpCenter.add(new JLabel("S = Move Down"));
        jpCenter.add(new JLabel("D = Move Right"));
        jpCenter.add(new JLabel("P = Pause Training"));
        jpCenter.add(new JLabel("Esc = Abandon Training"));

        jp.add(jpTop, BorderLayout.NORTH);
        jp.add(jpCenter, BorderLayout.SOUTH);

        return jp;
    }

    public void newGame() {
        startTime = System.currentTimeMillis();
        gravballredList = new ArrayList<>();
        gravballblueList = new ArrayList<>();
        gravballgreenList = new ArrayList<>();
        gravballyellowList = new ArrayList<>();
        gravballpurpleList = new ArrayList<>();
        prevLifeAwardScore = 0;
        prevSpawnScore = 0;
        greenScore = 0;
        scoreAddon = 0;
        em.newEntityRandomer();
//                GravBallBlue gbp = new GravBallBlue("p1",getWindowWidth()/2, getWindowHeight()/2, 16,16,0,0,this.gbp, false);
//                gravballblueList.add(gbp);
        p.resetSpeed();
        lives = 1;
        p.startPos();
        shield.turnOn(1);
    }

    public void endPause() {
        scoreAddonTest = scoreAddon;
        startTime = System.currentTimeMillis();
        System.out.println("un-pausing:"+greenScore+","+scoreAddon+","+
                ((System.currentTimeMillis()-startTime)/1000));
    }

    public JPanel getAgilSidePane() {
        return agilSidePane;
    }

    public void startPause() {
        scoreAddon += calcScore()-scoreAddonTest;
        System.out.println("endPause-scoreAddon: "+scoreAddon);
        greenScore = 0;
    }

    public ArrayList<GravBallRed> getRedGravBallList(){ return gravballredList; }
    public ArrayList<GravBallBlue> getBlueGravBallList(){ return gravballblueList; }
    public ArrayList<GravBallGreen> getGreenGravBallList() { return gravballgreenList; }
    public ArrayList<GravBallYellow> getYellowGravBallList() { return gravballyellowList; }
    public ArrayList<GravBallPurple> getPurpleGravBallList() { return gravballpurpleList; }
    public long getScore() {
        return score;
    }
    public void setScore(long l) { score = l; }
    public long getprevLifeAwardScore() { return prevLifeAwardScore; }
    public void setprevLifeAwardScore(long prevLifeAwardScore) { this.prevLifeAwardScore = prevLifeAwardScore; }
    public long getprevSpawnScore() { return prevSpawnScore; }
    public void setprevSpawnScore(long prevSpawnScore) { this.prevSpawnScore = prevSpawnScore; }
    public void incrementLives() { lives++; }
    public int getGreenScore() { return greenScore; }
    public void setGreenScore(int i) { greenScore = i; }
    public int getPOrigSpeed() {
        return pOrigSpeed;
    }
    public long getStartTime() { return startTime; }
    public long getScoreAddon() { return scoreAddon; }
    public int getLives() { return lives; }
    public void setLives(int i) { lives = i; }
    public Shield getShield() { return shield; }
    public void agilScoreLSetText(String s) { agilScoreL.setText(s); }
    public void agilLivesLSetText(String s) { agilLivesL.setText(s); }
    public void agilShieldLSetText(String s) { agilShieldL.setText(s); }
}
