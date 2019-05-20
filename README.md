# Software Engineer Challenge
1. Coding task:

    In object-oriented and functional programming, an immutable object is an object whose state cannot be modified after it is created. This is in contrast to a mutable object which can be modified after it is created. 

    Classes should be immutable unless there's a very good reason to make them mutable. If a class cannot be made immutable, limit its mutability as much as possible. The JDK contains many immutable classes, including String, the boxed primitive classes, and BigInteger and etc. Basically the immutable classes are less prone to error. 

    Please implement an immutable queue with the following api:
	
    Scala Version:
    ```scala
	trait Queue[T] {
	  def isEmpty: Boolean
	  def enQueue(t: T): Queue[T]
	  # Removes the element at the beginning of the immutable queue, and returns the new queue.
	  def deQueue(): Queue[T]
	  def head: Option[T]
	}
	object Queue {
	  def empty[T]: Queue[T] = ???
	}
    ```

    Java Version:
    ```java
	public interface Queue[T] {
	    public Queue<T> enQueue(T t);
	    #Removes the element at the beginning of the immutable queue, and returns the new queue.
	    public Queue<T> deQueue();
	    public T head();
	    public boolean isEmpty();
	}
    ```

2. Design Question: Design A Google Analytic like Backend System.
    We need to provide Google Analytic like services to our customers. Pls provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.
	
	The system needs to:

	1) handle large write volume: Billions write events per day.
	
	2) handle large read/query volume: Millions merchants want to get insight about their business. Read/Query patterns are time-series related metrics. 
	
	3) provide metrics to customers with at most one hour delay.
	
	4) run with minimum downtime.
	
	5) have the ability to reprocess historical data in case of bugs in the processing logic.
