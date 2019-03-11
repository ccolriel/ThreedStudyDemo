package com.ccol.threed.T01Atomic;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicDemo {
    public static void main(String[] args) throws InterruptedException {
		final AtomicInteger value = new AtomicInteger(0);
		/*System.out.println(value.compareAndSet(1,2));
		System.out.println(value.get());
		System.out.println(value.incrementAndGet());
		System.out.println(value.getAndAdd(2));
		System.out.println(value.get());*/
		
		final int threadSize = 10;
		
		Thread[] ts = new Thread[threadSize];
        for (int i = 0; i < threadSize; i++) {
            ts[i] = new Thread() {
                public void run() {
                    value.incrementAndGet();
                }
            };
        }
        //这里特别注意 如果只使用t.start()可能会导致value.get()的结果不准确，因为不能确定第几个线程执行完了就会执行main的线程，所以要用t.join让所以的线程执行完了之后再执行main的线程
        for(Thread t:ts) {
            t.start();
        }
       for(Thread t:ts) {
            t.join();
        }
		System.out.println(value.get());
		
	}
}
