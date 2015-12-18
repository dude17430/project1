package graphics;

import StratObject.*;
import input.KeyManager;
import main.AgilUtil;
import main.FileManager;
import main.Game;
import AgilObject.*;
import main.StratUtil;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dude on 12/3/2015.
 */
public class GraphicsManager extends JComponent {

    private final FileManager fm;
    private final StratUtil su;
    private final Game game;
    private Player p;
    private KeyManager km;

    private Image mmBGIMG;
    private Image playerIMG;
    private Image bgIMG;
    private Image arrowLeft;
    private Image arrowRight;
    private Image gravballred;
    private Image gravballblue;
    private Image gravballgreen;
    private Image gravballyellow;
    private Image gravballpurple;
    private Shield shield;
    private AgilUtil au;
    private Image map1;
    private Image map1Thumb;
    private Image map2;
    private Image map2Thumb;
    private Image t1;
    private Image t2;
    private Image t3;
    private Image t4;

    public GraphicsManager(Game game, Player p, Shield shield, AgilUtil au, FileManager fm, StratUtil su) throws IOException {
        this.game = game;
        this.p = p;
        this.km=game.getKeyManager();
        this.shield = shield;
        this.au = au;
        this.su = su;
        this.fm = fm;

        loadImages();
    }

    @Override
    public Dimension getPreferredSize() {
        Dimension d = new Dimension(game.getWindowWidth(), game.getWindowHeight());
        return d;
    }

    private void loadImages() throws IOException {
        mmBGIMG = ImageIO.read(getClass().getClassLoader().getResource("other/bg2.jpg"));
        playerIMG = ImageIO.read(getClass().getClassLoader().getResource("agil/player.png"));
        bgIMG = ImageIO.read(getClass().getClassLoader().getResource("agil/BG-Game2.jpg"));
        arrowLeft = ImageIO.read(getClass().getClassLoader().getResource("other/arrowLeft.png"));
        arrowRight = ImageIO.read(getClass().getClassLoader().getResource("other/arrowRight.png"));
        gravballred = ImageIO.read(getClass().getClassLoader().getResource("agil/grav-ball-red.png"));
        gravballblue = ImageIO.read(getClass().getClassLoader().getResource("agil/grav-ball-blue.png"));
        gravballgreen = ImageIO.read(getClass().getClassLoader().getResource("agil/grav-ball-green.png"));
        gravballyellow = ImageIO.read(getClass().getClassLoader().getResource("agil/grav-ball-yellow.png"));
        gravballpurple = ImageIO.read(getClass().getClassLoader().getResource("agil/grav-ball-purple.png"));
        //
        map1 = ImageIO.read(getClass().getClassLoader().getResource("strat/map1_perty.jpg"));
        map1Thumb = ImageIO.read(getClass().getClassLoader().getResource("strat/map1_thumb.jpg"));
        map2 = ImageIO.read(getClass().getClassLoader().getResource("strat/map2.jpg"));
        map2Thumb = ImageIO.read(getClass().getClassLoader().getResource("strat/map2_thumb.jpg"));
        t1 = ImageIO.read(getClass().getClassLoader().getResource("strat/t1.png"));
        t2 = ImageIO.read(getClass().getClassLoader().getResource("strat/t2.png"));
        t3 = ImageIO.read(getClass().getClassLoader().getResource("strat/t3.png"));
        t4 = ImageIO.read(getClass().getClassLoader().getResource("strat/t4.png"));

    }

    public void drawUpdate() {
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        //clear screen
        g.clearRect(0,0, game.getWindowWidth(), game.getWindowHeight());
        switch (game.getGameState()) {
            //----General----
            case 0:// MM
                drawMainMenu(g);
                break;
            case 100: //about
                drawAbout(g);
                break;
            //----Agil----
            case 1: //agil
                drawAgilGame(g);
                break;
            case 2://deaded
                drawAgilGame(g);
                drawAgilRestart(g);
                break;
            case 3://paused
                drawAgilGame(g);
                drawAgilPausedScreen(g);
                break;
            case 4://pregame
                drawAgilGame(g);
                try { drawAgilPreGame(g); } catch (FileNotFoundException e) { e.printStackTrace(); }
                break;
            //----Strat----
            case 10:
                drawStratGame(g);
                try { drawStratPreGame(g); } catch (FileNotFoundException e) { e.printStackTrace(); }
                break;
            case 11:
                drawStratSetupMenu(g);
                break;
            case 12:
                drawStratGame(g);
                drawStratMouseTower(g);
                drawStratTowerRadius(g);
                break;
        }
    }

