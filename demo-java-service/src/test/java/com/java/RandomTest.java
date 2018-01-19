package com.java;

import org.junit.Test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by iss on 17/7/19.
 */
public class RandomTest {
    private final static char[] numbers = {'0', '1', '2', '3', '4', '5','6', '7', '8', '9'};

    private final static char[]  letters= {
            'A', 'B', 'C', 'D', 'E', 'F',
            'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R',
            'S', 'T', 'U', 'V', 'W', 'X',
            'Y', 'Z'
    };

    @Test
    public void test() throws InterruptedException {
        Set<String> set = new HashSet<String>();
        while(set.size()<1000){
            String code = produceCode();
            System.out.println(code);
            set.add(code);
        }

    }

    private String produceCode() throws InterruptedException {
        Thread.sleep(10);
        StringBuffer code = new StringBuffer();
        Long seed = System.currentTimeMillis();
        Random random = new Random(seed);
        for(int i=0;i<8;i++){
            code.append(letters[random.nextInt(letters.length)]);
        }

        for(int j=0;j<8;j++){
            code.append(numbers[random.nextInt(numbers.length)]);
        }
        return code.toString();
    }

}
