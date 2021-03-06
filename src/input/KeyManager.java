package input;

import main.Game;
import AgilObject.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Dude on 12/3/2015.
 */
public class KeyManager implements KeyListener{

    private Game game;
    private Player p;
    private ArrayList<Integer> keyDownArray;
    private int mainMenuEntry;
    private int agilRestartMenuEntry;
    private int stratmapselected;
    private String toweronmouse;

    public KeyManager(Game game, Player p) {
        this.game = game;
        this.p = p;
        keyDownArray = new ArrayList<>();
        mainMenuEntry = 0;
        agilRestartMenuEntry = 0;
        stratmapselected = 1;
        toweronmouse = null;
    }

    public void manage() throws IOException {
        for (int code : keyDownArray) {
            switch (game.getGameState()){
                case 0://main menu
                    break;//end menu case
                case 1:
                    switch (code) {
                        case 87://W
                            p.moveUp();
                            break;
                        case 65://A
                            p.moveLeft();
                            break;
                        case 83://S
                            p.moveDown();
                            break;
                        case 68://D
                            p.moveRight();
                            break;
                        case 27: //esc
                            game.changeGameState(0);
                            break;
                        case 80://p
                            game.changeGameState(3);
                            break;
                    }
                    break; //end game case
                case 2://restart menu
                    break;
                case 3://paused screen
                    break;
            }

        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //meh
    }

    @Override
    public void keyPressed(KeyEvent e) { //add to keyPressArray depending on state
        if(game.getGameState() == 1){
            if(!(keyDownArray.contains(e.getKeyCode()) ) ) {
                keyDownArray.add(e.getKeyCode());
            }
        }
        else if (game.getGameState() == 0) { //main menu
            switch (e.getKeyCode()) {
                case 87://menu up
                    if(mainMenuEntry>0){
                        mainMenuEntry--;
                    }
                    break;
                case 83://menu down
                    if(mainMenuEntry<3) {
                        mainMenuEntry++;
                    }
                    break;
                case 32://spacebar (select)
                    if(mainMenuEntry == 0){  //agil
                        try {
                            game.changeGameState(4);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else if (mainMenuEntry == 1){ //strat
                        try {
                            game.changeGameState(10);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }else if (mainMenuEntry == 2){ //mem
                    }else if (mainMenuEntry == 3){ //about
                        try {
                            game.changeGameState(100);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                default:
                    System.out.println("Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 2) { //restart menu
            switch (e.getKeyCode()) {
                case 65://menu left
                    if(agilRestartMenuEntry==1){
                        agilRestartMenuEntry--;
                    }
                    break;
                case 68://menu right
                    if(agilRestartMenuEntry==0) {
                        agilRestartMenuEntry++;
                    }
                    break;
                case 32://spacebar (select)
                    if(agilRestartMenuEntry == 0){  //restart
                        try {
                            game.changeGameState(0);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        try {
                            game.changeGameState(1);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        agilRestartMenuEntry = 0;
                    } else if (agilRestartMenuEntry == 1){ //main menu
                        try {
                            game.changeGameState(0);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        agilRestartMenuEntry = 0;
                    }
                    break;
                default:
                    System.out.println("Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 3){
            switch (e.getKeyCode()){
                case 80://p
                    try {
                        game.changeGameState(1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 4){
            switch (e.getKeyCode()) {
                case 32:
                    try {
                        game.changeGameState(1);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 27:
                    try {
                        game.changeGameState(0);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        //----------Pre Game
        else if (game.getGameState() == 10){
            switch (e.getKeyCode()){
                case 27:
                    try {
                        game.changeGameState(0);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 32:
                    try { game.changeGameState(11); } catch (IOException e1) { e1.printStackTrace(); }
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        //----------Strat Setup
        else if (game.getGameState() == 11){
            switch (e.getKeyCode()){
                case 27:
                    try {
                        game.changeGameState(10);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 32:
                    try {
                        game.changeGameState(12);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case 65:
                    if(stratmapselected > 1)
                        stratmapselected--;
                    break;
                case 68:
                    if(stratmapselected < 2)
                        stratmapselected++;
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        //----------Strat Game
        else if (game.getGameState() == 12){
            switch (e.getKeyCode()){
                case 27:
                    if(toweronmouse != null){
                        toweronmouse = null;
                    } else {
                        try {
                            game.changeGameState(11);
                        } catch (IOException e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
                case 80:
                    try { game.changeGameState(13); } catch (IOException e1) { e1.printStackTrace(); }
                    break;
                case 49:
                    if(game.getSu().getMoney()>=game.getSu().getTowerOneCost()){
                        toweronmouse = "t1"; }
                    break;
                case 50:
                    if(game.getSu().getMoney()>=game.getSu().getTowerTwoCost()){
                        toweronmouse = "t2"; }
                    break;
                case 51:
                    if(game.getSu().getMoney()>=game.getSu().getTowerThreeCost()){
                        toweronmouse = "t3"; }
                    break;
                case 52:
                    if(game.getSu().getMoney()>=game.getSu().getTowerFourCost()){
                        toweronmouse = "t4"; }
                    break;
                case 53:
                    System.out.println("X: "+game.getMouseX()+"Y: "+game.getMouseY());
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        //----------About
        else if (game.getGameState() == 100){
            switch (e.getKeyCode()) {
                case 27:
                    try { game.changeGameState(0); } catch (IOException e1) { e1.printStackTrace(); }
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) { //remove from keyPressArray if exists
        int code = e.getKeyCode();
        if(keyDownArray.contains(code)) {
            keyDownArray.remove(keyDownArray.indexOf(code));
        }
    }

    public int getMainMenuEntry(){
        return mainMenuEntry;
    }
    public void resetMainMenuEntry(){ mainMenuEntry = 0;}
    public int getAgilRestartMenuEntry(){return agilRestartMenuEntry;}
    public int getStratmapselected(){ return stratmapselected; }
    public String getToweronmouse(){return toweronmouse;}
    public void clearTowerOnMouse() {
        toweronmouse = null;
    }
}
