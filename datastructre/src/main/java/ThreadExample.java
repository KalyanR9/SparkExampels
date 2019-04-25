import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadExample {

	public ThreadExample() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {

		Callable<Integer> task = () -> {
			try {
				TimeUnit.SECONDS.sleep(1);
				return 123;
			} catch (InterruptedException e) {
				throw new IllegalStateException("task interrupted", e);
			}
		};

		ExecutorService executor = Executors.newFixedThreadPool(1);
		Future<Integer> future = executor.submit(task);

		System.out.println("future done? " + future.isDone());

		AtomicInteger atomicInt = new AtomicInteger(0);
		
		Integer result;
		try {
			result = future.get(1, TimeUnit.SECONDS);
			System.out.println("future done? " + future.isDone());
			System.out.print("result: " + result);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			e.printStackTrace();
		}

		ExecutorService executor1 = Executors.newSingleThreadExecutor();
		executor1.submit(() -> {
			String threadName = Thread.currentThread().getName();
			System.out.println("Hello " + threadName);
		});

		ExecutorService executorService2 = Executors.newFixedThreadPool(5);
		executorService2.submit(() -> {

		});

		executor1.shutdown();
		executorService2.shutdown();
	}

}
