/**
 * Created by alien on 11/14/16.
 */
public class TimeStep implements Runnable{
    Thread t;
    /**
     * When an object implementing interface <code>Runnable</code> is used
     * to create a thread, starting the thread causes the object's
     * <code>run</code> method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method <code>run</code> is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        int fps = 40;
        int stepTime = 1000 / fps;
        long nextFrame = System.currentTimeMillis();
        int loop = 0;
        long timeA = System.currentTimeMillis();
        while(true){

            while(System.currentTimeMillis() <= nextFrame){
                System.out.println("counting");
                //long timeA = System.currentTimeMillis();
                update();
                //long timeB = System.currentTimeMillis();
                //System.out.println("time to update "+ (timeB-timeA));

            }
            nextFrame += stepTime;
            System.out.println("repaint" + (loop++));


            if(loop % 40 == 0){
                long timeB = System.currentTimeMillis();
                System.out.println(timeB-timeA);
                timeA = timeB;
            }

        }
    }


    public void start() {

        t = new Thread(this);
        t.start();
    }

    public void update(){
        try{
            Thread.sleep(10);
        }
        catch (InterruptedException ie){
            System.out.println("exception");
        }
    }


}
