package com.urise.webapp;

import java.util.Arrays;

public class StreamMethod {

    public static void main(String[] args) {
        int[] values = new int[]{9, 0, 8};
        StreamMethod sm = new StreamMethod();
        System.out.println(sm.minValue(values));
    }

    int minValue(int[] values) {
        return Arrays.stream(values).
                distinct().sorted().reduce(0, (a, b) -> 10 * a + b);
    }


//    List<Integer> oddOrEven(List<Integer> integers){
//       integers.stream()
//
//                .filter(p -> {
//            if () {
//                return true;
//            }
//            return false;
//        }).findAny()
//                .orElse(null);
//
//
//
//        return new ArrayList<>();
//    }
}



    /*
если сумма всех чисел нечетная - удалить все нечетные,
если четная - удалить все четные. Сложность алгоритма должна быть O(N). Optional - решение в один стрим.*/



