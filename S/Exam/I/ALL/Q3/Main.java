import java.util.*;

// --- TODO 1: 特殊攻擊者介面 ---
interface SpecialAttacker {
    int getSpecialPower(); // 定義特殊加成後的戰鬥力計算方法
}

// --- TODO 2: 神奇寶貝抽象父類別 ---
abstract class Pokemon {
    protected int id;
    protected String name;
    protected String type;
    protected int strength;
    protected int speed;
    
    // 靜態變數用於自動分配唯一編號
    public static int totalCount = 0;

    public Pokemon(String name, String type, int s, int v) {
        this.name = name;
        this.type = type;
        this.strength = s;
        this.speed = v;
        this.id = ++totalCount; // 每建立一隻，總數加 1 並賦予 ID
    }

    // 基礎戰鬥力公式：7 * strength + 5 * speed
    public int getBasePower() {
        return 7 * strength + 5 * speed;
    }

    public String getType() { return type; }
    public String getName() { return name; }

    // 顯示詳細資訊 (Command 4)
    public void display() {
        System.out.println("ID: " + id + ", Type: " + name + ", Str: " + strength + ", Spd: " + speed);
    }

    // 抽象方法：要求子類別必須實作
    public abstract void makeSound();
    public abstract void eat();
}

// --- TODO 3: 具體子類別實作 ---

// 1. 皮卡丘: 電屬性, 具備特攻加成
class Pikachu extends Pokemon implements SpecialAttacker {
    public Pikachu() { super("Pikachu", "Electric", 10, 7); }
    
    @Override
    public void makeSound() { System.out.println(name + ": Pika Pika!"); }
    
    @Override
    public void eat() {
        int oldS = strength;
        strength += 2;
        System.out.println(name + " is eating food. [Str: " + oldS + "->" + strength + ", Spd: " + speed + "->" + speed + "]");
    }

    @Override
    public int getSpecialPower() {
        // 公式：7 * (strength * 2) + 5 * speed
        return 7 * (strength * 2) + 5 * speed;
    }
}

// 2. 波加曼: 水屬性, 平衡型 (無特攻介面)
class Piplup extends Pokemon {
    public Piplup() { super("Piplup", "Water", 7, 5); }
    
    @Override
    public void makeSound() { System.out.println(name + ": Piplup!"); }
    
    @Override
    public void eat() {
        int oldS = strength, oldV = speed;
        strength += 1;
        speed += 3;
        System.out.println(name + " is eating food. [Str: " + oldS + "->" + strength + ", Spd: " + oldV + "->" + speed + "]");
    }
}

// 3. 木守宮: 草屬性, 具備特攻加成
class Treecko extends Pokemon implements SpecialAttacker {
    public Treecko() { super("Treecko", "Grass", 6, 10); }
    
    @Override
    public void makeSound() { System.out.println(name + ": Treecko!"); }
    
    @Override
    public void eat() {
        int oldV = speed;
        speed += 5;
        System.out.println(name + " is eating food. [Str: " + strength + "->" + strength + ", Spd: " + oldV + "->" + speed + "]");
    }

    @Override
    public int getSpecialPower() {
        // 公式：7 * strength + 5 * (speed * 3)
        return 7 * strength + 5 * (speed * 3);
    }
}

// 4. 小火龍: 火屬性, 具備特攻加成
class Charmander extends Pokemon implements SpecialAttacker {
    public Charmander() { super("Charmander", "Fire", 8, 8); }
    
    @Override
    public void makeSound() { System.out.println(name + ": Charrr!"); }
    
    @Override
    public void eat() {
        int oldS = strength, oldV = speed;
        strength += 2;
        speed += 2;
        System.out.println(name + " is eating food. [Str: " + oldS + "->" + strength + ", Spd: " + oldV + "->" + speed + "]");
    }

    @Override
    public int getSpecialPower() {
        // 公式：基礎 Power * 1.2 (取整數)
        return (int)(getBasePower() * 1.2);
    }
}


public class Main {
    // 屬性克制判定方法
    public static boolean isStrongAgainst(String a, String b) {
        if (a.equals("Fire") && b.equals("Grass")) return true;
        if (a.equals("Grass") && b.equals("Water")) return true;
        if (a.equals("Water") && b.equals("Fire")) return true;
        if (a.equals("Electric") && b.equals("Water")) return true;
        return false;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Pokemon> list = new ArrayList<>();

        while (sc.hasNextInt()) {
            int cmd = sc.nextInt();
            if (cmd == -1) break;

            if (cmd == 1) { // 建立神奇寶貝
                int t = sc.nextInt();
                if (t == 1) list.add(new Pikachu());
                else if (t == 2) list.add(new Piplup());
                else if (t == 3) list.add(new Treecko());
                else if (t == 4) list.add(new Charmander());
            } 
            else if (cmd == 2) { // 執行行為
                int id = sc.nextInt();
                int act = sc.nextInt();
                if (id > list.size() || id <= 0) continue;
                Pokemon p = list.get(id - 1);

                if (act == 1) p.makeSound();
                else if (act == 2) p.eat();
                else if (act == 3) { // 對戰比較
                    int id2 = sc.nextInt();
                    if (id2 > list.size() || id2 <= 0) continue;
                    Pokemon p2 = list.get(id2 - 1);
                    
                    // 動態判斷是否實作 SpecialAttacker 介面
                    int v1 = (p instanceof SpecialAttacker) ? ((SpecialAttacker) p).getSpecialPower() : p.getBasePower();
                    int v2 = (p2 instanceof SpecialAttacker) ? ((SpecialAttacker) p2).getSpecialPower() : p2.getBasePower();
                    
                    // 檢查屬性克制倍率 (1.5倍)
                    if (isStrongAgainst(p.getType(), p2.getType())) {
                        v1 = (int)(v1 * 1.5);
                    }
                    
                    System.out.println("Power: " + v1 + " vs. " + v2);
                    if (v1 > v2) System.out.println(p.getName() + " wins!");
                    else if (v1 < v2) System.out.println(p2.getName() + " wins!");
                    else System.out.println("Draw!");
                }
            } 
            else if (cmd == 3) { // 顯示總神奇寶貝數
                System.out.println("Total: " + Pokemon.totalCount);
            }
            else if (cmd == 4) { // 顯示詳細資訊
                int id = sc.nextInt();
                if (id <= list.size() && id > 0) list.get(id - 1).display();
            }
        }
        sc.close();
    }
}