    //---------------General
    private void drawAbout(Graphics g) {
        drawAgilGame(g);
        g.setColor(new Color(0.75f,0.75f,0.75f,0.75f));
        g.fillRect(200,168,400,220);
        g.setColor(Color.blue);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("About Project:",295,200);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("This Project is a continuation and expansion of",210,230);
        g.drawString("a final project from a school final project.",210,260);
        g.drawString("The goal of this project is to create a game that",210,290);
        g.drawString("simulates a space training program. where each",210,320);
        g.drawString("min-game is a training exercise.",210,350);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 16));
        g.drawString("('esc' to go back)",350,380);
    }

    private void drawMainMenu(Graphics g){
        //background
        g.setColor(Color.black);
        g.fillRect(0,0,game.getWindowWidth(),game.getWindowHeight());

        drawMainMenuBGTiler(g);
        drawArrows(g);

        g.setColor(Color.gray); //BG for text contrast
        g.fillRect(300,160,266,220);

        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("Main Menu:", 350,200);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));

        if(km.getMainMenuEntry() == 0){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.BLUE);
        }
        g.drawString("Reflex/Agility Exercise", 316,230); //dodge the things

        if(km.getMainMenuEntry() == 1){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.BLUE);
        }
        g.drawString("Strategy Exercise", 340,260);//TD

        if(km.getMainMenuEntry() == 2){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.BLUE);
        }
        g.drawString("Memory Exercise", 340,290);//

        if(km.getMainMenuEntry() == 3){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.BLUE);
        }
        g.drawString("About", 400,320);//TD

        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("(Space to Select)", 368,370);
    }

    private void drawArrows(Graphics g) {
        if(game.getKeyManager().getMainMenuEntry() == 0){
            g.drawImage(arrowLeft,250,205,null);
            g.drawImage(arrowRight,582,205,null);
        }
        if(game.getKeyManager().getMainMenuEntry() == 1){
            g.drawImage(arrowLeft,250,235,null);
            g.drawImage(arrowRight,582,235,null);
        }
        if(game.getKeyManager().getMainMenuEntry() == 2){
            g.drawImage(arrowLeft,250,265,null);
            g.drawImage(arrowRight,582,265,null);
        }
        if(game.getKeyManager().getMainMenuEntry() == 3){
            g.drawImage(arrowLeft,250,295,null);
            g.drawImage(arrowRight,582,295,null);
        }
    }

    private void drawMainMenuBGTiler(Graphics g) {
        int x = 0;
        int y = 0;
        while (y < game.getWindowHeight()){
            while (x < game.getWindowWidth()){
                g.drawImage(mmBGIMG,x,y,null);
                x += 64;
            }
            x = 0;
            y += 64;
        }

    }
    //---------------Agil
    private void drawAgilPreGame(Graphics g) throws FileNotFoundException {
        drawAgilGame(g);
        Color bgOverlayColor = new Color(0.25f,0.25f,0.75f,0.75f);
        g.setColor(bgOverlayColor);
        g.fillRect(0,0,game.getWindowWidth(), game.getWindowHeight());

        Color fontColor = new Color(1.0f, 0.8039216f, 0.4392157f);
        g.setColor(fontColor);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("Pre-Game Info:",300,50);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Exercise Description:",20,100);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("the reflex/agility exercise is how it sounds, a ",20,130);
        g.drawString("test of your reaction time, and maneuver-ability.",20,160);
        g.drawString("The goal is to last as long as possible. To do this",20,190);
        g.drawString("you must dodge the red GravBalls that we will be",20,220);
        g.drawString("adding into the room, one ever 20 seconds.",20,250);
        g.drawString("Then evr 60 seconds you will be granted an extra",20,280);
        g.drawString("life. There are also blue shield GravBalls, that",20,310);
        g.drawString("grant 10s shield, and yellow GravBalls that random",20,340);
        g.drawString("teleport, and green GravBalls that give +10 score.",20,370);
        g.drawString("The last type is purple which give you a permanent",20,400);
        g.drawString("speed boost.",20,430);

        g.fillRect(500,70,5,460);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("HighScore:",580,200);
        g.drawString(fm.fetchVal("AgilHS"),650,270);

        g.drawString("(press 'spacebar' to start)",270,590);
    }

    private void drawAgilPausedScreen(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect((game.getWindowWidth()/2)-66,(game.getWindowHeight()/2)-46,165,64);

        g.setColor(Color.BLUE);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("PAUSED",(game.getWindowWidth()/2)-50,game.getWindowHeight()/2);
    }

    private void drawAgilRestart(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(299, 184, 230,115);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.setColor(Color.blue);
        g.drawString("Score: "+au.getScore(),350,230);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        if(km.getAgilRestartMenuEntry() == 0){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.BLUE);
        }
        g.drawString("Restart", 310,265); //dodge the things

        if(km.getAgilRestartMenuEntry() == 1){
            g.setColor(Color.red);
        }else {
            g.setColor(Color.BLUE);
        }
        g.drawString("Main Menu", 400,265); //dodge the things

    }

    private void drawAgilGame(Graphics g){
        //background
        g.drawImage(bgIMG, 0, 0, null);
        //objects
        g.drawImage(playerIMG, p.getX(), p.getY(), null);
        if(shield.getEnabled()){
            g.setColor(Color.BLUE);
            g.drawArc(p.getX()-2,p.getY()-2,p.getWidth()+4,p.getHeight()+4,0,360);
        }
        for (GravBallRed go : au.getRedGravBallList()) {
            g.drawImage(gravballred,go.getX(),go.getY(),null);
        }
        for (GravBallBlue go : au.getBlueGravBallList()) {
            g.drawImage(gravballblue,go.getX(),go.getY(),null);
        }
        for (GravBallGreen go : au.getGreenGravBallList()) {
            g.drawImage(gravballgreen,go.getX(),go.getY(),null);
        }
        for (GravBallYellow go : au.getYellowGravBallList()) {
            g.drawImage(gravballyellow,go.getX(),go.getY(),null);
        }
        for (GravBallPurple go : au.getPurpleGravBallList()) {
            g.drawImage(gravballpurple,go.getX(),go.getY(),null);
        }
    }
    //---------------Strat
    private void drawStratPreGame(Graphics g) throws FileNotFoundException {
        drawStratGame(g);
        Color bgOverlayColor = new Color(0.25f,0.25f,0.75f,0.75f);
        g.setColor(bgOverlayColor);
        g.fillRect(0,0,game.getWindowWidth(), game.getWindowHeight());

        Color fontColor = new Color(1.0f, 0.8039216f, 0.4392157f);
        g.setColor(fontColor);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("Pre-Game Info:",300,50);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 24));
        g.drawString("Exercise Description:",20,100);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.drawString("the strategy exercise is exactly it sounds, an ",20,130);
        g.drawString("exercise to test your strategic abilites in order",20,160);
        g.drawString("to make sure you are able to keep a clear head",20,190);
        g.drawString("and remain able to make desisions under pressure",20,220);
        g.drawString("and stress.",20,250); //add game details
        g.drawString("",20,280);
        g.drawString("",20,310);
        g.drawString("",20,340);
        g.drawString("",20,370);
        g.drawString("",20,400);
        g.drawString("",20,430);

        g.fillRect(500,70,5,460);

        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawString("Highest Round:",550,200);
        g.drawString("Map1: "+fm.fetchVal("StratMap1HS"),520,270);
        g.drawString("Map2: "+fm.fetchVal("StratMap2HS"),520,300);

        g.drawString("(press 'spacebar' to start)",270,590);
    }

    private void drawStratSetupMenu(Graphics g) {
        int centerx = game.getWindowWidth()/2;
        int centery = game.getWindowHeight()/2;
        drawMainMenuBGTiler(g);
        g.setColor(new Color(0.80f,0.80f,0.80f,0.85f));
        g.fillRect(0,centery-96-10,game.getWindowWidth(),192+20);
        g.setColor(Color.red);
        g.fillRect(centerx-128-10,centery-96-10,256+20,192+20);
        int[] pos1 = {centerx-128,centery-96};//center
        int[] pos2 = {centerx+128+20,centery-96};//right1
        int[] pos3 = {centerx-128-20-256,centery-96};//left1
        if(km.getStratmapselected() == 1){
            g.drawImage(map1Thumb,pos1[0],pos1[1],null);
            g.drawImage(map2Thumb,pos2[0],pos2[1],null);
        }
        if(km.getStratmapselected() == 2){
            g.drawImage(map1Thumb,pos3[0],pos3[1],null);
            g.drawImage(map2Thumb,pos1[0],pos1[1],null);
        }
        g.setColor(new Color(0.5f,0.5f,0.5f,0.75f));
        g.fillRect(game.getWindowWidth()-32-20,0,32+20,game.getWindowHeight());
        g.setColor(new Color(0.5f,0.5f,0.5f,0.75f));
        g.fillRect(0,0,32+20,game.getWindowHeight());
        g.setColor(Color.black);
        g.setFont(new Font("TimesRoman", Font.PLAIN, 32));
        g.drawImage(arrowRight,10,284,null);
        g.drawString("A",15,350);
        g.drawImage(arrowLeft,758,284,null);
        g.drawString("D",762,350);
        //
        g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
        g.setColor(new Color(0.5f,0.5f,0.5f));
        g.fillRect(centerx-100,centery*2-25,95*2+34,28);
        g.setColor(Color.black);
        g.drawString("(press 'spacebar' to select)",centerx-95,centery*2-8);
    }

    private void drawStratMouseTower(Graphics g) {
        if(km.getToweronmouse() != null){
            g.setColor(new Color(0.1764706f, 1.0f, 0.02745098f, 0.50f));
            int x = game.getMouseX()-30;
            int y = game.getMouseY()-30;
            switch(km.getToweronmouse()){
                case "t1":
                    g.drawImage(t1, x, y, null);
                    g.fillArc((x+30)-100,(y+30)-100,100*2,100*2,0,360);
                    break;
                case "t2":
                    g.drawImage(t2, x, y, null);
                    g.fillArc((x+30)-300,(y+30)-300,300*2,300*2,0,360);
                    break;
                case "t3":
                    g.drawImage(t3, x, y, null);
                    g.fillArc((x+30)-100,(y+30)-100,100*2,100*2,0,360);
                    break;
                case "t4":
                    g.drawImage(t4, x, y, null);
                    g.fillArc((x+30)-300,(y+30)-300,300*2,300*2,0,360);
                    break;
            }
        }
    }

    private void drawStratGame(Graphics g) {
        //map
        switch (km.getStratmapselected()){
            case 1:
                g.drawImage(map1,0,0,null);
                break;
            case 2:
                g.drawImage(map2,0,0,null);
                break;
        }
        for (TowerOne go : su.getToweroneList()){
            g.drawImage(t1,go.getX(),go.getY(),null);
        }
        for (TowerTwo go : su.getTowertwoList()){
            g.drawImage(t2,go.getX(),go.getY(),null);
        }
        for (TowerThree go : su.getTowerthreeList()){
            g.drawImage(t3,go.getX(), go.getY(),null);
        }
        for (TowerFour go : su.getTowerfourList()){
            g.drawImage(t4,go.getX(),go.getY(),null);
        }
        for (Enemy go: su.getEnemyList()){
            g.setColor(Color.red);
            if(go.getStrength() == 2){
                g.setColor(Color.blue);
            }
            if(go.getStrength() == 3){
                g.setColor(Color.green);
            }
            if(go.getStrength() == 4){
                g.setColor(Color.yellow);
            }
            if(go.getStrength() == 5){
                g.setColor(Color.pink);
            }
            g.fillArc(go.getX(),go.getY(),(go.getRadius()*2),(go.getRadius()*2),0,360);
        }
        for (TowerProjectile go: su.getProjectileList()){
            g.setColor(Color.YELLOW);
            g.fillArc((int) go.getX(),(int) go.getY(),(go.getRadius()*2),(go.getRadius()*2),0,360);
        }

        //mouse towers last (so on  top)
    }

    private void drawStratTowerRadius(Graphics g) {
        for (TowerOne go : su.getToweroneList()){
            if(su.getSC().checkMouseOverT1(go)){
                g.setColor(new Color(0.1764706f, 1.0f, 0.02745098f, 0.50f));
                g.fillArc((go.getX()+30)-go.getCheckRadius(),
                        (go.getY()+30)-go.getCheckRadius(),
                        go.getCheckRadius()*2,
                        go.getCheckRadius()*2,
                        0,360);
            }
        }
        for (TowerTwo go : su.getTowertwoList()){
            if(su.getSC().checkMouseOverT2(go)){
                g.setColor(new Color(0.1764706f, 1.0f, 0.02745098f, 0.50f));
                g.fillArc((go.getX()+30)-go.getCheckRadius(),
                        (go.getY()+30)-go.getCheckRadius(),
                        go.getCheckRadius()*2,
                        go.getCheckRadius()*2,
                        0,360);
            }
        }
        for (TowerThree go : su.getTowerthreeList()){
            if(su.getSC().checkMouseOverT3(go)){
                g.setColor(new Color(0.1764706f, 1.0f, 0.02745098f, 0.50f));
                g.fillArc((go.getX()+30)-go.getCheckRadius(),
                        (go.getY()+30)-go.getCheckRadius(),
                        go.getCheckRadius()*2,
                        go.getCheckRadius()*2,
                        0,360);
            }
        }
        for (TowerFour go : su.getTowerfourList()){
            if(su.getSC().checkMouseOverT4(go)){
                g.setColor(new Color(0.1764706f, 1.0f, 0.02745098f, 0.50f));
                g.fillArc((go.getX()+30)-go.getCheckRadius(),
                        (go.getY()+30)-go.getCheckRadius(),
                        go.getCheckRadius()*2,
                        go.getCheckRadius()*2,
                        0,360);
            }
        }
    }
}
