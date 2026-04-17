class MyArray 
{
    private:
        int* data;
        int size;

    public:
        MyArray(int s) : size(s) 
        {
            data = new int[size];
            for(int i=0; i<size; ++i) data[i] = i;
        }

        // 拷貝建構子（深拷貝）
        MyArray(const MyArray& other) : size(other.size) 
        {
            data = new int[size];   // 配置新記憶體
            for(int i=0; i<size; ++i) data[i] = other.data[i];    // 複製值
            cout << "拷貝建構子執行\n";
        }

        // 拷貝賦值運算子
        MyArray& operator=(const MyArray& other) 
        {
            if(this == &other) return *this;   // 自我賦值檢查
            delete[] data;  // 釋放舊記憶體
            size = other.size;
            data = new int[size];
            for(int i=0; i<size; ++i) data[i] = other.data[i];
            return *this;
        }

        // C++11 引入的 Move 建構子
        MyArray(MyArray&& other) noexcept
            : data(other.data), size(other.size) 
            {
                other.data = nullptr;   // 搬資源而非複製
                other.size = 0;
                cout << "Move 建構子執行\n";
            }

        ~MyArray() { delete[] data; }

        void print() 
        {
            for(int i=0; i<size; ++i) cout << data[i] << " \n"[i==size-1];
        }
};

int main() 
{
    MyArray a(5);   // 一般建構
    MyArray b = a;    // 拷貝建構子（深拷貝）
    MyArray c(10);
    c = a;      // 拷貝賦值運算子

    b.data[0] = 999;
    a.print();    // 0 1 2 3 4（a 不受影響，因為是深拷貝）
    b.print();    // 999 1 2 3 4
}