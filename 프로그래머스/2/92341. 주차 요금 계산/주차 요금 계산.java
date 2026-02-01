import java.util.*;

class Solution {
    class Car {
        ArrayDeque<Integer> stack;
        int totalTime; 
        int totalFee; 
        
        public Car() {
            stack = new ArrayDeque<>();
            totalTime = 0; 
            totalFee = 0;
        }
    }
        
    public int[] solution(int[] fees, String[] records) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        // <차량 번호, 차 정보> 
        Map<Integer, Car> carMap = new HashMap<>();
        
        for (String record: records) {
            String[] parts = record.split(" ");
            String timeStr = parts[0];
            int carNum = Integer.parseInt(parts[1]);
            String cmd = parts[2]; 
            
            int time = toMinute(timeStr); // hh:mm -> 분 형태로 변환

            carMap.putIfAbsent(carNum, new Car());
            Car car = carMap.get(carNum);
            if (cmd.equals("IN")) {
                car.stack.push(time);
            } else if (cmd.equals("OUT")) {
                int startTime = car.stack.pop();
                car.totalTime += time - startTime; 
            }
        }
        
        // 출차 내역없는 차량 처리 
        for (int key: carMap.keySet()) {
            Car car = carMap.get(key);
            if (!car.stack.isEmpty()) { // 출차 내역이 없다면 
                int endTime = toMinute("23:59");
                int startTime = car.stack.pop();
                
                car.totalTime += endTime - startTime; 
            }
            
            // System.out.println(key + ": " + car.totalTime);
        }
        
        // 모든 차량에 대해 주차 요금 계산 
        for (int key: carMap.keySet()) {
            pq.offer(key);
            Car car = carMap.get(key);
            
            int baseTime = fees[0]; // 기본 시간 
            int baseFee = fees[1]; // 기본 요금  
            int unitTime = fees[2]; // 단위 시간 
            int unitPrice = fees[3]; // 단위 요금
            
            int fee = 0;
            if (car.totalTime <= baseTime) {
                fee = baseFee; 
            } else {
                fee = baseFee + (car.totalTime - baseTime + unitTime - 1) / unitTime * unitPrice;
            }
            car.totalFee = fee; 
        }
        
        int[] result = new int[pq.size()];
        int idx = 0;
        while (!pq.isEmpty()) {
            int carNum = pq.poll();
            int fee = carMap.get(carNum).totalFee;
            
            result[idx++] = fee;
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