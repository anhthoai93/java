# Java Concurrent

### Introduce java.util.concurrent
 * Executor
 * ExecutorService
 * ScheduledExecutorService
 * Future
 * CountDownLatch
 * CyclicBarrier
 * Semaphore
 * ThreadFactory
 * BlockingQueue
 * DelayQueue
 * Locks
 * Phaser

### Executor
 * is an interface that represents an object that executes provided task. 
 * ` ExecutorSample`
### ExecutorService
 * is an interface which allow us to execute tasks on thread asynchronously.
 * helps in maintaining a pool of thread and assign them tasks.
 * manages an in-memory queue and schedules summited tasks based on thread availability.
 * `ExecutorServiceSample.java`
### ScheduledExecutorService
 * like ExecutorService, but it can perform tasks periodically.
 * `ScheduledExecutorServiceSample.java`
### Future
 * is used to represent the result of an asynchronous operation.
 * `FutureSample.java`
### CountDownLatch
 * blocks a set of threads until some operation completes
 * is initialized with a counter which decrements as the dependent threads completed execution.
 * ` CountDownLatchSample`
### CyclicBarrier
 * works almost the same as CountDownLatch except that we can reuse it
 * It allows multiple threads to wait for each other using await() before invoking the final task.
 * used in we have a fix number of threads that must wait for each other to reach a common point before continuing execution.
 * ` CyclicBarrierSample`
# Semaphore
 * use to limit the number of concurrent threads accessing a specific resource.
 * used for blocking thread level access to some part of the physical or logical resource.
 * contains a set of permits, whenever a thread tries to enter the critical section. it needs to check the semaphore if a permit is available ot not.
 * if a permit is not available (tryAcquire()), the thread is not allowed to jump into the critical section.
 * if the permit is available the access is granted, and the permit counter decrease.
### ThreadFactory
 * acts as a thread(non-existing) pool which creates a new thread on demand.
 * ` ThreadFactorySample`
### BlockingQueue
 * is a data-structure in asynchronous programming
 * there are 2 types: unbounded queue(can grow almost indefinitely) and bounded queue(maximal capacity defined)
 * UnboundedQueue
   * the capacity will be set to Integet.MAX_VALUE
   * All operations that add an element to that queue will never block.
   * the memory could fill up and get an OOM.
 * Bounded
   * passing the capacity as an argument to constructor
 ### DelayQueue
 * An unbounded blocking queue of Delay elements, in which an element can only be taken when its delay has expired.
### Lock
 * is more flexible and sophisticated thread synchronization mechanism than the  standard synchronized block.
 * Different between Lock and synchronized block
   * A synchronized block is fully contained within a method. We can have Lock APIs lock() and unlock() operation in separate method.
   * A synchronized block does not support the fairness. Any thread can acquire the lock once released, and no preference can be specified. We can archive fairness within the Lock Apis by specifying the fairness property.it makes sure the longest waiting thread is given access to the lock.
   * A thread gets blocked if it cannot get access to the synchronized block. Lock API provides tryLock() method which acquired lock only if it's available and not help by any other thread. This reduces blocking time of thread waiting for the block.
### Phaser
 * is a barrier on which the dynamic number of threads need to wait before continuing execution. In the CountDownLatch, that number cannot be configured dynamically and must be supplied when creating the instance.
 * threads need to wait on the barrier before going to the next step of execution
### Synchronized Block
 * Allowing only one thread to execute at any given time.
 * Can use the synchronized keyword on:
   * Instance Methods
   * Static Method
   * Code Blocks
### ThreadLocal
 * allows us to store data that will be accessible only by a specific thread.
 * ThreadLocal vs ThreadPool should be extra careful when using them together, should cleanups last time for that thread local in the pool, that read will be reuse on next time.
### Kill java Thread
 * Use Flag
 * Interrupt
### Difference between run() and start() method in thread
 * start(): create a new thread and code inside run() is executed in the new thread.
 * run() no new thread is created and code inside method is executed in the current thread.
### Wait and Notify In Thread
 * the current thread must own the object's monitor and execute synchronized instance method, body of a block or synchronized static method.
 * wait: force the current thread to wait until some other thread invokes notify() or notifyAll() on the same object.
   * notify or notifyAll(): for walking up threads that are waiting for access to this object's monitor.
### Wait and Sleep
 * wait: only be called from a synchronized block. It releases the lock on the object so that another thread can jump in and acquired a lock.
 * Sleep: a static method called from any context. it pauses the current thread and does not release any locks.
### Join() Thread
 * the calling thread goes into a waiting state
### Difference between Daemon and User Threads
 * User threads are high-priority threads, JVM will wait for any user thread to complete its task before terminating it.
 * Daemon threads are low-priority threads whose only role is to provide the services to user thread.
 * Daemon thread serves for user thread and only need while user threads are running. they wont prevent the JVM from exiting once all user threads have finished their execution.
 * Daemon threads are useful for background supporting task such as garbage collection, releasing memory of unused objects abd removing unwanted entries from the cache. Most of the JVM threads are demon threads.
 * 