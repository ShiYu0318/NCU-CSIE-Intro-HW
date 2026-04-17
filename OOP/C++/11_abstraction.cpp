// 抽象類別：有純虛函式，不能直接建立物件
class Vehicle 
{
    protected:
        string brand;
        int speed;

    public:
        Vehicle(string b, int s) : brand(b), speed(s) { }

        // 純虛函式：= 0，強制子類別實作
        virtual void move() = 0;
        virtual void refuel() = 0;

        // 非純虛：有預設實作，子類別可以覆寫也可以不覆寫
        virtual void honk() 
        {
            cout << brand << " 按喇叭：叭叭！" << endl;
        }

        virtual ~Vehicle() { }
};

class Car : public Vehicle 
{
    public:
        Car(string b, int s) : Vehicle(b, s) { }

        void move() override { cout << brand << " 在公路行駛，時速 " << speed << endl; }
        void refuel() override { cout << brand << " 加汽油" << endl; }
};

class Boat : public Vehicle 
{
    public:
        Boat(string b, int s) : Vehicle(b, s) { }

        void move() override { cout << brand << " 在海上航行，時速 " << speed << endl; }
        void refuel() override { cout << brand << " 加柴油" << endl; }
        void honk() override { cout << brand << " 鳴汽笛：嗚～" << endl; }  // 覆寫喇叭聲
};

// Vehicle v("test", 0);   // 錯誤，抽象類別不能建立物件
Car  car("Toyota", 120);
Boat boat("Viking", 30);

car.move();     // Toyota 在公路行駛，時速 120
boat.move();    // Viking 在海上航行，時速 30
car.honk();     // Toyota 按喇叭：叭叭！
boat.honk();    // Viking 鳴汽笛：嗚～