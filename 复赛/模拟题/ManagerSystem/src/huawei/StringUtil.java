package huawei;

import java.util.HashMap;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class StringUtil {
	//���붼��Сд��ĸ
	public static boolean isLowerCase(String name) {
		int lowercase=0;
		for(int i = 0;i < name.length();i++){
			char ch = name.charAt(i);
			//���붼Ϊ�ַ�
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
		System.out.println("1:"+s.indexOf("t"));//�����򷵻ص�һ�γ��ֵ�λ�ã��������򷵻�-1��
		System.out.println("2:"+s.lastIndexOf("t"));//�����򷵻������ֵ�λ�ã��������򷵻�-1��
		System.out.println("3:"+s.charAt(0));//��ȡָ���������ַ�
		System.out.println("4:"+s.trim().charAt(0));//ȥ���ո�
		
		System.out.println("5:"+s.replace("s","67"));//��str�е�����s1�滻��s2
		
		System.out.println("6:"+s.substring(5,9));//���ַ�������5���ַ�����8��
		
		System.out.println("7:"+s.toUpperCase());//ת��Ϊ��д ;  toLowerCase()ת��ΪСд
		
		System.out.println("8:"+String.valueOf(true));//ת��Ϊ�ַ���
		
		System.out.println("9:"+"true".startsWith("t"));//�Ƿ����ض��ַ�����ʼ ;  endWith()���������Ƿ����ض��ַ�������
		System.out.println("10:"+"true".startsWith("u", 2));//"true"�ĵ�2λ�ַ��Ƿ�Ϊu��ͷ
		
		
		StringBuilder sb = new StringBuilder("test");
		sb.append(" ok");
		System.out.println("11:"+sb);
		System.out.println("12:"+sb.reverse());
		System.out.println("13:"+sb);
		
		System.out.println("14:"+sb.replace(0, 4, "oline"));//sb��0-3���ַ��滻
		
		StringTokenizer st =new StringTokenizer("Welcome to my school");
		while(st.hasMoreTokens()) {
			System.out.println(st.nextToken());
		}
	
	}
	
	
	
}
