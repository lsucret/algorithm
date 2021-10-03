package com.company.coding_test.kakao.internship2019.크레인인형뽑기게임;

import java.util.*;

/**
 * 코딩테스트 연습
 * 2019 카카오 개발자 겨울 인턴십
 * 크레인 인형뽑기 게임
 * https://programmers.co.kr/learn/courses/30/lessons/64061
 *
 */
class Solution {

    public static void main(String[] args) {
        // testcase 1
        int[][] board = {
                 {0,0,0,0,0}
                ,{0,0,1,0,3}
                ,{0,2,5,0,1}
                ,{4,2,4,4,2}
                ,{3,5,1,3,1}};
        Solution so = new Solution();
        int[] moves = {1,5,3,5,1,2,1,4};
        System.out.println("solution result : " + so.solution(board, moves)); // 4
    }

    /**
     * 시간복잡도 1000n (moves:1-1000, n : board size)
     * 테스트 1 〉	통과 (0.12ms, 74.6MB)
     * 테스트 2 〉	통과 (0.15ms, 76.3MB)
     * 테스트 3 〉	통과 (0.14ms, 73.8MB)
     * 테스트 4 〉	통과 (0.88ms, 74.2MB)
     * 테스트 5 〉	통과 (0.21ms, 74.1MB)
     * 테스트 6 〉	통과 (0.13ms, 73.6MB)
     * 테스트 7 〉	통과 (0.19ms, 76.9MB)
     * 테스트 8 〉	통과 (0.72ms, 79.1MB)
     * 테스트 9 〉	통과 (0.40ms, 73.5MB)
     * 테스트 10 〉	통과 (0.39ms, 72.4MB)
     * 테스트 11 〉	통과 (0.73ms, 71.3MB)
    */
    public int solution(int[][] board, int[] moves) {
        int removedToy = 0;
        Stack<Integer> prize = new Stack<>();

        for (int move : moves) {
            int pick = move - 1;

            for (int i = 0; i < board.length; i++) {
                if (board[i][pick] != 0) {
                    if (!prize.empty() && prize.peek() == board[i][pick]) {
                        prize.pop();
                        removedToy += 2;
                    } else {
                        prize.push(board[i][pick]);
                    }
                    board[i][pick] = 0;
                    break;
                }
            }
        }

        return removedToy;
    }
}