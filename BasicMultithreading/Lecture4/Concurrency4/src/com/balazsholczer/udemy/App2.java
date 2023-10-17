package com.balazsholczer.udemy;

class Runner11 extends Thread {
	
	@Override
	public void run() {
		for(int i=0;i<10;++i){
			System.out.println("Runner1: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

class Runner22 extends Thread {
	
	@Override
	public void run() {
		for(int i=0;i<10;++i){
			System.out.println("Runner2: "+i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

public class App2 {

	public static void main(String[] args) throws InterruptedException {
		
	Thread t1 = new Thread(new Runner11());
	Thread t2 = new Thread(new Runner22());
		
		//Runner1 t1 = new Runner1();
		//Runner2 t2 = new Runner2();

		t1.start();
		Thread.sleep(10);
		t2.start();

		try {
			t1.join();
			t2.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		System.out.println("Finished the tasks...");
	}
}
