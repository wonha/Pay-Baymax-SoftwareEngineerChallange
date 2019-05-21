# Immutable Queue

## Requirements
- null safe - yes
````
- null safe - No
    - -null value can't exist on this stack, if we have to persist null, then we can create special object to represent the end of queue & stack-
````

- Iterator - further improve
- thread safe - further improve

## Interface Specifications
Since some of the details of the interface were not specified in the original requirement, 

| Method | Parameters | Returns | Throws |
| --- | --- | --- | --- |
| `enQueue(e)` | e - the element to add| false - if the element could not added to this queue | ClassCastException - if the class of the specified element prevents it from being added to this queue <br><br> NullPointerException - if the specified element is null|
| `deQueue()` | | null - if the queue is empty | |
| `element()` | | null - if the queue is empty | |

## Test
Test will be black box

### Unit Test
| Category | Test Target | Normal Case | Extreme Case | Illegal Case | Strange Case |
| --- | --- | --- | --- | --- | --- |
| Write | | | | | |
| | `enQueue(e)` | Given When Then | | 1. add null<br><br> 2. add wrong typed elem | Duplicated elem|
| | `deQueue()` | | from empty| | |
| Read | | | | | |
| | `deQueue()` | | | | |
| | `element()` | | | | |
| Util | | | | | |
| | `isEmpty()` | | | | |

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