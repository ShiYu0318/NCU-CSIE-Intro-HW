import java.util.*;

public class A062_114502540
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        StringBuilder sb = new StringBuilder();
        for(char c : s.toCharArray())
        {
            if(Character.isLetter(c) || Character.isDigit(c)) sb.insert(0, c);
        }
        System.out.println(sb.toString());
        sc.close();
    }
}