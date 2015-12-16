package AgilObject;

import main.AgilUtil;
import main.Game;

/**
 * Created by Dude on 12/3/2015.
 */
public class Player {

    private int _x;
    private int _y;
    private int _width;
    private int _height;
    private int _speed;
    private Game _game;
    private boolean sheildEnabled;
    private AgilUtil _au;

    public Player(String player, int x, int y, int width, int height,
                  int speed, Game game, AgilUtil au) {
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
        this._speed = speed;
        this._game = game;
        this._au = au;
    }

    //Mutators
    public void moveUp() {
        _y -= _speed;
    }

    public void moveLeft() {
        _x -= _speed;
    }

    public void moveDown() {
        _y += _speed;
    }

    public void moveRight() {
        _x += _speed;
    }
    //Accessors
    public int getX(){return _x;}
    public int getY(){return _y;}
    public int getWidth(){return _width;}
    public int getHeight(){return _height;}

    public void resetPos() {
        _x = _game.getWindowWidth()/2-(_width/2);
        _y = _game.getWindowHeight()/2-(_height/2);
    }

    public void teleport() {
        _x = _game.randomNum(_game.getWindowWidth()-(_width/2), _width/2);
        _y = _game.randomNum(_game.getWindowHeight()-(_height/2), _height/2);
    }

    public void resetSpeed() {
        _speed = _au.getPOrigSpeed();
    }

    public void incrementSpeed() {
        _speed+=2;
    }

    public void startPos() {
        _x = 100;
        _y = 100;
    }
}
