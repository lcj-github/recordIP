package com.test.simultance;

import org.apache.log4j.Logger;

public abstract class Task implements BaseTask {

private static Logger log = Logger.getLogger(Task.class);
	
	private String threadNo;//入参示例，这里传入线程号，实际可参与对象
	
	private UserDaoI ud;//此测待测试的接口
	/**
	 * 通过构造方法来初始化入参
	 * @param thread
	 */
	public Task(String thread){
		this.threadNo = thread;
	}
	
	@Override
	public abstract void run();

	@Override
	public void init() {
		// TODO Auto-generated method stub
		log.info("init "+this.threadNo);
		ud = new UserDaoImpl();
	}

	@Override
	public String action(int i) {
		// TODO Auto-generated method stub
		log.info("action "+this.threadNo+" iterator "+i);
		//System.out.println("action "+this.threadNo+" iterator "+i);
		//测试ud.getUserById()接口
		return ud.getUserById(this.threadNo+"-"+i);//这个是我们要测试的接口主法
		
	}

	@Override
	public void end() {
		// TODO Auto-generated method stub

	}

}
