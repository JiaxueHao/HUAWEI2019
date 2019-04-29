package huawei;


import java.util.*;

/*
 * ʹ�ù�ϣ��ʵ�����ݼ�¼��һ��Key(��¼��Long)��Ӧ���Value(��¼�е�������Ϣ)
 * HashMap<Long, Entry<K, V>>
 *  ����1
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
public class Records2<K, V> {
	
    private HashMap<Long, Entry<K, V>> cache = new HashMap<Long, Entry<K, V>>() ;//��ϣ��+����
    private TreeSet<Long> ids = new TreeSet<>();

    public static class Entry<K , V> {
        private String Status = "Status";
        private String employeeID ="employeeID";
        private String employeeName = "employeeName";
        private String employeeType = "employeeType";
    }

    public void init() {
    	cache.clear();
    }
    
    public String createEmployee(String value1, String value2) {

    	if(value1.length()>18 || !StringUtil.isLowerCase(value1))
    	{
    		return null;
    	}
    	Entry<K, V> entry = new Entry<>();
    	String employee;
    	Long ID = generate();
    	if(value2.equals("true")) {
    		employee = ""+value1.charAt(0);
    	}else {
    		employee = "WX"+value1.charAt(0);
    	}
    	String employeeID = employee+ID;
    	
            entry.Status = "true";
            entry.employeeType = value2;
            entry.employeeName = value1;
            entry.employeeID = employeeID;
            cache.put(ID, entry);
            ids.add(ID);
            return employeeID;
    }

    private Long generate() {
    	TreeSet<Long> old = new TreeSet<>();
		// TODO Auto-generated method stub
		for(Long i = 1000L;i<=9999;i++) {
			old.add(i);
		}
//    	for (Map.Entry<Long, Entry<K, V>> info:cache.entrySet()) {
//			Long id_uesd=info.getKey();
//			ids.add(id_uesd);
//		}
    	old.removeAll(ids);
    	return old.first();
		
	}

	public String getEmployeeIDByName(String employeeName) {
		TreeSet<Long> all = new TreeSet<Long>();
		Boolean flag = false;
		for (Map.Entry<Long, Entry<K, V>> info:cache.entrySet()) {
			if(info.getValue().employeeName.equals(employeeName) ) {
				if(info.getValue().Status.equals("true")) {
					Long id = info.getKey();
					all.add(id);
				}else {
					flag = true;
				}
				
			}
		}
		
		if(!all.isEmpty()) {
			Long id_sel = all.first();
			String employeeid = cache.get(id_sel).employeeID;
			return employeeid;
		}else if(flag == true) {
			return "quit";
		}
		return "unknown";

	}

	public boolean updateEmployeeID (String oldEmployeeID, long idNumber) {
		Long id =0L;
		for (Map.Entry<Long, Entry<K, V>> info:cache.entrySet()) {
			if(info.getValue().employeeID.equals(oldEmployeeID) ) {
				id = info.getKey();
			}
		}
		if(id!=0l) {
			Entry<K,V> employee = cache.get(id);
			String name = employee.employeeName;
			String status = employee.Status;
			String type = employee.employeeType;
			
			//Ա����ְ����ʽ
			if(status.equals("true") && type.equals("true")) {
				String employeeID_new = ""+name.charAt(0)+idNumber;
				employee.employeeID = employeeID_new;
				ids.remove(id);
				ids.add(idNumber);
				cache.remove(id);
				cache.put(idNumber, employee);
				return true;
			}
		}
		return false;
	}
	
	public Boolean updateStatus(String employeeID, String status) {
		
		Long id =0L;
		for (Map.Entry<Long, Entry<K, V>> info:cache.entrySet()) {
			if(info.getValue().employeeID.equals(employeeID) ) {
				id = info.getKey();
			}
		}
		if(id!=0l) {
			Entry<K,V> employee = cache.get(id);
			String status_old = employee.Status;
			//Ա����ְ����ʽ
			if(status_old.equals("true")) {
				if(status.equals("true")) {
					ids.add(id);
				}else {
					ids.remove(id);
				}
				employee.Status = status;
				cache.put(id, employee);
				return true;
			}
		}
		return false;
	}
	
	
	
	
}