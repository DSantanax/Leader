import java.util.concurrent.LinkedBlockingQueue;

public class Official {
    public LinkedBlockingQueue<String> lbq = new LinkedBlockingQueue<>();
    public String name;
    public int rank;
    public Boolean lead;
    private String leader;
    private Ranking rnk;
    private String guess;

    public Official(String name, int rank, Boolean leads, Ranking rnk) {
        this.name = name;
        this.rank = rank;
        this.lead = leads;
        this.rnk = rnk;
    }

    public void notifyRankThread() {
        rnk.notifyRank(name, rank);
    }

    public void putIntoWait() {
        rnk.waitThreads(name);
    }

    public void checkLeaders() {
        lead = rnk.checkLeader(name);
    }

    public void setLeader() {
        leader = rnk.setTheLeader(name);
    }

    public void checkGuess() {
        if (guess == leader) {
            System.out.println(name + " guessed the leader");
        } else {
            System.out.println(name + " guessed wrong");
        }
    }

    public void guess() {
        System.out.println(name + " guess: " + guess);
    }

    public void setGuess(String name) {
        guess = name;
    }

    public String getName() {
        return name;
    }
}