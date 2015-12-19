package main;

import StratObject.*;
import physics.MapFunction;
import physics.StratCalc;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Dude on 12/13/2015.
 */
public class StratUtil {

    private Game game;
    private JPanel stratSidePane;
    private JLabel stratMoneyL;
    private JLabel stratRoundL;
    private ArrayList<TowerProjectile> projectilesList;
    private ArrayList<TowerOne> toweroneList;
    private ArrayList<TowerTwo> towertwoList;
    private ArrayList<TowerThree> towerthreeList;
    private ArrayList<TowerFour> towerfourList;
    private ArrayList<Enemy> enemyList;
    private StratCalc sc;
    private int money;
    private int round;
    private MapFunction mp;
    private int TowerOneCheckRadius;
    private int TowerTwoCheckRadius;
    private int TowerThreeCheckRadius;
    private int TowerFourCheckRadius;
    private RoundManager rm;

    public StratUtil(Game game){
        this.game = game;
        rm = new RoundManager(this);
        projectilesList = new ArrayList();
        toweroneList = new ArrayList();
        towertwoList = new ArrayList();
        towerthreeList = new ArrayList();
        towerfourList = new ArrayList();
        enemyList = new ArrayList<>();
        sc = new StratCalc(this);

        TowerOneCheckRadius = 100;
        TowerTwoCheckRadius = 300;
        TowerThreeCheckRadius = 100;
        TowerFourCheckRadius = 300;

        stratSidePane = makeStrategySidePane();

    }

    public void updateStats(){
        stratMoneyL.setText("Money: "+money);
        stratRoundL.setText("Round: "+round);
    }

