package com.test.simultance;

public interface BaseTask extends Runnable {
	
	/** 
     * 初始化操作 
     */  
    public void init();  
    /** 
     * 具体迭代的内容，要执行的测试程序放在此方法中，可以有入参 
     * @param i 
     */  
    public String action(int i);  
    /** 
     * 收尾工作 
     */  
    public void end();  

}
