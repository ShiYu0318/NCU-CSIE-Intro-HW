class BankAccount {
    private String owner;
    private double balance;

    public BankAccount(String owner, double balance) {
        this.owner = owner;
        this.balance = balance;
    }

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("存款金額必須大於 0");
            return;
        }
        balance += amount;
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("提款金額必須大於 0");
            return;
        }
        if (amount > balance) {
            System.out.println("餘額不足");
            return;
        }
        balance -= amount;
    }

    public void printInfo() {
        System.out.println(owner + " 的餘額：" + balance);
    }

    public static void main(String[] args) {
        BankAccount acc = new BankAccount("Alice", 1000);
        acc.deposit(500);
        acc.withdraw(200);
        acc.printInfo();
    }
}