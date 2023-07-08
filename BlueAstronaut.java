import java.util.Arrays;

public class BlueAstronaut extends Player implements Crewmate{
    //instance variables
    private int numTasks;
    private int taskSpeed;
    private boolean tasksCompleted;
    //constructors
    public BlueAstronaut(String name) {
        this(name, 15, 6, 10);
    }
    public BlueAstronaut(String name, int susLevel, int numTasks, int taskSpeed) {
        super(name, susLevel);
        this.numTasks = numTasks;
        this.taskSpeed = taskSpeed;
        this.tasksCompleted = false;
    }
    //getters
    public int getNumTasks() {
        return this.numTasks;
    }
    public int getTaskSpeed() {
        return this.taskSpeed;
    }
    //setters
    public void setNumTasks(int numTasks) {
        this.numTasks = numTasks;
    }
    //methods
    public void emergencyMeeting() {
        if (isFrozen()) {
            return;
        }
        Player[] allPlayers = getPlayers();
        int activeCount = 0;
        for (Player p: allPlayers) {
            if (!p.isFrozen()) {
                activeCount++;
            }
        }
        Player[] players = new Player[activeCount];
        int i = 0;
        for (Player p: allPlayers) {
            if (!p.isFrozen()) {
                players[i] = p;
                i++;
            }
        }
        Arrays.sort(players);
        if (players[players.length - 1].getSusLevel() == players[players.length - 2].getSusLevel()) {
            return;
        }
        else {
            players[players.length - 1].setFrozen(true);
        }
        gameOver();
    }

    public void completeTask() {
        if (isFrozen()) {
            return;
        }
        else if (getTaskSpeed() > 20) {
            setNumTasks(getNumTasks() - 2);
        }
        else {
            setNumTasks(getNumTasks() - 1);
        }
        if (getNumTasks() < 0) {
            setNumTasks(0);
        }
        if (getNumTasks() == 0 && !this.tasksCompleted) {
            System.out.println("I have completed all my tasks");
            setSusLevel((int)(getSusLevel() * 0.5));
            this.tasksCompleted = true;
        }
    }

    public boolean equals(Object o) {
        if (o instanceof BlueAstronaut) {
            BlueAstronaut blueAstronaut = (BlueAstronaut) o;
            return this.getName().equals(blueAstronaut.getName()) && this.isFrozen() == blueAstronaut.isFrozen()
                    && this.getSusLevel() == blueAstronaut.getSusLevel() && this.getNumTasks() == blueAstronaut.getNumTasks()
                    && this.getTaskSpeed() == blueAstronaut.getTaskSpeed();
        }
        return false;
    }

    public String toString() {
        if (this.getSusLevel() <= 15) {
            return super.toString() + " I have " + getNumTasks() + " left over.";
        }
        else {
            return (super.toString() + " I have " + getNumTasks() + " left over.").toUpperCase();
        }
    }
}
