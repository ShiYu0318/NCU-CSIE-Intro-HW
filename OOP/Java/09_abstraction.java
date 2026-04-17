abstract class Vehicle {
    protected String brand;
    protected int speed;

    public Vehicle(String brand, int speed) {
        this.brand = brand;
        this.speed = speed;
    }

    public abstract void move();
    public abstract void refuel();

    public void honk() {
        System.out.println(brand + " 按喇叭：嘟嘟！");
    }
}

class Car extends Vehicle {
    public Car(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void move() {
        System.out.println(brand + " 在公路行駛，時速 " + speed);
    }

    @Override
    public void refuel() {
        System.out.println(brand + " 加汽油");
    }
}

class Boat extends Vehicle {
    public Boat(String brand, int speed) {
        super(brand, speed);
    }

    @Override
    public void move() {
        System.out.println(brand + " 在海上航行，時速 " + speed);
    }

    @Override
    public void refuel() {
        System.out.println(brand + " 加柴油");
    }

    @Override
    public void honk() {
        System.out.println(brand + " 鳴汽笛：嗚～");
    }
}

public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car("Toyota", 120);
        Vehicle boat = new Boat("Viking", 30);

        car.move();
        boat.move();
        car.honk();
        boat.honk();
    }
}