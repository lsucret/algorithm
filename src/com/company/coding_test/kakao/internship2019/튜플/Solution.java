package com.company.coding_test.kakao.internship2019.튜플;

import java.util.*;

/**
 * 코딩테스트 연습
 * 2019 카카오 개발자 겨울 인턴십
 * 튜플
 * https://programmers.co.kr/learn/courses/30/lessons/64065
 */
public class Solution {
    public static void main(String[] args) {
//        String s = "{{2},{2,1},{2,1,3},{2,1,3,4}}";
//        String s = "{{1,2,3},{2,1},{1,2,4,3},{2}}";
//        String s = "{{20,111},{111}}";
//        String s = "{{123}}";
        String s = "{{4,2,3},{3},{2,3,4,1},{2,3}}";
        Solution sol = new Solution();
        sol.solution(s);
    }

    /**
     * Set, Arrays sort를 사용한 풀이.
     * set.add() return 값은 boolean. 값이 없어 추가되면 true, 중복이라면 false
     */
    private int[] solution2(String s) {
        Set<String> set = new HashSet<>();
        String[] arr = s.replaceAll("[{]", " ").replaceAll("[}]", " ").trim().split(" , ");
        Arrays.sort(arr, Comparator.comparingInt(String::length));
        int[] answer = new int[arr.length];
        int idx = 0;
        for(String s1 : arr) {
            for(String s2 : s1.split(",")) {
                if(set.add(s2)) answer[idx++] = Integer.parseInt(s2);
            }
        }
        return answer;
    }

    public int[] solution(String s) {
        String str = s.substring(1,s.length()-1);
        char[] chars = str.toCharArray();

        Map<Integer, List<Integer>> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        String number = "";

        for (char ch : chars) {
            if (ch == '{') {
                list = new LinkedList<>();
            } else if (ch == ',') {
                if (!number.equalsIgnoreCase("")) {
                    list.add(Integer.parseInt(number));
                    number = "";
                }
            } else if (ch == '}') {
                list.add(Integer.parseInt(number));
                number = "";
                map.put(list.size(), list);
            } else {
                number += ch;
            }
        }

        List<Integer> result = new LinkedList<>();
        for (int i = 1; i <= map.size(); i++) {
            List<Integer> tmpList = map.get(i);
            if (tmpList.size() == 1) {
                result.add(tmpList.get(0));
            } else {
                for (Integer integer : tmpList) {
                    if (!result.contains(integer)) {
                        result.add(integer);
                    }
                }

            }
        }
        return result.stream().mapToInt(i->i).toArray();
    }
}
