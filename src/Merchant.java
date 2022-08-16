import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Merchant implements Seller {

    @Override
    public String sell(Goods goods, Hero hero) {
        String result = "";
        System.out.println("Сделайте свой выбор господин (Type)");
        int gold = hero.getGold();
        switch(goods) {
            case HEALTH: {
                if(buy(gold)) {
                    hero.setHealthPoints(hero.getHealthPoints() + gold);
                    System.out.println("your health " + hero.getHealthPoints());
                }else System.out.println("Not enough gold");
            }
            case DEFENCE: {
                if(buy(gold)) {
                    hero.setDefence(hero.getDefence() + gold);
                    System.out.println("your Defence " + hero.getDefence());
                }else System.out.println("Not enough gold");
            }
            case STRENGTH: {
                if(buy(gold)) {
                    hero.setStrength(hero.getStrength() + gold);
                    System.out.println("your Strength " + hero.getStrength());
                }else System.out.println("Not enough gold");
            }

        }
    return "Updated ";
    }

    public enum Goods {
        HEALTH,
        DEFENCE,
        STRENGTH
    }

    public void getPotions() {
        Goods[] volues = Goods.values();
        System.out.println("Greeting my Lord : I can humbly offer you the following :");
        for(Goods items : volues) {
            System.out.println(items);

        }
        System.out.println("Type 'da' to buy or 'no' to go back");
    }

    public boolean buy(int gold) {
        if (gold>0) return true;
        return false;
    }
}
