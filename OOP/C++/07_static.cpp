class Student 
{
    private:
        string name;
        static int totalCount;   // 所有學生共用這一個變數，static 屬於整個 class 不屬於某個物件

    public:
        Student(string n) : name(n)
        {
            totalCount++;   // 每建立一個學生，計數加一
            cout << name << " 建立，目前共 " << totalCount << " 個" << endl;
        }

        ~Student() 
        {
            totalCount--;
        }

        // static 方法只能存取 static 成員
        static int getCount() 
        {
            return totalCount;
        }
};

// static 成員必須在 class 外初始化
int Student::totalCount = 0;

int main() {
    Student s1("A");
    Student s2("B");
    Student s3("C");

    // 用類別名稱存取 static 方法
    cout << Student::getCount() << endl;   // 3
}