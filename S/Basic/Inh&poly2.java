// Payment.java — 父類
class Payment {
    public void pay(int amount) {
        System.out.println("付款金額：$" + amount);
    }
}

// CreditCard.java — 子類
class CreditCard extends Payment {
    @Override
    public void pay(int amount) {
        System.out.println("信用卡刷卡：$" + amount + "（將於下月帳單扣款）");
    }
}

// Cash.java — 子類
class Cash extends Payment {
    @Override
    public void pay(int amount) {
        System.out.println("現金付款：$" + amount + "（請找零）");
    }
}

// Main.java
public class Main {
    public static void main(String[] args) {
        Payment[] payments = {
            new CreditCard(),
            new Cash(),
            new CreditCard()
        };

        for (Payment p : payments) {
            p.pay(500); // 多型：自動呼叫對應子類的 pay()
        }
    }
}