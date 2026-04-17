import java.util.*;

// 經典題：最大子矩陣和

public class A071_114502540
{   
    public static int kadane(int[] pre)
    {   // 精髓: Greedy
        int maxSum = Integer.MIN_VALUE, tmp = 0;
        for(int i : pre)
        {
            tmp = Math.max(tmp + i, i);
            maxSum = Math.max(maxSum, tmp);
        }
        return maxSum;
    }

    public static int sol1(int[][] mat, int r, int c)
    {   // O(n^3) kadane 
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i<r; ++i)
        {
            int[] pre = new int[c];
            for(int j=i; j<r; ++j)
            {
                for(int k=0; k<c; ++k) pre[k] += mat[j][k];
                maxSum = Math.max(maxSum, kadane(pre));
            }
        }
        return maxSum;
    }

    public static int sol2(int[][] mat, int r, int c)
    {   // O(n^4) 2D prefix
        int maxSum = Integer.MIN_VALUE;
        int[][] pre = new int[r+1][c+1];
        for(int i=0; i<r; ++i) for(int j=0; j<c; ++j)   // 建二維前綴和
        {
            pre[i+1][j+1] = mat[i][j] + pre[i][j+1] + pre[i+1][j] - pre[i][j];
        }

        for(int ax=1; ax <= r; ++ax) for(int ay=1; ay <= c; ++ay)    // 枚舉左上
        for(int bx=ax; bx <= r; ++bx) for(int by=ay; by <= c; ++by)    // 枚舉右下
        {   // O(1) 算出子矩陣和並更新
            maxSum = Math.max(maxSum, pre[bx][by] - pre[ax-1][by] - pre[bx][ay-1] + pre[ax-1][ay-1]);
        }
        return maxSum;
    }

    public static int sol3(int[][] mat, int r, int c)
    {   // O(n^6) 暴力枚舉
        int maxSum = Integer.MIN_VALUE;
        for(int i=0; i<r; ++i) for(int j=0; j<c; ++j)    // 枚舉左上
        for(int k=i; k<r; ++k) for(int l=j; l<c; ++l)    // 枚舉右下
        {
            int sub = 0;    // 子矩陣和加總
            for(int m=i; m<=k; ++m) for(int n=j; n<=l; ++n) sub += mat[m][n];
            maxSum = Math.max(maxSum, sub);
        }
        return maxSum;
    }

    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);
        int r = sc.nextInt(), c = sc.nextInt();
        int[][] mat = new int[r][c];
        for(int i=0; i<r; ++i) for(int j=0; j<c; ++j) mat[i][j] = sc.nextInt();
        System.out.println(sol1(mat, r, c));
        sc.close();
    }
}