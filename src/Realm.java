import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Realm {
    private static BufferedReader br;
    private static FantasyCharacter player = null;
    private static BattleScene battleScene = null;

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
            player = new Hero(string,
                    100,
                    20,
                    20,
                    0,
                    0
            );
            System.out.println(String.format("Спасти наш мир от драконов вызвался %s, да пребудет с ним ШВОРЦ!", player.getName()));
            printNavigation();
        }
        switch (string) {
            case "1": {
                System.out.println("Торговец еще не приехал.");
                command(br.readLine());
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
            case "no": {
                printNavigation();
                command(br.readLine());
            }
        }command(br.readLine());
    }private static void printNavigation() {
        System.out.println("Where to ?");
        System.out.println("1. To Merchant ");
        System.out.println("2. To MirkWood");
        System.out.println("3. Exit:");
    }
    public static void commitFight() {
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
