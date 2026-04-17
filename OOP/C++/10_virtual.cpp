class Base 
{
    public:
        virtual void hello() { cout << "Base" << endl; }
};

class Derived : public Base 
{
    public:
        void hello() override { cout << "Derived" << endl; }
};

Base* ptr = new Derived();
ptr->hello();

// 沒有 virtual: 靜態綁定 -> 看指標型別 -> 印出 Base
// 有 virtual: 動態綁定 -> 查 vtable -> 印出 Derived