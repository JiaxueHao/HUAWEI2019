package huawei;

import java.util.*;
/*
 * cache.get(Object key);返回所有员工记录中员工号（仅数字）为key的员工所有字段信息
 * cache.keySet();返回所有的员工号(只有key)
 * cache.values;返回所有员工信息（只有value）   Collection<HashMap<String, String>>
 * cache.entrySet();返回所有员工信息的集合(key,value)   Set<HashMap.Entry<Long, HashMap<String, String>>>
 * 一条record 对应一名员工  HashMap.Entry<Long, HashMap<String, String>>
 * record.getKey();返回这个员工的Key
 * record.getValue();返回这个员工所有字段value  HashMap<String, String>
 * 一个cache包含多个record
*/
/*
 * 使用哈希表实现数据记录，一个Key(记录号Long)对应多个Value(记录中的数据信息)
 * HashMap<Long, HashMap<String, String>>
 *	 订单1
 * 		信息1：数据1
 * 		信息2：数据2
 *  	信息3：数据3
 *   	信息4：数据4
 *  订单2
 * 		信息1：数据1
 * 		信息2：数据2
 *  	信息3：数据3
 *   	信息4：数据4
 *  记录3
 * 		信息1：数据1
 * 		信息2：数据2
 *  	信息3：数据3
 *   	信息4：数据4
 * */
public class Records<K,V> {
	
    private HashMap<Long, HashMap<String, String>> cache;//哈希表，存放所有员工记录
    HashSet<Long> ids = new HashSet<>();//存放所有员工序号
    
    //员工信息字段
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

	//将一名员工的多个字段插入到员工记录中
	public String createEmployee(String value1, String value2) {
        //小写字符且个数不超过18个
		if(value1.length()>18 || !StringUtil.isLowerCase(value1)) {
			return null;
		}
		//找到最小ID，生成employeeID
		Long id = generateId();
        
        //一名员工的信息对应一个哈希表		
        HashMap<String, String> entry = new HashMap<>();
        
        String employee;
        if(value2.equals("true")) {
        	//正式员工
        	employee = ""+value1.charAt(0);
        }else {
        	//合作员工
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
		//合法性检查：合作员工
    	if( idNumber>9999 || idNumber<1000 || oldEmployeeID.length()!=5) {
    		return false;
    	}else {
    		//正式员工原先Id的数字标志部分
    			EmployeeID =Long.valueOf(oldEmployeeID.substring(1, 5)); 
    			//员工不存在
    			if(!ids.contains(EmployeeID)) {
    				return false;
    			}
    			//员工离职，获取到该员工信息
    			HashMap<String, String> Employee = cache.get(EmployeeID);
    			if(!Employee.get(Status).equals("true")) {
    				return false;
    			}
    			//可更改
    			ids.remove(EmployeeID);
    			ids.add(idNumber);
    			Employee.put(employeeID, ""+oldEmployeeID.charAt(0)+idNumber);
    			cache.put(idNumber, Employee);
    			cache.remove(EmployeeID);
    		return true;
    	}
    }
	
	
	    //根据员工姓名取出员工号
	public String getEmployeeIDByName(String employeeName) {

    	Set<HashMap.Entry<Long, HashMap<String, String>>> entryseSet=cache.entrySet();
    	boolean exist = false;//标记是否有这个员工（离职/在线）
    	//保存所有找到的同名员工，取最小数字标志的员工
    	TreeSet<Long> same_name = new TreeSet<Long>();
    	for (HashMap.Entry<Long, HashMap<String, String>> record:entryseSet) {
    		String name = record.getValue().get("employeeName");
    		if(name.equals(employeeName)) {
    			//找到该员工
    			exist = true;
    			//员工号
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
    		//通过employeeID找员工
    		String id = record.getValue().get("employeeID");
    		if(id.equals(employeeID)) {
    			String status_temp = record.getValue().get("Status");
    			//已离职，不可更改
    			if(status_temp.equals("false")) {
    				return false;
    			}
    			//可更改
	    			Long id_old=record.getKey();
	    			if(status.equals("false")) {
	    				//更改为离职
		    			ids.remove(id_old);
	    			}else {
	    				//更改为在职
	    				ids.add(id_old);
	    			}
	    			record.getValue().put(Status, status);
	    			return true;
    		}
    	}
    	//员工不存在
		return false;
	}
	
	 private Long generateId() {
		//预先定义好的1000-9999
		 	TreeSet<Long> old = new TreeSet<>();
	    	for(long i=1000;i<=9999;i++) {
	    		old.add(i);
	    	}
	    	    	
	    	//所有员工记录
	    	
//	    	Set<HashMap.Entry<Long, HashMap<String, String>>> records = cache.entrySet();
//	    	//将每条员工的id取出，添加到ids中
//	    	for (HashMap.Entry<Long, HashMap<String, String>> record:records) {
//	    		Long id = record.getKey();
//	    	}
	    	//返回最小的序号
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
