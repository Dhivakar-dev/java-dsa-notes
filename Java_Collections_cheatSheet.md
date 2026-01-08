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

---

## 6. Useful Methods

- `add(E e)`, `remove(Object o)`, `size()`, `contains(Object o)`  
  (for List, Set, Queue)
- `addFirst(E e)`, `addLast(E e)`, `pollFirst()`, `pollLast()`  
  (for Deque)
- `put(K key, V value)`, `get(K key)`, `keySet()`, `values()`  
  (for Map)

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
```

---

### Reference

- [Java Collections Framework (Oracle Docs)](https://docs.oracle.com/javase/8/docs/technotes/guides/collections/overview.html)