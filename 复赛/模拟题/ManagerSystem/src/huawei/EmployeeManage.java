package huawei;

import java.util.HashMap;

import huawei.Records2.Entry;

public class EmployeeManage {
	//两个方法都可以使用
	
	//方法一：HashMap<Long, Entry<K, V>>
	Records2 lru = new Records2<>();
	
	//方法二：
//	Records lru = new Records<>();
    
    public void init() {
    	lru.init();
    }
    
    public String createEmployee(String employeeName, boolean employeeType) {
    	String a = String.valueOf(employeeType);
    	String generate = lru.createEmployee(employeeName, a);
    	return generate;
    }


    public boolean updateEmployeeID (String oldEmployeeID, long idNumber)
    {
    	Boolean flag = lru.updateEmployeeID(oldEmployeeID, idNumber);
    	return flag;
    }
    
    public  boolean  updateStatus(String employeeID,  boolean status)
    {
    	String a = String.valueOf(status);
    	boolean flag = lru.updateStatus(employeeID, a);
    	return flag;
    }
    
    
    public  String getEmployeeIDByName(String employeeName)
    {
    	String generate = lru.getEmployeeIDByName(employeeName);
    	return generate;
    }

    
    public static void main(String[] args) {
    	EmployeeManage cache = new EmployeeManage();
    	cache.createEmployee("iris",true);
    	cache.createEmployee("bob",false);
    	cache.createEmployee("jack",false);
        String employeeID1 = cache.getEmployeeIDByName("iris");
        System.out.println(employeeID1);
        String employeeID2 = cache.getEmployeeIDByName("bob");
        System.out.println(employeeID2);
        String employeeID3 = cache.getEmployeeIDByName("jack");
        System.out.println(employeeID3);
        System.out.println("-----------");
        cache.updateEmployeeID("WXb1001",4888);
        cache.updateEmployeeID("i1000",5888);
        String employeeID4 = cache.getEmployeeIDByName("iris");
        System.out.println(employeeID4);
        String employeeID5 = cache.getEmployeeIDByName("bob");
        System.out.println(employeeID5);
        System.out.println("-----------");
        cache.updateStatus("WXb1001",true);
        cache.updateStatus("i5888",false);
        cache.createEmployee("iris",true);
        cache.createEmployee("litangsuo",true);
        cache.updateStatus("i1003", false);
        String aa=cache.getEmployeeIDByName("litangsuo");
        System.out.println(aa);
        
        
        
    }

}