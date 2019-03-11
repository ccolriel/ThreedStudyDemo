package com.ccol.threed.T02Lock;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/** 
 * 跟Sychronized进行效率比较  1.8的sychronized效率很快 1.8改了AtomicInteger的实现不是在用锁的方式去实现了效率很快
 * @author Administrator
 *
 */
public class LockDemo {
	public static void main(String[] args) {
		Runnable target = new CulBySychronized(); //4645242246
		Runnable target2= new CulByLock();//3234139152
		Runnable target3= new CulByLock2();//7331528615
		Thread t1 = new Thread(target3);
		Thread t2 = new Thread(target3);
		Thread t3 = new Thread(target3);
		Thread t4 = new Thread(target3);
		Thread t5 = new Thread(target3);
		Thread t6 = new Thread(target3);
		Thread t7 = new Thread(target3);
		Thread t8= new Thread(target3);
		Thread t9= new Thread(target3);
		Thread t10= new Thread(target3);
		long start = System.nanoTime();
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
		t7.start();
		t8.start();
		t9.start();
		t10.start();
		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
			t5.join();
			t6.join();
			t7.join();
			t8.join();
			t9.join();
			t10.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long end = System.nanoTime();
		System.out.println(end-start);
	}
}
class CulBySychronized implements Runnable{
	private int a = 100000000;
	@Override
	public void run() {
		while(true) {
			synchronized (CulBySychronized.class) {
					if(a>0) {
						a--;
					}else {
						break;
					}
				}
			}
		}
}
/**
 * AtomicInteger 底层的锁通过Lock实现 ，测试效率是sychronized的一倍
 * @author Administrator
 *
 */
class CulByLock implements Runnable{
	private final AtomicInteger a = new AtomicInteger(100000000);
	@Override
	public void run() {
		while(true) {
			if(a.get()>0) {
				a.getAndDecrement();
			}else {
				break;
			}
		}
		}
}

class CulByLock2 implements Runnable{
	private final AtomicIntWithLock a = new AtomicIntWithLock(100000000);
	@Override
	public void run() {
		while(true) {
			if(a.get()>0) {
				a.getAndDecrement();
			}else {
				break;
			}
		}
		}
}

class AtomicIntWithLock {
	 private int value;
	 private Lock lock = new ReentrantLock();
	 public AtomicIntWithLock() {
	 
	 }

	    public AtomicIntWithLock(int value) {
	        this.value = value;
	    }

	    public final int get() {
	        lock.lock();
	        try {
	            return value;
	        } finally {
	            lock.unlock();
	        }
	    }

	    public final void set(int newValue) {
	        lock.lock();
	        try {
	            value = newValue;
	        } finally {
	            lock.unlock();
	        }

	    }

	    public final int getAndSet(int newValue) {
	        lock.lock();
	        try {
	            int ret = value;
	            value = newValue;
	            return ret;
	        } finally {
	            lock.unlock();
	        }
	    }

	    public final boolean compareAndSet(int expect, int update) {
	        lock.lock();
	        try {
	            if (value == expect) {
	                value = update;
	                return true;
	            }
	            return false;
	        } finally {
	            lock.unlock();
	        }
	    }

	    public final int getAndIncrement() {
	        lock.lock();
	        try {
	            return value++;
	        } finally {
	            lock.unlock();
	        }
	    }

	    public final int getAndDecrement() {
	        lock.lock();
	        try {
	            return value--;
	        } finally {
	            lock.unlock();
	        }
	    }

	    public final int incrementAndGet() {
	        lock.lock();
	        try {
	            return ++value;
	        } finally {
	            lock.unlock();
	        }
	    }

	    public final int decrementAndGet() {
	        lock.lock();
	        try {
	            return --value;
	        } finally {
	            lock.unlock();
	        }
	    }
	    public String toString() {
	        return Integer.toString(get());
	    }
}