    private JPanel makeStrategySidePane(){
        JPanel jp = new JPanel();
        jp.setPreferredSize(new Dimension(200,550));
        jp.setLayout(new GridLayout(3,1));
        //1-top(stats),2-mid(towers),3-bottom(controls)
        JPanel jpTop = new JPanel();
        jpTop.setLayout(new BoxLayout(jpTop, BoxLayout.Y_AXIS));
        JPanel jpMid = new JPanel(new GridLayout(1,2));
        JPanel jpMidLeft = new JPanel();
        JPanel jpMidRight = new JPanel();
        jpMidLeft.setLayout(new BoxLayout(jpMidLeft, BoxLayout.Y_AXIS));
        jpMidRight.setLayout(new BoxLayout(jpMidRight, BoxLayout.Y_AXIS));
        JPanel jpBot = new JPanel(new FlowLayout());

        stratMoneyL = new JLabel("Earnings: 0");
        stratRoundL = new JLabel("Round: 0");
        stratMoneyL.setAlignmentX(Component.CENTER_ALIGNMENT);
        stratRoundL.setAlignmentX(Component.CENTER_ALIGNMENT);
        jpTop.add(stratMoneyL);
        jpTop.add(stratRoundL);

        JLabel T1L = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("strat/t1.png")));
        JLabel T2L = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("strat/t2.png")));
        JLabel T3L = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("strat/t3.png")));
        JLabel T4L = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("strat/t4.png")));

        T1L.setAlignmentX(Component.CENTER_ALIGNMENT);
        T2L.setAlignmentX(Component.CENTER_ALIGNMENT);
        T3L.setAlignmentX(Component.CENTER_ALIGNMENT);
        T4L.setAlignmentX(Component.CENTER_ALIGNMENT);

        T1L.setToolTipText("<html> Tower1 <br> Hotkey: 1 </html>");
        T2L.setToolTipText("<html> Tower2 <br> Hotkey: 2 </html>");
        T3L.setToolTipText("<html> Tower3 <br> Hotkey: 3 </html>");
        T4L.setToolTipText("<html> Tower4 <br> Hotkey: 4 </html>");

        jpMidLeft.add(T1L);
        jpMidRight.add(T2L);
        jpMidLeft.add(T3L);
        jpMidRight.add(T4L);

        jpMid.add(jpMidLeft);
        jpMid.add(jpMidRight);

        jp.add(jpTop);
        jp.add(jpMid);
        jp.add(jpBot);
        return jp;
    }

    public JPanel getStratSidePane() { return stratSidePane; }

    public void newGame() {
        money = 0;
        round = rm.resetRound();
        projectilesList = new ArrayList();
        toweroneList = new ArrayList();
        towertwoList = new ArrayList();
        towerthreeList = new ArrayList();
        towerfourList = new ArrayList();
        enemyList = new ArrayList();

    }

    public void update() {
        round = rm.update();
        for(Iterator<TowerOne> it = toweroneList.iterator(); it.hasNext();){
            TowerOne temp = it.next();
            temp.update();
        }
        for(Iterator<TowerTwo> it = towertwoList.iterator(); it.hasNext();){
            TowerTwo temp = it.next();
            temp.update();
        }
        for(Iterator<TowerThree> it = towerthreeList.iterator(); it.hasNext();){
            TowerThree temp = it.next();
            temp.update();
        }
        for(Iterator<TowerFour> it = towerfourList.iterator(); it.hasNext();){
            TowerFour temp = it.next();
            temp.update();
        }
        for(Iterator<TowerProjectile> it = projectilesList.iterator(); it.hasNext();){
            TowerProjectile temp = it.next();
            temp.update();
            if(temp.getDead()){
                it.remove();
            }
        }
        for(Iterator<Enemy> it = enemyList.iterator(); it.hasNext();){
            Enemy e = it.next();
            e.update();
            if(e.getDead()){
                it.remove();
            }
        }
//        -------------add enemies here----------------

//        if (testEnemy){
//            enemyList.add(new Enemy(mp.getPath(), 4));
//            testEnemy = false;
//        }
        updateStats();//side pane
    }

    public void newProjectile(String s, int x, int y) {
        int r = 3;
        switch (s){
            case "t1":
                projectilesList.add(new TowerProjectile(x,y,r,s,this,sc));
                break;
            case "t2":
                projectilesList.add(new TowerProjectile(x,y,r,s,this,sc));
                break;
            case "t3":
                projectilesList.add(new TowerProjectile(x,y,r,s,this,sc));
                break;
            case "t4":
                projectilesList.add(new TowerProjectile(x,y,r,s,this,sc));
                break;
        }
    }

    public void newTower(String s) {
        switch (s){
            case "t1":
                toweroneList.add(new TowerOne(game.getMouseX()-30,game.getMouseY()-30,this,TowerOneCheckRadius));
                break;
            case "t2":
                towertwoList.add(new TowerTwo(game.getMouseX()-30,game.getMouseY()-30,this,TowerTwoCheckRadius));
                break;
            case "t3":
                towerthreeList.add(new TowerThree(game.getMouseX()-30,game.getMouseY()-30,this,TowerThreeCheckRadius));
                break;
            case "t4":
                towerfourList.add(new TowerFour(game.getMouseX()-30,game.getMouseY()-30,this,TowerFourCheckRadius));
                break;
        }
        game.getKeyManager().clearTowerOnMouse();
    }

    public void spawnNewEnemy(int strength){
        enemyList.add(new Enemy(mp.getPath(), strength)); //adds a new enemy to the board with the path <path> and strength (int)
    }

    public ArrayList<TowerOne> getToweroneList() { return toweroneList; }
    public ArrayList<TowerTwo> getTowertwoList() { return towertwoList; }
    public ArrayList<TowerThree> getTowerthreeList() { return towerthreeList; }
    public ArrayList<TowerFour> getTowerfourList() { return towerfourList; }
    public int getTowerOneCheckRadius() { return TowerOneCheckRadius; }
    public int getTowerTwoCheckRadius() { return TowerTwoCheckRadius; }
    public int getTowerThreeCheckRadius() { return TowerThreeCheckRadius; }
    public int getTowerFourCheckRadius() { return TowerFourCheckRadius; }
    public ArrayList<Enemy> getEnemyList() { return enemyList; }
    public ArrayList<TowerProjectile> getProjectileList() { return projectilesList; }
    public StratCalc getSC() { return sc; }
    public void setMap(int i) throws IOException { mp= new MapFunction(i); }
    public Game getGame(){ return game; }
}
