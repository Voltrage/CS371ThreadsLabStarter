package edu.up.cs301threadslab;

import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;

import java.util.Vector;
import java.util.Random;

/**
 * StarAnimation
 *
 * displays a star field animation
 */
public class StarAnimation extends Animation {

    /* the field of stars */
    public static final int INIT_STAR_COUNT = 100;
    private Vector<Star> field = new Vector<Star>();

    /* when this is set to 'false' the next animation frame won't twinkle */
    private boolean twinkle = true;

    /** ctor expects to be told the size of the animation canvas */
    public StarAnimation(int initWidth, int initHeight) {
        super(initWidth, initHeight);


        //part 5
        StarDestroyerLifeGiver Thanos = new StarDestroyerLifeGiver(this);
        Thanos.start();

    }

    /** whenever the canvas size changes, generate new stars */
    @Override
    public void setSize(int newWidth, int newHeight) {
        super.setSize(newWidth, newHeight);

        //Create the stars
        field = new Vector<Star>();
        for(int i = 0; i < INIT_STAR_COUNT; ++i) {
            addStar();
        }
    }

    /** adds a randomly located star to the field */
    public void addStar() {
        //Ignore this call if the canvas hasn't been initialized yet
        if ((width <= 0) || (height <= 0) || field.size()>=1000) return;

        int x = rand.nextInt(width);
        int y = rand.nextInt(height);


        field.add(new Star(x, y));
    }//addStar

    /** removes a random star from the field */
    public void removeStar() {
        if (field.size() > 100) {
            int index = rand.nextInt(field.size());
            field.remove(index);
        }
    }//removeStar

    /** draws the next frame of the animation */
    @Override
    public void draw(Canvas canvas) {

        //part 6
        //while operating on "field" object, don't accept input from any other threads until finished with this section of code
        synchronized (field) {
            for (Star s : field) {
                s.draw(canvas);
                if (this.twinkle) {
                    s.twinkle();
                }

            }
        }

        this.twinkle = true;
    }//draw

    /** the seekbar progress specifies the brightnes of the stars. */
    @Override
    public void progressChange(int newProgress) {
//        int brightness = 255 - (newProgress * 2);
//        Star.starPaint.setColor(Color.rgb(brightness, brightness, brightness));
//        this.twinkle = false;

        synchronized (field) {
            int size;
            //made max 900
            newProgress += 100; //100-1000
            while ((size = field.size()) != (newProgress)) {

                if ((newProgress) < size) {
                    removeStar();
                } else if ((newProgress) > size) {
                    addStar();
                }

            }

        }
    }
}//class StarAnimation
