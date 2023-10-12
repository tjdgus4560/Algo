
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	
	static int N, max;
	static int[] heights;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int T = Integer.parseInt(br.readLine().trim());
		
		for (int i = 0; i < T; i++) {
			// inputs
			N = Integer.parseInt(br.readLine().trim());

			heights = new int[N];
			max = Integer.MIN_VALUE;
			
			String[] temp = br.readLine().trim().split(" ");
			for (int j = 0; j < N; j++) {
				int data = Integer.parseInt(temp[j]);
				heights[j] = data;
				max = Math.max(data, max);
			}
			
			// logic
			// 각 나무의 차이를 구해서 필요한 2, 1의 개수를 구하기
			int c1 = 0, c2 = 0;
			for (int j = 0; j < N; j++) {
				int val = max - heights[j];
//				System.out.print(val + " ");
				c2 += val / 2;
				c1 += val % 2;
			}
			
//			System.out.println("\n" + c1 + " " + c2);
			
			// 2가 필요한 개수를 줄이고, 1이 필요한 개수를 늘려 차이를 좁히기
			while(c2 > c1 + 1) {
				c1 = c1 + 2;
				c2--;
			}
			
//			System.out.println(c1 + " " + c2);
			
			int ans = c1 + c2;
			if (c1 > c2 + 1) ans += c1 - c2 - 1; 
			if (c2 > c1) ans += 1;
			
			System.out.printf("#%d %d\n", i + 1, ans);
			
			// 12 (31), 22, 27, 42, 47
			// t12 : 3 2 5 5 5 4 4 5 2 4 3 4 3 5 5 2 5 4 2 5 2 1 5 4 4 3 2 4 2 4
			//       2 3 0 0 0 1 1 0 3 1 2 1 2 0 0 3 0 1 3 0 3 4 0 1 1 2 3 1 3 1
			
		}
	}
}
