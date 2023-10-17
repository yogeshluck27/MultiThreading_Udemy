package concurrency10;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



public class App {

	private static int counter = 0;
	private static Lock lock = new ReentrantLock();
	
	public static void increment(){
		//If we will not use lock here then output will not be consistent
		lock.lock();
		counter++;
		lock.unlock();
	}
	
	public static void firstThread(){
		for(int i=0;i<10000000;i++){
			increment();
		}
	}
	
	public static void secondThread(){
		for(int i=0;i<10000000;i++){
			increment();
		}
	}
	
	public static void main(String[] args) {
		
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				firstThread();
			}
		});
		
		Thread t2 = new Thread(new Runnable() {
			public void run() {
				secondThread();
			}
		});
		
		t1.start();
		t2.start();
		
		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println(counter);
		
	}
}
