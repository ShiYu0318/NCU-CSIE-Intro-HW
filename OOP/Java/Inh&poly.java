// Animal.java — 父類
class Animal {
    public void makeSound() {
        System.out.println("...(animal sound)...");
    }
}

// Dog.java — 子類
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("汪汪！");
    }
}

// Cat.java — 子類
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("喵喵！");
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        // 多型：父類型別的參考指向子類物件
        Animal[] animals = {
            new Dog(),
            new Cat(),
            new Dog()
        };

        for (Animal a : animals) {
            a.makeSound(); // 呼叫各自 override 的版本
        }
    }
}