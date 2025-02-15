import java.util.Random;

public class Hero {
    private String name;
    private int hitPoints;
    private static final int MAX_HP = 100;
    private static final int DAMAGE = 10;
    private static final Random rand = new Random();

    public Hero(String name) {
        this.name = name;
        this.hitPoints = MAX_HP;
    }

    public String getName() {
        return name;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    @Override
    public String toString() {
        return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
    }

    public void attack(Hero opponent) {
        if (rand.nextDouble() < 0.5) {
            opponent.hitPoints -= DAMAGE;
        } else {
            this.hitPoints -= DAMAGE;
        }
    }

    public void senzuBean() {
        this.hitPoints = MAX_HP;
    }

    private void fightUntilTheDeathHelper(Hero opponent) {
        while (this.hitPoints > 0 && opponent.hitPoints > 0) {
            attack(opponent);
        }
    }

    public String fightUntilTheDeath(Hero opponent) {
        this.senzuBean();
        opponent.senzuBean();
        fightUntilTheDeathHelper(opponent);
        return this.name + ": " + this.hitPoints + " " + opponent.name + ": " + opponent.hitPoints;
    }

    private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
        int[] results = new int[2]; //results[0] = thihero wins, results[1] = opponent wins
        for (int i = 0; i < n; i++) {
            this.senzuBean();
            opponent.senzuBean();
            fightUntilTheDeathHelper(opponent);
            if (this.hitPoints > 0) {
                results[0]++;
            } else {
                results[1]++;
            }
        }
        return results;
    }

    public String nFightsToTheDeath(Hero opponent, int n) {
        int[] results = nFightsToTheDeathHelper(opponent, n);
        String resultString = this.name + ": " + results[0] + " wins\n" + opponent.name + ": " + results[1] + " wins\n";
        if (results[0] > results[1]) {
            resultString += this.name + " wins!";
        } else if (results[0] < results[1]) {
            resultString += opponent.name + " wins!";
        } else {
            resultString += "draw";
        }
        return resultString;
    }

    public void dramaticFightToTheDeath(Hero opponent) throws InterruptedException {
        this.senzuBean();
        opponent.senzuBean();
        while (this.hitPoints > 0 && opponent.hitPoints > 0) {
            attack(opponent);
            System.out.println(this);
            System.out.println(opponent);
        }
    }
}
