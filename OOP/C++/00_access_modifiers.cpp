class Student 
{
    // 存取修飾字
    private:
        // 只有 class 內部能存取
        string name;
        int age;
        double gpa;

    public:
        // 任何人都能存取
        void setName(string n) { name = n; }
        void setAge(int a) { age = a; }
        string getName() { return name; }

    protected:
        // 只有自己和子類別能存取（繼承時用到）
        int studentId;
};