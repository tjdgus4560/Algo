
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static int[] from;
	static int[] to;
	static int N,M;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		M = sc.nextInt();
		from = new int[N+M];
		to = new int[N+M];
		for (int i = 0; i < from.length; i++) {
			from[i] = sc.nextInt();
			to[i] = sc.nextInt();
		}
		
		bfs();
		
	}
	private static void bfs() {
		Queue<Integer> q = new LinkedList<Integer>();
		boolean[] v = new boolean[107];
		q.add(1);
		v[1] = true;
		int cnt = 0;
		while(!q.isEmpty()) {
			cnt++;
			int size = q.size();
			for (int i = 0; i < size; i++) {
				int n = q.poll();
				if(n>=100) {
					System.out.println(--cnt);
					System.exit(0);
				}
				for (int j = 0; j < from.length; j++) {
					if(from[j] == n) {
						n = to[j];
					}
				}
				for (int j = 1; j <= 6; j++) {
					if(v[n+j]== true)
						continue;
					q.add(n+j);
					v[n+j] = true;
				}
			}
		}
		
	}
}
