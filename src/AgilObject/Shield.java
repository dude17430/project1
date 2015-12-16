package AgilObject;

/**
 * Created by Dude on 12/6/2015.
 */
public class Shield {
    private int x;
    private int y;
    private int circleRadius;

    private int type;
    private boolean enabled;
    private long startTime;

    public Shield(int circleRadius) {
        this.circleRadius = circleRadius;
        this.enabled = false;
    }

    public void update(){
        if(enabled){
            if (type == 0 && System.currentTimeMillis()-startTime > 10000) {
                enabled = false;
            }
            else if (type == 1 && System.currentTimeMillis()-startTime > 5000){
                enabled = false;
            }
        }
    }

    public void turnOn(int type){
        enabled = true;
        this.type = type;
        startTime = System.currentTimeMillis();
    }

    public boolean getEnabled(){ return enabled; }
    public int getRadius(){ return circleRadius; }

}