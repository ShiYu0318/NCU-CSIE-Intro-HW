class Student 
{
    public:
        string name;
        int age;

        Student(): name("未知"), age(0) { }

        Student(string n): name(n), age(0) { }

        Student(string n, int a): name(n), age(a) { }

        // 委派建構子（C++11）：呼叫另一個建構子
        Student(string n) : Student(n, 0) { }
};

Student s1;
Student s2("A");
Student s3("B", 21);