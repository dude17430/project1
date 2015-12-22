package main;

import graphics.GraphicsManager;
import input.KeyManager;
import input.MouseManager;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.Random;

/**
 * Created by Dude on 12/3/2015.
 */
public class Game {

    private final MouseManager mouseManager;
    private JFrame frame;
    private FileManager fm;
    private KeyManager keyManager;
    private GraphicsManager graphicsManager;
    private boolean gameLoop;
    private int windowWidth;
    private int windowHeight;
    private int gameState;
    private AgilUtil au;
    private StratUtil su;


    public Game() throws IOException {
        fm = new FileManager();
        au = new AgilUtil(this,fm);
        su = new StratUtil(this);
        frame = new JFrame("Advance Space Training Program v0.1.6");
        keyManager = new KeyManager(this, au.getPlayer());
        mouseManager = new MouseManager(this, su);
        graphicsManager = new GraphicsManager(this, au.getPlayer(), au.getShield(),au,fm,su);
        gameLoop = true;
        windowWidth = 800;
        windowHeight = 600;
        gameState = 0;
    }

    public void launch() throws IOException {
        createWindow();
        loop();
    }

    private void createWindow() {
//        frame.setResizable(false);
        frame.setLayout(new FlowLayout(FlowLayout.LEADING,0,0));
        frame.addKeyListener(keyManager);
        frame.addMouseListener(mouseManager);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(graphicsManager);
        frame.pack();
    }

    private void loop() throws IOException {
        long lastSuccess = System.currentTimeMillis();
        int i = 0;
        while(gameLoop){
            keyManager.manage();
            if(gameState == 1){
                au.update();
            }
            if(gameState == 12){
                su.update();
            }
            graphicsManager.drawUpdate();
            //update limiter at end
            long lastCheck = System.currentTimeMillis();
            while(lastCheck-lastSuccess<20){
                lastCheck = System.currentTimeMillis();
            }
            lastSuccess = System.currentTimeMillis();
        }
    }


    public int randomNum(int max,int min){
        Random rand = new Random();
        return rand.nextInt((max-min)+1)+min;
    }

    public void changeGameState(int i) throws IOException {
        System.out.println("Game State Change from/to: "+gameState+"/"+i);
        if(i==1) {
            if (getGameState() != 3) {//new-game
                au.newGame();
            } else { //pause->agil
                au.endPause();
            }

        }
        else if (i == 0){//main menu
            if(gameState == 1 || gameState == 2 || gameState == 4){
                frame.remove(au.getAgilSidePane());
                frame.pack();
            }
            if(gameState == 10 || gameState == 11){
                frame.remove(su.getStratSidePane());
                frame.pack();
            }
            keyManager.resetMainMenuEntry();
        }
        else if (i == 3 && gameState == 1){//agil->pause
            au.startPause();
        }
        else if (i == 4) {//pre-game
            frame.add(au.getAgilSidePane());
            frame.pack();
            au.getPlayer().startPos();
        }
        else if (i == 10){//pre
            frame.add(su.getStratSidePane());
            frame.pack();
        }
        else if (i == 11){//map/diff selector
        }
        else if (i == 12){//
            if (getGameState() != 13) {//new-game
                su.newGame();
                su.setMap(keyManager.getStratmapselected());
            } else { //pause->agil
//                su.endPause();
            }
        }
        else if (i== 100){//about
            au.getPlayer().startPos();
        }
        gameState = i;
    }

    public int getMouseX(){
        Point point = graphicsManager.getLocationOnScreen();
        int sx = (int) point.getX();
        int mx = (int) MouseInfo.getPointerInfo().getLocation().getX();
        int x = mx-sx;
        return x;
    }

    public int getMouseY(){
        Point point = graphicsManager.getLocationOnScreen();
        int sy = (int) point.getY();
        int my = (int) MouseInfo.getPointerInfo().getLocation().getY();
        int y = my-sy;
        return y;
    }

    public int getWindowWidth() {
        return windowWidth;
    }
    public int getWindowHeight() {
        return windowHeight;
    }
    public KeyManager getKeyManager(){
        return keyManager;
    }
    public FileManager getFileManager(){ return fm; }
    public int getGameState(){
        return gameState;
    }
    public StratUtil getSu() {return su; }

}
