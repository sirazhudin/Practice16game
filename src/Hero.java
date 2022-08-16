public class Hero extends FantasyCharacter {
    private int defence;
    public Hero(String name, int healthPoints, int strength, int dexterity, int xp, int gold) {
        super(name, healthPoints, strength, dexterity, xp, gold);
        defence = 0;
    }
    public void setDefence(int defence){
        this.defence=defence;
    }
    public int getDefence(){
        return this.defence;
    }
}
