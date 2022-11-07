package level02;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

class Solution {
	public int[] solution(int[] fees, String[] records) {
		TreeSet<String> carSet = new TreeSet<>();
		Map<String, List<Integer>> inputMap = new HashMap<>(); // 입차 기록을 담는 map
		Map<String, List<Integer>> outputMap = new HashMap<>(); // 출차 기록을 담는 map

		for (String record : records) {
			String[] tokens = record.split(" ");
			int time = timeToInt(tokens[0]); // 시간 문자열을 분단위로 바꿔준다.

			if (!(carSet.contains(tokens[1])))
				carSet.add(tokens[1]);

			if (tokens[2].equals("IN")) { // 입차 기록 추가
				if (!(inputMap.containsKey(tokens[1])))
					inputMap.put(tokens[1], new ArrayList<Integer>());
				inputMap.get(tokens[1]).add(time);

			} else { // 출차 기록 추가
				if (!(outputMap.containsKey(tokens[1])))
					outputMap.put(tokens[1], new ArrayList<Integer>());
				outputMap.get(tokens[1]).add(time);
			}
		}

		int[] times = new int[carSet.size()];
		int k = 0;
		for (String car : carSet) {
			int timeSum = 0;
			List<Integer> inputList = inputMap.get(car);
			List<Integer> outputList = outputMap.get(car);
			for (int i = 0; i < inputList.size(); i++) {
				int outputTime = 0;
				if (outputList == null || i >= outputList.size())
					outputTime = timeToInt("23:59");
				else
					outputTime = outputList.get(i);

				int inputTime = inputList.get(i);
				timeSum += (outputTime - inputTime);
			}
			times[k] = timeSum;
			k++;
		}

		// 최종 요금 계산
		int[] answer = new int[carSet.size()];
		for (int i = 0; i < carSet.size(); i++) {
			if (times[i] <= fees[0])
				answer[i] = fees[1];
			else {
				int cnt = (times[i] - fees[0]) / fees[2];
				if ((times[i] - fees[0]) % fees[2] != 0)
					cnt++;
				answer[i] = fees[1] + cnt * fees[3];
			}
		}

		return answer;
	}

	public int timeToInt(String timeString) {
		int time = 0; // 분단위 시간이 저장될 변수
		String[] tokens = timeString.split(":");
		time += Integer.parseInt(tokens[0]) * 60;
		time += Integer.parseInt(tokens[1]);
		return time;
	}

}