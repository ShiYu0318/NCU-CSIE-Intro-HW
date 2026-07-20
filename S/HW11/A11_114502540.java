import java.util.*;

// Bitmask Top-down DP
// O(2^n * 4 * n)

public class A11_114502540
{
    static int T;
    static int[][] fr = new int[10][2];
    static int[] sortedIdx = new int[10];

    static final int[] A = {0,1,1,2};
    static final int[] B = {0,0,1,1};

    static int encodeState(int a, int b)
    {
        if (a == 0) return 0;
        if (b == 0) return 1;
        if (a == 1) return 2;
        return 3;
    }

    // {frame, state, pos} -> {bonus, new state}
    static int[] transition(int[] f, int s, int pos)
    {
        int a = A[s], b = B[s], b1 = f[0], b2 = f[1];
        if (pos == 10)   return new int[]{a*b1 + b*b2, 0};
        if (b1 == 10)    return new int[]{a*10, encodeState(b+1, 1)};  // Strike
        if (b1+b2 == 10) return new int[]{a*b1 + b*b2, encodeState(1, 0)};  // Spare
        return new int[]{a*b1 + b*b2, 0};  // Open
    }

    // 碎片 f 能不能放第 1-9 局
    static boolean canNonTenth(int[] f)
    {
        if (f[0] == 10) return f[1] == 0;  // Strike 佔位符必須是 0
        return f[0] + f[1] <= 10;  // 兩球合計不能超過 10
    }

    // 碎片 f 能不能放第 10 局
    static boolean canTenth(int[] f)
    {
        if (T > 0) return f[0] == 10 || f[0]+f[1] == 10;
        return true;
    }

    static int[][][] dp = new int[1<<10][4][2];    // dp[mask][state][max/min]
    static boolean[][] vis = new boolean[1<<10][4];

    static int[] solve(int mask, int s, int pos)
    {
        if (pos == 11) return new int[]{0, 0};
        if (vis[mask][s]) return dp[mask][s];
        vis[mask][s] = true;

        int bestMax = Integer.MIN_VALUE, bestMin = Integer.MAX_VALUE;
        for (int j = 0; j < 10; j++)
        {
            if ((mask >> j & 1) == 1) continue;
            if (pos == 10 && !canTenth(fr[j])) continue;
            if (pos <  10 && !canNonTenth(fr[j])) continue;  // 新增

            int[] tr = transition(fr[j], s, pos);
            int bonus = tr[0], ns = tr[1];

            int[] sub = solve(mask|(1<<j), ns, pos+1);
            int subMax = sub[0], subMin = sub[1];

            if (subMax != Integer.MIN_VALUE) bestMax = Math.max(bestMax, bonus + subMax);
            if (subMin != Integer.MAX_VALUE) bestMin = Math.min(bestMin, bonus + subMin);
        }
        dp[mask][s][0] = bestMax;
        dp[mask][s][1] = bestMin;
        return dp[mask][s];
    }

    static List<int[]> res = new ArrayList<>();

    static void build(int mask, int s, int pos, int remaining)
    {
        if (pos == 11) return;
        for (int i : sortedIdx)
        {
            if ((mask >> i & 1) == 1) continue;
            if (pos == 10 && !canTenth(fr[i])) continue;
            if (pos <  10 && !canNonTenth(fr[i])) continue;  // 新增

            int[] tr = transition(fr[i], s, pos);
            int bonus = tr[0], ns = tr[1];
            int need = remaining - bonus;

            int[] sub = solve(mask|(1<<i), ns, pos+1);
            if (sub[0] == need)
            {
                res.add(fr[i]);
                build(mask|(1<<i), ns, pos+1, need);
                return;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        T = sc.nextInt();
        int base = T;

        for (int i = 0; i < 10; i++)
        {
            fr[i][0] = sc.nextInt();
            fr[i][1] = sc.nextInt();
            base += fr[i][0] + fr[i][1];
            sortedIdx[i] = i;
        }

        Integer[] idxBox = new Integer[10];
        for (int i = 0; i < 10; i++) idxBox[i] = i;
        Arrays.sort(idxBox, (i, j) -> {
            if (fr[i][0] != fr[j][0]) return fr[i][0] - fr[j][0];
            return fr[i][1] - fr[j][1];
        });
        for (int i = 0; i < 10; i++) sortedIdx[i] = idxBox[i];

        int[] result = solve(0, 0, 1);
        int maxBonus = result[0], minBonus = result[1];

        System.out.println((base + maxBonus) + " " + (base + minBonus));

        build(0, 0, 1, maxBonus);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 10; i++)
        {
            if (i > 0) sb.append(" ");
            sb.append(res.get(i)[0]).append(" ").append(res.get(i)[1]);
        }
        System.out.println(sb);
    }
}