import java.util.Arrays;

public class RedAstronaut extends Player implements Impostor {
    //instance variables
    private String skill;
    //constructors
    public RedAstronaut(String name) {
        this(name, 15, "experienced");
    }
    public RedAstronaut(String name, int susLevel, String skill) {
        super(name, susLevel);
        this.skill = skill;
    }
    //getters
    public String getSkill() {
        return this.skill;
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
        if (players[players.length - 1] != this) {
            if (players[players.length - 1].getSusLevel() == players[players.length - 2].getSusLevel()) {
                return;
            }
            else {
                players[players.length - 1].setFrozen(true);
            }
            }
        else {
            if (players[players.length - 2].getSusLevel() == players[players.length - 3].getSusLevel()) {
                return;
            }
            else {
                players[players.length - 2].setFrozen(true);
            }
        }
        gameOver();
    }
        
    public void freeze(Player p) {
        if (this.isFrozen() || p instanceof RedAstronaut || p.isFrozen()) {
            return;
        }
        else if (this.getSusLevel() < p.getSusLevel()) {
            p.setFrozen(true);
        }
        else {
            this.setSusLevel(getSusLevel() * 2);
        }
        gameOver();
    }

    public void sabotage(Player p) {
        if (this.isFrozen() || p instanceof RedAstronaut || p.isFrozen()) {
            return;
        }
        else if (this.getSusLevel() < 20) {
            p.setSusLevel((int)(p.getSusLevel() * 1.5));
        }
        else {
            p.setSusLevel((int)(p.getSusLevel() * 1.25));
        }
    }

    public boolean equals(Object o) {
        if (o instanceof RedAstronaut) {
            RedAstronaut redAstronaut = (RedAstronaut) o;
            return this.getName().equals(redAstronaut.getName()) && this.isFrozen() == redAstronaut.isFrozen()
                    && this.getSusLevel() == redAstronaut.getSusLevel() && this.getSkill().equals(redAstronaut.getSkill());
        }
        return false;
    }

    public String toString() {
        if (this.getSusLevel() <= 15) {
            return super.toString() + " I am an " + this.getSkill() + " player!";
        }
        else {
            return (super.toString() + " I am an " + this.getSkill() + " player!").toUpperCase();
        }
    }
}
