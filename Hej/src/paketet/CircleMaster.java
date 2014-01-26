package paketet;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;

public class CircleMaster implements Runnable {

    private ArrayList<Circle> CircleList, CircleListOld;
    public boolean running;
    public static final long SLEEP_TIME = 1;
    Rectangle northFrame, eastFrame, southFrame, westFrame;

    public CircleMaster() {
        CircleList = new ArrayList<Circle>();
        CircleListOld = new ArrayList<Circle>();
    }

    public void addCirle(Vector pos, Vector vel, Vector acc, double rad) {
        CircleList.add(new Circle(pos, vel, acc, rad));
        CircleListOld.add(new Circle(pos, vel, acc, rad));
    }
    public void addCircle(Vector pos, Vector vel, Vector acc, double rad, double omegar, double mass) {
        CircleList.add(new Circle(pos, vel, acc, rad, omegar, mass));
        CircleListOld.add(new Circle(pos, vel, acc, rad, omegar, mass));
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
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }  
        }

    }


    public void iterate() {

        double elasticity = 0.95;
        double rotRatio = 0.1; //How much of the rotation of the circles is transferred on the collision event (0<rR<1)

        for (Circle c1: CircleList) {
            for (Circle c2: CircleList) {
                Vector v;
                if(!c1.equals(c2)) {
                    v = c1.inBounds(c2); 
                    // v is sort of how deep into each other they are.
                    if (v != null) {
                        //                        System.out.println(v.getX() + ", " + v.getY());
                        // c2.pos = c2.pos.minus(v);
                        // c1.pos = c1.pos.minus(c1.vel);
                        // c1.vel = c1.vel.minus(c1.acc);
                        // c1.alphar = c1.alphar - c1.omegar;
                        // c2.pos = c2.pos.minus(c2.vel);
                        // c2.vel = c2.vel.minus(c2.acc);
                        // c2.alphar = c2.alphar - c2.omegar;

                        Vector normalVector = c1.pos.minus(c2.pos).unitVector();

                        double inertia1 = c1.inertia, 
                                inertia2 = c2.inertia;

                        c1.pos = c1.pos.plus(v.times(c1.getRad()/(c1.getRad()+c2.getRad())));
                        c2.pos = c2.pos.minus(v.times(c2.getRad()/(c1.getRad()+c2.getRad())));

                        //                        c1.pos = collPos.plus(
                        //                                normalVector.times((c1.getRad()+c2.getRad())*(c2.mass/(c1.mass+c2.mass))));
                        //                        c2.pos = collPos.minus(
                        //                                normalVector.times((c1.getRad()+c2.getRad())*(c1.mass/(c1.mass+c2.mass))));


                        //                        c1.pos = c1.pos.mean(c2.pos).plus( (c1.pos.minus(c2.pos).unitVector().times(c1.getRad()+c2.getRad()))
                        //                                        .times(c1.mass/(c1.mass+c2.mass)));
                        //                        c2.pos = tempPos.mean(c2.pos).plus((c2.pos.minus(c1.pos).unitVector().times(c2.getRad()+c1.getRad()))
                        //                                        .times(c2.mass/(c1.mass+c2.mass))); 

                        Vector vp = c1.vel.plus(new Vector(0,0,c1.omegar).cross(normalVector.times(c1.getRad()))).
                                minus(c2.vel.plus(new Vector(0,0,c2.omegar).cross(normalVector.times(-c2.getRad())))); 
                        System.out.println(vp.length());

                        double I = -(1+elasticity)/
                                ((1/c1.mass)+(1/c2.mass) + 
                                        (normalVector.times(c1.getRad()).area2d(normalVector)) *
                                        (normalVector.times(c1.getRad()).area2d(normalVector))/inertia1 + 
                                        (normalVector.times(-c2.getRad()).area2d(normalVector)) * 
                                        (normalVector.times(-c2.getRad()).area2d(normalVector))/inertia2);

                        c1.vel = c1.vel.plus(vp.times(I/c1.mass)); 
                        c2.vel = c2.vel.minus(vp.times(I/c2.mass)); 

                        double temp = c1.omegar;
                        c1.omegar = c1.omegar*(c1.mass/(c1.mass+c2.mass))-c2.omegar*(c2.mass/(c1.mass+c2.mass));
                        c2.omegar = c2.omegar*(c2.mass/(c1.mass+c2.mass))-temp*(c1.mass/(c1.mass+c2.mass));



                        //                        c1.vel = c1.vel.times(c1.mass/(c1.mass+c2.mass)).
                        //                                    plus(v.unitVector().times(c2.vel).times(c2.mass/(c1.mass+c2.mass)));
                        //                        c2.vel = c2.vel.times(c2.mass/(c1.mass+c2.mass)).
                        //                                plus(v.unitVector().times(c1.vel).times(-c1.mass/(c1.mass+c2.mass)));

                        //TODO Change omega and make new vel depend on omega

                        // Reset the acceleration of both the bodies
                        c1.acc = c1.gravityAcc.clone();
                        c2.acc = c2.gravityAcc.clone();


                    }
                }
            }
        }

        // TODO Give oldCircleList to new or w/e 
        // Within Frame/Map edges/Edges?
        //TODO Change omega and make new vel depend on omega
        for (Circle c1: CircleList) {


            if (c1.pos.getY()<c1.getRad()) { //North
                Vector normalVector = new Vector(0,-1,0);
                Vector vp = c1.vel.plus(new Vector(0,0,c1.omegar).cross(normalVector.times(c1.getRad())));

                c1.pos.setY(c1.getRad());
                c1.vel = vp.times(elasticity);
                c1.vel.setY(-c1.vel.getY()*elasticity);
            }
            if (c1.pos.getX() > Main.screenSizeX - c1.getRad()) { //East
                Vector normalVector = new Vector(1,0,0);
                Vector vp = c1.vel.plus(new Vector(0,0,c1.omegar).cross(normalVector.times(c1.getRad())));

                c1.pos.setX(Main.screenSizeX - c1.getRad());
                c1.vel = vp.times(elasticity);
                c1.vel.setX(-c1.vel.getX()*elasticity);
            }
            if (c1.pos.getY() > Main.screenSizeY - c1.getRad()) { //South
                Vector normalVector = new Vector(0,1,0);
                Vector vp = c1.vel.plus(new Vector(0,0,c1.omegar).cross(normalVector.times(c1.getRad())));
                if (Math.abs(c1.vel.getY())<0.01){ //Stop the circle from moving
                    c1.pos.setY(Main.screenSizeY - c1.getRad()*1.01);
                    c1.vel.setY(0);
                    c1.acc.setY(0);
                } else {
                    c1.pos.setY(Main.screenSizeY - c1.getRad());
                    c1.vel = vp.times(elasticity);
                    c1.vel.setY(-c1.vel.getY()*elasticity);
                }
            }
            if (c1.pos.getX()< c1.getRad()) { //West
                Vector normalVector = new Vector(-1,0,0);
                Vector vp = c1.vel.plus(new Vector(0,0,c1.omegar).cross(normalVector.times(c1.getRad())));
                c1.pos.setX(c1.getRad());
                c1.vel = vp.times(elasticity);
                c1.vel.setX(-c1.vel.getX()*elasticity);
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
