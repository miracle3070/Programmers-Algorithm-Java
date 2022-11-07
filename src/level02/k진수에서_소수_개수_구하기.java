package level02;

import java.util.StringTokenizer;

class Solution {

	public int solution(int n, int k) {
		int answer = 0;
		String number = convertNumber(n, k);
		StringTokenizer st = new StringTokenizer(number, "0");
		while(st.hasMoreTokens()) {
			String token = st.nextToken();
			if("".equals(token))
				continue;
			long num = Long.parseLong(token);
			if(isPrime(num)) // 해당 숫자가 소수일 경우
				answer++;
		}

		return answer;
	}
	
	public boolean isPrime(long num) {
		if (num == 1)
			return false;
		
		for(int i=2; i < (long)Math.sqrt(num)+1; i++) {
			if(num % i == 0)
				return false;
		}
		return true;
	}

	public String convertNumber(int n, int k) {
		StringBuilder sb = new StringBuilder();
		while (n > 0) {
			int r = n % k;
			n /= k;
			sb.append(r);
		}
		sb.reverse();
		return sb.toString();
	}
}
