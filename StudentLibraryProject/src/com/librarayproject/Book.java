package com.librarayproject;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {

	private int id;
	private Lock lock;

	public Book(int id) {
		this.lock = new ReentrantLock();
		this.id = id;
	}

	public void read(Student student) throws InterruptedException {
		//We can also use lock.lock() method.But in that case Thread will be blocked until lock is acquired
		//If we use tryLock then it will try to acquire lock in given time.& immediately return true or false.
		//It will not be blocked for infinite time
		if(lock.tryLock(10, TimeUnit.SECONDS)){
			System.out.println(student + " starts reading " + this);
			Thread.sleep(2000);
			lock.unlock();
			System.out.println(student + " has finished reading " + this);
		}
		
	}

	public String toString() {
		return "Book" + id;
	}

}