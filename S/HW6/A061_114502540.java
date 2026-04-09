import java.util.*;

public class A061_114502540
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String[] sa = sc.next().split(";");
        String maxName = "";
        double maxPrice = 0;
        for(String i : sa)
        {
            String[] it = i.split(",");
            String name = it[0];
            double price = Double.parseDouble(it[1]);
            if(price > maxPrice)
            {
                maxPrice = price;
                maxName = name;
            }
            if(price >= 500) System.out.println("敗家項目:" + name + "(" + price + ")");
        }
        System.out.println("Most Expensive Item:" + maxName + ",Price:" + maxPrice);
        sc.close();
    }
}