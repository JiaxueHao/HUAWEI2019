package huawei;

import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class StringUtil {
	//输入都是小写字母
	public static boolean isLowerCase(String name) {
		int lowercase=0;
		for(int i = 0;i < name.length();i++){
			char ch = name.charAt(i);
			//输入都为字符
			if(Character.isLetter(ch) && Character.isLowerCase(ch)){
					lowercase++; 
			}
		}
		if(lowercase==name.length()) {
			return true;
		}else {
			return false;
		}
	}
	
	public static void main(String[] args) {
		System.out.println(Character.isLetter('@'));
		String s = " otTfFsSent";
		System.out.println("1:"+s.indexOf("t"));//存在则返回第一次出现的位置，不存在则返回-1；
		System.out.println("2:"+s.lastIndexOf("t"));//存在则返回最后出现的位置，不存在则返回-1；
		System.out.println("3:"+s.charAt(0));//获取指定索引的字符
		System.out.println("4:"+s.trim().charAt(0));//去除空格
		
		System.out.println("5:"+s.replace("s","67"));//将str中的所有s1替换成s2
		
		System.out.println("6:"+s.substring(5,9));//子字符串：第5个字符到第8个
		
		System.out.println("7:"+s.toUpperCase());//转换为大写 ;  toLowerCase()转换为小写
		
		System.out.println("8:"+String.valueOf(true));//转换为字符串
		
		System.out.println("9:"+"true".startsWith("t"));//是否以特定字符串开始 ;  endWith()方法决定是否以特定字符串结束
		System.out.println("10:"+"true".startsWith("u", 2));//"true"的第2位字符是否为u开头
		
		
		StringBuilder sb = new StringBuilder("test");
		sb.append(" ok");
		System.out.println("11:"+sb);
		System.out.println("12:"+sb.reverse());
		System.out.println("13:"+sb);
		
		System.out.println("14:"+sb.replace(0, 4, "oline"));//sb的0-3个字符替换
		
		StringTokenizer st =new StringTokenizer("Welcome to my school");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	
	}
	
	
	
}
