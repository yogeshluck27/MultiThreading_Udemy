package com.balazsholczer.threads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
//For implementing Runnable, the run() method needs to be implemented which does not return anything,
//while for a Callable, the call() method needs to be implemented which returns a result on completion

//When the call() method completes, answer must be stored in an object known to the main thread, 
//so that the main thread can know about the result that the thread returned. 
//How will the program store and obtain this result later? For this, a Future object can be used

class Processor implements Callable<String>{
	
	private int id;
	
	public Processor(int id){
		this.id = id;
	}

	@Override
	public String call() throws Exception {
		Thread.sleep(1000);
		return "Id: "+this.id;
	}
}

public class App {

	
	public static void main(String[] args) {
		
		ExecutorService executorService = Executors.newFixedThreadPool(2);
		List<Future<String>> list = new ArrayList<>();
		
		for(int i=0;i<5;i++){
			Future<String> future = executorService.submit(new Processor(i+1));
			//executorService.submit takes both runnable & callable as return type is Future
			//executorService.execute takes both runnable with void return type
	
			list.add(future);
		}
		
		for(Future<String> future : list){
			try{
				System.out.println(future.get()); //future.get,future.cancle,future.isDone
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
		executorService.shutdown();
		
	}
}
