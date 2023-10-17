package com.balazsholczer.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
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

class StockMarketUpdater implements Runnable{
		
		@Override
		public void run() {
			System.out.println("Updating and downloading Stock related data..");
		}
}

public class ScheduledThreadPoolExample {

	public static void main(String[] args) {
		
		ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
		executorService.scheduleAtFixedRate(new StockMarketUpdater(),1000,5000,TimeUnit.MILLISECONDS);
		/*initialDelay - the time to delay first execution
		period - the period between successive executions
		unit - the time unit of the initialDelay and period parameters*/
		//We have to shutdown the executor
	}
}
