package fif;

import java.awt.*;
import java.util.Random;
import javax.swing.ImageIcon;

public class A1 implements Runnable{
    Rectangle A1;
    int xDirection,yDirection,x,y;
    boolean resting=false;
    boolean shouldSetRandDir=true;
    Image boom;
    public A1(Rectangle r){
        A1=r;
        ImageIcon i=new ImageIcon("a.gif");
        boom=i.getImage();
    }
    public void draw (Graphics g){
        g.setColor(Color.YELLOW);
        if(A1!=null)
            g.drawImage(boom, A1.x, A1.y, null);
        x=A1.x;y=A1.y;
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
        if(A1.y<580)
            A1.y=580;
        if(A1.y>599)
            A1.y=599;
        if(A1.x<100)
            A1.x=100;
        if(A1.x>800)
            A1.x=800;
        A1.x+=xDirection;
        A1.y+=yDirection;
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
