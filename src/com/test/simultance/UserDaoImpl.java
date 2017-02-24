package com.test.simultance;

public class UserDaoImpl implements UserDaoI {  
  
    @Override  
    public String getUserById(String id) {  
        // TODO Auto-generated method stub  
        return id;  
    }  
  
    @Override  
    public void inOrModifyUser(UserInfo userInfo) {  
        // TODO Auto-generated method stub  
        System.out.println(userInfo.getUserName());  
    }  
  
    @Override  
    public void delUser(String id) {  
        // TODO Auto-generated method stub  
        System.out.println("del "+id);  
    }  
  
}  