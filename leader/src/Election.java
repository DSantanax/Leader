import java.util.Random;

public class Election {
    public static void main(String[] args) {
        Random rand = new Random();
        int numberOfficials = 4;
        Thread[] threads = new Thread[numberOfficials];
        // start rank thread

        Boolean[] lead = new Boolean[numberOfficials];
        int leader = rand.nextInt(numberOfficials);

        for(int i =0; i < lead.length; i ++){
            if(i == leader)
                lead[i] = true;
            else 
                lead[i] = false;
        }
        Ranking rnk = new Ranking(threads.length);
        Thread rank = new Thread(rnk);
        rank.start();
        Official[] off = new Official[numberOfficials];
        OfficialThread[] ot = new OfficialThread[numberOfficials];
        for(int i =0; i < threads.length; i++){
            String name = "Thread: " + Integer.toString(i+1);
            off[i] = new Official(name, rand.nextInt(100), lead[i], rnk);
            ot[i] = new OfficialThread(off[i],numberOfficials);
            threads[i] = new Thread(ot[i]);
        }
        for(int i = 0; i < threads.length; i++){
            off[i].setGuess(off[rand.nextInt(numberOfficials-1)].getName());
        }
        // slowly start official thread
        for (int i = 0; i < threads.length; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                }
            threads[i].start();
        }
    }
}