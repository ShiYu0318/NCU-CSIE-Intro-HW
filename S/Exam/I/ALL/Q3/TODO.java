import java.util.*;

interface SpecialAttacker {
    // TODO 1: 定義方法原型
    // 包含一個方法 getSpecialPower 回傳一個值
}

/* TODO 2: 神奇寶貝抽象父類別 (Pokemon)
   規則：
   - 屬性：id, name, type, strength, speed
   - 靜態屬性：totalCount
   - 建構子：name, type, strength, speed，並處理 id 遞增。
   - 方法：實作 getBasePower()
   - 抽象方法：定義 makeSound() 與 eat()。
*/
abstract class Pokemon {
    // TODO: 宣告屬性與靜態變數

    public Pokemon(String name, String type, int s, int v) {
        // TODO: 實作建構子
    }

    public int getBasePower() {
        // TODO: 實作公式
    }

    public String getType() { return type; }
    public String getName() { return name; }

    public void display() {
        System.out.println("ID: " + id + ", Type: " + name + ", Str: " + strength + ", Spd: " + speed);
    }

    // TODO: 定義抽象方法 makeSound() 以及 eat()
}

// TODO 3: 實作四種神奇寶貝類別

class Pikachu extends Pokemon implements SpecialAttacker {
    public Pikachu() { 
        // TODO: 呼叫父類別建構子，並傳入正確的名稱("Pikachu")、屬性("Electric")、力量(10)與速度(7)
    }

    // TODO: 實作 makeSound (印出 "Pikachu: Pika Pika!")
    // TODO: 實作 eat (印出 "Pikachu is eating food." 並顯示 [Str: 舊->新, Spd: 舊->新])
    // TODO: 實作 getSpecialPower (公式：7 * (strength * 2) + 5 * speed)
}

// (其餘 Charmander, Piplup, Treecko 類別也請依照題目規則實作...)


// =========================================================
// 以下為大木博士提供的測試主系統，請勿修改任何程式碼
// =========================================================
public class TODO {
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

            if (cmd == 1) { // 1. 建立神奇寶貝
                int t = sc.nextInt();
                if (t == 1) list.add(new Pikachu());
                else if (t == 2) list.add(new Piplup());
                else if (t == 3) list.add(new Treecko());
                else if (t == 4) list.add(new Charmander());
            } 
            else if (cmd == 2) { // 2. 執行行為
                int id = sc.nextInt();
                int act = sc.nextInt();
                if (id > list.size() || id <= 0) continue;
                Pokemon p = list.get(id - 1);

                if (act == 1) p.makeSound();
                else if (act == 2) p.eat();
                else if (act == 3) { // 2-3. 對戰比較
                    int id2 = sc.nextInt();
                    if (id2 > list.size() || id2 <= 0) continue;
                    Pokemon p2 = list.get(id2 - 1);
                    
                    // 多型與介面判斷
                    int v1 = (p instanceof SpecialAttacker) ? ((SpecialAttacker) p).getSpecialPower() : p.getBasePower();
                    int v2 = (p2 instanceof SpecialAttacker) ? ((SpecialAttacker) p2).getSpecialPower() : p2.getBasePower();
                    
                    // 屬性克制加成
                    if (isStrongAgainst(p.getType(), p2.getType())) v1 = (int)(v1 * 1.5);
                    
                    System.out.println("Power: " + v1 + " vs. " + v2);
                    if (v1 > v2) System.out.println(p.getName() + " wins!");
                    else if (v1 < v2) System.out.println(p2.getName() + " wins!");
                    else System.out.println("Draw!");
                }
            } 
            else if (cmd == 3) { // 3. 顯示總數
                System.out.println("Total: " + Pokemon.totalCount);
            }
            else if (cmd == 4) { // 4. 顯示詳細資訊
                int id = sc.nextInt();
                if (id <= list.size() && id > 0) list.get(id - 1).display();
            }
        }
        sc.close();
    }
}