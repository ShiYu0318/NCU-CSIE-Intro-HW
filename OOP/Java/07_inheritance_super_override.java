class Animal {
    protected String name;
    protected int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void eat() {
        System.out.println(name + " 在吃東西");
    }

    public void sleep() {
        System.out.println(name + " 在睡覺");
    }

    public void speak() {
        System.out.println(name + " 發出聲音");
    }
}

class Dog extends Animal {
    private String breed;

    public Dog(String name, int age, String breed) {
        super(name, age);
        this.breed = breed;
    }

    @Override
    public void speak() {
        System.out.println(name + " 說：汪汪！");
    }

    public void fetch() {
        System.out.println(name + "（" + breed + "）去撿球！");
    }

    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", 3, "Golden Retriever");
        dog.eat();
        dog.sleep();
        dog.speak();
        dog.fetch();
    }
}