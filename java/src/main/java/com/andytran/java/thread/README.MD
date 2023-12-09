# Simple Thread Session


#### Java Threads
  * Allow a program to operate more efficiently by doing multiple thing at the same time.
  * Can be used to perform complicated tasks in the background without interrupt the main program.
  * LifeCycle of a Thread in JAVA
    * NEW : been created but not yet started
    * RUNNABLE: either running or ready to run, but they are waiting for resource allocation from the system
    * BLOCKED: waiting for a monitor lock and trying to access a section of code is locked by some other thread.
    * WAITING: waiting for some other thread to perform a particular action
    * TIMED_WAITING: as WAITING, but within a stipulated amount of time.
    * TERMINATED: ether finished or was terminated abnormally
### How to create a thread
  * There are 2 ways to create a thread by extending the Thread class or implement the Runnable interface.

### Extend Thread class
   * SimpleThread.java
### Implement Runnable interface
   * SimpleRunnable.java
### Runnable or Thread?
| No | Key        | Thread                                                               | Runnable                                |
|----|------------|----------------------------------------------------------------------|-----------------------------------------|
| 1  | Basic      | Thread is a class                                                    | Runnable is a functional interface      |
| 2  | Methods    | has multiple methods as start(), run()                               | only abstract method run()              |
| 3  |            | Each thread creates a unique object and gets associated with it      | Multiple threads share the same objects |
| 4  | Memory     | More Memory required                                                 | Less Memory required                    |
| 5  | Limitation | After a class extends Thread class, It cannot extend any other class | Can extend another class                |




