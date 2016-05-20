
//主宠 第二技能必中 光守护 每回合减少对手五分之一 青龙 每回合回复50hp 雷伊 百分之十伤害三倍 谱尼 第二技能伤害加五十 风风 附加百分之33自身伤害
//super 1-6 主宠 光守护 青龙 雷伊 谱尼 风风
//
//#map      difficulty  recommended             bonus
//first map easy 2+ pets or 10+lvl              lvl++
//second map mid 3+pets or 50+lvl               lvl+2
//third map 3+ hard 50lvl+ pets or 2+ 100lvl    lvl+3
//boss page extremely hard 6superx100lvl pets     -

/*
    skill1 normal 90
    skill2 strength 80+petlvl
    skill3 recover 100+petlvl
    skill4 seckill  success 0.05

*/

package fif;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class FiF extends JFrame implements Runnable{
    //==========================================================================    Variables
    long cattime,savtime,stt,storystt,skillt,enemyskilltime;//calc times used
    int x,y,xDirection,yDirection,hpl=400,hpr=400,damage=0,recover=0;
    int suc4=0,rec4=0,suc2=0,rec1=0,triple=0,addit1=1;
    int petnumber=1,petleft=0,petused=0,mapadi,enemyskill,bossleft=1;
    int safecount=0,showpets=0,mapexp,next=0;
    int bag1l,bag2l,bag3l,bag4l,bag5l,bag6l,map1l,map2l,map3l,bagl;
    int enemyadi=0;
    
    private Image dbI;
    private Graphics dbg;
    
    Image bagfullima,mission1,mission2,mission3,greenH,pass,HPL,HPR,catchit,tryagain;//insert images
    Image mappet1,mappet2,mappet3,safeon,safeoff,safe,youwin,youlose,nulimage,endgif;//insert images
    Image thunderbaby,thunderbabyr,windbaby,windbabyr,leavebaby,leavebabyr;//map#1 pets
    Image wibir,wibirr,mibir,mibirr,wuno,wunor;//map#2 pets
    Image popo,popor,urian,urianr,bruck,bruckr;//map#3 pets
    Image mainpet1,mainpet2,mainpet3;//three main pets
    Image runmans,runmand,runman,runmansb,runmandb;//role
    Image startgif,storygif,bcgd,mainpage,mainpage1,choosepage,map1,map2,map3,boss,dangersign;      //maps
    Image mainpet1s, mainpet1d, mainpet2s, mainpet2d, mainpet3s, mainpet3d,map1ft,map2ft,map3ft,tri;
    Image mainpet1up,mainpet2up,mainpet3up,bag1up,bag2up,bag3up;//evolution images of three mainpets
    Image bag1,bag2,bag3,bag4,bag5,bag6,bagpet,backpack;  //order of fighting
    Image super1,super2,super3,super4,super5,super6;//6super pet image
    Image boss1,boss2,boss3,boss4,boss5,boss6;//6 boss image
    Image help1,help2,help3,help4,help5,help6,help7;//help pictures
    
    boolean exe=false,turn=true,exe1,bug,drawpetst=false,showpetsH,savefile,savestring,exer=false;;
    boolean bag1al=false,bag2al=false,bag3al=false,bag4al=false,bag5al=false,bag6al=false,bagfull=false,f;
    boolean mapst=true,mapnd,maprd,bosspage,savetri;                                         //maps
    boolean Ai1=false,Ai2=false,Ai3=false,win=false,lose=false,sethpl=false,sethpr=false;
    boolean start=false,help=false,oldgame=false,select=false,fighting=false,sH,dH,oH,nH,og=false,ng=false,game=false;
    boolean firstpet=false,secondpet=false,thirdpet=false;                      //choose pet
    boolean firstH,secondH,thirdH,skill1H,skill2H,skill3H,skill4H,remove2H,remove3H,remove4H,remove5H,remove6H,firstf6H,firstf2H,firstf3H,firstf4H,firstf5H;
    boolean fir6,fir2,fir3,fir4,fir5,rem2,rem3,rem4,rem5,rem6,sp=false;
    boolean skilla=false,skillb=false,skillc=false,skilld=false,winbt1=false,winbt2=false,winbt3=false,winbt1H=false,winbt2H=false,winbt3H=false;
    boolean catchAi1=false,catsuc,catfai,setstarttime=false,setstorystarttime=false,drawgif=true,drawstory=true;
    boolean m1H,m2H,m3H,m1,m2,m3,p1,p2,p3,m1s,m2s,m3s;
    boolean setbossnum=false,setmboss=false;
    boolean skill4s,skill3s,skill2s,skill1s;
    boolean rand=false;
    boolean enemyskills=true;
    boolean setenemyskill=false,setenemyrecover=false;
    
    Random r=new Random();
    //========================================================================== BUTTONS
    //main page
    Rectangle startButton = new Rectangle (375,350,145,35);
    Rectangle newButton = new Rectangle (575,350,145,35);
    Rectangle oldButton = new Rectangle (575,400,145,35);
    Rectangle dButton = new Rectangle (375,450,145,35);
    //fighting page
    Rectangle skill1 = new Rectangle(52,597,207,120);
    Rectangle skill2 = new Rectangle(301,597,207,120);
    Rectangle skill3 = new Rectangle(554,597,207,120);
    Rectangle skill4 = new Rectangle(804,597,207,120);
    Rectangle catchpet = new Rectangle (574,373,96,113);
    Rectangle levelup = new Rectangle (408,373,96,113);
    Rectangle expup = new Rectangle (467,370,96,113);
    //game page
    Rectangle petbags = new Rectangle (960,50,50,50);
    Rectangle safemode = new Rectangle(50,50,80,50);
    Rectangle save = new Rectangle (480,30,100,35);
    //choosepage
    Rectangle firstchoice = new Rectangle (150,400,145,35);
    Rectangle secondchoice = new Rectangle (450,560,145,35);
    Rectangle thirdchoice = new Rectangle (750,400,145,35);
    //pet bag
    Rectangle firstfight2 = new Rectangle (210,200,145,35);
    Rectangle firstfight3 = new Rectangle (370,200,145,35);
    Rectangle firstfight4 = new Rectangle (530,200,145,35);
    Rectangle firstfight5 = new Rectangle (690,200,145,35);
    Rectangle firstfight6 = new Rectangle (850,200,145,35);
    Rectangle remove1 = new Rectangle (50,250,145,35);
    Rectangle remove2 = new Rectangle (210,250,145,35);
    Rectangle remove3 = new Rectangle (370,250,145,35);
    Rectangle remove4 = new Rectangle (530,250,145,35);
    Rectangle remove5 = new Rectangle (690,250,145,35);
    Rectangle remove6 = new Rectangle (850,250,145,35);
    //AI
    static Rectangle o1=new Rectangle(800,600,0,0);
    static Rectangle o2=new Rectangle(600,600,0,0);
    static Rectangle o3=new Rectangle(400,600,0,0);
    static A1 A1=new A1(o1);
    static A2 A2=new A2(o2);
    static A3 A3=new A3(o3);
    
    Thread music;
    
    @Override
    public void run() {
         try{
            //music
            MP3Player.loop=true;
            playMusic("mp3/background.wav");
            
            while(true){
                
                move();
                Thread.sleep(2);
         
            }
            
        }catch(Exception e){
            System.out.print("error");
        }
    }
    
    public void playMusic(String filename){
    MP3Player.filename=filename;
    if(MP3Player.musicRunning==false){
        music = new Thread(new MP3Player());
        music.start();
        this.requestFocusInWindow();
    }
    }
    
    private void move() {
        x+=xDirection;
        y+=yDirection;
        if(exe||exe1){
            exe=false;exe1=false;//change map once a time
        }
        //======================================================================Limitation of y
        if(y<=600)
            y=600;
        if(y>=680)
            y=680;
        //======================================================================Changing over to previous map
        if(x<=20){
            if(mapst){x=20;}
            else{
                x=880;
                y=675;
                if(mapnd&&!exe1){
                    mapst=true;
                    mapnd=false;
                    exe1=true;
                }
                if(maprd&&!exe1){
                    mapnd=true;
                    maprd=false;
                    exe1=true;
                }
                if(bosspage&&!exe1){
                    maprd=true;
                    bosspage=false;
                    exe1=true;
                }
            }
        }
        //======================================================================Changing over to next map
        if(x>=900){
            if(bosspage){x=900;}
            else{
                x=40;
                y=675;
                if(mapst&&!exe){
                    mapst=false;
                    mapnd=true;
                    exe=true;
                }
                if(mapnd&&!exe){
                    mapnd=false;
                    maprd=true;
                    exe=true;
                }
                if(maprd&&!exe){
                    maprd=false;
                    bosspage=true;
                    exe=true;
                }
            }
        }
    }
    
    public void setXDirection(int xdir){
        xDirection=xdir;
    }
    public void setYDirection(int ydir){
        yDirection=ydir;
    }
    
    public FiF(){
        //====================================================================== IMAGES OF BACKGROUND AND MAIN PETS
        ImageIcon i1=new ImageIcon("background/bcgd.jpg");
        bcgd=i1.getImage();
        ImageIcon i2=new ImageIcon("background/main.jpg");
        mainpage=i2.getImage();
        ImageIcon i3=new ImageIcon("insertimage/main1.gif");
        mainpage1=i3.getImage();
        ImageIcon i4=new ImageIcon("background/choosepage.jpg");
        i4.setImage(i4.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        choosepage=i4.getImage();
        ImageIcon i5=new ImageIcon("pets/mainpet1.gif");
        i5.setImage(i5.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        mainpet1d=i5.getImage();
        ImageIcon main1up=new ImageIcon("pets/mainpet1up.gif");
        main1up.setImage(main1up.getImage().getScaledInstance(215, 230, Image.SCALE_DEFAULT));
        mainpet1up=main1up.getImage();
        ImageIcon main2up=new ImageIcon("pets/mainpet2up.gif");
        main2up.setImage(main2up.getImage().getScaledInstance(201, 186, Image.SCALE_DEFAULT));
        mainpet2up=main2up.getImage();
        ImageIcon main3up=new ImageIcon("pets/mainpet3up.gif");
        main3up.setImage(main3up.getImage().getScaledInstance(215, 136, Image.SCALE_DEFAULT));
        mainpet3up=main3up.getImage();
        ImageIcon i6=new ImageIcon("pets/mainpet2.gif");
        i6.setImage(i6.getImage().getScaledInstance(240, 150, Image.SCALE_DEFAULT));
        mainpet2d=i6.getImage();
        ImageIcon i7=new ImageIcon("pets/mainpet3.gif");
        i7.setImage(i7.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        mainpet3d=i7.getImage();
        ImageIcon i12=new ImageIcon("choosepage/1.gif");
        i12.setImage(i12.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        mainpet1s=i12.getImage();
        ImageIcon i13=new ImageIcon("choosepage/2.gif");
        i13.setImage(i13.getImage().getScaledInstance(240, 150, Image.SCALE_DEFAULT));
        mainpet2s=i13.getImage();
        ImageIcon i14=new ImageIcon("choosepage/3.gif");
        i14.setImage(i14.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        mainpet3s=i14.getImage();
        ImageIcon i8=new ImageIcon("background/start.gif");
        startgif=i8.getImage();
        ImageIcon i20=new ImageIcon("background/story.gif");
        storygif=i20.getImage();
        ImageIcon i21=new ImageIcon("background/end.gif");
        endgif=i21.getImage();
        ImageIcon i9=new ImageIcon("background/map1.jpg");
        i9.setImage(i9.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        map1=i9.getImage();
        ImageIcon i10=new ImageIcon("background/map2.gif");
        i10.setImage(i10.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        map2=i10.getImage();
        ImageIcon i11=new ImageIcon("background/map3.jpg");
        map3=i11.getImage();
        ImageIcon runmanstatic=new ImageIcon("background/runman-s.gif");
        runmans=runmanstatic.getImage();
        ImageIcon runmandynamic=new ImageIcon("background/runman-d.gif");
        runmand=runmandynamic.getImage();
        ImageIcon runmanstaticback=new ImageIcon("background/runman-sb.gif");
        runmansb=runmanstaticback.getImage();
        ImageIcon runmandynamicback=new ImageIcon("background/runman-db.gif");
        runmandb=runmandynamicback.getImage();
        runman=runmans;
        boss=choosepage;
        ImageIcon map1f=new ImageIcon("background/map1f.gif");
        map1f.setImage(map1f.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        map1ft=map1f.getImage();
        ImageIcon map2f=new ImageIcon("background/map2f.gif");
        map2f.setImage(map2f.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        map2ft=map2f.getImage();
        ImageIcon map3f=new ImageIcon("background/map3f.gif");
        map3ft=map3f.getImage();
        //======================================================================  IMAGES OF PETS R-map1
        ImageIcon TBR=new ImageIcon("pets/TBR.gif");
        TBR.setImage(TBR.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        thunderbabyr=TBR.getImage();
        ImageIcon TB=new ImageIcon("pets/TB.gif");
        TB.setImage(TB.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        thunderbaby=TB.getImage();
        
        ImageIcon WBR=new ImageIcon("pets/WBR.gif");
        WBR.setImage(WBR.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        windbabyr=WBR.getImage();
        ImageIcon WB=new ImageIcon("pets/WB.gif");
        WB.setImage(WB.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        windbaby=WB.getImage();
        
        ImageIcon LBR=new ImageIcon("pets/LBR.gif");
        LBR.setImage(LBR.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        leavebabyr=LBR.getImage();
        ImageIcon LB=new ImageIcon("pets/LB.gif");
        LB.setImage(LB.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        leavebaby=LB.getImage();
        //======================================================================  IMAGES OF PETS R-map2
        ImageIcon WIR=new ImageIcon("pets/wibirR.gif");
        WIR.setImage(WIR.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        wibirr=WIR.getImage();
        ImageIcon WI=new ImageIcon("pets/wibir.gif");
        WI.setImage(WI.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        wibir=WI.getImage();
        
        ImageIcon MIR=new ImageIcon("pets/mibirR.gif");
        MIR.setImage(MIR.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        mibirr=MIR.getImage();
        ImageIcon MI=new ImageIcon("pets/mibir.gif");
        MI.setImage(MI.getImage().getScaledInstance(240, 180, Image.SCALE_DEFAULT));
        mibir=MI.getImage();
        
        ImageIcon WNR=new ImageIcon("pets/wunoR.gif");
        WNR.setImage(WNR.getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT));
        wunor=WNR.getImage();
        ImageIcon WN=new ImageIcon("pets/wuno.gif");
        WN.setImage(WN.getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT));
        wuno=WN.getImage();
        //======================================================================  IMAGES OF PETS R-map3
        ImageIcon BRR=new ImageIcon("pets/bruckR.gif");
        BRR.setImage(BRR.getImage().getScaledInstance(180, 240, Image.SCALE_DEFAULT));
        bruckr=BRR.getImage();
        ImageIcon BR=new ImageIcon("pets/bruck.gif");
        BR.setImage(BR.getImage().getScaledInstance(180, 240, Image.SCALE_DEFAULT));
        bruck=BR.getImage();
        
        ImageIcon URR=new ImageIcon("pets/leiyiR.gif");
        URR.setImage(URR.getImage().getScaledInstance(178, 220, Image.SCALE_DEFAULT));
        urianr=URR.getImage();
        ImageIcon UR=new ImageIcon("pets/leiyi.gif");
        UR.setImage(UR.getImage().getScaledInstance(178, 220, Image.SCALE_DEFAULT));
        urian=UR.getImage();
        
        ImageIcon PPR=new ImageIcon("pets/urianR.gif");
        PPR.setImage(PPR.getImage().getScaledInstance(240, 220, Image.SCALE_DEFAULT));
        popor=PPR.getImage();
        ImageIcon PP=new ImageIcon("pets/urian.gif");
        PP.setImage(PP.getImage().getScaledInstance(240, 220, Image.SCALE_DEFAULT));
        popo=PP.getImage();
        //======================================================================  IMAGES OF SUPER TEAM
        ImageIcon s2=new ImageIcon("pets/TBup.gif");
        s2.setImage(s2.getImage().getScaledInstance(220, 230, Image.SCALE_DEFAULT));
        super2=s2.getImage();
        ImageIcon s3=new ImageIcon("pets/LBup.gif");
        s3.setImage(s3.getImage().getScaledInstance(284, 230, Image.SCALE_DEFAULT));
        super3=s3.getImage();
        ImageIcon s4=new ImageIcon("pets/urianup.gif");
        s4.setImage(s4.getImage().getScaledInstance(215, 230, Image.SCALE_DEFAULT));
        super4=s4.getImage();
        ImageIcon s5=new ImageIcon("pets/mibirup.gif");
        s5.setImage(s5.getImage().getScaledInstance(184, 230, Image.SCALE_DEFAULT));
        super5=s5.getImage();
        ImageIcon s6=new ImageIcon("pets/wibirup.gif");
        s6.setImage(s6.getImage().getScaledInstance(251, 230, Image.SCALE_DEFAULT));
        super6=s6.getImage();
        //======================================================================  IMAGES OF BOSS TEAM
        ImageIcon b1=new ImageIcon("boss/boss1.gif");
        b1.setImage(b1.getImage().getScaledInstance(260, 230, Image.SCALE_DEFAULT));
        boss1=b1.getImage();
        ImageIcon b2=new ImageIcon("boss/boss2.gif");
        b2.setImage(b2.getImage().getScaledInstance(281, 215, Image.SCALE_DEFAULT));
        boss2=b2.getImage();
        ImageIcon b3=new ImageIcon("boss/boss3.gif");
        b3.setImage(b3.getImage().getScaledInstance(255, 230, Image.SCALE_DEFAULT));
        boss3=b3.getImage();
        ImageIcon b4=new ImageIcon("boss/boss4.gif");
        b4.setImage(b4.getImage().getScaledInstance(290, 230, Image.SCALE_DEFAULT));
        boss4=b4.getImage();
        ImageIcon b5=new ImageIcon("boss/boss5.gif");
        b5.setImage(b5.getImage().getScaledInstance(250, 230, Image.SCALE_DEFAULT));
        boss5=b5.getImage();
        ImageIcon b6=new ImageIcon("boss/boss6.gif");
        //s6.setImage(s6.getImage().getScaledInstance(251, 230, Image.SCALE_DEFAULT));
        boss6=b6.getImage();
        //====================================================================== INSERT IMAGE OF GAME
        
        ImageIcon nul=new ImageIcon("");
        nulimage=nul.getImage();
        ImageIcon mA=new ImageIcon("insertimage/mA.gif");
        mission1=mA.getImage();
        ImageIcon mB=new ImageIcon("insertimage/mB.gif");
        mission2=mB.getImage();
        ImageIcon mC=new ImageIcon("insertimage/mC.gif");
        mission3=mC.getImage();
        ImageIcon hover=new ImageIcon("insertimage/H.gif");
        greenH=hover.getImage();
        ImageIcon ps=new ImageIcon("insertimage/pass.gif");
        pass=ps.getImage();
        ImageIcon safemodeon=new ImageIcon("insertimage/safe.gif");
        safeon=safemodeon.getImage();
        ImageIcon safemodeoff=new ImageIcon("insertimage/nosafe.gif");
        safeoff=safemodeoff.getImage();
        safe=safeoff;
        ImageIcon winpage=new ImageIcon("insertimage/win.gif");
        youwin=winpage.getImage();
        ImageIcon losepage=new ImageIcon("insertimage/lose.gif");
        youlose=losepage.getImage();
        ImageIcon Bag=new ImageIcon("insertimage/Bag.png");
        Bag.setImage(Bag.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT));
        backpack=Bag.getImage();
        ImageIcon Danger=new ImageIcon("insertimage/danger.gif");
        Danger.setImage(Danger.getImage().getScaledInstance(90, 120, Image.SCALE_DEFAULT));
        dangersign=Danger.getImage();
        ImageIcon succat=new ImageIcon("insertimage/succat.gif");
        succat.setImage(succat.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
        catchit=succat.getImage();
        ImageIcon faicat=new ImageIcon("insertimage/faicat.gif");
        faicat.setImage(faicat.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
        tryagain=faicat.getImage();
        ImageIcon bagfullimage=new ImageIcon("insertimage/bagfull.gif");
        bagfullimage.setImage(bagfullimage.getImage().getScaledInstance(400, 300, Image.SCALE_DEFAULT));
        bagfullima=bagfullimage.getImage();
        ImageIcon triangle=new ImageIcon("insertimage/save.gif");
        tri=triangle.getImage();
        
        //====================================================================== IMAGES OF HELP PAGE
        ImageIcon h1=new ImageIcon("help/1.jpg");
        h1.setImage(h1.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help1=h1.getImage();
        ImageIcon h2=new ImageIcon("help/2.jpg");
        h2.setImage(h2.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help2=h2.getImage();
        ImageIcon h3=new ImageIcon("help/3.jpg");
        h3.setImage(h3.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help3=h3.getImage();
        ImageIcon h4=new ImageIcon("help/4.jpg");
        h4.setImage(h4.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help4=h4.getImage();
        ImageIcon h5=new ImageIcon("help/5.jpg");
        h5.setImage(h5.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help5=h5.getImage();
        ImageIcon h6=new ImageIcon("help/6.jpg");
        h6.setImage(h6.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help6=h6.getImage();
        ImageIcon h7=new ImageIcon("help/7.jpg");
        h7.setImage(h7.getImage().getScaledInstance(1060, 760, Image.SCALE_DEFAULT));
        help7=h7.getImage();
        //======================================================================
        
        
        this.setTitle("FIF");
        this.setSize(1060,760);
        this.setVisible(true);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setBackground(Color.BLACK);
        this.addKeyListener(new AL());
        this.addMouseListener(new ML());
        this.addMouseMotionListener(new ML());
        
        x=40;y=675;
    }
    
    @Override
    public void paint(Graphics g){
        //======================================================================Start gif
        System.out.println(System.currentTimeMillis()/100-stt/100);
        if(drawgif){
        g.drawImage(startgif, 0, 0, this);
            if(!setstarttime){
            stt=System.currentTimeMillis();
            setstarttime=true;
            }
        }
        if((System.currentTimeMillis()/100-stt/100)<55){
        }
        if((System.currentTimeMillis()/100-stt/100)>=53){
        drawgif=false;
        dbI=createImage(getWidth(),getHeight());
        dbg=dbI.getGraphics();
        paintComponent(dbg);
        g.drawImage(dbI,0,0,this);
        }
        
        //======================================================================Save record
        if(savefile){
            try {
                saveRecord();
            } catch (IOException ex) {
                Logger.getLogger(FiF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        //======================================================================Read record
        if(og){
            try {
                ogRecord();
            } catch (IOException ex) {
                Logger.getLogger(FiF.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void paintComponent(Graphics g){
            //==================================================================Setup mainpage
            g.drawImage(mainpage, 0, 0, this);
            g.drawImage(mainpage1, 800, 550, this);
            if(!help){
                g.setFont(new Font("Arial", Font.BOLD,26));
                g.setColor(Color.WHITE);
                g.drawString("Fight In Forest",355,250);
                if(!sH){
                    g.setColor(Color.CYAN);
                }
                else{
                    g.setColor(Color.PINK);
                    g.fillRect(oldButton.x, oldButton.y, oldButton.width, oldButton.height);
                    g.fillRect(newButton.x, newButton.y, newButton.width, newButton.height);
                    g.setFont(new Font("Arial", Font.BOLD,16));
                    g.setColor(Color.GRAY);
                    g.drawString("New Game", 605,375);
                    g.drawString("Saved Game", 605,425);
                    g.setColor(Color.PINK);
                }
                g.fillRect(startButton.x, startButton.y, startButton.width, startButton.height);
                g.setFont(new Font("Arial", Font.BOLD,16));
                g.setColor(Color.GRAY);
                g.drawString("Start Game (S)", 395,375);
                if(!dH){
                    g.setColor(Color.CYAN);}
                else{
                    g.setColor(Color.PINK);}
                g.fillRect(dButton.x, dButton.y, dButton.width, dButton.height);
                g.setFont(new Font("Arial", Font.BOLD,16));
                g.setColor(Color.GRAY);
                g.drawString("Help (H)", 415,475);
            }else{
                //==============================================================HELP PAGE
                g.setFont(new Font("Arial", Font.BOLD,32));
                g.drawString("CLICK TO CONTINUE", 355,475);
                if(next==1){g.drawImage(help1, 0, 0, this);}
                if(next==2){g.drawImage(help2, 0, 0, this);}
                if(next==3){g.drawImage(help3, 0, 0, this);}
                if(next==4){g.drawImage(help4, 0, 0, this);}
                if(next==5){g.drawImage(help5, 0, 0, this);}
                if(next==6){g.drawImage(help6, 0, 0, this);}
                if(next==7){g.drawImage(help7, 0, 0, this);}
                if(next==8){next=0;help=false;}
            }
        if(start){
            if(!oH){//start record
                    g.setColor(Color.CYAN);
                }
                else{
                    g.setColor(Color.PINK);}
            g.fillRect(oldButton.x, oldButton.y, oldButton.width, oldButton.height);
            if(!nH){//start a new game
                    g.setColor(Color.CYAN);
                }
                else{
                    g.setColor(Color.PINK);}
            g.fillRect(newButton.x, newButton.y, newButton.width, newButton.height);
            g.setFont(new Font("Arial", Font.BOLD,16));
            g.setColor(Color.GRAY);
            g.drawString("New Game", 605,375);
            g.drawString("Saved Game", 605,425);
            
            if(og){/*read file*/safecount=1;game=true;if(f){og=false;}}
            else if(ng){
                //==============================================================background story gif
                if(drawstory){
                    g.drawImage(storygif, 0, 0, this);
                    if(!setstorystarttime){
                        storystt=System.currentTimeMillis();
                        setstorystarttime=true;
                    }
                }
                if(System.currentTimeMillis()-storystt<=28900){//System.currentTimeMillis()-storystt<=28900
            
                }else{
                drawstory=false;
                g.drawImage(choosepage, 0, 0, this);
                g.drawImage(mainpet1, 125, 235, this);
                g.drawImage(mainpet2, 385, 405, this);
                g.drawImage(mainpet3, 710, 235, this);
                //==============================================================Choose main pet
                if(!firstH){
                    g.setColor(Color.CYAN);}
                else{
                    g.setColor(Color.PINK);}
                g.fillRect(firstchoice.x, firstchoice.y, firstchoice.width, firstchoice.height);
                if(!secondH){
                    g.setColor(Color.CYAN);}
                else{
                    g.setColor(Color.PINK);}
                g.fillRect(secondchoice.x, secondchoice.y, secondchoice.width, secondchoice.height);
                if(!thirdH){
                    g.setColor(Color.CYAN);}
                else{
                    g.setColor(Color.PINK);}
                g.fillRect(thirdchoice.x, thirdchoice.y, thirdchoice.width, thirdchoice.height);
                g.setFont(new Font("Arial", Font.BOLD,16));
                g.setColor(Color.GRAY);
                g.drawString("mikai", 195,423);
                g.drawString("laioe", 495,583);
                g.drawString("fuxiu", 795,423);
                //==============================================================add first pet to bag
                ArrayList<String>bag=new ArrayList();
                if(firstpet){bag.add("mikai");bag1=mainpet1d;bag1up=mainpet1up;bag1l=1;super1=bag1;firstpet=false;}
                if(secondpet){bag.add("laioe");bag1=mainpet2d;bag1up=mainpet2up;bag1l=1;super1=bag1;secondpet=false;}
                if(thirdpet){bag.add("fuxiu");bag1=mainpet3d;bag1up=mainpet3up;bag1l=1;super1=bag1;thirdpet=false;}
                if(bag.size()>0){
                game=true;
                g.drawImage(mainpage, 0, 0, this);
                ng=false;}
                }
            }
            if(game){
                //============================================================== SET BACKGROUND IMAGE, exp and difficulty of each map
                if(mapst){
                    g.drawImage(map1, 0, 0, this);mapadi=2;mapexp=1;
                }
                else if(mapnd){
                    g.drawImage(map2, 0, 0, this);mapadi=24;mapexp=2;
                }
                else if(maprd){
                    g.drawImage(map3, 0, 0, this);mapadi=50;mapexp=3;
                }
                else if(bosspage){
                    safecount=2;
                    g.drawImage(boss, 0, 0, this);
                    //==========================================================BOSS PAGE TIPS
                    if(System.currentTimeMillis()/5000%2==1){
                        g.setFont(new Font("Arial", Font.BOLD,20));
                        g.setColor(Color.YELLOW);
                        g.drawString("TIPS: Boss will recover a random amount hp every round", 200, 150);
                    }else{
                        g.setFont(new Font("Arial", Font.BOLD,20));
                        g.setColor(Color.YELLOW);
                        g.drawString("TIPS: All of your pets will level down everytime you fail last mission", 200, 150);
                    }
                    g.setFont(new Font("Arial", Font.BOLD,16));
                }
                //==============================================================insert images on maps
                    g.drawImage(runman, x, y, this);
                    if(!bosspage){
                    A1.draw(g);
                    A2.draw(g);
                    A3.draw(g);
                    g.drawImage(tri, 505, 30, rootPane);
                    if(savetri){
                    g.setColor(Color.green);
                    g.fillRect(save.x, save.y, save.width, save.height);
                    }
                    g.setColor(Color.yellow);
                    g.drawImage(dangersign, 960, 650, this);
                    g.drawImage(backpack, 960, 50, this);
                    }
                    else{
                        g.drawImage(mission1, 95, 200, rootPane);
                        g.drawImage(mission2, 425, 200, rootPane);
                        g.drawImage(mission3, 755, 200, rootPane);
                        if(m1H){
                        g.drawImage(greenH, 95, 200, rootPane);
                        }
                        if(m2H){
                        g.drawImage(greenH, 425, 200, rootPane);
                        }
                        if(m3H){
                        g.drawImage(greenH, 755, 200, rootPane);
                        }
                    }
                    /*TO GET lvl 100
                    if(bug){
                            bag1l+=100;bug=false;
                    }
                    */
                    if(showpetsH&&!bosspage){//pet bag image
                    g.drawRect(petbags.x, petbags.y, petbags.width, petbags.height);}
                    
                    //set first set remove button
                    if(fir2){
                        if(petnumber>=2){
                        Image tempp; int templ;
                        tempp=bag2; templ=bag2l;
                        bag2=bag1;  bag2l=bag1l;
                        bag1=tempp; bag1l=templ;}
                        fir2=false;
                    }
                    else if(fir3){
                        if(petnumber>=3){
                        Image tempp; int templ;
                        tempp=bag3; templ=bag3l;
                        bag3=bag2;  bag3l=bag2l;
                        bag2=bag1; bag2l=bag1l;
                        bag1=tempp; bag1l=templ;}
                        fir3=false;
                    }
                    else if(fir4){
                        if(petnumber>=4){
                        Image tempp; int templ;
                        tempp=bag4; templ=bag4l;
                        bag4=bag3;  bag4l=bag3l;
                        bag3=bag2;  bag3l=bag2l;
                        bag2=bag1; bag2l=bag1l;
                        bag1=tempp; bag1l=templ;}
                        fir4=false;
                    }
                    else if(fir5){
                        if(petnumber>=5){
                        Image tempp; int templ;
                        tempp=bag5; templ=bag5l;
                        bag5=bag4;  bag5l=bag4l;
                        bag4=bag3;  bag4l=bag3l;
                        bag3=bag2;  bag3l=bag2l;
                        bag2=bag1; bag2l=bag1l;
                        bag1=tempp; bag1l=templ;}
                        fir5=false;
                    }
                    else if(fir6){
                        if(petnumber>=6){
                        Image tempp; int templ;
                        tempp=bag6; templ=bag6l;
                        bag6=bag5;  bag6l=bag5l;
                        bag5=bag4;  bag5l=bag4l;
                        bag4=bag3;  bag4l=bag3l;
                        bag3=bag2;  bag3l=bag2l;
                        bag2=bag1; bag2l=bag1l;
                        bag1=tempp; bag1l=templ;}
                        fir6=false;
                    }
                    
                    if(rem2){
                        if(petnumber>=2&&bag2!=super1&&bag2!=super2&&bag2!=super3&&bag2!=super4&&bag2!=super5&&bag2!=super6){
                            bag2=bag3;  bag2l=bag3l;
                            bag3=bag4;  bag3l=bag4l;
                            bag4=bag5;  bag4l=bag5l;
                            bag5=bag6;  bag5l=bag6l;
                            bag6=nulimage;
                            petnumber--;
                        }
                        rem2=false;
                    }
                    if(rem3){
                        if(petnumber>=3&&bag3!=super1&&bag3!=super2&&bag3!=super3&&bag3!=super4&&bag3!=super5&&bag3!=super6){
                            bag3=bag4;  bag3l=bag4l;
                            bag4=bag5;  bag4l=bag5l;
                            bag5=bag6;  bag5l=bag6l;
                            bag6=nulimage;
                            petnumber--;
                        }
                        rem3=false;
                    }
                    if(rem4){
                        if(petnumber>=4&&bag4!=super1&&bag4!=super2&&bag4!=super3&&bag4!=super4&&bag4!=super5&&bag4!=super6){
                            bag4=bag5;  bag4l=bag5l;
                            bag5=bag6;  bag5l=bag6l;
                            bag6=nulimage;
                            petnumber--;
                        }
                        rem4=false;
                    }
                    if(rem5){
                        if(petnumber>=5&&bag5!=super1&&bag5!=super2&&bag5!=super3&&bag5!=super4&&bag5!=super5&&bag5!=super6){
                            bag5=bag6;  bag5l=bag6l;
                            bag6=nulimage;
                            petnumber--;
                        }
                        rem5=false;
                    }
                    if(rem6){
                        if(petnumber>=6&&bag6!=super1&&bag6!=super2&&bag6!=super3&&bag6!=super4&&bag6!=super5&&bag6!=super6){
                            bag6=nulimage;
                            petnumber--;
                        }
                        rem6=false;
                    }
                    
                    
                    //==========================================================evolution of pet to super pet
                    if(bag1l>=50&&bag1==super1){
                        bag1=bag1up;
                        super1=bag1up;
                    }
                    if(bag1l>=50&&bag1==thunderbaby&&bag2!=super2&&bag3!=super2&&bag4!=super2&&bag5!=super2&&bag6!=super2){
                        bag1=super2;
                    }
                    if(bag1l>=50&&bag1==popo&&bag2!=super3&&bag3!=super3&&bag4!=super3&&bag5!=super3&&bag6!=super3){
                        bag1=super3;
                    }
                    if(bag1l>=50&&bag1==urian&&bag2!=super4&&bag3!=super4&&bag4!=super4&&bag5!=super4&&bag6!=super4){
                        bag1=super4;
                    }
                    if(bag1l>=50&&bag1==mibir&&bag2!=super5&&bag3!=super5&&bag4!=super5&&bag5!=super5&&bag6!=super5){
                        bag1=super5;
                    }
                    if(bag1l>=50&&bag1==wibir&&bag2!=super6&&bag3!=super6&&bag4!=super6&&bag5!=super6&&bag6!=super6){
                        bag1=super6;
                    }
                    //top lvl 100
                    if(bag1l>100){bag1l=100;}
                    if(bag2l>100){bag2l=100;}
                    if(bag3l>100){bag3l=100;}
                    if(bag4l>100){bag4l=100;}
                    if(bag5l>100){bag5l=100;}
                    if(bag6l>100){bag6l=100;}
                    //==========================================================DRAW PETS when OPEN BAG
                    if(showpets%2==1&&!bosspage){
                        if(safecount%2==0){safecount++;}
                        g.drawImage(bag1, 10, 350, this);//g.setColor(Color.cyan);g.fillRect(remove1.x, remove1.y, remove1.width, remove1.height);g.setColor(Color.RED);g.drawString("REMOVE", remove1.x+37, remove1.y+23);
                        g.drawImage(bag2, 170, 350, this);
                        g.drawImage(bag3, 330, 350, this);
                        g.drawImage(bag4, 490, 350, this);
                        g.drawImage(bag5, 650, 350, this);
                        g.drawImage(bag6, 810, 350, this);
                        
                        if(remove2H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(remove2.x, remove2.y, remove2.width, remove2.height);
                        if(firstf2H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(firstfight2.x, firstfight2.y, firstfight2.width, firstfight2.height);
                        if(remove3H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(remove3.x, remove3.y, remove3.width, remove3.height);
                        if(firstf3H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(firstfight3.x, firstfight3.y, firstfight3.width, firstfight3.height);
                        if(remove4H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(remove4.x, remove4.y, remove4.width, remove4.height);
                        if(firstf4H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(firstfight4.x, firstfight4.y, firstfight4.width, firstfight4.height);
                        if(remove5H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(remove5.x, remove5.y, remove5.width, remove5.height);
                        if(firstf5H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(firstfight5.x, firstfight5.y, firstfight5.width, firstfight5.height);
                        if(remove6H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(remove6.x, remove6.y, remove6.width, remove6.height);
                        if(firstf6H){
                            g.setColor(Color.PINK);
                        }else{
                            g.setColor(Color.cyan);
                        }
                        g.fillRect(firstfight6.x, firstfight6.y, firstfight6.width, firstfight6.height);
                        g.setColor(Color.yellow);
                        g.drawString("lv: "+bag1l, 90, 300);
                        if(petnumber>=2)
                        g.drawString("lv: "+bag2l, 250, 300);
                        if(petnumber>=3)
                        g.drawString("lv: "+bag3l, 410, 300);
                        if(petnumber>=4)
                        g.drawString("lv: "+bag4l, 570, 300);
                        if(petnumber>=5)
                        g.drawString("lv: "+bag5l, 730, 300);
                        if(petnumber>=6)
                        g.drawString("lv: "+bag6l, 890, 300);
                        
                        g.setColor(Color.RED);
                        g.drawString("REMOVE", remove2.x+37, remove2.y+23);
                        g.drawString("FIRST", firstfight2.x+47, firstfight2.y+23);
                        g.drawString("REMOVE", remove3.x+37, remove3.y+23);
                        g.drawString("FIRST", firstfight3.x+47, firstfight3.y+23);
                        g.drawString("REMOVE", remove4.x+37, remove4.y+23);
                        g.drawString("FIRST", firstfight4.x+47, firstfight4.y+23);
                        g.drawString("REMOVE", remove5.x+37, remove5.y+23);
                        g.drawString("FIRST", firstfight5.x+47, firstfight5.y+23);
                        g.drawString("REMOVE", remove6.x+37, remove6.y+23);
                        g.drawString("FIRST", firstfight6.x+47, firstfight6.y+23);
                        
                    }
                    //==========================================================SAFE MODE
                    if(safecount%2==1){//safe
                        safe=safeon;
                    }else{//will not meet map pets
                        safe=safeoff;
                        boolean exe=false;
                        //randomly meet map pets;
                        if(x>A1.x-20&&x<A1.x+20&&y>A1.y-20&&y<A1.y+20&&!exe&&!bosspage){
                            fighting=true;
                            Ai1=true;
                            exe=true;
                        }
                        else if(x>A2.x-20&&x<A2.x+20&&y>A2.y-20&&y<A2.y+20&&!exe&&!bosspage){
                            fighting=true;
                            Ai2=true;
                            exe=true;
                        }
                        else if(x>A3.x-20&&x<A3.x+20&&y>A3.y-20&&y<A3.y+20&&!exe&&!bosspage){
                            fighting=true;
                            Ai3=true;
                            exe=true;
                        }
                        if(m1&&!p1){
                            if(!setbossnum){
                                bossleft=1;
                                setbossnum=true;
                            }
                            fighting=true;
                            exe=true;
                        }
                        if(m2&&!p2){
                            if(!setbossnum){
                                bossleft=2;
                                setbossnum=true;
                            }
                            fighting=true;
                            exe=true;
                        }
                        if(m3&&!p3){
                            if(!setbossnum){
                                bossleft=3;
                                setbossnum=true;
                            }
                            fighting=true;
                            exe=true;
                        }
                    }
                    
                    g.drawImage(safe, 50, 50, rootPane);
                    //==========================================================FIGHTING PAGE
                    if(fighting){
                        if(bosspage){
                            //if not meet requirement,boss seckill pets;
                            if(m1){
                                if(petnumber==6){
                                m1s=false;
                                }else{
                                m1s=true;
                                }
                            }
                            else if(m2){
                                if(petnumber==6&&bag1l==100&&bag2l==100&&bag3l==100&&bag4l==100&&bag5l==100&&bag6l==100){
                                    m2s=false;
                                }else{
                                    m2s=true;
                                }
                            }
                            else if(m3){
                                if((bag1==super1||bag1==super2||bag1==super3||bag1==super4||bag1==super5||bag1==super6)&&(bag2==super1||bag2==super2||bag2==super3||bag2==super4||bag2==super5||bag2==super6)&&(bag3==super1||bag3==super2||bag3==super3||bag3==super4||bag3==super5||bag3==super6)&&(bag4==super1||bag4==super2||bag4==super3||bag4==super4||bag4==super5||bag4==super6)&&(bag5==super1||bag5==super2||bag5==super3||bag5==super4||bag5==super5||bag5==super6)&&(bag6==super1||bag6==super2||bag6==super3||bag6==super4||bag6==super5||bag6==super6)&&bag1l==100&&bag2l==100&&bag3l==100&&bag4l==100&&bag5l==100&&bag6l==100){
                                    m3s=false;
                                }else{
                                    m3s=true;
                                }
                            }
                        }
                        catsuc=false;
                        catfai=false;
                        bagfull=false;
                        enemyskill=100;
                        savestring=false;
                        //boolean ed;
                        setXDirection(0);
                        setYDirection(0);
                        if(showpets%2==1){showpets++;}
                        g.drawImage(map1ft, 0, 0, this);
                        
                        //draw HP image
                        if(hpr>0){
                            ImageIcon bloodr=new ImageIcon("insertimage/bloodr.jpg");
                            bloodr.setImage(bloodr.getImage().getScaledInstance(hpr, 20, Image.SCALE_DEFAULT));
                            HPR=bloodr.getImage();

                        g.drawImage(HPR, 1020-hpr, 170, rootPane);
                        }
                        if(hpl>0){
                            ImageIcon blood=new ImageIcon("insertimage/blood.jpg");
                            blood.setImage(blood.getImage().getScaledInstance(hpl, 20, Image.SCALE_DEFAULT));
                            HPL=blood.getImage();

                        g.drawImage(HPL, 50, 170, rootPane);
                        }
                        
                        if(Ai1&&!Ai2&&!Ai3){                                    //if AI1
                            if(mapst){
                            mappet1=thunderbabyr;
                            map1l=2;
                            }
                            if(mapnd){
                            mappet1=bruckr;
                            map1l=2;
                            }
                            if(maprd){
                            mappet1=mibirr;
                            map1l=2;
                            }
                        if(!sethpl||!sethpr){
                            hpl=400;hpr=400;turn=true;sethpl=true;sethpr=true;
                        }
                        }
                        else if(Ai2&&!Ai1&&!Ai3){                               //if AI2
                            if(mapst){
                            mappet1=windbabyr;
                            map1l=1;
                            }
                            if(mapnd){
                            mappet1=popor;
                            map1l=1;
                            }
                            if(maprd){
                            mappet1=wunor;
                            map1l=1;
                            }
                        if(!sethpl||!sethpr){
                            hpl=400;hpr=400;turn=true;sethpl=true;sethpr=true;
                        }
                        }
                        else if(Ai3&&!Ai1&&!Ai2){                               //if AI3
                            if(mapst){
                            mappet1=leavebabyr;
                            map1l=3;
                            }
                            if(mapnd){
                            mappet1=urianr;
                            map1l=3;
                            }
                            if(maprd){
                            mappet1=wibirr;
                            map1l=3;
                            }
                        if(!sethpl||!sethpr){
                            hpl=400;hpr=400;turn=true;sethpl=true;sethpr=true;
                        }
                        }
                        else if(m1&&!setmboss){                                 //if mission A
                            mappet1=boss1;setmboss=true;
                        }
                        else if(m2&&!setmboss){                                 //if mission A
                            mappet1=boss2;setmboss=true;
                        }
                        else if(m3&&!setmboss){                                 //if mission A
                            mappet1=boss4;setmboss=true;
                        }
                        //draw String show pet level
                        g.drawImage(mappet1, 810, 350, rootPane);
                        if(mapst||mapnd||maprd)
                        g.drawString("LV: "+(map1l+mapadi), 855, 150);
                        else{
                        g.drawString("LV: ??", 855, 150);}
                        
                        //draw String show pet hp value
                        g.setFont(new Font("Arial", Font.BOLD,22));
                        g.setColor(Color.red);
                        g.drawString(hpl+"/400", 50, 215);
                        g.drawString(hpr+"/400", 947, 215);
                        g.setFont(new Font("Arial", Font.BOLD,16));
                        g.setColor(Color.yellow);
                        
                        
                        //draw a pet on screen
                        if(!drawpetst){
                        bagpet=bag1;bagl=bag1l;drawpetst=true;}
                        g.drawImage(bagpet, 50, 350, rootPane);
                        g.drawString("LV: "+bagl, 180, 150);
                        
                        //======================================================skill button
                        if(skill1H){
                            g.drawRect(skill1.x, skill1.y, skill1.width, skill1.height);
                        }
                        else if(skill2H){
                             g.drawRect(skill2.x, skill2.y, skill2.width, skill2.height);
                        }
                        else if(skill3H){
                            g.drawRect(skill3.x, skill3.y, skill3.width, skill3.height);
                        }
                        else if(skill4H){
                            g.drawRect(skill4.x, skill4.y, skill4.width, skill4.height);
                        }
                        
                        //======================================================Player turn to choose skill
                        if(turn){
                            //logic of damage cost
                            g.setColor(Color.gray);
                            g.setFont(new Font("Arial", Font.BOLD,50));
                            if(!(skill1s||skill2s||skill3s||skill4s)&&!win&&!lose)
                            g.drawString("YOUR TURN", 390, 300);
                            if(hpl>0){
                                if(bagpet==super6&&!sp){
                                    hpr=hpr-(400-hpl)/3;
                                    sp=true;
                                }
                                if(bagpet==super3&&!sp){
                                    hpl+=50;
                                    sp=true;
                                }
                                if(bagpet==super2&&!sp){
                                    hpr=hpr-hpr/5;
                                    sp=true;
                                }
                            if(skilla){
                                int ski1addi=0;
                                if(bosspage){
                                    ski1addi-=50;
                                }
                                skill1s=true;
                                if(!rand){
                                triple=r.nextInt(10);
                                rec1=r.nextInt(100);
                                rand=true;
                                }
                                if(bagpet==super4&&triple==1){
                                    addit1=3;
                                }
                                
                                if(skill1s){
                                    if(System.currentTimeMillis()/100-skillt<=5){
                                    g.setFont(new Font("Arial", Font.BOLD,32));
                                    g.setColor(Color.RED);
                                    g.drawString("HP-"+(90*addit1+ski1addi), 850, 250);
                                    if(rec1<=2){g.setColor(Color.GREEN);g.drawString("HP+100",70 , 250);}
                                    }else{
                                            skill1s=false;
                                    }
                                }
                                
                                if(!skill1s){
                                    if(bosspage)
                                        hpr-=90*addit1-50;
                                    if(!bosspage)
                                        hpr-=90*addit1;
                                
                                if(rec1<=2){
                                    hpl+=100;
                                }
                                
                                addit1=1;
                                rand=false;
                                turn=false;
                                skilla=false;
                                }
                                
                            }
                            else if(skillb){
                                skill2s=true;
                                int ski2addi=0;
                                if(bagpet==super5){
                                    ski2addi+=50;
                                }
                                if(bosspage){
                                    ski2addi-=90;
                                }
                                if(!rand){
                                if(bagpet!=super1){
                                    suc2=r.nextInt(100);}
                                else{
                                    suc2=20;
                                }
                                rand=true;
                                }
                                if(ski2addi+80+bagl<0){
                                    ski2addi=-80-bagl;
                                }
                                if(skill2s){
                                    //System.out.println("fai+\t"+System.currentTimeMillis()/1000+"\t"+skill4t/1000);
                                    if(System.currentTimeMillis()/100-skillt<=5){
                                    g.setFont(new Font("Arial", Font.BOLD,32));
                                    g.setColor(Color.RED);
                                    if(suc2>2)
                                    g.drawString("HP-"+(ski2addi+80+bagl), 850, 250);
                                    if(suc2<=2)
                                    g.drawString("MISS", 850, 250);
                                    }else{
                                            skill2s=false;
                                    }
                                }
                                
                                if(!skill2s){
                                
                                if(suc2>2){
                                    hpr-=80+bagl+ski2addi;
                                }
                                
                                rand=false;
                                turn=false;
                                skillb=false;
                                }
                            }
                            else if(skillc){
                                skill3s=true;
                                int ski3addit=0;
                                if(bosspage){
                                    ski3addit-=bagl;
                                }
                                if(skill3s){
                                    //System.out.println("fai+\t"+System.currentTimeMillis()/1000+"\t"+skill4t/1000);
                                    if(System.currentTimeMillis()/100-skillt<=5){
                                    g.setFont(new Font("Arial", Font.BOLD,32));
                                    g.setColor(Color.GREEN);
                                    g.drawString("HP+"+(100+bagl+ski3addit), 70, 250);
                                    }else{
                                            skill3s=false;
                                    }
                                }
                                
                                if(!skill3s){
                                    if(hpl<=300-bagl-ski3addit){
                                        hpl+=100+bagl+ski3addit;
                                    }                                               //modify to up to 250 down 400
                                    if(bagl>300){
                                        hpl=400;
                                    }
                                    turn=false;
                                    skillc=false;
                                }
                            }
                            else if(skilld){
                                if(!rand){
                                suc4=r.nextInt(20);//probability of seckill;
                                rec4=r.nextInt(50);
                                rand=true;
                                }
                                if(bosspage){
                                    suc4=1;
                                }
                                skill4s=true;
                                
                                if(skill4s){
                                    //System.out.println("fai+\t"+System.currentTimeMillis()/1000+"\t"+skill4t/1000);
                                    if(System.currentTimeMillis()/100-skillt<=5){
                                    g.setFont(new Font("Arial", Font.BOLD,32));
                                    
                                    if(suc4==0){g.setColor(Color.RED);g.drawString("SECKILL", 850, 250);}
                                    if(rec4==0){g.setColor(Color.GREEN);g.drawString("RECOVER", 70, 250);}
                                    if(suc4!=0&&rec4!=0){g.setColor(Color.RED);g.drawString("MISS", 850, 250);}
                                    }else{
                                            skill4s=false;
                                    }
                                }
                                
                                if(!skill4s){
                                
                                if(suc4==0){
                                    hpr=0;
                                }
                                if(rec4==0){
                                    hpl=400;
                                }
                                rand=false;
                                turn=false;
                                skilld=false;
                                }
                            }
                            }
                            if(hpl>400){
                                hpl=400;
                            }
                        }else if(!turn){                                        //enemy attact
                            int ailvl=0;
                            if(!setenemyrecover){
                                enemyadi=r.nextInt(90)+30;
                                setenemyrecover=true;
                            }
                            
                            if(m1s||m2s||m3s){
                                exer=true;
                                if(enemyskills){
                                    if(System.currentTimeMillis()/100-skillt<=10){
                                    g.setFont(new Font("Arial", Font.BOLD,32));
                                    g.setColor(Color.GREEN);g.drawString("RECOVER", 850, 250);
                                    g.setColor(Color.RED);g.drawString("SECKILL", 70, 250);
                                    }else{
                                        enemyskills=false;
                                    }
                                }
                                if(!enemyskills){
                                    hpl=0;
                                    hpr+=enemyadi;
                                    setenemyskill=false;
                                    enemyskills=true;
                                            damage=0;
                                            recover=0;
                                    turn=true;
                                }
                            }
                            if(mapst){
                                ailvl=1;enemyadi=0;
                            }else if(mapnd){
                                ailvl=5;enemyadi=50;
                            }else if(maprd){
                                ailvl=10;enemyadi=100;
                            }
                            if(hpr>0){
                                if(!setenemyskill){
                                if(!bosspage)
                            enemyskill=r.nextInt(10);
                                 if(bosspage)
                            enemyskill=r.nextInt(7)+3;
                                setenemyskill=true;}
                            sp=false;
                            if(enemyskill==7||enemyskill==8||enemyskill==9&&!exer){
                                    damage=150+ailvl*ailvl;
                                    recover=enemyadi;
                            }
                            if(enemyskill==6||enemyskill==5||enemyskill==3||enemyskill==4&&!exer){
                                    damage=80+map1l*map1l*map1l*map1l+ailvl*ailvl;
                                    recover=enemyadi;
                            }
                            else if(enemyskill==1||enemyskill==2&&!exer){
                                    if(hpr<=300-map1l){
                                        recover=100+map1l;
                                    }
                            }
                            else if(enemyskill==0&&!exer){
                                    int suc=r.nextInt(20);//probability of seckill;
                                    if(suc==0){
                                        damage=400;
                                    }
                            }
                            
                                if(!exer){
                                    if(enemyskills){
                                            if(System.currentTimeMillis()/100-skillt<=10){
                                            g.setFont(new Font("Arial", Font.BOLD,32));
                                            g.setColor(Color.GREEN);g.drawString("HP+"+recover, 850, 250);
                                            if(mapst){
                                            g.setColor(Color.RED);g.drawString("HP-"+damage*2/3, 70, 250);}
                                            else{
                                            g.setColor(Color.RED);g.drawString("HP-"+damage, 70, 250);}
                                            }else{
                                                enemyskills=false;
                                            }
                                        }
                                        if(!enemyskills){
                                            if(mapst){
                                            hpl-=damage*2/3;
                                            }else{
                                            hpl-=damage;
                                            }
                                            hpr+=recover;
                                            setenemyskill=false;
                                            enemyskills=true;
                                            damage=0;
                                            recover=0;
                                            turn=true;
                                        }
                                }
                                            exer=false;
                                            setenemyrecover=false;
                            }else{turn=true;}
                            if(hpr>400){
                                hpr=400;
                            }
                        }

                            //draw next pet when previous pet hp=0
                            if(hpr<=0){
                                if(bosspage){
                                bossleft--;
                                if(bossleft==0)
                                win=true;
                                else if(bossleft==1){
                                    if(m3){
                                        mappet1=boss6;
                                        hpr=400;
                                    }else if(m2){
                                        mappet1=boss3;
                                        hpr=400;
                                    }
                                }
                                else if(bossleft==2){
                                    mappet1=boss5;
                                    hpr=400;
                                }
                                }else{
                                    win=true;
                                }
                            }
                            else if(hpl<=0){
                                petused++;
                                
                                if(petnumber-petused==0){
                                lose=true;}
                                
                                else if(petnumber==6){
                                    if(petused==1){
                                        bagpet=bag2;
                                        bagl=bag2l;
                                        hpl=400;
                                    }
                                    else if(petused==2){
                                        bagpet=bag3;
                                        bagl=bag3l;
                                        hpl=400;
                                    }
                                    else if(petused==3){
                                        bagpet=bag4;
                                        bagl=bag4l;
                                        hpl=400;
                                    }
                                    else if(petused==4){
                                        bagpet=bag5;
                                        bagl=bag5l;
                                        hpl=400;
                                    }
                                    else if(petused==5){
                                        bagpet=bag6;
                                        bagl=bag6l;
                                        hpl=400;
                                    }
                                }
                                else if(petnumber==5){
                                    if(petused==1){
                                        bagpet=bag2;
                                        bagl=bag2l;
                                        hpl=400;
                                    }
                                    else if(petused==2){
                                        bagpet=bag3;
                                        bagl=bag3l;
                                        hpl=400;
                                    }
                                    else if(petused==3){
                                        bagpet=bag4;
                                        bagl=bag4l;
                                        hpl=400;
                                    }
                                    else if(petused==4){
                                        bagpet=bag5;
                                        bagl=bag5l;
                                        hpl=400;
                                    }
                                }
                                else if(petnumber==4){
                                    if(petused==1){
                                        bagpet=bag2;
                                        bagl=bag2l;
                                        hpl=400;
                                    }
                                    else if(petused==2){
                                        bagpet=bag3;
                                        bagl=bag3l;
                                        hpl=400;
                                    }
                                    else if(petused==3){
                                        bagpet=bag4;
                                        bagl=bag4l;
                                        hpl=400;
                                    }
                                }
                                else if(petnumber==3){
                                    if(petused==1){
                                        bagpet=bag2;
                                        bagl=bag2l;
                                        hpl=400;
                                    }
                                    else if(petused==2){
                                        bagpet=bag3;
                                        bagl=bag3l;
                                        hpl=400;
                                    }
                                }
                                else if(petnumber==2){
                                    if(petused==1){
                                        bagpet=bag2;
                                        bagl=bag2l;
                                        hpl=400;
                                    }
                                }
                            }
                        
                        //======================================================WIN
                        if(win&&!bosspage){
                            g.drawImage(youwin, 330, 230, rootPane);
                            if(winbt1H){//lvl up
                                g.setColor(Color.gray);
                                g.drawRect(levelup.x, levelup.y, levelup.width, levelup.height);
                                if(winbt1){
                                    if(petnumber==1){
                                    bag1l+=mapexp;}
                                    if(petnumber==2){
                                    bag1l+=mapexp;bag2l+=mapexp;}
                                    if(petnumber==3){
                                    bag1l+=mapexp;bag2l+=mapexp;bag3l+=mapexp;}
                                    if(petnumber==4){
                                    bag1l+=mapexp;bag2l+=mapexp;bag3l+=mapexp;bag4l+=mapexp;}
                                    if(petnumber==5){
                                    bag1l+=mapexp;bag2l+=mapexp;bag3l+=mapexp;bag4l+=mapexp;bag5l+=mapexp;}
                                    if(petnumber==6){
                                    bag1l+=mapexp;bag2l+=mapexp;bag3l+=mapexp;bag4l+=mapexp;bag5l+=mapexp;bag6l+=mapexp;}
                                    
                                    Ai1=false;Ai2=false;Ai3=false;turn=true;bossleft=1;
                                    petused=0;bagpet=bag1;bagl=bag1l;drawpetst=false;sethpl=false;sethpr=false;hpr=400;hpl=400;winbt1=false;win=false;fighting=false;
                                }
                            }
                            if(winbt2H){
                                Random r=new Random();
                                int catpos=r.nextInt(100);
                                int addit=0;
                                if(bag1l<=24){addit=24;}
                                else if(bag1l<50&&bag1l>24)
                                {addit=bag1l;}
                                else{
                                    addit=50;
                                }
                                g.setColor(Color.gray);
                                g.drawRect(catchpet.x, catchpet.y, catchpet.width, catchpet.height);
                                if(winbt2){//catch
                                    if(catpos>=0&&catpos<=addit){
                                    catsuc=true;
                                    if(Ai1){
                                        if(petnumber==1){
                                            if(mapst){bag2=thunderbaby;}
                                            if(mapnd){bag2=bruck;}
                                            if(maprd){bag2=mibir;}
                                            bag2l=1;petnumber++;}
                                        else if(petnumber==2){
                                            if(mapst){bag3=thunderbaby;}
                                            if(mapnd){bag3=bruck;}
                                            if(maprd){bag3=mibir;}
                                            bag3l=1;petnumber++;}
                                        else if(petnumber==3){
                                            if(mapst){bag4=thunderbaby;}
                                            if(mapnd){bag4=bruck;}
                                            if(maprd){bag4=mibir;}
                                            bag4l=1;petnumber++;}
                                        else if(petnumber==4){
                                            if(mapst){bag5=thunderbaby;}
                                            if(mapnd){bag5=bruck;}
                                            if(maprd){bag5=mibir;}
                                            bag5l=1;petnumber++;}
                                        else if(petnumber==5){
                                            if(mapst){bag6=thunderbaby;}
                                            if(mapnd){bag6=bruck;}
                                            if(maprd){bag6=mibir;}
                                            bag6l=1;petnumber++;}
                                        else{bagfull=true;
                                            
                                        }
                                    }
                                    else if(Ai2){
                                        if(petnumber==1){
                                            if(mapst){bag2=windbaby;}
                                            if(mapnd){bag2=popo;}
                                            if(maprd){bag2=wuno;}
                                            bag2l=1;petnumber++;}
                                        else if(petnumber==2){
                                            if(mapst){bag3=windbaby;}
                                            if(mapnd){bag3=popo;}
                                            if(maprd){bag3=wuno;}
                                            bag3l=1;petnumber++;}
                                        else if(petnumber==3){
                                            if(mapst){bag4=windbaby;}
                                            if(mapnd){bag4=popo;}
                                            if(maprd){bag4=wuno;}
                                            bag4l=1;petnumber++;}
                                        else if(petnumber==4){
                                            if(mapst){bag5=windbaby;}
                                            if(mapnd){bag5=popo;}
                                            if(maprd){bag5=wuno;}
                                            bag5l=1;petnumber++;}
                                        else if(petnumber==5){
                                            if(mapst){bag6=windbaby;}
                                            if(mapnd){bag6=popo;}
                                            if(maprd){bag6=wuno;}
                                            bag6l=1;petnumber++;}
                                        else{bagfull=true;
                                            
                                        }
                                    }
                                    else if(Ai3){
                                        if(petnumber==1){
                                            if(mapst){bag2=leavebaby;}
                                            if(mapnd){bag2=urian;}
                                            if(maprd){bag2=wibir;}
                                            bag2l=1;petnumber++;}
                                        else if(petnumber==2){
                                            if(mapst){bag3=leavebaby;}
                                            if(mapnd){bag3=urian;}
                                            if(maprd){bag3=wibir;}
                                            bag3l=1;petnumber++;}
                                        else if(petnumber==3){
                                            if(mapst){bag4=leavebaby;}
                                            if(mapnd){bag4=urian;}
                                            if(maprd){bag4=wibir;}
                                            bag4l=1;petnumber++;}
                                        else if(petnumber==4){
                                            if(mapst){bag5=leavebaby;}
                                            if(mapnd){bag5=urian;}
                                            if(maprd){bag5=wibir;}
                                            bag5l=1;petnumber++;}
                                        else if(petnumber==5){
                                            if(mapst){bag6=leavebaby;}
                                            if(mapnd){bag6=urian;}
                                            if(maprd){bag6=wibir;}
                                            bag6l=1;petnumber++;}
                                        else{bagfull=true;
                                            
                                        }
                                    }
                                    }else{
                                        catfai=true;
                                    }
                                    Ai1=false;Ai2=false;Ai3=false;turn=true;bag1l++;bossleft=1;
                                    petused=0;bagpet=bag1;bagl=bag1l;drawpetst=false;sethpl=false;sethpr=false;hpr=400;hpl=400;winbt2=false;win=false;fighting=false;
                                }
                            }
                        }
                            //g.drawRect(catchpet.x, catchpet.y, catchpet.width, catchpet.height);
                            //Ai1=false;Ai2=false;Ai3=false;fighting=false;hpr=400;hpl=400;turn=true;winbt1=false;winbt2=false;winbt3=false;win=false;
                        if(win&&bosspage){
                                if(m1){
                                    p1=true;setbossnum=false;m1s=false;setmboss=false;
                                    m1=false;turn=true;petused=0;bagpet=bag1;bagl=bag1l;drawpetst=false;sethpl=false;sethpr=false;hpr=400;hpl=400;win=false;fighting=false;
                                }
                                else if(m2){
                                    p2=true;setbossnum=false;m2s=false;setmboss=false;
                                    m2=false;turn=true;petused=0;bagpet=bag1;bagl=bag1l;drawpetst=false;sethpl=false;sethpr=false;hpr=400;hpl=400;win=false;fighting=false;
                                }
                                else if(m3){
                                    p3=true;setbossnum=false;m3s=false;setmboss=false;
                                    m3=false;turn=true;petused=0;bagpet=bag1;bagl=bag1l;drawpetst=false;sethpl=false;sethpr=false;hpr=400;hpl=400;win=false;fighting=false;
                                }
                            }
                        
                        //======================================================LOSE
                        else if(lose){
                            
                            if(m3){bag1l-=2;bag2l--;bag3l--;bag4l--;bag5l--;bag6l--;m3=false;}
                            
                            g.drawImage(youlose, 330, 230, rootPane);
                            if(winbt3H){
                                g.setColor(Color.gray);
                                g.drawRect(expup.x, expup.y, expup.width, expup.height);
                                if(winbt3){
                                    Ai1=false;Ai2=false;Ai3=false;m1=false;m2=false;m3=false;turn=true;bag1l++;setbossnum=false;m1s=false;m2s=false;m3s=false;setmboss=false;
                                    petused=0;bagpet=bag1;bagl=bag1l;drawpetst=false;sethpl=false;sethpr=false;hpr=400;hpl=400;winbt3=false;lose=false;fighting=false;
                                }
                            }
                        }
                    }
                    
                    //draw image to show if success of catching
                    if(bagfull){
                        if(System.currentTimeMillis()/1000-cattime<=1){
                            g.drawImage(bagfullima, 330, 100, rootPane);
                        }else{
                            bagfull=false;
                        }
                    }
                    else if(catsuc){
                        if(System.currentTimeMillis()/1000-cattime<=1){
                            g.drawImage(catchit, 330, 100, this);
                        }else{
                            catsuc=false;
                        }
                    }
                    else if(catfai){
                        if(System.currentTimeMillis()/1000-cattime<=1){
                            //System.out.println("fai+\t"+System.currentTimeMillis()/1000+"\t"+cattime);
                            g.drawImage(tryagain, 330, 100, this);
                        }else{
                            catfai=false;
                        }
                    }
                    
                    //save file
                    if(savefile){
                     savestring=true;
                    }
                    //string to show saved successfully
                    if(savestring){
                        if(System.currentTimeMillis()/1000-savtime<=1){
                            //System.out.println("fai+\t"+System.currentTimeMillis()/1000+"\t"+savtime);
                            g.setFont(new Font("Arial", Font.BOLD,28));
                            g.setColor(Color.WHITE);
                            g.drawString("SAVED", 480, 60);
                        }else{
                            savestring=false;
                        }
                    }
                    //show if pass of three mission
                    if(bosspage&&!fighting){
                        if(p1)g.drawImage(pass, 95, 200, this);
                        if(p2)g.drawImage(pass, 425, 200, this);
                        if(p3)g.drawImage(pass, 755, 200, this);
                    }
                
            //==============================================================END STORY GIF
                if(p1&&p2&&p3) {
                    g.drawImage(endgif,0,0,this);
                }
                 
            }
        }
        repaint();
    }
    
    public void ogRecord() throws IOException{
        //======================================================================READ FILE
        ArrayList<String>reco=new ArrayList();
        String Line;
        BufferedReader ReadFile = new BufferedReader(new FileReader("record.txt"));
        while((Line=ReadFile.readLine())!=null){
          reco.add(Line);
        }
        String[] coor;
        coor=reco.get(0).split(",");//x,y coor
        x=Integer.valueOf(coor[0]);
        y=Integer.valueOf(coor[0]);
        
        //map info
        if(Integer.valueOf(reco.get(1))==1){
            mapnd=false;maprd=false;bosspage=false;mapst=true;
        }
        if(Integer.valueOf(reco.get(1))==2){
            mapst=false;maprd=false;bosspage=false;mapnd=true;
        }
        if(Integer.valueOf(reco.get(1))==3){
            mapst=false;mapnd=false;bosspage=false;maprd=true;
        }
        if(Integer.valueOf(reco.get(1))==4){
            mapst=false;mapnd=false;maprd=false;bosspage=true;
        }
        
        //boss info
        String[] bossmisspass;
        bossmisspass=reco.get(2).split(",");
        if(Integer.valueOf(bossmisspass[0])==1){
            p1=true;
        }
        if(Integer.valueOf(bossmisspass[1])==1){
            p2=true;
        }
        if(Integer.valueOf(bossmisspass[2])==1){
            p3=true;
        }
        
        //pet lvl
        String[] ptlvl;
        ptlvl=reco.get(3).split(",");
        bag1l=Integer.valueOf(ptlvl[0]);
        bag2l=Integer.valueOf(ptlvl[1]);
        bag3l=Integer.valueOf(ptlvl[2]);
        bag4l=Integer.valueOf(ptlvl[3]);
        bag5l=Integer.valueOf(ptlvl[4]);
        bag6l=Integer.valueOf(ptlvl[5]);
        
        //pet info
        String[] petimages;
        petimages=reco.get(4).split(",");
        if(Integer.valueOf(petimages[0])==0){bag1=nulimage;}
        if(Integer.valueOf(petimages[0])==2){bag1=super2;}
        if(Integer.valueOf(petimages[0])==3){bag1=super3;}
        if(Integer.valueOf(petimages[0])==4){bag1=super4;}
        if(Integer.valueOf(petimages[0])==5){bag1=super5;}
        if(Integer.valueOf(petimages[0])==6){bag1=super6;}
        if(Integer.valueOf(petimages[0])==11){bag1=mainpet1d;bag1up=mainpet1up;super1=bag1;}
        if(Integer.valueOf(petimages[0])==12){bag1=mainpet2d;bag1up=mainpet2up;super1=bag1;}
        if(Integer.valueOf(petimages[0])==13){bag1=mainpet3d;bag1up=mainpet3up;super1=bag1;}
        if(Integer.valueOf(petimages[0])==111){bag1=mainpet1up;bag1up=mainpet1up;super1=bag1;}
        if(Integer.valueOf(petimages[0])==121){bag1=mainpet2up;bag1up=mainpet2up;super1=bag1;}
        if(Integer.valueOf(petimages[0])==131){bag1=mainpet3up;bag1up=mainpet3up;super1=bag1;}
        if(Integer.valueOf(petimages[0])==21){bag1=thunderbaby;}
        if(Integer.valueOf(petimages[0])==22){bag1=windbaby;}
        if(Integer.valueOf(petimages[0])==23){bag1=leavebaby;}
        if(Integer.valueOf(petimages[0])==31){bag1=wibir;}
        if(Integer.valueOf(petimages[0])==32){bag1=mibir;}
        if(Integer.valueOf(petimages[0])==33){bag1=wuno;}
        if(Integer.valueOf(petimages[0])==41){bag1=popo;}
        if(Integer.valueOf(petimages[0])==42){bag1=urian;}
        if(Integer.valueOf(petimages[0])==43){bag1=bruck;}
        
        if(Integer.valueOf(petimages[1])==0){bag2=nulimage;}
        if(Integer.valueOf(petimages[1])==2){bag2=super2;}
        if(Integer.valueOf(petimages[1])==3){bag2=super3;}
        if(Integer.valueOf(petimages[1])==4){bag2=super4;}
        if(Integer.valueOf(petimages[1])==5){bag2=super5;}
        if(Integer.valueOf(petimages[1])==6){bag2=super6;}
        if(Integer.valueOf(petimages[1])==11){bag2=mainpet1d;bag1up=mainpet1up;super1=bag2;}
        if(Integer.valueOf(petimages[1])==12){bag2=mainpet2d;bag1up=mainpet2up;super1=bag2;}
        if(Integer.valueOf(petimages[1])==13){bag2=mainpet3d;bag1up=mainpet3up;super1=bag2;}
        if(Integer.valueOf(petimages[1])==111){bag2=mainpet1up;bag1up=mainpet1up;super1=bag2;}
        if(Integer.valueOf(petimages[1])==121){bag2=mainpet2up;bag1up=mainpet2up;super1=bag2;}
        if(Integer.valueOf(petimages[1])==131){bag2=mainpet3up;bag1up=mainpet3up;super1=bag2;}
        if(Integer.valueOf(petimages[1])==21){bag2=thunderbaby;}
        if(Integer.valueOf(petimages[1])==22){bag2=windbaby;}
        if(Integer.valueOf(petimages[1])==23){bag2=leavebaby;}
        if(Integer.valueOf(petimages[1])==31){bag2=wibir;}
        if(Integer.valueOf(petimages[1])==32){bag2=mibir;}
        if(Integer.valueOf(petimages[1])==33){bag2=wuno;}
        if(Integer.valueOf(petimages[1])==41){bag2=popo;}
        if(Integer.valueOf(petimages[1])==42){bag2=urian;}
        if(Integer.valueOf(petimages[1])==43){bag2=bruck;}

        if(Integer.valueOf(petimages[2])==0){bag3=nulimage;}
        if(Integer.valueOf(petimages[2])==2){bag3=super2;}
        if(Integer.valueOf(petimages[2])==3){bag3=super3;}
        if(Integer.valueOf(petimages[2])==4){bag3=super4;}
        if(Integer.valueOf(petimages[2])==5){bag3=super5;}
        if(Integer.valueOf(petimages[2])==6){bag3=super6;}
        if(Integer.valueOf(petimages[2])==11){bag3=mainpet1d;bag1up=mainpet1up;super1=bag3;}
        if(Integer.valueOf(petimages[2])==12){bag3=mainpet2d;bag1up=mainpet2up;super1=bag3;}
        if(Integer.valueOf(petimages[2])==13){bag3=mainpet3d;bag1up=mainpet3up;super1=bag3;}
        if(Integer.valueOf(petimages[2])==111){bag3=mainpet1up;bag1up=mainpet1up;super1=bag3;}
        if(Integer.valueOf(petimages[2])==121){bag3=mainpet2up;bag1up=mainpet2up;super1=bag3;}
        if(Integer.valueOf(petimages[2])==131){bag3=mainpet3up;bag1up=mainpet3up;super1=bag3;}
        if(Integer.valueOf(petimages[2])==21){bag3=thunderbaby;}
        if(Integer.valueOf(petimages[2])==22){bag3=windbaby;}
        if(Integer.valueOf(petimages[2])==23){bag3=leavebaby;}
        if(Integer.valueOf(petimages[2])==31){bag3=wibir;}
        if(Integer.valueOf(petimages[2])==32){bag3=mibir;}
        if(Integer.valueOf(petimages[2])==33){bag3=wuno;}
        if(Integer.valueOf(petimages[2])==41){bag3=popo;}
        if(Integer.valueOf(petimages[2])==42){bag3=urian;}
        if(Integer.valueOf(petimages[2])==43){bag3=bruck;}

        if(Integer.valueOf(petimages[3])==0){bag4=nulimage;}
        if(Integer.valueOf(petimages[3])==2){bag4=super2;}
        if(Integer.valueOf(petimages[3])==3){bag4=super3;}
        if(Integer.valueOf(petimages[3])==4){bag4=super4;}
        if(Integer.valueOf(petimages[3])==5){bag4=super5;}
        if(Integer.valueOf(petimages[3])==6){bag4=super6;}
        if(Integer.valueOf(petimages[3])==11){bag4=mainpet1d;bag1up=mainpet1up;super1=bag4;}
        if(Integer.valueOf(petimages[3])==12){bag4=mainpet2d;bag1up=mainpet2up;super1=bag4;}
        if(Integer.valueOf(petimages[3])==13){bag4=mainpet3d;bag1up=mainpet3up;super1=bag4;}
        if(Integer.valueOf(petimages[3])==111){bag4=mainpet1up;bag1up=mainpet1up;super1=bag4;}
        if(Integer.valueOf(petimages[3])==121){bag4=mainpet2up;bag1up=mainpet2up;super1=bag4;}
        if(Integer.valueOf(petimages[3])==131){bag4=mainpet3up;bag1up=mainpet3up;super1=bag4;}
        if(Integer.valueOf(petimages[3])==21){bag4=thunderbaby;}
        if(Integer.valueOf(petimages[3])==22){bag4=windbaby;}
        if(Integer.valueOf(petimages[3])==23){bag4=leavebaby;}
        if(Integer.valueOf(petimages[3])==31){bag4=wibir;}
        if(Integer.valueOf(petimages[3])==32){bag4=mibir;}
        if(Integer.valueOf(petimages[3])==33){bag4=wuno;}
        if(Integer.valueOf(petimages[3])==41){bag4=popo;}
        if(Integer.valueOf(petimages[3])==42){bag4=urian;}
        if(Integer.valueOf(petimages[3])==43){bag4=bruck;}

        if(Integer.valueOf(petimages[4])==0){bag5=nulimage;}
        if(Integer.valueOf(petimages[4])==2){bag5=super2;}
        if(Integer.valueOf(petimages[4])==3){bag5=super3;}
        if(Integer.valueOf(petimages[4])==4){bag5=super4;}
        if(Integer.valueOf(petimages[4])==5){bag5=super5;}
        if(Integer.valueOf(petimages[4])==6){bag5=super6;}
        if(Integer.valueOf(petimages[4])==11){bag5=mainpet1d;bag1up=mainpet1up;super1=bag5;}
        if(Integer.valueOf(petimages[4])==12){bag5=mainpet2d;bag1up=mainpet2up;super1=bag5;}
        if(Integer.valueOf(petimages[4])==13){bag5=mainpet3d;bag1up=mainpet3up;super1=bag5;}
        if(Integer.valueOf(petimages[4])==111){bag5=mainpet1up;bag1up=mainpet1up;super1=bag5;}
        if(Integer.valueOf(petimages[4])==121){bag5=mainpet2up;bag1up=mainpet2up;super1=bag5;}
        if(Integer.valueOf(petimages[4])==131){bag5=mainpet3up;bag1up=mainpet3up;super1=bag5;}
        if(Integer.valueOf(petimages[4])==21){bag5=thunderbaby;}
        if(Integer.valueOf(petimages[4])==22){bag5=windbaby;}
        if(Integer.valueOf(petimages[4])==23){bag5=leavebaby;}
        if(Integer.valueOf(petimages[4])==31){bag5=wibir;}
        if(Integer.valueOf(petimages[4])==32){bag5=mibir;}
        if(Integer.valueOf(petimages[4])==33){bag5=wuno;}
        if(Integer.valueOf(petimages[4])==41){bag5=popo;}
        if(Integer.valueOf(petimages[4])==42){bag5=urian;}
        if(Integer.valueOf(petimages[4])==43){bag5=bruck;}

        if(Integer.valueOf(petimages[5])==0){bag6=nulimage;}
        if(Integer.valueOf(petimages[5])==2){bag6=super2;}
        if(Integer.valueOf(petimages[5])==3){bag6=super3;}
        if(Integer.valueOf(petimages[5])==4){bag6=super4;}
        if(Integer.valueOf(petimages[5])==5){bag6=super5;}
        if(Integer.valueOf(petimages[5])==6){bag6=super6;}
        if(Integer.valueOf(petimages[5])==11){bag6=mainpet1d;bag1up=mainpet1up;super1=bag6;}
        if(Integer.valueOf(petimages[5])==12){bag6=mainpet2d;bag1up=mainpet2up;super1=bag6;}
        if(Integer.valueOf(petimages[5])==13){bag6=mainpet3d;bag1up=mainpet3up;super1=bag6;}
        if(Integer.valueOf(petimages[5])==111){bag6=mainpet1up;bag1up=mainpet1up;super1=bag6;}
        if(Integer.valueOf(petimages[5])==121){bag6=mainpet2up;bag1up=mainpet2up;super1=bag6;}
        if(Integer.valueOf(petimages[5])==131){bag6=mainpet3up;bag1up=mainpet3up;super1=bag6;}
        if(Integer.valueOf(petimages[5])==21){bag6=thunderbaby;}
        if(Integer.valueOf(petimages[5])==22){bag6=windbaby;}
        if(Integer.valueOf(petimages[5])==23){bag6=leavebaby;}
        if(Integer.valueOf(petimages[5])==31){bag6=wibir;}
        if(Integer.valueOf(petimages[5])==32){bag6=mibir;}
        if(Integer.valueOf(petimages[5])==33){bag6=wuno;}
        if(Integer.valueOf(petimages[5])==41){bag6=popo;}
        if(Integer.valueOf(petimages[5])==42){bag6=urian;}
        if(Integer.valueOf(petimages[5])==43){bag6=bruck;}
        
        //pet numbers
        if(bag1!=nulimage){petnumber=1;}
        if(bag2!=nulimage){petnumber=2;}
        if(bag3!=nulimage){petnumber=3;}
        if(bag4!=nulimage){petnumber=4;}
        if(bag5!=nulimage){petnumber=5;}
        if(bag6!=nulimage){petnumber=6;}
        
        
        ReadFile.close();
        
       
        
        f=true;
    }
    
    public void saveRecord() throws IOException{
        //======================================================================SAVE RECORD
        if(savefile){
            BufferedWriter WriteFile=new BufferedWriter (new FileWriter("record.txt"));
            //x , y coor
            WriteFile.write(x+","+y);
            WriteFile.newLine();
            //map
            int map=0;
            if(mapst){
                map=1;}
            if(mapnd){
                map=2;}
            if(maprd){
                map=3;}
            if(bosspage){
                map=4;}
            WriteFile.write(map+"");
            WriteFile.newLine();
            //boss info
            int pa,pb,pc;
            if(p1){pa=1;}else{pa=0;}
            if(p2){pb=1;}else{pb=0;}
            if(p3){pc=1;}else{pc=0;}
            WriteFile.write(pa+","+pb+","+pc);
            WriteFile.newLine();
            //bag info
            WriteFile.write(bag1l+","+bag2l+","+bag3l+","+bag4l+","+bag5l+","+bag6l);
            WriteFile.newLine();
            //pets info
            int b1=0,b2=0,b3=0,b4=0,b5=0,b6=0;
            if(bag1==nulimage){b1=0;}
            if(bag1==super2){b1=2;}
            if(bag1==super3){b1=3;}
            if(bag1==super4){b1=4;}
            if(bag1==super5){b1=5;}
            if(bag1==super6){b1=6;}
            if(bag1==mainpet1d){b1=11;}
            if(bag1==mainpet2d){b1=12;}
            if(bag1==mainpet3d){b1=13;}
            if(bag1==mainpet1up){b1=111;}
            if(bag1==mainpet2up){b1=121;}
            if(bag1==mainpet3up){b1=131;}
            if(bag1==thunderbaby){b1=21;}
            if(bag1==windbaby){b1=22;}
            if(bag1==leavebaby){b1=23;}
            if(bag1==wibir){b1=31;}
            if(bag1==mibir){b1=32;}
            if(bag1==wuno){b1=33;}
            if(bag1==popo){b1=41;}
            if(bag1==urian){b1=42;}
            if(bag1==bruck){b1=43;}
            
            if(bag2==nulimage){b2=0;}
            if(bag2==super2){b2=2;}
            if(bag2==super3){b2=3;}
            if(bag2==super4){b2=4;}
            if(bag2==super5){b2=5;}
            if(bag2==super6){b2=6;}
            if(bag2==mainpet1d){b2=11;}
            if(bag2==mainpet2d){b2=12;}
            if(bag2==mainpet3d){b2=13;}
            if(bag2==mainpet1up){b2=111;}
            if(bag2==mainpet2up){b2=121;}
            if(bag2==mainpet3up){b2=131;}
            if(bag2==thunderbaby){b2=21;}
            if(bag2==windbaby){b2=22;}
            if(bag2==leavebaby){b2=23;}
            if(bag2==wibir){b2=31;}
            if(bag2==mibir){b2=32;}
            if(bag2==wuno){b2=33;}
            if(bag2==popo){b2=41;}
            if(bag2==urian){b2=42;}
            if(bag2==bruck){b2=43;}

            if(bag3==nulimage){b3=0;}
            if(bag3==super2){b3=2;}
            if(bag3==super3){b3=3;}
            if(bag3==super4){b3=4;}
            if(bag3==super5){b3=5;}
            if(bag3==super6){b3=6;}
            if(bag3==mainpet1d){b3=11;}
            if(bag3==mainpet2d){b3=12;}
            if(bag3==mainpet3d){b3=13;}
            if(bag3==mainpet1up){b3=111;}
            if(bag3==mainpet2up){b3=121;}
            if(bag3==mainpet3up){b3=131;}
            if(bag3==thunderbaby){b3=21;}
            if(bag3==windbaby){b3=22;}
            if(bag3==leavebaby){b3=23;}
            if(bag3==wibir){b3=31;}
            if(bag3==mibir){b3=32;}
            if(bag3==wuno){b3=33;}
            if(bag3==popo){b3=41;}
            if(bag3==urian){b3=42;}
            if(bag3==bruck){b3=43;}
            
            if(bag4==nulimage){b4=0;}
            if(bag4==super2){b4=2;}
            if(bag4==super3){b4=3;}
            if(bag4==super4){b4=4;}
            if(bag4==super5){b4=5;}
            if(bag4==super6){b4=6;}
            if(bag4==mainpet1d){b4=11;}
            if(bag4==mainpet2d){b4=12;}
            if(bag4==mainpet3d){b4=13;}
            if(bag4==mainpet1up){b4=111;}
            if(bag4==mainpet2up){b4=121;}
            if(bag4==mainpet3up){b4=131;}
            if(bag4==thunderbaby){b4=21;}
            if(bag4==windbaby){b4=22;}
            if(bag4==leavebaby){b4=23;}
            if(bag4==wibir){b4=31;}
            if(bag4==mibir){b4=32;}
            if(bag4==wuno){b4=33;}
            if(bag4==popo){b4=41;}
            if(bag4==urian){b4=42;}
            if(bag4==bruck){b4=43;}
            
            if(bag5==nulimage){b5=0;}
            if(bag5==super2){b5=2;}
            if(bag5==super3){b5=3;}
            if(bag5==super4){b5=4;}
            if(bag5==super5){b5=5;}
            if(bag5==super6){b5=6;}
            if(bag5==mainpet1d){b5=11;}
            if(bag5==mainpet2d){b5=12;}
            if(bag5==mainpet3d){b5=13;}
            if(bag5==mainpet1up){b5=111;}
            if(bag5==mainpet2up){b5=121;}
            if(bag5==mainpet3up){b5=131;}
            if(bag5==thunderbaby){b5=21;}
            if(bag5==windbaby){b5=22;}
            if(bag5==leavebaby){b5=23;}
            if(bag5==wibir){b5=31;}
            if(bag5==mibir){b5=32;}
            if(bag5==wuno){b5=33;}
            if(bag5==popo){b5=41;}
            if(bag5==urian){b5=42;}
            if(bag5==bruck){b5=43;}
            
            if(bag6==nulimage){b6=0;}
            if(bag6==super2){b6=2;}
            if(bag6==super3){b6=3;}
            if(bag6==super4){b6=4;}
            if(bag6==super5){b6=5;}
            if(bag6==super6){b6=6;}
            if(bag6==mainpet1d){b6=11;}
            if(bag6==mainpet2d){b6=12;}
            if(bag6==mainpet3d){b6=13;}
            if(bag6==mainpet1up){b6=111;}
            if(bag6==mainpet2up){b6=121;}
            if(bag6==mainpet3up){b6=131;}
            if(bag6==thunderbaby){b6=21;}
            if(bag6==windbaby){b6=22;}
            if(bag6==leavebaby){b6=23;}
            if(bag6==wibir){b6=31;}
            if(bag6==mibir){b6=32;}
            if(bag6==wuno){b6=33;}
            if(bag6==popo){b6=41;}
            if(bag6==urian){b6=42;}
            if(bag6==bruck){b6=43;}

            WriteFile.write(b1+","+b2+","+b3+","+b4+","+b5+","+b6);
            
            WriteFile.close();
            savefile=false;
        }
    }
    
    public static void main(String[] args) {
            FiF fif=new FiF();
            Thread t=new Thread(fif);
            t.start();
            
            //Music mus = new Music();
            //==================================================================THREE AI EVERY MAP
            Thread t1=new Thread(A1);
            t1.start();
            Thread t2=new Thread(A2);
            t2.start();
            Thread t3=new Thread(A3);
            t3.start();
            
    }
    
    public class AL extends KeyAdapter{
        @Override
        public void keyPressed(KeyEvent e){
            int keyCode=e.getKeyCode();
            //==================================================================MOVING
            if(keyCode==e.VK_LEFT){
                if(!fighting){
                setXDirection(-1);
                runman=runmandb;
                }
                else{setXDirection(0);}
            }
            else if(keyCode==e.VK_RIGHT){
                if(!fighting){
                setXDirection(+1);
                runman=runmand;
                }
                else{setXDirection(0);}
            }
            else if(keyCode==e.VK_UP){
                if(!fighting){
                setYDirection(-1);
                if(runman==runmansb)
                runman=runmandb;
                else runman=runmand;
                }
                else{setYDirection(0);}
            }
            else if(keyCode==e.VK_DOWN){
                if(!fighting){
                setYDirection(+1);
                if(runman==runmansb)
                runman=runmandb;
                else runman=runmand;
                }
                else{setYDirection(0);}
            }
            if(keyCode==e.VK_S){
                sH=true;
                start=true;
            }
            if(keyCode==e.VK_B){
                help=false;
            }
            if(keyCode==e.VK_H){
                help=true;
            }
            //win directly
            if(keyCode==e.VK_W){
                if(fighting)
                    win=true;
            }
            if(keyCode==e.VK_U){
                bug=true;
            }
        }
        @Override
        public void keyReleased(KeyEvent e){
            int keyCode=e.getKeyCode();
            if(keyCode==e.VK_LEFT){
                setXDirection(0);
                runman=runmansb;
            }
            else if(keyCode==e.VK_RIGHT){
                setXDirection(0);
                runman=runmans;
            }
            else if(keyCode==e.VK_UP){
                setYDirection(0);
                if(runman==runmand)
                runman=runmans;
                else runman=runmansb;
            }
            else if(keyCode==e.VK_DOWN){
                setYDirection(0);
                if(runman==runmand)
                runman=runmans;
                else runman=runmansb;
            }
            
        }
    }
        public class ML extends MouseAdapter{
            
            @Override
            public void mouseMoved(MouseEvent e){
                int mx=e.getX();
                int my=e.getY();
                if(mx>startButton.x && mx<startButton.x+startButton.width &&my>startButton.y && my<startButton.y+startButton.height){
                    sH=true;
                }else sH=false;
                if(mx>dButton.x && mx<dButton.x+dButton.width &&my>dButton.y && my<dButton.y+dButton.height){
                    dH=true;
                }else dH=false;
                if(mx>oldButton.x && mx<oldButton.x+oldButton.width &&my>oldButton.y && my<oldButton.y+oldButton.height){
                    oH=true;
                }else oH=false;
                if(mx>newButton.x && mx<newButton.x+newButton.width &&my>newButton.y && my<newButton.y+newButton.height){
                    nH=true;
                }else nH=false;
                if(mx>firstchoice.x && mx < firstchoice.x+firstchoice.width &&my > firstchoice.y && my < firstchoice.y+firstchoice.height)
                {firstH=true;mainpet1=mainpet1d;}else {firstH=false;mainpet1=mainpet1s;}
                if(mx>secondchoice.x && mx<secondchoice.x+secondchoice.width &&my>secondchoice.y && my<secondchoice.y+secondchoice.height)
                {secondH=true;mainpet2=mainpet2d;}else {secondH=false;mainpet2=mainpet2s;}
                if(mx>thirdchoice.x && mx<thirdchoice.x+thirdchoice.width &&my>thirdchoice.y && my<thirdchoice.y+thirdchoice.height)
                {thirdH=true;mainpet3=mainpet3d;}else {thirdH=false;mainpet3=mainpet3s;}
                
                
                
                if(fighting){
                    if(mx>skill1.x && mx<skill1.x+skill1.width &&my>skill1.y && my<skill1.y+skill1.height){
                    skill1H=true;
                    }else{skill1H=false;}
                    if(mx>skill2.x && mx<skill2.x+skill2.width &&my>skill2.y && my<skill2.y+skill2.height){
                    skill2H=true;
                    }else{skill2H=false;}
                    if(mx>skill3.x && mx<skill3.x+skill3.width &&my>skill3.y && my<skill3.y+skill3.height){
                    skill3H=true;
                    }else{skill3H=false;}
                    if(mx>skill4.x && mx<skill4.x+skill4.width &&my>skill4.y && my<skill4.y+skill4.height){
                    skill4H=true;
                    }else{skill4H=false;}
                    if(win){
                        if(mx>levelup.x && mx<levelup.x+levelup.width &&my>levelup.y && my<levelup.y+levelup.height){
                        winbt1H=true;
                        }else{winbt1H=false;}
                        if(mx>catchpet.x && mx<catchpet.x+catchpet.width &&my>catchpet.y && my<catchpet.y+catchpet.height){
                        winbt2H=true;
                        }else{winbt2H=false;}
                    }
                    if(lose){
                        if(mx>expup.x && mx<expup.x+expup.width &&my>expup.y && my<expup.y+expup.height){
                        winbt3H=true;
                        }else{winbt3H=false;}
                    }
                }
                if(game&&!fighting){
                    if(mx>505 && mx<555 &&my>0 && my<50){
                        savetri=true;
                    }else{savetri=false;}
                    if(mx>petbags.x && mx<petbags.x+petbags.width &&my>petbags.y && my<petbags.y+petbags.height){
                        showpetsH=true;
                    }else{showpetsH=false;}
                    if(showpets%2==1){
                    if(mx>firstfight2.x && mx<firstfight2.x+firstfight2.width &&my>firstfight2.y && my<firstfight2.y+firstfight2.height){
                    firstf2H=true;
                    }else{firstf2H=false;}
                    if(mx>firstfight3.x && mx<firstfight3.x+firstfight3.width &&my>firstfight3.y && my<firstfight3.y+firstfight3.height){
                    firstf3H=true;
                    }else{firstf3H=false;}
                    if(mx>firstfight4.x && mx<firstfight4.x+firstfight4.width &&my>firstfight4.y && my<firstfight4.y+firstfight4.height){
                    firstf4H=true;
                    }else{firstf4H=false;}
                    if(mx>firstfight5.x && mx<firstfight5.x+firstfight5.width &&my>firstfight5.y && my<firstfight5.y+firstfight5.height){
                    firstf5H=true;
                    }else{firstf5H=false;}
                    if(mx>firstfight6.x && mx<firstfight6.x+firstfight6.width &&my>firstfight6.y && my<firstfight6.y+firstfight6.height){
                    firstf6H=true;
                    }else{firstf6H=false;}
                    if(mx>remove2.x && mx<remove2.x+remove2.width &&my>remove2.y && my<remove2.y+remove2.height){
                    remove2H=true;
                    }else{remove2H=false;}
                    if(mx>remove3.x && mx<remove3.x+remove3.width &&my>remove3.y && my<remove3.y+remove3.height){
                    remove3H=true;
                    }else{remove3H=false;}
                    if(mx>remove4.x && mx<remove4.x+remove4.width &&my>remove4.y && my<remove4.y+remove4.height){
                    remove4H=true;
                    }else{remove4H=false;}
                    if(mx>remove5.x && mx<remove5.x+remove5.width &&my>remove5.y && my<remove5.y+remove5.height){
                    remove5H=true;
                    }else{remove5H=false;}
                    if(mx>remove6.x && mx<remove6.x+remove6.width &&my>remove6.y && my<remove6.y+remove6.height){
                    remove6H=true;
                    }else{remove6H=false;}
                    
                    }
                }
                
                if(bosspage){
                    if(mx>95 && mx<315 &&my>200 && my<420){
                    m1H=true;
                    }else{m1H=false;}
                    if(mx>425 && mx<645 &&my>200 && my<420){
                    m2H=true;
                    }else{m2H=false;}
                    if(mx>755 && mx<975 &&my>200 && my<420){
                    m3H=true;
                    }else{m3H=false;}
                }
            }
            @Override
            public void mousePressed (MouseEvent e){
                int mx=e.getX();
                int my=e.getY();
                if(!start){
                    if(mx > startButton.x && mx < startButton.x+startButton.width && my> startButton.y && my<startButton.y+startButton.height)
                    {start=true;}
                    if(!ng){
                    if(mx > dButton.x && mx < dButton.x+dButton.width && my> dButton.y && my<dButton.y+dButton.height)
                    {help=true;start=false;}
                    }
                }
                if(help){
                    if(mx > 0 && mx < 1060 &&my > 0 && my < 760)
                    {next++;}
                }
                if(start&&!game){
                    if(mx > oldButton.x && mx < oldButton.x+oldButton.width &&my > oldButton.y && my < oldButton.y+oldButton.height)
                    {og=true;f=false;}
                    if(mx>newButton.x && mx<newButton.x+newButton.width &&my>newButton.y && my<newButton.y+newButton.height)
                    {ng=true;}
                    if(!ng){
                    if(mx > dButton.x && mx < dButton.x+dButton.width && my> dButton.y && my<dButton.y+dButton.height)
                    {help=true;start=false;}
                    }
                }
                if(ng){
                    if(mx>firstchoice.x && mx < firstchoice.x+firstchoice.width &&my > firstchoice.y && my < firstchoice.y+firstchoice.height)
                    {firstpet=true;}
                    if(mx>secondchoice.x && mx<secondchoice.x+secondchoice.width &&my>secondchoice.y && my<secondchoice.y+secondchoice.height)
                    {secondpet=true;}
                    if(mx>thirdchoice.x && mx<thirdchoice.x+thirdchoice.width &&my>thirdchoice.y && my<thirdchoice.y+thirdchoice.height)
                    {thirdpet=true;}
                }
                if(fighting){
                    if(mx>skill1.x && mx<skill1.x+skill1.width &&my>skill1.y && my<skill1.y+skill1.height){
                    skill1H=false;
                    skilla=true;
                    skillt=System.currentTimeMillis()/100;
                    }
                    if(mx>skill2.x && mx<skill2.x+skill2.width &&my>skill2.y && my<skill2.y+skill2.height){
                    skill2H=false;
                    skillb=true;
                    skillt=System.currentTimeMillis()/100;
                    }
                    if(mx>skill3.x && mx<skill3.x+skill3.width &&my>skill3.y && my<skill3.y+skill3.height){
                    skill3H=false;
                    skillc=true;
                    skillt=System.currentTimeMillis()/100;
                    }
                    if(mx>skill4.x && mx<skill4.x+skill4.width &&my>skill4.y && my<skill4.y+skill4.height){
                    skill4H=false;
                    skilld=true;
                    skillt=System.currentTimeMillis()/100;
                    }
                    
                    if(win){
                        if(mx>levelup.x && mx<levelup.x+levelup.width &&my>levelup.y && my<levelup.y+levelup.height){
                        winbt1H=false;
                        winbt1=true;
                        
                        }
                        if(mx>catchpet.x && mx<catchpet.x+catchpet.width &&my>catchpet.y && my<catchpet.y+catchpet.height){
                        winbt2H=false;
                        winbt2=true;
                        cattime=System.currentTimeMillis()/1000;
                        }
                    }
                    if(lose){
                        if(mx>expup.x && mx<expup.x+expup.width &&my>expup.y && my<expup.y+expup.height){
                        winbt3H=false;
                        winbt3=true;
                        }
                    }
                }
                if(game&&!fighting){
                    if(mx>safemode.x && mx<safemode.x+safemode.width &&my>safemode.y && my<safemode.y+safemode.height){
                    safecount++;
                    }
                    if(mx>petbags.x && mx<petbags.x+petbags.width &&my>petbags.y && my<petbags.y+petbags.height){
                    showpets++;
                    }
                    if(showpets%2==1){
                    if(mx>firstfight2.x && mx<firstfight2.x+firstfight2.width &&my>firstfight2.y && my<firstfight2.y+firstfight2.height){
                    fir2=true;
                    }
                    if(mx>firstfight3.x && mx<firstfight3.x+firstfight3.width &&my>firstfight3.y && my<firstfight3.y+firstfight3.height){
                    fir3=true;
                    }
                    if(mx>firstfight4.x && mx<firstfight4.x+firstfight4.width &&my>firstfight4.y && my<firstfight4.y+firstfight4.height){
                    fir4=true;
                    }
                    if(mx>firstfight5.x && mx<firstfight5.x+firstfight5.width &&my>firstfight5.y && my<firstfight5.y+firstfight5.height){
                    fir5=true;
                    }
                    if(mx>firstfight6.x && mx<firstfight6.x+firstfight6.width &&my>firstfight6.y && my<firstfight6.y+firstfight6.height){
                    fir6=true;
                    }
                    if(mx>remove2.x && mx<remove2.x+remove2.width &&my>remove2.y && my<remove2.y+remove2.height){
                    rem2=true;
                    }
                    if(mx>remove3.x && mx<remove3.x+remove3.width &&my>remove3.y && my<remove3.y+remove3.height){
                    rem3=true;
                    }
                    if(mx>remove4.x && mx<remove4.x+remove4.width &&my>remove4.y && my<remove4.y+remove4.height){
                    rem4=true;
                    }
                    if(mx>remove5.x && mx<remove5.x+remove5.width &&my>remove5.y && my<remove5.y+remove5.height){
                    rem5=true;
                    }
                    if(mx>remove6.x && mx<remove6.x+remove6.width &&my>remove6.y && my<remove6.y+remove6.height){
                    rem6=true;
                    }
                    }
                }
                
                if(bosspage&&!fighting&&safecount%2!=1){
                    if(!p1&&mx>95 && mx<315 &&my>200 && my<420){
                    m1=true;
                    }
                    if(!p2&mx>425 && mx<645 &&my>200 && my<420){
                    m2=true;
                    }
                    if(!p3&mx>755 && mx<975 &&my>200 && my<420){
                    m3=true;
                    }
                }
            }
            @Override
            public void mouseReleased (MouseEvent e){
                int mx=e.getX();
                int my=e.getY();
                if(fighting){
                    if(mx>skill1.x && mx<skill1.x+skill1.width &&my>skill1.y && my<skill1.y+skill1.height){
                    skill1H=true;
                    }
                    if(mx>skill2.x && mx<skill2.x+skill2.width &&my>skill2.y && my<skill2.y+skill2.height){
                    skill2H=true;
                    }
                    if(mx>skill3.x && mx<skill3.x+skill3.width &&my>skill3.y && my<skill3.y+skill3.height){
                    skill3H=true;
                    }
                    if(mx>skill4.x && mx<skill4.x+skill4.width &&my>skill4.y && my<skill4.y+skill4.height){
                    skill4H=true;
                    }
                    if(win){
                        if(mx>levelup.x && mx<levelup.x+levelup.width &&my>levelup.y && my<levelup.y+levelup.height){
                        winbt1H=true;
                        }
                        if(mx>catchpet.x && mx<catchpet.x+catchpet.width &&my>catchpet.y && my<catchpet.y+catchpet.height){
                        winbt2H=true;
                        }
                    }
                    if(lose){
                        if(mx>expup.x && mx<expup.x+expup.width &&my>expup.y && my<expup.y+expup.height){
                        winbt3H=true;
                        }
                    }
                }
                if(savetri){
                    if(mx>save.x && mx<save.x+save.width &&my>save.y && my<save.y+save.height){
                        savefile=true;savtime=System.currentTimeMillis()/1000;//set false after save;
                    }
                }
            }
        }
        
    
}
