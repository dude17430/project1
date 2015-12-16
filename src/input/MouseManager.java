package input;

import StratObject.TowerOne;
import main.Game;
import main.StratUtil;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Dude on 12/13/2015.
 */
public class MouseManager implements MouseListener {

    private final Game game;
    private KeyManager km;
    private StratUtil su;

    public MouseManager(Game game, StratUtil su){
        this.game = game;
        this.su = su;
        km = game.getKeyManager();

    }
    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(km.getToweronmouse() != null){
            switch (km.getToweronmouse()){
                case "t1":
                    su.newTower("t1");
                    break;
                case "t2":
                    su.newTower("t2");
                    break;
                case "t3":
                    su.newTower("t3");
                    break;
                case "t4":
                    su.newTower("t4");
                    break;
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
