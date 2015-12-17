package AgilObject;

import physics.GravBallPhysics;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Dude on 12/4/2015.
 */
public class GravBallGreen {

    private int speedX;
    private int speedY;
    private GravBallPhysics phy;
    private String _id;
    private int _x;
    private int _y;
    private int _width;
    private int _height;
    private boolean needRemoval;

    public GravBallGreen(String id, int x, int y, int width, int height, int speedX, int speedY, GravBallPhysics phy, boolean needRemoval){
        this._id = id;
        this._x = x;
        this._y = y;
        this._width = width;
        this._height = height;
        this.speedX = speedX;
        this.speedY = speedY;
        this.phy = phy;
        this.needRemoval = needRemoval;
    }

    public void update() throws IOException {
        _x += speedX;
        _y += speedY;
        //call bounds check;
        phy.checkBounds(this);
        phy.checkPcollided(this);
    }

    public boolean getNeedRemoval(){ return needRemoval; }
    public void setNeedRemoval(boolean b){ needRemoval = b; }
    public String getID(){ return _id; }
    public int getSpeedX(){ return speedX; }
    public int getSpeedY(){ return speedY; }
    public void invertSpeedX(){ speedX = speedX * -1; }
    public void invertSpeedY(){ speedY = speedY * -1; }
    public int getX(){
        return _x;
    }
    public int getY(){
        return _y;
    }
    public int getWidth(){
        return _width;
    }
    public int getHeight(){
        return _height;
    }


}
