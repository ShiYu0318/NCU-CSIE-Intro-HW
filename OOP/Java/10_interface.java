// 介面：所有方法預設是 public abstract
interface Flyable {
    void fly();                          // 抽象方法
    void land();

    // Java 8+ 可以有 default 方法（有預設實作）
    default void glide() {
        System.out.println("滑翔中");
    }

    // Java 8+ 可以有 static 方法
    static String getDescription() {
        return "這是一個會飛的東西";
    }
}

interface Swimmable {
    void swim();
}

// 一個 class 只能 extends 一個 class
// 但可以 implements 多個 interface！
class Duck extends Animal implements Flyable, Swimmable {
    public Duck(String name, int age) {
        super(name, age);
    }

    @Override
    public void fly() {
        System.out.println(name + " 鴨子在飛");
    }

    @Override
    public void land() {
        System.out.println(name + " 鴨子降落");
    }

    @Override
    public void swim() {
        System.out.println(name + " 鴨子在游泳");
    }
}

class Airplane implements Flyable {
    private String model;

    public Airplane(String model) { this.model = model; }

    @Override
    public void fly() {
        System.out.println(model + " 飛機起飛");
    }

    @Override
    public void land() {
        System.out.println(model + " 飛機降落");
    }
}

public class Main {
    public static void main(String[] args) {
        // 介面也可以當型別
        Flyable[] flyers = {
            new Duck("唐老鴨", 5),
            new Airplane("波音 747")
        };

        for (Flyable f : flyers) {
            f.fly();
        }
        // 唐老鴨 鴨子在飛
        // 波音 747 飛機起飛
    }
}