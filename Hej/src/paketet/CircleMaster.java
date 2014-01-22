package paketet;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.newdawn.slick.Color;

public class CircleMaster implements Runnable {
    
    private ArrayList<Circle> CircleList, CircleListOld;
    public boolean running;
    public static final long SLEEP_TIME = 2;
    Rectangle northFrame, eastFrame, southFrame, westFrame;
    
    public CircleMaster() {
        CircleList = new ArrayList<Circle>();
        CircleListOld = new ArrayList<Circle>();
    }
    
    public void addCirle(Vector pos, Vector vel, Vector acc, double rad) {
        CircleList.add(new Circle(pos, vel, acc, rad));
        CircleListOld.add(new Circle(pos, vel, acc, rad));
    }
    public void addCircle(Vector pos, Vector vel, Vector acc, double rad, double omegar) {
        CircleList.add(new Circle(pos, vel, acc, rad, omegar));
        CircleListOld.add(new Circle(pos, vel, acc, rad, omegar));
    }
    
    public void stop() {
        running = false;
    }
    @Override
    public void run() {
        running = true;
        while(running) {
            move();
            iterate();
            try {
                Thread.sleep(SLEEP_TIME);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            if(Keyboard.isKeyDown(Keyboard.KEY_SPACE)){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  
        }
        
    }
    
    
    public void iterate() {

        double damping = 0.80; //?% damping
        
        for (Circle c1: CircleList) {
            for (Circle c2: CircleList) {
                Vector v;
                if(!c1.equals(c2)) {
                    v = c1.inBounds(c2); 
                    // v is sort of how deep into each other they are.
                    if (v != null) {
//                        System.out.println(v.getX() + ", " + v.getY());
                        c1.pos = c2.pos.plus(c1.pos.minus(c2.pos).unitVector().times(c1.getRad()+c2.getRad()));
//                        c2.pos = c2.pos.minus(v);
//                        c1.pos = c1.pos.minus(c1.vel);
//                        c1.vel = c1.vel.minus(c1.acc);
//                        c1.alphar = c1.alphar - c1.omegar;
//                        c2.pos = c2.pos.minus(c2.vel);
//                        c2.vel = c2.vel.minus(c2.acc);
//                        c2.alphar = c2.alphar - c2.omegar;
                        
                        Vector temp = c1.vel.clone();
                        c1.vel = v.unitVector().times(c2.vel.length()*damping);
                        c2.vel = v.unitVector().times(-temp.length()*damping);
                        
                        //TODO Change omega and make new vel depend on omega
                        c1.omegar += 1;
                        c2.omegar -=1;
                    }
                }
            }
        }
        // TODO Give oldCircleList to new or w/e 
        // Within Frame/Map edges/Edges?
        //TODO Change omega and make new vel depend on omega
        for (Circle c1: CircleList) {
            if (c1.pos.getY()<c1.getRad()) { //North
                c1.pos.setY(c1.getRad());
                c1.vel.setY(-c1.vel.getY()*damping);
            }
            if (c1.pos.getX() > Main.screenSizeX - c1.getRad()) { //East
                c1.pos.setX(Main.screenSizeX - c1.getRad());
                c1.vel.setX(-c1.vel.getX()*damping);
            }
            if (c1.pos.getY() > Main.screenSizeY - c1.getRad()) { //South
                System.out.println("hej");
                if (Math.abs(c1.vel.getY())<0.01){ //Stop the circle from moving
                    c1.pos.setY(Main.screenSizeY - c1.getRad()*1.01);
                    c1.vel.setY(0);
                    c1.acc.setY(0);
                } else {
                    c1.pos.setY(Main.screenSizeY - c1.getRad());
                    c1.vel.setY(-c1.vel.getY()*damping);
                }
            }
            if (c1.pos.getX()< c1.getRad()) { //West
                c1.pos.setX(c1.getRad());
                c1.vel.setX(-c1.vel.getX()*damping);
            }
            
        }
    }
    
    
    public void move() {
        for (Circle c : CircleList) {
            c.move();
        }
    }
    public void draw() {
        for (Circle c : CircleList) {
            c.drawMe(new Color(0,0,1));
        }
    }
}
