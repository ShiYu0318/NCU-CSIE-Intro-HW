class BankAccount 
{
    private:
        string owner;
        double balance;

    public:
        BankAccount(string o, double b) : owner(o), balance(b) { }

        // Getter：只讀，加 const 表示不修改物件
        string getOwner()  const { return owner; }
        double getBalance() const { return balance; }

        // Setter：有驗證邏輯
        void deposit(double amount) 
        {
            if (amount <= 0) 
            {
                cout << "存款金額必須大於 0" << endl;
                return;
            }
            balance += amount;
        }

        void withdraw(double amount) 
        {
            if (amount <= 0) {
                cout << "提款金額必須大於 0" << endl;
                return;
            }
            if (amount > balance) 
            {
                cout << "餘額不足" << endl;
                return;
            }
            balance -= amount;
        }

        void printInfo() const 
        {
            cout << owner << " 的餘額：" << balance << endl;
        }
};

int main() 
{
    BankAccount acc("A", 1000);
    // acc.balance = 99999;    // 錯誤，private 無法直接存取
    acc.deposit(500);
    acc.withdraw(200);
    acc.printInfo();    // A 的餘額：1300
}