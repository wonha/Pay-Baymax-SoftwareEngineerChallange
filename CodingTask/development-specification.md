# Immutable Queue

## Requirements
In object-oriented and functional programming, an immutable object is an object whose state cannot be modified after it is created. This is in contrast to a mutable object which can be modified after it is created. 

Classes should be immutable unless there's a very good reason to make them mutable. If a class cannot be made immutable, limit its mutability as much as possible. The JDK contains many immutable classes, including String, the boxed primitive classes, and BigInteger and etc. Basically the immutable classes are less prone to error. 

Please implement an immutable queue with the following api:
	
Scala Version:
```scala
trait Queue[T] {
	def isEmpty: Boolean
	def enQueue(t: T): Queue[T]
	def deQueue(): Queue[T]
	def head: Option[T]
}
object Queue {
	def empty[T]: Queue[T] = ???
}
```

Java Version:
```java
public interface Queue<T> {
	Queue<T> enQueue(T t);
	Queue<T> deQueue();
	T head();
	boolean isEmpty();
}
```

# Requirement Assumption
Null safe, boundless collection, Singletone world AMAP - dequeue should return singleton value when the queue is empty

TBD - Along with ImmutableQueue and ImmutableStack, the Iterator implementation also should be immutable. ImmutableStackIterator should return new Iterator whenever `pop()` is called, which is defined in the StackIterator interface.

## Interface Specifications
Since some of the details of the interface were not specified in the original requirement, 

- null safe - yes
````
- null safe - No
    - -null value can't exist on this stack, if we have to persist null, then we can create special object to represent the end of queue & stack-
````

| Method | Parameters | Special Return Values | Throws |
| --- | --- | --- | --- |
| `enQueue(e)` | e - the element to add | | |
| `deQueue()` | | empty queue - if the queue is already empty | |
| `head()` | | null - if the queue is empty | |

## Test
Test will be black box testing

### Unit Test
| Category | Test Target | Normal Case | Extreme Case | Illegal Case | Strange Case |
| --- | --- | --- | --- | --- | --- |
| Write | | | | | |
| | `enQueue(e)` | GIVEN empty queue, WHEN adding one element THEN return new queue instance containing new element at head | GIVEN empty queue, WHEN enQueue() is called with null value, THEN queue should hold null element| | GIVEN empty queue, WHEN adding identical element twice, THEN return new queue instance containing 2 element |
| | `deQueue()` | GIVEN queue containing 2 elements, WHEN deQueue() is called, THEN return new queue instance containing 1 element, which is the last element of original queue | GIVEN empty queue, WHEN dequeue() is called, THEN return empty queue, which is identical to the original empty queue | | GIVEN empty queue and another queue containing 1 element, WHEN dequeue() is called on 1 element queue, THEN return empty queue which is identical to original empty queue|
| Read | | | | | |
| | `head()` | GIVEN queue with 1 element, WHEN head() is called, THEN return the first element | GIVEN empty queue, WHEN head() is called, THEN return null | | GIVEN queue containing null value as an element (size == 1), WHEN head is called, THEN return null
| Util | | | | | |
| | `isEmpty()` | GIVEN empty queue, WHEN isEmpty() is called, THEN return true <br><br> GIVEN queue containing 1 element, WHEN isEmpty() is called, THEN return false | | | |
| | `getEmptyInstance()` | WHENEVER this method is called, return value should be identical | | | |

### Integration Test
N/A

### End to End Test
N/A

## Implementation Specifications

Internally uses array for spatial locality, downside is it doens't shirnk automatically.
Time complexity enQueue & deQueue, avg case: O(1)
If we internally use array instead of immutablestack, then time complexity become O(n)

---
Stack
getEmptyInstance() guarantees that all the empty ImmutableStack instances are identical as well as equal.
This is efficiency and based on thinking that "duplicated Immutable object does not need to exist in functional world"
The private constructur and 2 argument constructor guarantees this

Iterator is concurrent safe, unlike Java's other iterator, there is no need to keep track of modification count of backing ImmutableStack