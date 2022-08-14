public class BattleScene {
    public void fight(FantasyCharacter hero, FantasyCharacter monster, Realm.FightCallBack fightCallback) {
        Runnable runnable = () -> {
            int turn = 1;
            boolean isFightEnded = false;
            while (!isFightEnded) {
                System.out.println("----Ход:" + turn + "----");
                if(turn++ %2 !=0){
                    isFightEnded = makeHit(monster, hero, fightCallback);
                } else {
                    isFightEnded = makeHit(hero, monster, fightCallback);
                }try
                {
                    Thread.sleep(1000);
                }catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }; Thread thread = new Thread(runnable);
        thread.start();
    }
    private boolean makeHit(FantasyCharacter defender, FantasyCharacter attacker, Realm.FightCallBack fightCallBack){
        int hit = attacker.attack();
        int defenderHealth = defender.getHealthPoints() - hit;
        if(hit !=0) {
            System.out.println(String.format("%s Нанёс Удар в %d едениц здоровья...",attacker.getName(),hit));
            System.out.println(String.format("У %s осталось %d едениц здоровья ", defender.getName(),defenderHealth));
        } else {
            System.out.println(String.format("%s промахнулся! ",attacker.getName()));
        }if(defenderHealth <=0 && defender instanceof Hero) {
            System.out.println("Вы пали смертью харбрых - (наверно)");
            fightCallBack.fightLost();
            return true;
        } else if(defenderHealth<=0) {
            System.out.println(String.format("Враг Повержен! Вы получаете %d опыта и %d золота ",
                    defender.getXp(),defender.getGold()));
            attacker.setXp(attacker.getXp() + defender.getXp());
            attacker.setGold(attacker.getGold()+defender.getGold());
            fightCallBack.fightWin();
            return true;
        } else {
            defender.setHealthPoints(defenderHealth);
            return false;
        }

    }
}
