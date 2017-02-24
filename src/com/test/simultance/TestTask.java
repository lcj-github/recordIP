package com.test.simultance;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

import org.apache.log4j.Logger;

public class TestTask extends Task {

	private static Logger log = Logger.getLogger(TestTask.class);

	private static AtomicLong totalfailRecords = new AtomicLong(0);

	private static AtomicLong totalPassRecords = new AtomicLong(0);
	
	private static int  runTime = 30000 ;//测试执行时长，单位毫秒

	public TestTask(String thread) {
		super(thread);
	}

	/**
	 * 开5个线程执行任务，平时测试中开启多少个线程自己决定
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
		for (int i = 1; i <= 5; i++) {
			new Thread(new TestTask("t" + i)).start();//开启线程执行任务
		}
	}

	@Override
	public void run() {
		this.init();
		int i = 0;
		boolean ifRun = true;
		long start = System.currentTimeMillis();
		while (ifRun) {
			String user = this.action(i++);
			if (null != user)//验证事务是否成功
				totalPassRecords.incrementAndGet();
			else
				totalfailRecords.incrementAndGet();
			// 模拟ThinkTime，单位毫秒,对于TPS过万的接口建议不用模拟ThinkTime
			try {
				TimeUnit.MILLISECONDS.sleep(2000L);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long end = System.currentTimeMillis();
			long runInstance = end - start;
			if(runInstance >= runTime){
				ifRun = false;
				//执行完之后计算TPS
				System.out.println("Duration(ms): " + (runInstance));
				System.out.println("---------------------------------------------");
				System.out.println("pass trasactions : " + totalPassRecords.get());
				System.out.println("fail trasactions : " + totalfailRecords.get());
				System.out.println("TPS: " + totalPassRecords.get() / (runInstance / 1000));
				System.out.println("---------------------------------------------");
				Thread.currentThread().stop();
			}
			//log.info("Fail trasactions : " + totalfailRecords.get());
			//log.info("Pass trasactions ：  " + totalPassRecords.get());
			//System.out.println("Iterator : " + totalPassRecords.get());
			
		}
	}

}
