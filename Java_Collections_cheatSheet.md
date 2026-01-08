# Java Collections Cheatsheet with Explanation

Java Collections Framework provides a set of interfaces and classes for storing and manipulating groups of data as a single unit. Here's a handy cheatsheet covering the most important collections, with examples and explanations.

---

## 1. List

- **Allows duplicates, preserves order.**
- Common Implementations: `ArrayList`, `LinkedList`, `Vector`

**Example:**
```java
List<String> list = new ArrayList<>();
list.add("apple");
list.add("banana");
```

| Implementation | Features |
|----------------|----------|
| ArrayList      | Fast random access, slow insert/delete in middle |
| LinkedList     | Fast insert/delete, slow random access          |
| Vector         | Synchronized (thread-safe), rarely used         |

**Common DSA Methods:**
```java
list.get(index)           // Access element at index - O(1) for ArrayList
list.set(index, element)  // Update element at index
list.indexOf(element)     // Find first occurrence
list.lastIndexOf(element) // Find last occurrence
list.subList(from, to)    // Get sublist view
Collections.reverse(list) // Reverse the list
Collections.sort(list)    // Sort the list
```

---

## 2. Set

- **No duplicates, no guaranteed order (unless using SortedSet/LinkedHashSet).**
- Common Implementations: `HashSet`, `LinkedHashSet`, `TreeSet`

**Example:**
```java
Set<Integer> set = new HashSet<>();
set.add(1);
set.add(2);
set.add(2); // ignored, no duplicates
```

| Implementation   | Features                                   |
|------------------|--------------------------------------------|
| HashSet          | Fast, unordered                            |
| LinkedHashSet    | Maintains insertion order                  |
| TreeSet          | Sorted order (according to Comparable/Comparator) |

**Common DSA Methods:**
```java
set.contains(element)     // Check if element exists - O(1) for HashSet
set.remove(element)       // Remove element
set.isEmpty()             // Check if empty
set.addAll(collection)    // Union of sets
set.retainAll(collection) // Intersection of sets
set.removeAll(collection) // Difference of sets

// TreeSet specific (sorted)
TreeSet<Integer> treeSet = new TreeSet<>();
treeSet.first()           // Get smallest element
treeSet.last()            // Get largest element
treeSet.lower(element)    // Get largest element < given element
treeSet.higher(element)   // Get smallest element > given element
treeSet.floor(element)    // Get largest element <= given element
treeSet.ceiling(element)  // Get smallest element >= given element
```

---

## 3. Queue

- **FIFO (First-In, First-Out) data structure.**
- Common Implementations: `LinkedList`, `PriorityQueue`, `ArrayDeque`

**Example:**
```java
Queue<String> queue = new LinkedList<>();
queue.add("first");
queue.add("second");
String next = queue.poll(); // "first"
```

| Implementation   | Features                                   |
|------------------|--------------------------------------------|
| LinkedList       | Implements both Queue and Deque            |
| PriorityQueue    | Orders elements by priority                |
| ArrayDeque       | Resizable array, efficient for both ends   |

**Common DSA Methods:**
```java
queue.offer(element)      // Add element (preferred over add)
queue.poll()              // Remove and return head, null if empty
queue.peek()              // View head without removal, null if empty
queue.remove()            // Remove and return head, throws exception if empty
queue.element()           // View head, throws exception if empty

// PriorityQueue specific (Min-Heap by default)
PriorityQueue<Integer> pq = new PriorityQueue<>();
pq.offer(5);              // Add element - O(log n)
pq.poll()                 // Remove min element - O(log n)
pq.peek()                 // View min element - O(1)

// Max-Heap
PriorityQueue<Integer> maxPQ = new PriorityQueue<>(Collections.reverseOrder());
```

---

## 4. Deque

- **Double-Ended Queue, supports insertion/removal at both ends.**
- Common Implementations: `ArrayDeque`, `LinkedList`

**Example:**
```java
Deque<String> deque = new ArrayDeque<>();
deque.addFirst("first");
deque.addLast("last");
String front = deque.pollFirst(); // "first"
String back = deque.pollLast();   // "last"
```

| Implementation   | Features                                   |
|------------------|--------------------------------------------|
| ArrayDeque       | Fast, resizable array, preferred choice    |
| LinkedList       | Implements both Queue and Deque            |

**Common DSA Methods:**
```java
// Add operations
deque.addFirst(element)   // Add at front (throws exception if fails)
deque.addLast(element)    // Add at end (throws exception if fails)
deque.offerFirst(element) // Add at front (returns false if fails)
deque.offerLast(element)  // Add at end (returns false if fails)

// Remove operations
deque.removeFirst()       // Remove from front (throws exception if empty)
deque.removeLast()        // Remove from end (throws exception if empty)
deque.pollFirst()         // Remove from front (returns null if empty)
deque.pollLast()          // Remove from end (returns null if empty)

// Peek operations (view without removal)
deque.peekFirst()         // View front element (returns null if empty)
deque.peekLast()          // View last element (returns null if empty)
deque.getFirst()          // View front element (throws exception if empty)
deque.getLast()           // View last element (throws exception if empty)

// Use as Stack (LIFO)
deque.push(element)       // Same as addFirst()
deque.pop()               // Same as removeFirst()
deque.peek()              // Same as peekFirst()
```

