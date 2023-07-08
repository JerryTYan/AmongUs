/**
 * This abstract class represents a player in the Among Us game.
 */
public abstract class Player implements Comparable<Player> {
    private String name;
    private int susLevel;
    private boolean frozen;
    // This array holds all the players in the game.
    private static Player[] players;

    /**
     * Constructs a Player object.
     * @param name The player's name.
     * @param susLevel The player's suspicion level. If negative, it's set to 0.
     */
    public Player(String name, int susLevel) {
        this.name = name;
        if (susLevel >= 0) {
            this.susLevel = susLevel;
        } else {
            this.susLevel = 0;
        }
        if (players == null) {
            players = new Player[1];
            players[0] = this;
        } else {
            int length = players.length + 1;
            Player[] temp = players;
            players = new Player[length];
            for (int i = 0; i < temp.length; i++) {
                players[i] = temp[i];
            }
            players[players.length - 1] = this;
        }
    }

    /**
     * Represents an emergency meeting call in the game.
     */
    public abstract void emergencyMeeting();

    /**
     * Compares two players based on their susLevel.
     * @param p The player to compare to.
     * @return The difference in susLevel.
     */
    @Override
    public int compareTo(Player p) {
        return this.susLevel - p.susLevel;
    }

    /**
     * Checks if this player is equal to another object.
     * @param o The object to compare to.
     * @return true if the objects are equal, false otherwise.
     */
    public boolean equals(Object o) {
        if (o instanceof Player) {
            Player player = (Player) o;
            return this.name.equals(player.name) && this.frozen == player.frozen && this.susLevel == player.susLevel;
        }
        return false;
    }

    /**
     * Provides a string representation of the player.
     * @return A string containing the player's name, susLevel and frozen status.
     */
    public String toString() {
        String frozenStatus = frozen ? "frozen" : "not frozen";
        return "My name is " + this.name + ", and I have a susLevel of " + this.susLevel + ". I am currently " + frozenStatus + ".";
    }

    /**
     * Checks if the game is over.
     * @return true if the game is over, false otherwise.
     */
    public boolean gameOver() {
        int impostorCount = 0;
        int crewmateCount = 0;
        for (Player p : players) {
            if (p instanceof Impostor && !p.frozen) {
                impostorCount++;
            } else if (p instanceof Crewmate && !p.frozen) {
                crewmateCount++;
            }
        }
        if (impostorCount == 0) {
            System.out.println("Crewmates Win!");
            return true;
        } else if (impostorCount >= crewmateCount) {
            System.out.println("Impostors Win!");
            return true;
        }
        return false;
    }

    // Standard getter and setter methods

    public String getName() {
        return name;
    }

    public int getSusLevel() {
        return susLevel;
    }

    public void setSusLevel(int susLevel) {
        this.susLevel = Math.max(susLevel, 0); // ensures susLevel is not negative
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }

    /**
     * Returns all the players in the game.
     * @return The array of all players.
     */
    public static Player[] getPlayers() {
        return players;
    }
}
