package queens;

import java.util.*;

public class Queens {

    public static boolean checkpos(int[] q, int n) {
        for (int i = 0; i < n; i++) {
            if ((q[i] == q[n])||((q[i] - q[n]) == (n - i))||((q[n] - q[i]) == (n - i)))
            	return false;
        }
        return true;
    }

    public static void printQueens(int[] q) {
        int n = q.length;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (q[i] == j) System.out.print("Q ");
                else           System.out.print("* ");
            }
            System.out.println();
        }  
        System.out.println();
    }

    public static void solve(int n) {
        int[] a = new int[n];
        solve(a, 0);
    }

    public static void solve(int[] q, int k) {
        int n = q.length;
        if (k == n) printQueens(q);
        else {
            for (int i = 0; i < n; i++) {
                q[k] = i;
                if (checkpos(q, k)) solve(q, k+1);
            }
        }
    }  


    public static void main(String[] args) {
    	Scanner i = new Scanner(System.in);
        System.out.print("Enter the number of queens: ");
        int n = i.nextInt();
        solve(n);
    }

}