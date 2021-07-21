package com.dikshaJ.pubSub.PubSub;

import com.dikshaJ.pubSub.PubSub.DTO.Message;
import com.dikshaJ.pubSub.PubSub.Service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;

@SpringBootApplication
public class PubSubApplication {

	public static void main(String[] args) {
		new Timestamp(System.currentTimeMillis());

		PubSubService pubSubService = PubSubService.getInstance();

		Publisher javaPub1 = new PublisherImp();
		Publisher pyhtonPub2 = new PublisherImp();

		Subscriber javaSub1 = new SubscriberImp();
		Subscriber javaSub2 = new SubscriberImp();
		Subscriber pythonSub3 = new SubscriberImp();

		Message javaMessage1 = new Message("java", "Hi we are working on java 1");
		Message javaMessage2 = new Message("python", "Hi we are working on python 2");
		Message pythonMessage3 = new Message("java", "Hi we are working on java 3");
		Message pythonMessage4 = new Message("python", "Hi we are working on python 4");


		ExecutorService pool = Executors.newFixedThreadPool(4);

		for(int  i = 0; i < 5; i++){
			int finalI = i;
			pool.execute(() -> javaPub1.publish(new Message("java","Hi we are working on java " + finalI)));
		}
//		pool.execute(() -> {
//			javaPub1.publish(javaMessage1);
//			javaPub1.publish(javaMessage2);
//			pyhtonPub2.publish(pythonMessage3);
//			pyhtonPub2.publish(pythonMessage4);
//		});
//		pool.execute(() -> {
//			javaPub1.publish(javaMessage2);
//			System.out.println("current thread" + Thread.currentThread().getName());
//		});
//		pool.execute(() -> {
//			pyhtonPub2.publish(pythonMessage3);
//			System.out.println("current thread" + Thread.currentThread().getName());
//		});
//
//		pool.execute(() -> {
//			pyhtonPub2.publish(pythonMessage4);
//			System.out.println("current thread" + Thread.currentThread().getName());
//		});

		pool.execute(() -> {
			javaSub1.addSubscriber("java");
			System.out.println("current thread" + Thread.currentThread().getName());
		});
		pool.execute(() -> {
			javaSub2.addSubscriber("java");
			System.out.println("current thread" + Thread.currentThread().getName());
		});


//		pool.execute(() -> {
//			javaSub2.addSubscriber("java");
//			System.out.println("current thread" + Thread.currentThread().getName());
//		});
//		pool.execute(() -> {
//			pythonSub3.addSubscriber("python");
//			System.out.println("current thread" + Thread.currentThread().getName());
//		});


		ExecutorService executorService = Executors.newSingleThreadExecutor();

		executorService.submit(new Runnable() {
			public void run() {
				try {
					while(true)
					{
						pubSubService.broadCast();
						Thread.sleep(600L);

					}
				} catch (InterruptedException e) {
				}
			}
        });

		pubSubService.getMessagesForSubscriberOfTopic("java", javaSub1);


		System.out.println("Messages of Java Subscriber are: ");
		pool.execute(() -> javaSub1.printMessages());
		pool.execute(() -> javaSub2.printMessages());

		System.out.println("\nMessages of Python Subscriber are: ");
		pool.execute(() -> pythonSub3.printMessages());

		//pool.shutdown();




	}

}
