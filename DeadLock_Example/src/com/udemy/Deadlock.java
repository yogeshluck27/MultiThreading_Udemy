package com.udemy;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Deadlock {
	//Deadlock: A situation in which two or more processes are unable to proceed
	//because each is waiting for one the others to do something.
	//LiveLock: A situation in which two or more processes continuously change their states in response
	//to changes in the other process(es) without doing any useful work.
	//Starvation: A situation in which a runnable process is overlooked indefinitely by the scheduler; 
	//although it is able to proceed, it is never chosen.
	
	private Lock lock1 = new ReentrantLock(true);
	private Lock lock2 = new ReentrantLock(true);
	
	public static void main(String[] args) {
		
		Deadlock deadlock = new Deadlock();
		
		new Thread(deadlock::worker1, "worker1").start();
		new Thread(deadlock::worker2, "worker2").start();
	}
	
	public void worker1() {
		lock1.lock();
		System.out.println("Worker1 acquires the lock1...");
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock2.lock();
		System.out.println("Worker1 acquires the lock2...");
		
		lock1.unlock();
		lock2.unlock();
	}
	
	public void worker2() {
		lock2.lock();
		System.out.println("Worker2 acquires the lock2...");
		
		try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		lock1.lock();
		System.out.println("Worker2 acquires the lock1...");
		lock2.unlock();
		lock1.unlock();
	}
	//We need to change the order in which these two threads acquire the lock.
	//Order should be lock1.lock & lock2.lock in Thread 2
}
