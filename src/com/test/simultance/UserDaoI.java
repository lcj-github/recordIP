package com.test.simultance;

public interface UserDaoI {

	public String getUserById(String id);  
    
    public void inOrModifyUser(UserInfo userInfo);  
      
    public void delUser(String id); 
}
