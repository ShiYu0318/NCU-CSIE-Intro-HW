import java.util.*;

// double queue 模擬 greedy 賽局

public class A072_114502540
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        
        Queue<Integer> R = new LinkedList<>();
        Queue<Integer> B = new LinkedList<>();

        for(int i=0; i<n; ++i)
        {
            if(s.charAt(i) == 'R') R.offer(i);
            else B.offer(i);
        }

        while(!R.isEmpty() && !B.isEmpty())
        {
            int r = R.poll();
            int b = B.poll();

            if(r < b) R.offer(r + n);
            else B.offer(b + n);
        }
        
        System.out.println(R.isEmpty() ? "BLUE" : "RED");
        sc.close();
    }
}