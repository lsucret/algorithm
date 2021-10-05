package com.company.coding_test.kakao.internship2019.캐시;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 코딩테스트 연습
 * 2018 KAKAO BLIND RECRUITMENT
 * [1차] 캐시
 * https://programmers.co.kr/learn/courses/30/lessons/17680?language=java#_=
 *
 */
class Solution {

    public static void main(String[] args) {
        // case 1
//        int cacheSize = 3;
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"};

        // case 3
//        int cacheSize = 2;
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};

        // case 4
//        int cacheSize = 3;
//        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA", "SanFrancisco", "Seoul", "Rome", "Paris", "Jeju", "NewYork", "Rome"};

        // case 5
        int cacheSize = 0;
        String[] cities = {"Jeju", "Pangyo", "Seoul", "NewYork", "LA"};
        Solution s = new Solution();
        System.out.println(s.solution(cacheSize, cities));
    }

    /**
     * my solution
     * 테스트 1 〉	통과 (0.18ms, 75.6MB)
     * 테스트 2 〉	통과 (0.15ms, 75.1MB)
     * 테스트 3 〉	통과 (0.17ms, 76.4MB)
     * 테스트 4 〉	통과 (0.20ms, 78.3MB)
     * 테스트 5 〉	통과 (0.20ms, 87.6MB)
     * 테스트 6 〉	통과 (0.02ms, 73.6MB)
     * 테스트 7 〉	통과 (0.01ms, 84.1MB)
     * 테스트 8 〉	통과 (0.19ms, 78.5MB)
     * 테스트 9 〉	통과 (0.14ms, 73.9MB)
     * 테스트 10 〉	통과 (0.59ms, 74.1MB)
     * 테스트 11 〉	통과 (38.04ms, 109MB) !!
     * 테스트 12 〉	통과 (0.38ms, 72.7MB)
     * 테스트 13 〉	통과 (0.61ms, 74.3MB)
     * 테스트 14 〉	통과 (1.45ms, 81.4MB)
     * 테스트 15 〉	통과 (1.23ms, 85.1MB)
     * 테스트 16 〉	통과 (1.97ms, 78.7MB)
     * 테스트 17 〉	통과 (0.02ms, 73.1MB)
     * 테스트 18 〉	통과 (2.47ms, 80.6MB)
     * 테스트 19 〉	통과 (3.77ms, 74MB)
     * 테스트 20 〉	통과 (2.43ms, 75.2MB)
     */
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) {
            return cities.length * 5;
        }

        int answer = 0;
        List<String> list = new LinkedList<>();

        for (String city : cities) {
            city = city.toUpperCase();
            if (list.contains(city)) {
                answer += 1;
                list.remove(city);
                list.add(city);
            } else if (list.size() >= cacheSize) {
                answer += 5;
                list.remove(0);
                list.add(city);
            } else {
                answer += 5;
                list.add(city);
            }
        }

        return answer;
    }

    /**
     * others solution
     * 테스트 1 〉	통과 (0.31ms, 78MB)
     * 테스트 2 〉	통과 (0.46ms, 74.8MB)
     * 테스트 3 〉	통과 (0.47ms, 79MB)
     * 테스트 4 〉	통과 (0.47ms, 66.3MB)
     * 테스트 5 〉	통과 (0.44ms, 66.5MB)
     * 테스트 6 〉	통과 (0.40ms, 89.9MB)
     * 테스트 7 〉	통과 (0.55ms, 76.5MB)
     * 테스트 8 〉	통과 (0.61ms, 72.1MB)
     * 테스트 9 〉	통과 (0.26ms, 74.2MB)
     * 테스트 10 〉	통과 (0.67ms, 74.5MB)
     * 테스트 11 〉	통과 (28.74ms, 123MB)
     * 테스트 12 〉	통과 (0.61ms, 76.4MB)
     * 테스트 13 〉	통과 (0.83ms, 80.1MB)
     * 테스트 14 〉	통과 (1.22ms, 66.8MB)
     * 테스트 15 〉	통과 (1.43ms, 76.6MB)
     * 테스트 16 〉	통과 (2.11ms, 79.3MB)
     * 테스트 17 〉	통과 (2.22ms, 77.9MB)
     * 테스트 18 〉	통과 (1.89ms, 82.9MB)
     * 테스트 19 〉	통과 (2.08ms, 76.7MB)
     * 테스트 20 〉	통과 (2.35ms, 84.7MB)
     */
    public int solution2(int cacheSize, String[] cities) {
        int answer = 0;
        LRU<String, String> clsTemp = LRU.newInstance(cacheSize);
        for (int i = 0; i < cities.length; i++) {
            String sTemp = cities[i].toUpperCase();
            if(clsTemp.containsKey(sTemp)) {
                answer++;
            }else {
                answer +=5;
            }
            clsTemp.put(sTemp, sTemp);
        }

        return answer;
    }

}

class LRU<K, V> extends LinkedHashMap<K, V> {
    private int size;

    private LRU(int size) {
        super(size, 0.75f, true);
        this.size = size;
    }
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > size;
    }
    public static <K,V> LRU<K,V> newInstance(int size) {
        return new LRU<K,V>(size);
    }
}