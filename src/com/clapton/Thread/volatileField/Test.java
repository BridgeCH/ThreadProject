package com.clapton.Thread.volatileField;

import com.sun.deploy.util.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Test {
	private static boolean flag = true;
	
	public static void main(String[] args) throws InterruptedException {
//		System.out.println("主线程开始====");
//		thread1();
//		Thread.sleep(100);
//		flag = false;
//		System.out.println("主线程关闭====");

		String unzipDir = Thread.currentThread().getContextClassLoader().getResource("").getPath();
		System.out.println(unzipDir);
	}
	
	public static void thread1() {
		Thread t1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(flag) {
//					System.out.println();
				}
			}
		});
		t1.start();
	}
}