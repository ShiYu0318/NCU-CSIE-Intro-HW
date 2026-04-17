import java.util.*;

interface SpecialAttacker {
    // TODO 1: Define the method prototype
    // Include a method named getSpecialPower that returns an value.
}

/**
 * TODO 2: Implement the Abstract Class: Pokemon
 * Rules:
 * - Attributes: id, name, type, strength, speed.
 * - Static Attribute: totalCount (used to auto-increment IDs starting from 1).
 * - Constructor: Initializes name, type, strength, speed, and increments the ID.
 * - Method: Implement getBasePower()
 * - Abstract Methods: Define makeSound() and eat().
 */
abstract class Pokemon {
    // TODO: Declare protected attributes and the public static totalCount variable.

    public Pokemon(String name, String type, int s, int v) {
        // TODO: Implement the constructor logic.
    }

    public int getBasePower() {
        // TODO: Implement the base power formula.
    }

    public String getType() { return type; }
    public String getName() { return name; }

    public void display() {
        System.out.println("ID: " + id + ", Type: " + name + ", Str: " + strength + ", Spd: " + speed);
    }

    // TODO: Define abstract methods for makeSound() and eat().
}

// --- TODO 3: Implement the Four Concrete Pokémon Classes ---

class Pikachu extends Pokemon implements SpecialAttacker {
    public Pikachu() { 
        // TODO: Call the super constructor with correct values.
    }

    // TODO: Implement makeSound: Print "Pikachu: Pika Pika!"
    // TODO: Implement eat: Update strength (+2) and print growth info.
    // TODO: Implement getSpecialPower: Calculate based on the formula.
}
// (Please implement Piplup, Treecko, and Charmander categories similarly...)


// =========================================================
// The following is the main test system provided by Professor Oak.
// DO NOT modify any of the code below.
// =========================================================
public class TODO_EN {
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

            if (cmd == 1) { // 1. Create Pokemon
                int t = sc.nextInt();
                if (t == 1) list.add(new Pikachu());
                else if (t == 2) list.add(new Piplup());
                else if (t == 3) list.add(new Treecko());
                else if (t == 4) list.add(new Charmander());
            } 
            else if (cmd == 2) { // 2. Perform Behavior
                int id = sc.nextInt();
                int act = sc.nextInt();
                if (id > list.size() || id <= 0) continue;
                Pokemon p = list.get(id - 1);

                if (act == 1) p.makeSound();
                else if (act == 2) p.eat();
                else if (act == 3) { // 2-3. Battle Comparision
                    int id2 = sc.nextInt();
                    if (id2 > list.size() || id2 <= 0) continue;
                    Pokemon p2 = list.get(id2 - 1);
                    
                    // Logic for Interface and Power calculation
                    int v1 = (p instanceof SpecialAttacker) ? ((SpecialAttacker) p).getSpecialPower() : p.getBasePower();
                    int v2 = (p2 instanceof SpecialAttacker) ? ((SpecialAttacker) p2).getSpecialPower() : p2.getBasePower();
                    
                    // Type advantage damage
                    if (isStrongAgainst(p.getType(), p2.getType())) v1 = (int)(v1 * 1.5);
                    
                    System.out.println("Power: " + v1 + " vs. " + v2);
                    if (v1 > v2) System.out.println(p.getName() + " wins!");
                    else if (v1 < v2) System.out.println(p2.getName() + " wins!");
                    else System.out.println("Draw!");
                }
            } 
            else if (cmd == 3) { // 3. Display Total count
                System.out.println("Total: " + Pokemon.totalCount);
            }
            else if (cmd == 4) { // 4. Display Info
                int id = sc.nextInt();
                if (id <= list.size() && id > 0) list.get(id - 1).display();
            }
        }
        sc.close();
    }
}