package input;

import main.Game;
import AgilObject.Player;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
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

    public void manage() {
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
                        game.changeGameState(4);
                    }else if (mainMenuEntry == 1){ //strat
                        game.changeGameState(10);
                    }else if (mainMenuEntry == 2){ //mem
                    }else if (mainMenuEntry == 3){ //about
                        game.changeGameState(100);
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
                        game.changeGameState(0);
                        game.changeGameState(1);
                        agilRestartMenuEntry = 0;
                    } else if (agilRestartMenuEntry == 1){ //main menu
                        game.changeGameState(0);
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
                    game.changeGameState(1);
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 4){
            switch (e.getKeyCode()) {
                case 32:
                    game.changeGameState(1);
                    break;
                case 27:
                    game.changeGameState(0);
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 10){//pre
            switch (e.getKeyCode()){
                case 27:
                    game.changeGameState(0);
                    break;
                case 32:
                    game.changeGameState(11);
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 11){//setup
            switch (e.getKeyCode()){
                case 27:
                    game.changeGameState(10);
                    break;
                case 32:
                    game.changeGameState(12);
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
        else if (game.getGameState() == 12){//strat
            switch (e.getKeyCode()){
                case 27:
                    if(toweronmouse != null){
                        toweronmouse = null;
                    }else{
                        game.changeGameState(11);
                    }
                    break;
                case 80:
                    game.changeGameState(13);
                    break;
                case 49:
                    toweronmouse = "t1";
                    break;
                case 50:
                    toweronmouse = "t2";
                    break;
                case 51:
                    toweronmouse = "t3";
                    break;
                case 52:
                    toweronmouse = "t4";
                    break;
                case 53:
                    System.out.println("X: "+game.getMouseX()+"Y: "+game.getMouseY());
                    break;
                default:
                    System.out.println("Unknown Key Pressed - Char: '"+e.getKeyChar()+"' - Code: '"+e.getKeyCode()+"'");
                    break;
            }
        }
        else if (game.getGameState() == 100){
            switch (e.getKeyCode()) {
                case 27:
                    game.changeGameState(0);
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