---

## 5. Map

- **Key-value pairs, keys are unique.**
- Common Implementations: `HashMap`, `LinkedHashMap`, `TreeMap`, `Hashtable`

**Example:**
```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("b", 2);
int val = map.get("a"); // 1
```

| Implementation   | Features                                   |
|------------------|--------------------------------------------|
| HashMap          | Fast, unordered                            |
| LinkedHashMap    | Maintains insertion order                  |
| TreeMap          | Sorted order by keys                       |
| Hashtable        | Synchronized (thread-safe), legacy         |

**Common DSA Methods:**
```java
map.put(key, value)           // Add/update key-value pair
map.get(key)                  // Get value, returns null if not found
map.getOrDefault(key, default) // Get value or default if not found
map.containsKey(key)          // Check if key exists - O(1) for HashMap
map.containsValue(value)      // Check if value exists - O(n)
map.remove(key)               // Remove key-value pair
map.putIfAbsent(key, value)   // Add only if key doesn't exist
map.keySet()                  // Get all keys as Set
map.values()                  // Get all values as Collection
map.entrySet()                // Get all key-value pairs

// Useful for counting frequency
map.put(key, map.getOrDefault(key, 0) + 1);

// TreeMap specific (sorted by keys)
TreeMap<Integer, String> treeMap = new TreeMap<>();
treeMap.firstKey()            // Get smallest key
treeMap.lastKey()             // Get largest key
treeMap.lowerKey(key)         // Get largest key < given key
treeMap.higherKey(key)        // Get smallest key > given key
treeMap.floorKey(key)         // Get largest key <= given key
treeMap.ceilingKey(key)       // Get smallest key >= given key
```

---

## 6. Useful Methods

**List Methods:**
- `add(E e)`, `get(int index)`, `set(int index, E e)`, `remove(Object o)`
- `indexOf(Object o)`, `lastIndexOf(Object o)`, `subList(from, to)`
- `size()`, `isEmpty()`, `contains(Object o)`, `clear()`

**Set Methods:**
- `add(E e)`, `remove(Object o)`, `contains(Object o)`, `size()`, `isEmpty()`
- `addAll(Collection c)`, `retainAll(Collection c)`, `removeAll(Collection c)`

**Queue Methods:**
- `offer(E e)`, `poll()`, `peek()`, `remove()`, `element()`

**Deque Methods:**
- `addFirst(E e)`, `addLast(E e)`, `offerFirst(E e)`, `offerLast(E e)`
- `removeFirst()`, `removeLast()`, `pollFirst()`, `pollLast()`
- `peekFirst()`, `peekLast()`, `getFirst()`, `getLast()`
- `push(E e)`, `pop()`, `peek()` (Stack operations)

**Map Methods:**
- `put(K key, V value)`, `get(K key)`, `getOrDefault(K key, V default)`
- `containsKey(K key)`, `containsValue(V value)`, `remove(K key)`
- `keySet()`, `values()`, `entrySet()`, `putIfAbsent(K key, V value)`

---

## 7. Iteration Examples

**For-each Loop:**
```java
for(String item : list) {
    System.out.println(item);
}
```

**Iterator:**
```java
Iterator<String> it = set.iterator();
while(it.hasNext()) {
    System.out.println(it.next());
}
```

**Map Iteration:**
```java
for(Map.Entry<String, Integer> entry : map.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

**Using Streams (Java 8+):**
```java
list.stream().filter(x -> x.length() > 5).forEach(System.out::println);
map.forEach((key, value) -> System.out.println(key + ": " + value));
```

---

## 8. Synchronization

- **Not thread-safe by default.** Use `Collections.synchronizedList(list)` or concurrent collections like `ConcurrentHashMap` for thread safety.

---

## 9. Comparable vs Comparator

- **Comparable**: Implement in your class (`compareTo` method) for natural ordering.
- **Comparator**: Pass custom ordering logic to collections or sorting methods.

**Example:**
```java
Collections.sort(list, new Comparator<String>() {
    public int compare(String a, String b) {
        return a.length() - b.length();
    }
});

// Lambda expression (Java 8+)
Collections.sort(list, (a, b) -> a.length() - b.length());
```

---

## 10. Common DSA Patterns

**Sliding Window (Deque):**
```java
Deque<Integer> deque = new ArrayDeque<>();
// Maintain max/min in window using deque
```

**Frequency Count (HashMap):**
```java
Map<Character, Integer> freq = new HashMap<>();
for(char c : str.toCharArray()) {
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

**Two Pointers (List):**
```java
int left = 0, right = list.size() - 1;
while(left < right) {
    // Process elements
}
```

**Stack using Deque:**
```java
Deque<Integer> stack = new ArrayDeque<>();
stack.push(1);    // Add to top
stack.pop();      // Remove from top
stack.peek();     // View top
```

---

### Reference

- [Java Collections Framework (Oracle Docs)](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)
