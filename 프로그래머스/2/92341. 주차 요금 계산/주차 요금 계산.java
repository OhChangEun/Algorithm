import java.util.*;

class Solution {
    class Car {
        int inTime = -1;
        int totalTime = 0;
    }
        
    public int[] solution(int[] fees, String[] records) {                   
        int baseTime = fees[0]; // 기본 시간 
        int baseFee = fees[1]; // 기본 요금  
        int unitTime = fees[2]; // 단위 시간 
        int unitPrice = fees[3]; // 단위 요금
    
        // <차량 번호, 차 정보> 
        Map<Integer, Car> carMap = new HashMap<>();
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (String record: records) {
            String[] parts = record.split(" ");
            int time = toMinute(parts[0]); // hh:mm -> 분 형태로 변환
            int carNum = Integer.parseInt(parts[1]);
            String cmd = parts[2]; 
            
            carMap.putIfAbsent(carNum, new Car());
            Car car = carMap.get(carNum);
            
            if (cmd.equals("IN")) {
                car.inTime = time; 
            } else if (cmd.equals("OUT")) {
                car.totalTime += time - car.inTime; 
                car.inTime = -1; 
            }
        }
        
        // 출차 내역없는 차량 처리 
        for (int key: carMap.keySet()) {
            Car car = carMap.get(key);
            if (car.inTime != -1) { // 출차 내역이 없다면 
                int endTime = toMinute("23:59");
                int startTime = car.inTime;
                
                car.totalTime += endTime - startTime; 
            }
            pq.offer(key);
            // System.out.println(key + ": " + car.totalTime);
        }
        
        // 모든 차량에 대해 주차 요금 계산 
        int[] result = new int[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            int carNum = pq.poll();
            Car car = carMap.get(carNum);
            
            int totalFee = 0;
            if (car.totalTime <= baseTime) {
                totalFee = baseFee; 
            } else {
                totalFee = baseFee + (car.totalTime - baseTime + unitTime - 1) / unitTime * unitPrice;
            }
            
            result[idx++] = totalFee;
        }
        
        return result;
    }
    
    private int toMinute(String time) {
        String[] parts = time.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minute = Integer.parseInt(parts[1]);

        return hour * 60 + minute;
    }
}