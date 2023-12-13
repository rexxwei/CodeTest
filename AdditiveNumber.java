/*
 * Additive Number
 * Problem:Additive numbers are defined to be a positive integer whose digits form an additive sequence. 
 * E.g. 11235 (1+1=2, 1+2=3, 2+3=5). What makes it difficult is that 12,122,436 is also one (12+12=24, 12+24=36). 
 * Given a range of integers, find all the additive numbers in that range.
 */

import java.util.*;

public class AdditiveNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findAdditiveNumber(1, 11236));
		System.out.println(findAdditiveNumber(1, 11236).size());
	}

	public static List<Integer> findAdditiveNumber(int start, int end) {
		List<Integer> rst = new ArrayList<>();
		if (start > end)
			return rst;

		// method 2
		for (int num = start; num <= end; num++) {
			if (isAdditive(num))
				rst.add(num);
		}
		return rst;
	}

	public static boolean isAdditive(int n) {
		String num = String.valueOf(n);
		for (int i = 1; i < num.length()-1; i++) //遍历前两个数所有的可能
			for (int j = i + 1; j < num.length(); j++) {
				if (backTrack(num,0, i, j))
					return true;
			}
		return false;
	}

	static boolean backTrack(String num, int i, int j, int k) { //i,j,k为第1,2,3个数的起始位置
		String first = new String();
		String second = new String();
		String sum;
		for(int l = i; l < j; l++) {
			first += String.valueOf(num.charAt(l));
		}
		for(int l = j; l < k; l++) {
			second += String.valueOf(num.charAt(l));
		}
		if ((first.charAt(0) == '0' && (first.length() != 1) || (second.charAt(0) == '0' && second.length() != 1)))
			//单独的零是可以存在的，其他情况第一位不能为零
			return false;
		sum = String.valueOf(Long.valueOf(first) + Long.valueOf(second)); //sum为前两位数字相加
		if (k + sum.length() > num.length()) //第三位长度不够，返回false
			return false;
		for (int l = k, p = 0; p < sum.length(); l++, p++) { //第三位数匹配
			if(num.charAt(l) != sum.charAt(p))
				return false;
		}
		if (k + sum.length() == num.length()) //刚好到最后一位，返回true
			return true;
		else
			return backTrack(num, j, k,k + sum.length());
	}

}
