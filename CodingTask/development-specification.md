# Immutable Queue

## Requirements

### Original Requirement
In object-oriented and functional programming, an immutable object is an object whose state cannot be modified after it is created. This is in contrast to a mutable object which can be modified after it is created. 

Classes should be immutable unless there's a very good reason to make them mutable. If a class cannot be made immutable, limit its mutability as much as possible. The JDK contains many immutable classes, including String, the boxed primitive classes, and BigInteger and etc. Basically the immutable classes are less prone to error. 

Please implement an immutable queue with the following api:

... _(skip)_ ...

### Requirement Assumption
As several details and usecases are not given on the original requirement, this section will clarify those details and add some more requirements based on usecase assumption.

- The immutable queue should permit all elements (including null).
  
- The `enQueue()`, `deQueue()`, `head()` methods should be designed for use when failure is normal (e.g., de-queueing on empty queue will not throw any exception and return special value).  

- The queue should be boundless, which can extend until the memory allows as it's elements increase.

- To embrace functional programming style, `enQueue()`, `deQueue()`, `head()` are designed as pure function without any side effect. It means they should return identical value whenever it has called with identical arguments. It means one singleton (identical) object must be reused as a return value across the same VM whenever the arguments are identical (e.g., de-queueing 100 times on same queue which contains 1 value, must always return identical empty queue)
    - Due to implementation restriction of Java language, this requirement is not mandatory, but embrace functional programming style as much as possible.

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
Many of test cases below are integration test and does not use stub/mock object.

| Category | Test Target | Normal Case | Extreme Case | Illegal Case | Strange Case | Test Pass |
| --- | --- | --- | --- | --- | --- | --- |
| Write | | | | | | |
| | `enQueue(e)` | GIVEN empty queue, WHEN adding one element THEN return new queue instance containing 1 element <br><br> GIVEN a queue containing 1 element, WHEN enQueue() is called with any element, THEN return new queue instance containing old element at head | GIVEN empty queue, WHEN enQueue() is called with null value, THEN return new queue instance holding null element| | GIVEN empty queue, WHEN adding identical element twice, THEN return new queue instance containing 2 element | Passed |
| | `deQueue()` | GIVEN queue containing 2 elements, WHEN deQueue() is called, THEN return new queue instance containing 1 element, which is the last element of original queue | GIVEN empty queue, WHEN dequeue() is called, THEN return empty queue, which is identical to the original empty queue | | GIVEN empty queue and another queue containing 1 element, WHEN dequeue() is called on 1 element queue, THEN return empty queue which is identical to original empty queue| Passed |
| Read | | | | | |
| | `head()` | GIVEN queue with 1 element, WHEN head() is called, THEN return the first element | GIVEN empty queue, WHEN head() is called, THEN return null | | GIVEN queue containing null value as an element (size == 1), WHEN head is called, THEN return null | Passed |
| Utility | | | | | |
| | `isEmpty()` | GIVEN empty queue, WHEN isEmpty() is called, THEN return true <br><br> GIVEN queue containing 1 element, WHEN isEmpty() is called, THEN return false | | | | Passed |

Along with the functional test cases above, non-functional test is required as well to confirm that implementation satisfies requirement.

| Category | Test Target | Normal Case | Extreme Case | Illegal Case | Strange Case | Test Pass |
| --- | --- | --- | --- | --- | --- | --- |
| Non-functional | | | | | |
| | `getEmptyInstance()` | WHENEVER this method is called, return value should be identical | | | | Passed |

### End to End Test
N/A

## Implementation Specifications



### Complexity
1. Time Complexity :
1. Space Complexity :

Functional Programming style AMAP, 
    1. most of instance variables are immutable except some for implementation simplicity
    1. Most of the methods has no side effect and some of them are even pure function (for example, the return value of deQueue() method on 1 element queue is always identical)

Internally uses array for spatial locality, downside is it doens't shirnk automatically.
Time complexity enQueue & deQueue, avg case: O(1)
If we internally use array instead of immutablestack, then time complexity become O(n)

### Stack
getEmptyInstance() guarantees that all the empty ImmutableStack instances are identical as well as equal.
This is efficiency and based on thinking that "duplicated Immutable object does not need to exist in functional world"
The private constructur and 2 argument constructor guarantees this

Iterator is concurrent safe, unlike Java's other iterator, there is no need to keep track of modification count of backing ImmutableStack

### Iterator
TBD - Along with ImmutableQueue and ImmutableStack, the Iterator implementation also should be immutable. ImmutableStackIterator should return new Iterator whenever `pop()` is called, which is defined in the StackIterator interface. This class does not need to keep track of the modification count to avoid `ConcurrentModificationException()`, since the object is immutable.
