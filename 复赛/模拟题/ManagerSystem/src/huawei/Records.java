package huawei;

import java.util.*;
/*
 * cache.get(Object key);��������Ա����¼��Ա���ţ������֣�Ϊkey��Ա�������ֶ���Ϣ
 * cache.keySet();�������е�Ա����(ֻ��key)
 * cache.values;��������Ա����Ϣ��ֻ��value��   Collection<HashMap<String, String>>
 * cache.entrySet();��������Ա����Ϣ�ļ���(key,value)   Set<HashMap.Entry<Long, HashMap<String, String>>>
 * һ��record ��Ӧһ��Ա��  HashMap.Entry<Long, HashMap<String, String>>
 * record.getKey();�������Ա����Key
 * record.getValue();�������Ա�������ֶ�value  HashMap<String, String>
 * һ��cache�������record
*/
/*
 * ʹ�ù�ϣ��ʵ�����ݼ�¼��һ��Key(��¼��Long)��Ӧ���Value(��¼�е�������Ϣ)
 * HashMap<Long, HashMap<String, String>>
 *	 ����1
 * 		��Ϣ1������1
 * 		��Ϣ2������2
 *  	��Ϣ3������3
 *   	��Ϣ4������4
 *  ����2
 * 		��Ϣ1������1
 * 		��Ϣ2������2
 *  	��Ϣ3������3
 *   	��Ϣ4������4
 *  ��¼3
 * 		��Ϣ1������1
 * 		��Ϣ2������2
 *  	��Ϣ3������3
 *   	��Ϣ4������4
 * */
public class Records<K,V> {
	
    private HashMap<Long, HashMap<String, String>> cache;//��ϣ���������Ա����¼
    HashSet<Long> ids = new HashSet<>();//�������Ա�����
    
    //Ա����Ϣ�ֶ�
    private String Status ="Status";
    private String employeeID ="employeeID";
    private String employeeName = "employeeName";
    private String employeeType = "employeeType";
    
    
    public Records() {
	        cache = new HashMap<Long, HashMap<String,String>>();
	    }
	
    public void init() {
    	cache.clear();
    }

	//��һ��Ա���Ķ���ֶβ��뵽Ա����¼��
	public String createEmployee(String value1, String value2) {
        //Сд�ַ��Ҹ���������18��
		if(value1.length()>18 || !StringUtil.isLowerCase(value1)) {
			return null;
		}
		//�ҵ���СID������employeeID
		Long id = generateId();
        
        //һ��Ա������Ϣ��Ӧһ����ϣ��		
        HashMap<String, String> entry = new HashMap<>();
        
        String employee;
        if(value2.equals("true")) {
        	//��ʽԱ��
        	employee = ""+value1.charAt(0);
        }else {
        	//����Ա��
        	employee = "WX"+value1.charAt(0);
        }
        String generate = employee+id;
        	entry.put(employeeName, value1);
        	entry.put(employeeType, value2);
        	entry.put(Status, "true");
        	entry.put(employeeID, generate);
        ids.add(id);	
        cache.put(id, entry);
        
        return generate;
    }

		public boolean updateEmployeeID (String oldEmployeeID, long idNumber)
    {
		Long EmployeeID;
		//�Ϸ��Լ�飺����Ա��
    	if( idNumber>9999 || idNumber<1000 || oldEmployeeID.length()!=5) {
    		return false;
    	}else {
    		//��ʽԱ��ԭ��Id�����ֱ�־����
    			EmployeeID =Long.valueOf(oldEmployeeID.substring(1, 5)); 
    			//Ա��������
    			if(!ids.contains(EmployeeID)) {
    				return false;
    			}
    			//Ա����ְ����ȡ����Ա����Ϣ
    			HashMap<String, String> Employee = cache.get(EmployeeID);
    			if(!Employee.get(Status).equals("true")) {
    				return false;
    			}
    			//�ɸ���
    			ids.remove(EmployeeID);
    			ids.add(idNumber);
    			Employee.put(employeeID, ""+oldEmployeeID.charAt(0)+idNumber);
    			cache.put(idNumber, Employee);
    			cache.remove(EmployeeID);
    		return true;
    	}
    }
	
	
	    //����Ա������ȡ��Ա����
	public String getEmployeeIDByName(String employeeName) {

    	Set<HashMap.Entry<Long, HashMap<String, String>>> entryseSet=cache.entrySet();
    	boolean exist = false;//����Ƿ������Ա������ְ/���ߣ�
    	//���������ҵ���ͬ��Ա����ȡ��С���ֱ�־��Ա��
    	TreeSet<Long> same_name = new TreeSet<Long>();
    	for (HashMap.Entry<Long, HashMap<String, String>> record:entryseSet) {
    		String name = record.getValue().get("employeeName");
    		if(name.equals(employeeName)) {
    			//�ҵ���Ա��
    			exist = true;
    			//Ա����
    			Long num = record.getKey();
    			if(record.getValue().get("Status")!="false") {
    				same_name.add(num);
    			}
    		}
    		
    	}
    	if(exist==true) {
    		if(same_name.isEmpty()) {
    			return "quit";
    		}
    		return cache.get(same_name.first()).get("employeeID");
    	}
    		return "unknown";
    		 	        
    }
	
	
	public Boolean updateStatus(String employeeID, String status) {
		
		Set<HashMap.Entry<Long, HashMap<String, String>>> entryseSet=cache.entrySet();
    	for (HashMap.Entry<Long, HashMap<String, String>> record:entryseSet) {
    		//ͨ��employeeID��Ա��
    		String id = record.getValue().get("employeeID");
    		if(id.equals(employeeID)) {
    			String status_temp = record.getValue().get("Status");
    			//����ְ�����ɸ���
    			if(status_temp.equals("false")) {
    				return false;
    			}
    			//�ɸ���
	    			Long id_old=record.getKey();
	    			if(status.equals("false")) {
	    				//����Ϊ��ְ
		    			ids.remove(id_old);
	    			}else {
	    				//����Ϊ��ְ
	    				ids.add(id_old);
	    			}
	    			record.getValue().put(Status, status);
	    			return true;
    		}
    	}
    	//Ա��������
		return false;
	}
	
	 private Long generateId() {
		//Ԥ�ȶ���õ�1000-9999
		 	TreeSet<Long> old = new TreeSet<>();
	    	for(long i=1000;i<=9999;i++) {
	    		old.add(i);
	    	}
	    	    	
	    	//����Ա����¼
	    	
//	    	Set<HashMap.Entry<Long, HashMap<String, String>>> records = cache.entrySet();
//	    	//��ÿ��Ա����idȡ������ӵ�ids��
//	    	for (HashMap.Entry<Long, HashMap<String, String>> record:records) {
//	    		Long id = record.getKey();
//	    	}
	    	//������С�����
	    	old.removeAll(ids);
	    	Long id = old.first();
	    	
	    	
			return id;
		}
	
 
//    public static void main(String[] args) {
//    	Records<String,String> lru2 = new Records<>();
//        lru2.createEmployee("ir@is","true");
//        lru2.createEmployee("bob","false");
//        String employeeID1 = lru2.getEmployeeIDByName("iris");
//        System.out.println(employeeID1);
//        String employeeID2 = lru2.getEmployeeIDByName("b!!ob");
//        System.out.println(employeeID2);
//    }


}
