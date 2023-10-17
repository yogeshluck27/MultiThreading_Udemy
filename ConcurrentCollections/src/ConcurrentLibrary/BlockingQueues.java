package ConcurrentLibrary;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * QUEUE has a FIFO structure but it is not synchronized data structure.
 * 	BlockingQueue -> an interface that represents a queue that is thread safe
 * 		Put items or take items from it ...
 * 
 * 		For example: one thread putting items into the queue and another thread taking items from it
 * 			at the same time !!!
 * 				We can do it with producer-consumer pattern !!!
 * 
 * 		put() putting items to the queue
 * 		take() retreive & remove item from head .If queue is empty it will wait till there is an element to remove
 * 		poll() retrieve & remove item from Head of queue
 * 		add()
 * 		offer()->Add item to tail of queue & if queue is full then it will wait till the space is created to add item
 * 
 * BlockingQueue interface supports flow control (in addition to queue) by introducing blocking if 
 * either BlockingQueue is full or empty. 
 * A thread trying to enqueue an element in a full queue is blocked until some other thread makes space 
 * in the queue, either by dequeuing one or more elements or clearing the queue completely. 
 * Similarly, it blocks a thread trying to delete from an empty queue until some other threads insert an item. 
 * BlockingQueue does not accept a null value. 
 * If we try to enqueue the null item, then it throws NullPointerException.
 * 
 */

class FirstWorker implements Runnable {

	private BlockingQueue<String> blockingQueue;
	
	public FirstWorker(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
			blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("B");
            Thread.sleep(1000);
            blockingQueue.put("C");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }	
	}
}

class SecondWorker implements Runnable {

	private BlockingQueue<String> blockingQueue;
	
	public SecondWorker(BlockingQueue<String> blockingQueue) {
		this.blockingQueue = blockingQueue;
	}

	@Override
	public void run() {
		try {
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
	}
}

public class BlockingQueues {

	public static void main(String[] args) {
		
		BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);

		FirstWorker firstWorker = new FirstWorker(queue);
		SecondWorker secondWorker = new SecondWorker(queue);

        new Thread(firstWorker).start();
        new Thread(secondWorker).start();
		
	}
}
