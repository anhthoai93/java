### Stack and Heap


# Stack
 * used for static memory allocation and the execution of a thread.
 * contains primitive values that are specific to a method and reference to object referred from the method that are in a heap.
 * Key features
   * it grows and shrinks as new method are called and returned
   * variables inside the stack exit only as long as the method that created them is running.
   * automatically allocated and deallocated when the method finishes execution.
   * if this memory is full, throws StackOverFlowError
   * Access to this memory is fast when compared to head memory.
   * This memory is thread-safe, as each thread operates in its own stack
# Heap
 * used for dynamic memory allocation of java object and JRE classes at runtime.
 * New objects are always created in heap space and the references to these objects are stored in stack memory.
 * These objects have global access and we can access them from anywhere in the application
 * Memory Model Generation:
   * Young generation: this is where all new objects are allocated and aged. A minor GC occurs when this fills up.
   * Old or Tenured Generation:  This is where long surviving objects are stored. 
   * Permanent Generation: this consists of JVM metadata for the runtime classes and application methods.
 * Key features
   * accessed via complex memory management techniques that include these Generations above.
   * if heap space is full, throws OutOfMemory
   * Accessed to this memory is comparatively slower than  stack memory.
   * This memory is not deallocated, it needs to GC to free up unused objects so as to keep the efficiency of the memory usage.
   * Unlike stack, a heap is not thread-safe and need to be handled by code.
# Summary

| Parameter               | Stack Memory                                                                 | Heap Space                                                                                    |
|-------------------------|------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------|
| Application             | used in parts, one at a time during execution of a thread                    | entire application uses the heap space during runtime                                         |
| Size                    | has size limits depending upon OS, usually smaller than heap                 | no size limit on Heap                                                                         |
| Storage                 | only primitive vars and references to objects that created in the heap space | All the newly created objects are stored here                                                 |
| Order                   | LIFO                                                                         | via complex memory management techniques that include these generations above                 |
| Life                    | the current method is running                                                | as long as the application runs                                                               |
| Efficiency              | Faster to allocate when compared to heap                                     | Slower to allocate when compared to stack                                                     |   
| Allocation/DeAllocation | Automatically allocated and deallocated when a method is called and returned | Allocated when new object are created and deallocated by GC when they're no longer referenced |
