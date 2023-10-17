package com.balazsholczer.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

	/**
	 * 
	 *  1.) ExecutorService es = Executors.newCachedThreadPool();
	 *  	- going to return an executorService that can dynamically
	 *  		reuse threads
	 *		- before starting a job -> it going to check whether there are any threads that
	 *			finished the job...reuse them
	 *		- if there are no waiting threads -> it is going to create another one 
	 *		- good for the processor ... effective solution !!!
	 *
	 *	2.) ExecutorService es = Executors.newFixedThreadPool(N);
	 *		- maximize the number of threads
	 *		- if we want to start a job -> if all the threads are busy, we have to wait for one
	 *			to terminate
	 *
	 *	3.) ExecutorService es = Executors.newSingleThreadExecutor();
	 *		It uses a single thread for the job
	 *
	 *		execute() -> runnable + callable
	 *		submit() -> runnable
	
	 */

class Task implements Runnable{
		private int id ;
		public Task(int id){
			this.id=id;
		}
		@Override
		public void run() {
			System.out.println("Task with id "+id+" is in work - thread id "+Thread.currentThread().getId());
			long duration = (long)Math.random()*5;
			try {
				TimeUnit.SECONDS.sleep(duration);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
}

public class SingleThreadExecutorExample {

	public static void main(String[] args) {
		//It is the single thread that will execute the task sequentially
		//one after the other
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		
		for(int i=0;i<5;i++){
			executorService.execute(new Task(i));
		}
		//We have to shutdown the executor
	}
}
