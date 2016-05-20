package fif;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class A3 implements Runnable{
    Rectangle A3;
    int xDirection,yDirection,x,y;
    boolean resting=false;
    boolean shouldSetRandDir=true;
    Image boom;
    public A3(Rectangle r){
        A3=r;
        ImageIcon i=new ImageIcon("a.gif");
        boom=i.getImage();
    }
    public void draw (Graphics g){
        g.setColor(Color.YELLOW);
        if(A3!=null)
            g.drawImage(boom, A3.x, A3.y, null);
        x=A3.x;y=A3.y;
    }
    
    public int choosexRandomDirection(){
        Random r=new Random();
        int[] randDirections=new int[2];
        randDirections[0]=-1;
        randDirections[1]=1;;
        int randChoice = r.nextInt(2);
        return randDirections[randChoice];
    }
    public int chooseyRandomDirection(){
        Random r=new Random();
        int[] randDirections=new int[2];
        randDirections[0]=-1;
        randDirections[1]=1;;
        int randChoice = r.nextInt(2);
        return randDirections[randChoice];
    }
    
    public void setXDirection(int dir){
        xDirection=dir;
    }
    public void setYDirection(int dir){
        yDirection=dir;
    }
    public void move(){
        if(A3.y<680)
            A3.y=680;
        if(A3.y>699)
            A3.y=699;
        if(A3.x<100)
            A3.x=100;
        if(A3.x>800)
            A3.x=800;
        A3.x+=xDirection;
        A3.y+=yDirection;
    }
    
    @Override
    public void run(){
        try{
            while(true){
                if(!resting){
                    if(shouldSetRandDir){
                        setXDirection(choosexRandomDirection());
                        setYDirection(chooseyRandomDirection());
                        shouldSetRandDir = false;
                    }
                    long start=System.currentTimeMillis();
                    long end = start + 1000;
                    while (System.currentTimeMillis()<end){
                        move();
                        Thread.sleep(9);
                    }
                    resting = true;
                }
                else{
                    Thread.sleep(0);
                    shouldSetRandDir = true;
                    resting=false;
                }
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }
}

