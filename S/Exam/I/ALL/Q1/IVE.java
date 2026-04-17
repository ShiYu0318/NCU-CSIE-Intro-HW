import java.util.*;
import static java.lang.System.*;

public class IVE{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        while(sc.hasNextLine())
        {
            String input = sc.nextLine();
            if(input.equals("0"))
                break;
            String str = input;
            int count = str.equals("9") ? 1 : 0;
            while(str.length() != 1)
            {
                int sum = 0;
                for(int i = 0; i < str.length(); i++)
                    sum += str.charAt(i) - '0';
                if(sum % 9 == 0)
                    count ++;
                else
                    break;
                str = Integer.toString(sum);
            }
            if(count > 0)
                System.out.printf("%s is a multiple of 9 and has 9-degree %d.\n", input, count);
            else
                System.out.printf("%s is not a multiple of 9.\n", input);
        }
    }
};