// 父類別（Base class）
class Animal 
{
    protected:
        string name;
        int age;

    public:
        Animal(string n, int a) : name(n), age(a) { }

        void eat() 
        {
            cout << name << " 在吃東西" << endl;
        }

        void sleep() 
        {
            cout << name << " 在睡覺" << endl;
        }

        // 多型用 virtual 讓子類別可以 override
        virtual void speak() 
        {
            cout << name << " 發出聲音" << endl;
        }

        virtual ~Animal() { }   // 有繼承就要 virtual destructor
};

// 子類別（Derived class）
class Dog : public Animal 
{
    private:
        string breed;   // Dog 自己額外的屬性

    public:
        // 子類別建構子要呼叫父類別建構子
        Dog(string n, int a, string b)
            : Animal(n, a), breed(b) { }

        // 覆寫父類別的方法
        void speak() override 
        {
            cout << name << " 說：汪汪！" << endl;
        }

        void fetch() 
        {
            cout << name << "（" << breed << "）去撿球！" << endl;
        }
};

class Cat : public Animal 
{
    public:
        Cat(string n, int a) : Animal(n, a) { }

        void speak() override 
        {
            cout << name << " 說：喵～" << endl;
        }
};

int main() 
{
    Dog d("小黑", 3, "拉布拉多");
    Cat c("咪咪", 2);

    d.eat();    // 繼承自 Animal：小黑 在吃東西
    d.speak();   // 覆寫的版本：小黑 說：汪汪！
    d.fetch();   // Dog 自己的方法
    c.speak();   // 咪咪 說：喵～
}