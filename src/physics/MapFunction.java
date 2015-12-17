package physics;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/*
 * Created by DustinHarris on 12/15/15.
 */


public class MapFunction {

    private ArrayList<ArrayList<Integer>> map;
    private ArrayList<ArrayList<Integer>> path;
    private ArrayList<Integer> coordinates;
    private Integer pathOffset;

    public MapFunction(int n) throws IOException {
        map = new ArrayList<ArrayList<Integer>>();
        path = new ArrayList<ArrayList<Integer>>();
        coordinates = new ArrayList<Integer>();

                    populateMap(n);

                }

            public void populateMap(int mapNum) throws IOException {

                BufferedImage mapIMG = ImageIO.read(getClass().getClassLoader().getResource("strat/map"+mapNum+"_path.jpg"));
                int width = mapIMG.getWidth();
                int height = mapIMG.getHeight();
                pathOffset = ((width-800)/2);


                for (int x = 0; x < width; x++) {
                    ArrayList<Integer> map_y = new ArrayList<Integer>();
                    for (int y = 0; y < height; y++) {

                        Color mycolor = new Color(mapIMG.getRGB(x,y));
                        int red = mycolor.getRed();
                        int green = mycolor.getGreen();
                        int blue = mycolor.getBlue();

                        if( red<=15 && green>=240 && blue<=15 ){
                            map_y.add(2);
                            addPathCoordinates(x,y);
                        }
                        else if( red<=15 && green<=15 && blue<=15 ){
                            map_y.add(1);
                        }
                        else if( red>=240 && green<=15 && blue<=15 ){
                            map_y.add(-1);
                        }
                        else{
                            map_y.add(0);
                        }
                    }
                    map.add(map_y);
                }
                findPath();
            }


    public void findPath(){
        int j = 0;
        for (int i = 0; j >=0; i++) {

            System.out.println();
            System.out.println();
            System.out.println();
            System.out.println(path.get(i));

            for (int y = pathCoordinates(i,1)-1; y < pathCoordinates(i,1)+2; y++) {

                System.out.println();

                for (int x = pathCoordinates(i,0)-1; x < pathCoordinates(i,0)+2; x++) {

                    if(!(x == pathCoordinates(i,0) && y == pathCoordinates(i,1))) {
                        System.out.print(valueAtCoordinates(x,y));

                        if( valueAtCoordinates(x,y) == 1){
                            addPathCoordinates(x,y);
                            changeMapValue(x,y,0);
                            x+=3;
                            y+=3;
                        }

                        else if( valueAtCoordinates(x,y) == -1){
                            addPathCoordinates(-1,-1);
                            changeMapValue(x,y,0);
                            System.out.println();
                            System.out.println();
                            System.out.println();
                            System.out.print(path.get(i+1));
                            y+=3;
                            x+=3;
                            j=-1;
                        }
                    }
                    else{System.out.print(" ");}
                }
            }
        }
    }

    public void changeMapValue(int x, int y , int val){
        ArrayList<Integer> temp = new  ArrayList(map.get(x));
        temp.set(y,val);
        map.set(x,temp);
    }
    public void addPathCoordinates(int x,int y){
        ArrayList<Integer> temp = new ArrayList<Integer>();
        if(x==-1&&y==-1){
            temp.add(pathOffset);
            temp.add(0);
            path.add(temp);
        }
        else {

            temp.add(x - pathOffset);
            temp.add(y - pathOffset);
            path.add(temp);
        }


    }
    public Integer valueAtCoordinates(int x, int y){

        if(x == -1 || x == map.size()){
            return 0;
        }
        else if(y == -1 || y == map.get(1).size()){
            return 0;
        }
        return map.get(x).get(y);
    }
    public Integer pathCoordinates(int numberStep,int xORy){

        return path.get(numberStep).get(xORy)+pathOffset;

    }


    public ArrayList<ArrayList<Integer>> getPath(){
        return path;
    }



}




