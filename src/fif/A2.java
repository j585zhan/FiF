package fif;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class A2 implements Runnable{
    Rectangle A2;
    int xDirection,yDirection,x,y;
    boolean resting=false;
    boolean shouldSetRandDir=true;
    Image boom;
    public A2(Rectangle r){
        A2=r;
        ImageIcon i=new ImageIcon("a.gif");
        boom=i.getImage();
    }
    public void draw (Graphics g){
        g.setColor(Color.YELLOW);
        if(A2!=null)
            g.drawImage(boom, A2.x, A2.y, null);
        x=A2.x;y=A2.y;
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
        if(A2.y<620)
            A2.y=620;
        if(A2.y>659)
            A2.y=659;
        if(A2.x<100)
            A2.x=100;
        if(A2.x>800)
            A2.x=800;
        A2.x+=xDirection;
        A2.y+=yDirection;
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

