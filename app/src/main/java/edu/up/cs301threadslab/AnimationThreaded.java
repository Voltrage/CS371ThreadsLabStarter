package edu.up.cs301threadslab;

/**
 * Created by Luke on 4/5/2018.
 */

public class AnimationThreaded extends Thread implements Runnable{

    private AnimationView reference;

    public AnimationThreaded(AnimationView ref) {
        reference = ref;
    }

    @Override
    public void run() {
//        super.run();
        while(true) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ie) {
                //don't care
            }
            reference.postInvalidate();
        }
    }
}
