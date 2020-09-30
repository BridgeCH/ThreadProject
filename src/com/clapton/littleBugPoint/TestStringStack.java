package com.clapton.littleBugPoint;

import java.util.ArrayList;

public class TestStringStack {
    public static void main(String[] args) {
        ArrayList<String> nums = new ArrayList<String>();
        nums.add("2");
        nums.add("3");

        for (String num: nums) {
            num = num+10;
        }
        System.out.println(nums);
    }
}
