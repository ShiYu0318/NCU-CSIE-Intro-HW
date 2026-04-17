class Student 
{
    private:
        string name;
        int* grades;    // 動態配置的陣列

    public:
        Student(string n, int numGrades): name(n) 
        {
            grades = new int[numGrades];    // heap 配置
            cout << name << " 建立" << endl;
        }

        ~Student() 
        {
            delete[] grades;    // 釋放記憶體
            cout << name << " 銷毀" << endl;
        }
};

int main() 
{
    Student s("A", 5);   // 建立，印出「A 建立」
}   // 離開 scope，自動印出「A 銷毀」