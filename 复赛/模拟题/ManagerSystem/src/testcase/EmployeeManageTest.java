package testcase;


import java.io.BufferedReader;
import java.io.FileReader;

import org.junit.Test;

import huawei.EmployeeManage;
import junit.framework.TestCase;


public class EmployeeManageTest extends TestCase {
	protected void setUp() throws Exception
	{
		super.setUp();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}
	
	@Test
	public void testCase01() {
		try {
			EmployeeManage test = new EmployeeManage();
			System.out.println("=======test case1==========");
		    	test.init();
		    
		    
		    assertEquals("l1000", test.createEmployee("litangsuo",true));
		    assertEquals("WXc1001", test.createEmployee("chenxiaoxiao",false));
		    assertEquals("z1002", test.createEmployee("zhangguoxiang",true));
		    assertEquals("WXw1003", test.createEmployee("wusiju",false));
	
		    
		    
		} catch (Exception e) {
		    assertTrue(false);
        }
	}
	
	@Test
	public void testCase02() {
	    try {
	    	EmployeeManage test = new EmployeeManage();
	        test.init();
		   
		    System.out.println("=======test case4==========");
	    
		    test.createEmployee("litangsuo",true); // l1000
		    test.createEmployee("chenxiaoxiao",false);// WXc1001
		    test.createEmployee("zhangguoxiang",true); // z1002
		    test.createEmployee("wusiju",false); //WXw1003
		    test.updateStatus("l1000", false);
		    assertEquals("s1000", test.createEmployee("shijinde", true));  //s1000
		    assertEquals("WXk1004", test.createEmployee("kaixin", false)); // WXk1004
		    
		    
	    } catch (Exception e) {
	        assertTrue(false);
	    }
	}

    @Test
    public void testCase03() {
        try {
	    	EmployeeManage test = new EmployeeManage();
	        test.init();
		   
		    System.out.println("=======test case3==========");
	    
		    test.createEmployee("litangsuo",true); // l1000
		    test.createEmployee("chenxiaoxiao",false);// WXc1001
		    test.createEmployee("zhangguoxiang",true); // z1002
		    test.createEmployee("wusiju",false); //WXw1003
		    assertEquals(true, test.updateStatus("l1000", true));
		    assertEquals(true, test.updateStatus("l1000", false));
		    assertEquals(false, test.updateStatus("l1000", true));
		    assertEquals(false, test.updateStatus("l1000", false));
		    assertEquals(false, test.updateStatus("x9999", false));
		    
	
        } catch (Exception e) {
          //  e.printStackTrace();
            assertTrue(false);
        }
	}
	  
    @Test
    public void testCase04() {
        try {
        	EmployeeManage test = new EmployeeManage();
	        test.init();
		  
	    
		    assertEquals("l1000", test.createEmployee("litangsuo",true));
		    assertEquals("WXc1001", test.createEmployee("chenxiaoxiao",false));
		    assertEquals("z1002", test.createEmployee("zhangguoxiang",true));
		    assertEquals("WXw1003", test.createEmployee("wusiju",false));
		    assertEquals("c1004", test.createEmployee("chenxiaoxiao",true));
		    
		   assertEquals(true, test.updateStatus("l1000", false));
		   assertEquals(false, test.updateEmployeeID("l1000", 1000));
		   assertEquals("quit", test.getEmployeeIDByName("litangsuo"));
		    
		   assertEquals(true, test.updateEmployeeID("z1002", 1000)); //zhangguoxiang   z1000  (1002 free)		    
		  assertEquals("z1000", test.getEmployeeIDByName("zhangguoxiang"));
		    
		    assertEquals(false, test.updateEmployeeID("WXw1003", 1002));
		    assertEquals(true, test.updateEmployeeID("c1004", 1002));  //chenxiaoxiao  c1002 (1004 free)
		    
		    assertEquals(true, test.updateEmployeeID("z1000", 1004)); 
		    
		    assertEquals("WXc1001", test.getEmployeeIDByName("chenxiaoxiao"));
		    assertEquals(true, test.updateStatus("WXc1001", false));
		    assertEquals("c1002", test.getEmployeeIDByName("chenxiaoxiao"));
		    
		    assertEquals("WXo1000", test.createEmployee("oujianshen",false));

        } catch (Exception e) {
          //  e.printStackTrace();
            assertTrue(false);
        }
    }
    
    private String readFileValue(String filename, String key) {
        BufferedReader fbr = null;
        try {
            fbr = new BufferedReader(new FileReader(filename));
            String line = null;
            
            while ((line = fbr.readLine()) != null) {
                String[] subs = line.split(":");
                if (key.equals(subs[0])) {
                    return subs[1];
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fbr != null) {
                try {
                    fbr.close();
                } catch (Exception e) {}
            }
        }
        
        return null;
    }
}