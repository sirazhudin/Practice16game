public abstract class FantasyCharacter implements Fighter {
    private String name;
    private int healthPoints;
    private int strength;
    private int dexterity;

    private int xp;
    private int gold;

    public FantasyCharacter(String name, int healthPoints, int strength,
                            int dexterity, int xp, int gold) {
        this.name=name;
        this.healthPoints=healthPoints;
        this.strength=strength;
        this.dexterity=dexterity;
        this.xp=xp;
        this.gold=gold;
    }
    @Override
    public int attack() {
        if(dexterity * 3 > getRandomValue()) return strength;
        else return 0;
    }

    public String getName() {
        return name;
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public int getStrength() {
        return strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public int getXp() {
        return xp;
    }

    public int getGold() {
        return gold;
    }

    private int getRandomValue() {
        return (int)(Math.random() * 100);
    }

    @Override
    public String toString() {
        return String.format("%s здоровье:%d",name,healthPoints);
    }
}
