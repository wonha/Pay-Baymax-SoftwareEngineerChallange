# Immutable Queue

## Requirements

### Original Requirement
In object-oriented and functional programming, an immutable object is an object whose state cannot be modified after it is created. This is in contrast to a mutable object which can be modified after it is created. 

Classes should be immutable unless there's a very good reason to make them mutable. If a class cannot be made immutable, limit its mutability as much as possible. The JDK contains many immutable classes, including String, the boxed primitive classes, and BigInteger and etc. Basically the immutable classes are less prone to error. 

Please implement an immutable queue with the following api:

... _(skip)_ ...

### Extended Requirements
This section clarifies several requirement details and add more requirements based on usecases assumption.

- The immutable queue should permit all elements (including null).
  
- The `enQueue()`, `deQueue()`, `head()` methods should be designed for use when failure is normal (e.g., de-queueing on empty queue will not throw any exception and return special value).  

- The queue should be boundless; they grows until the memory allows as it's elements increase.

- To embrace functional programming style, `enQueue()`, `deQueue()`, `head()` are designed as pure function without any side effect. It means they should return identical value whenever it has called with identical arguments. It means one singleton (identical) object must be reused as a return value across the same VM whenever the arguments are identical (e.g., de-queueing 100 times on same queue which contains 1 value, must always return identical empty queue)
    - Due to implementation restriction of JDK, this requirement is not mandatory, however embrace functional programming style on a best-effort basis.

- The implementation does not necessarily to support thread-safe (concurrent access by multiple threads).

## Interface Specifications
As methods are designed to use when failure is normal, some methods of `Queue` class returns special return values to indicate certain circumstances. The methods does not throw any exceptions for those circumstances. 

| Method | Parameters | Special Return Values | Throws |
| --- | --- | --- | --- |
| `enQueue(e)` | e - the element to add (nullable) | | |
| `deQueue()` | | empty queue - if the queue is already empty | |
| `head()` | | null - if the queue is empty | |

## Test
All the testing are black box testing. `ImmutableQueue` class is the concrete class used on testing. 

### Unit & Integration Test
Many of test cases below are actually integration test; ImmutableQueue uses ImmutableStack, and ImmutableStack uses ImmutableStackIterator (for-each loop).    
However cases will not use stub/mock object to this black box testing on ImmutableQueue class.

| Category | Test Target | Normal Case | Extreme Case | Illegal Case | Strange Case | Test Pass |
| --- | --- | --- | --- | --- | --- | --- |
| Write | | | | | | |
| | `enQueue(e)` | GIVEN empty queue <br> WHEN adding one element <br> THEN return new queue instance containing 1 element <br><br> GIVEN queue of 1 element <br> WHEN enQueue() is called with new element <br> THEN return new queue instance containing old element at head | GIVEN empty queue <br> WHEN enQueue() is called with null value <br> THEN return new queue instance holding null element| | GIVEN empty queue <br> WHEN adding identical element twice <br> THEN return new queue instance containing 2 element | Passed |
| | `deQueue()` | GIVEN queue containing 2 elements <br> WHEN deQueue() is called <br> THEN return new queue instance containing 1 element, which is the last element of original queue | GIVEN empty queue <br> WHEN dequeue() is called <br> THEN return empty queue, which is identical to the original empty queue | | GIVEN empty queue and another queue containing 1 element <br> WHEN dequeue() is called on 1 element queue <br> THEN return empty queue which is identical to original empty queue| Passed |
| Read | | | | | |
| | `head()` | GIVEN queue with 1 element <br> WHEN head() is called <br> THEN return the first element | GIVEN empty queue <br> WHEN head() is called <br> THEN return null | | GIVEN queue containing null value as an element (size == 1) <br> WHEN head is called <br> THEN return null | Passed |
| Utility | | | | | |
| | `isEmpty()` | GIVEN empty queue <br> WHEN isEmpty() is called <br> THEN return true <br><br> GIVEN queue containing 1 element <br> WHEN isEmpty() is called <br> THEN return false | | | | Passed |

Along with the functional and non-functional test cases of methods from Queue interface, additional non-functional test case is required for ImmutableQueue's origin method to confirm that implementation satisfies requirement.

| Category | Test Target | Normal Case | Extreme Case | Illegal Case | Strange Case | Test Pass |
| --- | --- | --- | --- | --- | --- | --- |
| Non-functional | | | | | |
| | `getEmptyInstance()` | WHENEVER this method is called <br> THEN return a empty queue identical to others | | | | Passed |

### End to End Test
N/A

## Implementation Specifications

### Functional Programming style

Functional Programming style is introduced on a best-effort basis.
1. Instance variables are immutable except for some for implementation simplicity
1. Most of the methods has no side effect and some are pure function (for example, the return value of deQueue() method on 1 element queue is always identical), although it could not be the first class citizen. 

### Implementation Trade-offs

This `ImmutableQueue` class is likely to be faster than any other implementation based on array or list which copies all the value inside to create new instance.   
'Faster' above means in amortized time manner, and as we can expect spatial locality by using array, some of the operations will be a bit faster in array based approach. However, one another downside is that since array does not automatically shrink in Java, code level complexity is increased to free unused memory space.   
Also if array or list is used internally, then time complexity of enQueue() and deQueue() will become O(n).

`ImmutableQueue` and `ImmutableStack` are implementing `Countable` interface which contains `size()` operation. Better option will be let `Queue` and `Stack` interface extend an interface containing `size()` operation (like JDK library).

`ImmutableStackIterator` and `StackIterator` is used to implement `Iterable` interface. This is to use stack implementation easier with for-each loop.

Concrete classes does not expose public constructor.   
Need to call the static method to get empty Queue or Stack to start with.  
This is in order to reuse singleton object (like TRUE and FALSE object in Boolean class).

### Complexity

All of the `ImmutableQueue` operations run in amortized constant time.  


| | Method | Best | Worst | Avg. |
| --- |--- | --- | --- | --- |
| Time Complexity | | | |
| | enQueue() | O(1)| O(1) | O(1) |
| | deQueue() | O(1) | O(n) | O(1) |
| | head() | O(1) | O(1) | O(1) |
| | isEmpty() | O(1) | O(1) | O(1) |
| Space Complexity | | | |
| | enQueue() | O(1)| O(1) | O(1) |
| | deQueue() | O(1) | O(1) | O(1) |
| | head() | O(1) | O(1) | O(1) |
| | isEmpty() | O(1) | O(1) | O(1) |

### Stack
`ImmutableStack` class implements `Stack` interface.

getEmptyInstance() guarantees that all the empty ImmutableStack instances are identical as well as equal.
This is because duplicated immutable object does not need to be exist in functional world.

`ImmutableStack` is completely immutable object, and most of the methods are written in functional style.

### Iterator
`ImmutableStackIterator` class implements `StackIterator` interface and this interface extends `Iterator` interface.   
This structure is to give the same level of abstraction with other JDK collections framework.   

`ImmutableStackIterator` is also immutable class (although it internally change it's state).   
`StackIterator` contains `pop()` operation which returns immutable iterator.

Unlike other iterator implementations in `java.util`, the iterator returned by `ImmutableStack` class's iterator method does not need to be fail-fast (concurrent-safe); backing object is already immutable, so no need to keep track of modification count of backing object, and `ConcurrentModificationException` won't be thrown. 