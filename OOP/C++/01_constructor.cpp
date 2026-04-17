class Student {
    private:
        string name;
        int age;
        double gpa;

    public:
        // 無參數預設建構子
        Student() 
        {
            name = "未知";
            age = 0;
            gpa = 0.0;
            cout << "建立 Student" << endl;
        }

        // 有參數的建構子
        Student(string n, int a, double g) 
        {
            name = n;
            age = a;
            gpa = g;
        }

        // 初始化列表寫法
        Student(string n, int a, double g): name(n), age(a), gpa(g) { }

        void printInfo() 
        {
            cout << name << "，" << age << " 歲，GPA: " << gpa << endl;
        }
};

int main() 
{
    Student s1;     // 呼叫預設建構子
    Student s2("A", 20, 3.9);   // 呼叫有參數的建構子
    Student s3 = {"B", 21, 3.5};    // C++11 統一初始化語法

    s1.printInfo();     // 未知，0 歲，GPA: 0
    s2.printInfo();     // A，20 歲，GPA: 3.9
}