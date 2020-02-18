public class OfficialThread implements Runnable {
    Official of;
    public static int increment = 0;
    public int size;

    public OfficialThread(Official of, int num) {
        this.of = of;
        size = num;
    }

    @Override
    public void run() {
        System.out.println(of.name + " rank: " + of.rank);
        of.guess();
        of.notifyRankThread();

        increment++;
        while (size > increment) {
            of.putIntoWait();
            of.checkLeaders();
            of.setLeader();
        }
        of.checkGuess();
        
    }
}