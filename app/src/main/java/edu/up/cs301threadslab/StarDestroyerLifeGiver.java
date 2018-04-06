package edu.up.cs301threadslab;

import java.util.Random;
import java.util.Vector;

import static edu.up.cs301threadslab.Animation.rand;

/**
 * Created by Luke on 4/5/2018.
 */

public class StarDestroyerLifeGiver extends Thread implements Runnable {

    private StarAnimation Ether;

    public StarDestroyerLifeGiver(StarAnimation E) {
        //initialize array of stars
        Ether = E;
    }

    @Override
    public void run() {
//        super.run();

        while(true) {

            try {
                Thread.sleep(2);
            } catch (InterruptedException ie) {
                //don't care
            }
            if(rand.nextBoolean()){
                Ether.addStar();
            }
            else
                Ether.removeStar();

        }


    }
}
