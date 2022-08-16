import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;
    private static FantasyCharacter player = null;
    private static BattleScene battleScene = null;
    private static Merchant merchant = null;

    public static void main(String[] args) {
             br = new BufferedReader(new InputStreamReader(System.in));
        battleScene = new BattleScene();
        System.out.println("Введите имя персонажа :");
        try {
            command(br.readLine());
        }catch (IOException e) {
            e.printStackTrace();
        }

    }
    private static void command(String string) throws IOException {
        if(player == null) {
            player = new Hero(
                    string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            if(merchant == null) {
                merchant = new Merchant();
            }
            System.out.printf("Спасти наш мир от драконов вызвался %s, да пребудет с ним ШВОРЦ!", player.getName());
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Greeting my Lord : I can humbly offer you the following :");
                merchant.getPotions();
                System.out.println("Сделайте свой выбор господин " + player.getName());
                System.out.println("Type 'da' to buy or 'no' to go back");
                command(br.readLine());
                printNavigation();
            }
            break;
            case "2": {
                commitFight();
            }
            break;
            case "3": {
                System.exit(1);
                break;
            }
            case "yes": {
                command("2");
                break;
            }
            case "no":
            case "7":{
                printNavigation();
                command(br.readLine());
            }
            case "4":{
                System.out.println("----------------\nHero\n----------------\nname : " + player.getName()+
                        "\nhealth:" + player.getHealthPoints()+
                        "\nexperience:" + player.getXp()+
                        "\nstrength:" + player.getStrength()+
                        "\ngold:" + player.getGold()+
                        "\n----------------"
                );printNavigation();command(br.readLine());
            }
            case "da":{//case "STRENGTH": case "DEFENCE":{
                System.out.println("You have : " + player.getGold() + " gold\nTherefore you can buy as many of any of ");
                merchant.getPotions();
                System.out.println("points - type (select one from above).");//+merchant.getPotions()+"points" );
                //String str = br.readLine();
                merchant.sell(Goods.valueOf(br.readLine()),(Hero)player);
                printNavigation();command(br.readLine());
            }

        }command(br.readLine());
    }private static void printNavigation() {
        System.out.println("Where to ?");
        System.out.println("1. To Merchant ");
        System.out.println("2. To MirkWood");
        System.out.println("3. Exit:");
        System.out.println("4. Print Hero");
    }

    public static
    void commitFight() {
        battleScene.fight(player, createMonster(), new FightCallBack() {
            @Override
            public void fightWin() {
                System.out.println(String.format(" %s победил!Теперь у вас %d опыта и %d золота, а также осталось %d едениц здровья ", player.getName()
                        ,player.getXp(),player.getGold(),player.getHealthPoints()));
                System.out.println("Continue or back to the city? (yes/no)");
                try{
                    command(br.readLine());
                }catch (IOException e) {
                    e.printStackTrace();
                }
            }@Override
            public void fightLost() {}
        });
    }
    private static FantasyCharacter createMonster() {
        int random = (int) (Math.random() * 10) ;
        if(random % 2 == 0) return new Goblin("Goblin",50,10,10,100,20);
        else return new Skeleton("Skeleton",25,20,20,100,10);

    }
    interface FightCallBack {
        void fightWin();
        void fightLost();
    }

}
