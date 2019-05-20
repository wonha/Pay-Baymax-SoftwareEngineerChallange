# Immutable Queue

## Requirements
- null safe - No
- Iterator - further improve
- thread safe - further improve

## Interface Specifications
Since some of the details of the interface were not specified in the original requirement, 

| Method | Parameters | Returns | Throws |
| --- | --- | --- | --- |
| `enQueue(e)` | e - the element to add| false - if the element could not added to this queue | ClassCastException - if the class of the specified element prevents it from being added to this queue <br><br> NullPointerException - if the specified element is null|
| `deQueue()` | | null - if the queue is empty | |
| `head()` | | null - if the queue is empty | |

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
| | `head()` | | | | |
| Util | | | | | |
| | `isEmpty()` | | | | |

### Integration Test
N/A

### End to End Test
N/A

## Implementation Specifications

Internally uses array for spatial locality, downside is it doens't shirnk automatically.
