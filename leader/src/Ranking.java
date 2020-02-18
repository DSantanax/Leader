import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.Iterator;

public class Ranking implements Runnable {
    HashMap<String, Integer> hmp;
    private LinkedBlockingQueue<Integer> lbq;
    private Object lock1 = new Object();
    private Object lock2 = new Object();
    private int total;
    private int inc;
    private int max;
    private String leader;

    public Ranking(int total) {
        this.total = total;
        inc = 0;
        hmp = new HashMap<>();
        lbq = new LinkedBlockingQueue<>();
    }

    @Override
    public void run() {
        while (total > inc) {
            waitCreation();
            inc++;
        }
        //free remaining
        synchronized(lock2){lock2.notifyAll();}

        System.out.println("Ranked Finished");
    }
    public void waitCreation() {
        synchronized (lock1) {
            try {
                System.out.println("Ranked waiting");
                lock1.wait();
            } catch (InterruptedException e) {
                }

            System.out.println("Notified ranked");
            Iterator<Integer> itr = lbq.iterator();
            int tempMax = (Integer) itr.next();
            
            while(itr.hasNext()){
                int val;
                if((val = itr.next()) > tempMax){
                    tempMax = val;
                }
            }
            
            System.out.println(tempMax);
            if(max != tempMax){
                max = tempMax;
                performRanking();
            }
        }
    }

    public void notifyRank(String name, int rank) {
        synchronized (lock1) {
            lbq.add(rank);
            hmp.put(name, rank);
            lock1.notify();
        }
    }

    private void performRanking() {
        synchronized(lock2){
            System.out.println("Rank notifying all");
            lock2.notifyAll();
        }
    }

    public void waitThreads(String name) {
        synchronized (lock2) {
            try {
                System.out.println(name + " waiting");
                lock2.wait();
            } catch (InterruptedException e) { }
            System.out.println(name + " notified");
            }
    }

    public synchronized boolean checkLeader(String name) {
           int val = hmp.get(name);
            if(max==val){
                System.out.println("I am leader " + name);
                leader = name;
                return true;
            }
            return false;
    }
	public synchronized String setTheLeader(String name) {
		return leader;
    }

}