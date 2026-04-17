// this 是指向 目前物件本身 的指標，解決名稱衝突或實現 method chaining
class Student 
{
    private:
        string name;
        int age;

    public:
        // 參數名稱和成員變數同名，用 this-> 區分
        Student(string name, int age) 
        {
            this->name = name;   // this->name 是成員變數
            this->age = age;    // age（右邊）是參數
        }

        // this 還可以用來回傳自己，支援 method chaining
        Student& setName(string name) 
        {
            this->name = name;
            return *this;   // 回傳自己的參考
        }

        Student& setAge(int age) 
        {
            this->age = age;
            return *this;
        }

        void print() 
        {
            cout << name << " " << age << endl;
        }
};

int main() 
{
    Student s("", 0);

    // 可以 Method chaining 因為每個方法都回傳 *this
    s.setName("A").setAge(19).print();   // A 19
}