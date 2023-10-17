package com.balazsholczer.threads;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 
 * 1.) ExecutorService es = Executors.newCachedThreadPool(); - going to return
 * an executorService that can dynamically reuse threads - before starting a job
 * -> it going to check whether there are any threads that finished the
 * job...reuse them - if there are no waiting threads -> it is going to create
 * another one - good for the processor ... effective solution !!!
 *
 * 2.) ExecutorService es = Executors.newFixedThreadPool(N); - maximize the
 * number of threads - if we want to start a job -> if all the threads are busy,
 * we have to wait for one to terminate
 *
 * 3.) ExecutorService es = Executors.newSingleThreadExecutor(); It uses a
 * single thread for the job
 *
 * execute() -> runnable + callable submit() -> runnable
 * 
 */

class Work implements Runnable {
	private int id;

	public Work(int id) {
		this.id = id;
	}

	@Override
	public void run() {
		System.out.println("Task with id " + id + " is in work - thread id " + Thread.currentThread().getId());
		long duration = (long) Math.random() * 5;
		try {
			TimeUnit.SECONDS.sleep(duration);
		} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
		}
	}
}

public class FixedThreadPoolExecutorExample {

	public static void main(String[] args) {

		ExecutorService executorService = Executors.newFixedThreadPool(5);

		for (int i = 0; i < 100; i++) {
			executorService.execute(new Work(i));
		}
		// We have to shutdown the executor
		// orderly shutdown.No new task will be executed.Will wait for all tasks
		// to finish the task
		executorService.shutdown();

		try {
			//Blocks  until all tasks have completed execution after a 
			//shutdownrequest, or the timeout occurs, 
			//or the current thread is interrupted, whichever happens first
			
			if (!executorService.awaitTermination(1000, TimeUnit.MILLISECONDS)) {
				// Attempts to stop all actively executing tasks, halts
				// theprocessing of waiting tasks,
				// and returns a list of the tasksthat were awaiting execution
				executorService.shutdownNow();
			}
		} catch (InterruptedException e) {
			executorService.shutdownNow();
		}
	}
}
