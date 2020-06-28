package com.zxa.leetcode;

import java.util.Arrays;

/**
 * @Classname FirstMissNumber
 * @Date 2020/6/27 6:43 下午
 * @Created by zhangxinan
 */
public class FirstMissingPositive {

	public static void main(String[] args) {
		firstMissingPositive(new int[]{1});
	}

	public static int firstMissingPositive(int[] nums) {
		int flag[] = new int[nums.length + 1];
		Arrays.fill(flag, 0);
		int max = nums.length;
		for(int i = 0; i < nums.length; i++){
			int index = nums[i];
			if(index > max || index < 0){
				continue;
			}
			flag[index] = index;
		}
		for(int i = 1; i < flag.length; i++){
			int index = flag[i];
			if(index!= i){
				return i;
			}
		}
		return 1;
	}
}
