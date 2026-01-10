# Java Functional Programming Cheat Sheet

## Table of Contents
1. [Introduction](#introduction)
2. [Functional Interfaces](#functional-interfaces)
3. [Lambda Expressions](#lambda-expressions)
4. [Built-in Functional Interfaces](#built-in-functional-interfaces)
5. [Method References](#method-references)
6. [Stream API](#stream-api)
7. [Real-World Examples](#real-world-examples)

---

## Introduction

Functional programming in Java (introduced in Java 8) allows you to write cleaner, more concise code by treating functions as first-class citizens.

**Key Concepts:**
- Lambda Expressions
- Functional Interfaces
- Method References
- Streams
- Optional

---

## Functional Interfaces

A **Functional Interface** is an interface with exactly **one abstract method** (SAM - Single Abstract Method).

### Creating Custom Functional Interfaces

```java
@FunctionalInterface
public interface Calculator {
    int calculate(int a, int b);
}

// Traditional way (Anonymous Inner Class)
Calculator addition = new Calculator() {
    @Override
    public int calculate(int a, int b) {
        return a + b;
    }
};

// Lambda Expression way
Calculator additionLambda = (a, b) -> a + b;
```

### How Functional Interface Converts to Lambda

**Step-by-Step Conversion:**

```java
// Step 1: Traditional Anonymous Inner Class
Runnable r1 = new Runnable() {
    @Override
    public void run() {
        System.out.println("Running");
    }
};

// Step 2: Remove unnecessary syntax
// - Remove "new Runnable()"
// - Remove method name "run()"
// - Use arrow operator ->
Runnable r2 = () -> {
    System.out.println("Running");
};

// Step 3: If single statement, remove braces
Runnable r3 = () -> System.out.println("Running");
```

**Another Example:**

```java
@FunctionalInterface
interface StringProcessor {
    String process(String input);
}

// Traditional
StringProcessor traditional = new StringProcessor() {
    @Override
    public String process(String input) {
        return input.toUpperCase();
    }
};

// Lambda - Step 1: Keep parameter types
StringProcessor lambda1 = (String input) -> {
    return input.toUpperCase();
};

// Lambda - Step 2: Remove parameter types (type inference)
StringProcessor lambda2 = (input) -> {
    return input.toUpperCase();
};

// Lambda - Step 3: Remove parentheses (single parameter)
StringProcessor lambda3 = input -> {
    return input.toUpperCase();
};

// Lambda - Step 4: Remove braces and return (single expression)
StringProcessor lambda4 = input -> input.toUpperCase();
```

---

## Lambda Expressions

### Syntax

```java
// No parameters
() -> System.out.println("Hello")

// One parameter (parentheses optional)
x -> x * x
(x) -> x * x

// Multiple parameters
(x, y) -> x + y

// Multiple statements
(x, y) -> {
    int sum = x + y;
    return sum * 2;
}

// With type declarations
(int x, int y) -> x + y
```

### Lambda Examples

```java
// Example 1: Comparator
List<String> names = Arrays.asList("John", "Alice", "Bob");

// Traditional
Collections.sort(names, new Comparator<String>() {
    @Override
    public int compare(String s1, String s2) {
        return s1.compareTo(s2);
    }
});

// Lambda
Collections.sort(names, (s1, s2) -> s1.compareTo(s2));

// Method Reference
Collections.sort(names, String::compareTo);

// Example 2: Thread
// Traditional
Thread t1 = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("Thread running");
    }
});

// Lambda
Thread t2 = new Thread(() -> System.out.println("Thread running"));
```

---

## Built-in Functional Interfaces

Java provides many functional interfaces in `java.util.function` package.

### 1. **Function<T, R>**
Takes one argument and produces a result.

```java
Function<String, Integer> stringLength = str -> str.length();
System.out.println(stringLength.apply("Hello")); // Output: 5

// Real-world: Convert user input
Function<String, User> createUser = name -> new User(name);

// Chaining functions
Function<Integer, Integer> multiply = x -> x * 2;
Function<Integer, Integer> add = x -> x + 3;
Function<Integer, Integer> combined = multiply.andThen(add); // First multiply, then add
System.out.println(combined.apply(5)); // (5*2) + 3 = 13

Function<Integer, Integer> composed = multiply.compose(add); // First add, then multiply
System.out.println(composed.apply(5)); // (5+3) * 2 = 16
```

**Useful Methods:**
- `apply(T t)` - Applies the function
- `andThen(Function<R, V> after)` - Chains another function after this
- `compose(Function<V, T> before)` - Chains another function before this

### 2. **Predicate<T>**
Takes one argument and returns boolean.

```java
Predicate<Integer> isEven = num -> num % 2 == 0;
System.out.println(isEven.test(4)); // true
System.out.println(isEven.test(5)); // false

// Real-world: Filtering
Predicate<String> isLongWord = word -> word.length() > 5;
Predicate<String> startsWithA = word -> word.startsWith("A");

// Combining predicates
Predicate<String> combined = isLongWord.and(startsWithA);
Predicate<String> either = isLongWord.or(startsWithA);
Predicate<String> notLong = isLongWord.negate();
```

**Useful Methods:**
- `test(T t)` - Tests the predicate
- `and(Predicate<T> other)` - Logical AND
- `or(Predicate<T> other)` - Logical OR
- `negate()` - Logical NOT

### 3. **Consumer<T>**
Takes one argument and returns nothing (void).

```java
Consumer<String> print = msg -> System.out.println(msg);
print.accept("Hello World"); // Output: Hello World

// Real-world: Processing items
Consumer<User> sendEmail = user -> emailService.send(user.getEmail());
Consumer<User> logActivity = user -> logger.log("User: " + user.getName());

// Chaining consumers
Consumer<User> process = sendEmail.andThen(logActivity);
process.accept(new User("John"));
```

**Useful Methods:**
- `accept(T t)` - Performs the operation
- `andThen(Consumer<T> after)` - Chains another consumer

### 4. **Supplier<T>**
Takes no arguments and returns a result.

```java
Supplier<Double> randomValue = () -> Math.random();
System.out.println(randomValue.get());

// Real-world: Lazy initialization
Supplier<List<User>> userSupplier = () -> database.fetchUsers();
List<User> users = userSupplier.get(); // Only fetched when needed

// Factory pattern
Supplier<User> userFactory = () -> new User("Default");
```

**Useful Methods:**
- `get()` - Gets a result

### 5. **BiFunction<T, U, R>**
Takes two arguments and returns a result.

```java
BiFunction<Integer, Integer, Integer> add = (a, b) -> a + b;
System.out.println(add.apply(5, 3)); // Output: 8

// Real-world: Calculation
BiFunction<Double, Double, Double> calculateDiscount = 
    (price, discount) -> price - (price * discount / 100);
System.out.println(calculateDiscount.apply(100.0, 10.0)); // 90.0
```

**Useful Methods:**
- `apply(T t, U u)` - Applies the function
- `andThen(Function<R, V> after)` - Chains another function

### 6. **BiPredicate<T, U>**
Takes two arguments and returns boolean.

```java
BiPredicate<String, Integer> isLengthEqual = (str, len) -> str.length() == len;
System.out.println(isLengthEqual.test("Hello", 5)); // true

// Real-world: Validation
BiPredicate<String, String> passwordMatch = (pwd, confirm) -> pwd.equals(confirm);
```

### 7. **BiConsumer<T, U>**
Takes two arguments and returns nothing.

```java
BiConsumer<String, Integer> printKeyValue = (key, value) -> 
    System.out.println(key + ": " + value);
printKeyValue.accept("Age", 25); // Age: 25

// Real-world: Map iteration
Map<String, Integer> map = new HashMap<>();
map.put("Apple", 5);
map.put("Banana", 3);
map.forEach((key, value) -> System.out.println(key + " = " + value));
```

### 8. **UnaryOperator<T>**
Special case of Function where input and output types are same.

```java
UnaryOperator<Integer> square = x -> x * x;
System.out.println(square.apply(5)); // 25

UnaryOperator<String> toUpperCase = String::toUpperCase;
System.out.println(toUpperCase.apply("hello")); // HELLO
```

### 9. **BinaryOperator<T>**
Special case of BiFunction where both inputs and output are same type.

```java
BinaryOperator<Integer> multiply = (a, b) -> a * b;
System.out.println(multiply.apply(4, 5)); // 20

BinaryOperator<String> concat = (s1, s2) -> s1 + s2;
System.out.println(concat.apply("Hello", " World")); // Hello World
```

---

## Method References

Method references are shorthand for lambda expressions that only call a method.

### Types of Method References

```java
// 1. Static Method Reference
// Lambda: x -> ClassName.staticMethod(x)
// Method Reference: ClassName::staticMethod
Function<String, Integer> parser1 = x -> Integer.parseInt(x);
Function<String, Integer> parser2 = Integer::parseInt;

// 2. Instance Method Reference (on particular object)
// Lambda: x -> obj.instanceMethod(x)
// Method Reference: obj::instanceMethod
String prefix = "Hello ";
Function<String, String> greeter1 = x -> prefix.concat(x);
Function<String, String> greeter2 = prefix::concat;

// 3. Instance Method Reference (on arbitrary object)
// Lambda: (obj, args) -> obj.instanceMethod(args)
// Method Reference: ClassName::instanceMethod
BiPredicate<String, String> equals1 = (s1, s2) -> s1.equals(s2);
BiPredicate<String, String> equals2 = String::equals;

// 4. Constructor Reference
// Lambda: () -> new ClassName()
// Method Reference: ClassName::new
Supplier<List<String>> listSupplier1 = () -> new ArrayList<>();
Supplier<List<String>> listSupplier2 = ArrayList::new;

Function<String, User> userCreator1 = name -> new User(name);
Function<String, User> userCreator2 = User::new;
```

### Method Reference Examples

```java
// Example 1: Printing
List<String> names = Arrays.asList("Alice", "Bob", "Charlie");

// Lambda
names.forEach(name -> System.out.println(name));

// Method Reference
names.forEach(System.out::println);

// Example 2: Sorting
// Lambda
names.sort((s1, s2) -> s1.compareToIgnoreCase(s2));

// Method Reference
names.sort(String::compareToIgnoreCase);

// Example 3: Mapping
List<Integer> lengths = names.stream()
    .map(String::length)
    .collect(Collectors.toList());
```

---

## Stream API

Streams provide a functional approach to process collections.

### Creating Streams

```java
// From collection
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> stream1 = list.stream();

// From array
String[] array = {"a", "b", "c"};
Stream<String> stream2 = Arrays.stream(array);

// Using Stream.of()
Stream<String> stream3 = Stream.of("a", "b", "c");

// Infinite streams
Stream<Integer> infiniteStream = Stream.iterate(0, n -> n + 1);
Stream<Double> randomStream = Stream.generate(Math::random);

// From file
Stream<String> lines = Files.lines(Paths.get("file.txt"));
```

### Intermediate Operations

These return a new stream and are lazy (not executed until terminal operation).

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

// filter - Select elements
List<Integer> evens = numbers.stream()
    .filter(n -> n % 2 == 0)
    .collect(Collectors.toList());
// [2, 4, 6, 8, 10]

// map - Transform elements
List<Integer> squares = numbers.stream()
    .map(n -> n * n)
    .collect(Collectors.toList());
// [1, 4, 9, 16, 25, 36, 49, 64, 81, 100]

// flatMap - Flatten nested structures
List<List<Integer>> nested = Arrays.asList(
    Arrays.asList(1, 2),
    Arrays.asList(3, 4),
    Arrays.asList(5, 6)
);
List<Integer> flattened = nested.stream()
    .flatMap(list -> list.stream())
    .collect(Collectors.toList());
// [1, 2, 3, 4, 5, 6]

// distinct - Remove duplicates
List<Integer> unique = Arrays.asList(1, 2, 2, 3, 3, 4).stream()
    .distinct()
    .collect(Collectors.toList());
// [1, 2, 3, 4]

// sorted - Sort elements
List<Integer> sorted = numbers.stream()
    .sorted()
    .collect(Collectors.toList());

List<String> names = Arrays.asList("John", "Alice", "Bob");
List<String> sortedNames = names.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());

// peek - Perform action without modifying stream (debugging)
List<Integer> result = numbers.stream()
    .peek(n -> System.out.println("Original: " + n))
    .map(n -> n * 2)
    .peek(n -> System.out.println("Doubled: " + n))
    .collect(Collectors.toList());

// limit - Limit stream size
List<Integer> first5 = numbers.stream()
    .limit(5)
    .collect(Collectors.toList());
// [1, 2, 3, 4, 5]

// skip - Skip first n elements
List<Integer> skip5 = numbers.stream()
    .skip(5)
    .collect(Collectors.toList());
// [6, 7, 8, 9, 10]
```

### Terminal Operations

These trigger stream processing and return a result.

```java
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

// forEach - Perform action on each element
numbers.stream().forEach(System.out::println);

// collect - Collect to collection
List<Integer> list = numbers.stream().collect(Collectors.toList());
Set<Integer> set = numbers.stream().collect(Collectors.toSet());
Map<Integer, String> map = numbers.stream()
    .collect(Collectors.toMap(n -> n, n -> "Number: " + n));

// count - Count elements
long count = numbers.stream().filter(n -> n > 2).count(); // 3

// reduce - Combine elements
Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
int sumWithIdentity = numbers.stream().reduce(0, (a, b) -> a + b); // 15
int product = numbers.stream().reduce(1, (a, b) -> a * b); // 120

// min/max
Optional<Integer> min = numbers.stream().min(Integer::compareTo); // 1
Optional<Integer> max = numbers.stream().max(Integer::compareTo); // 5

// anyMatch, allMatch, noneMatch
boolean anyEven = numbers.stream().anyMatch(n -> n % 2 == 0); // true
boolean allPositive = numbers.stream().allMatch(n -> n > 0); // true
boolean noneNegative = numbers.stream().noneMatch(n -> n < 0); // true

// findFirst, findAny
Optional<Integer> first = numbers.stream().findFirst(); // 1
Optional<Integer> any = numbers.stream().findAny(); // Any element

// toArray
Integer[] array = numbers.stream().toArray(Integer[]::new);
```

### Advanced Collectors

```java
List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David", "Eve");

// joining - Join strings
String joined = names.stream().collect(Collectors.joining(", "));
// "Alice, Bob, Charlie, David, Eve"

// groupingBy - Group by criterion
Map<Integer, List<String>> byLength = names.stream()
    .collect(Collectors.groupingBy(String::length));
// {3=[Bob, Eve], 5=[Alice, David], 7=[Charlie]}

// partitioningBy - Partition by predicate
Map<Boolean, List<String>> partitioned = names.stream()
    .collect(Collectors.partitioningBy(name -> name.length() > 4));
// {false=[Bob, Eve], true=[Alice, Charlie, David]}

// counting
Map<Integer, Long> lengthCount = names.stream()
    .collect(Collectors.groupingBy(String::length, Collectors.counting()));
// {3=2, 5=2, 7=1}

// summarizingInt - Statistics
List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
IntSummaryStatistics stats = numbers.stream()
    .collect(Collectors.summarizingInt(Integer::intValue));
System.out.println("Average: " + stats.getAverage());
System.out.println("Max: " + stats.getMax());
System.out.println("Min: " + stats.getMin());
```

---

## Real-World Examples

### Example 1: Employee Management System

```java
class Employee {
    private String name;
    private String department;
    private double salary;
    private int age;
    
    // Constructor, getters, setters
    public Employee(String name, String department, double salary, int age) {
        this.name = name;
        this.department = department;
        this.salary = salary;
        this.age = age;
    }
    
    // Getters
    public String getName() { return name; }
    public String getDepartment() { return department; }
    public double getSalary() { return salary; }
    public int getAge() { return age; }
}

List<Employee> employees = Arrays.asList(
    new Employee("John", "IT", 75000, 28),
    new Employee("Alice", "HR", 65000, 32),
    new Employee("Bob", "IT", 85000, 35),
    new Employee("Charlie", "Finance", 70000, 29),
    new Employee("David", "IT", 90000, 40)
);

// 1. Find all IT department employees
List<Employee> itEmployees = employees.stream()
    .filter(e -> e.getDepartment().equals("IT"))
    .collect(Collectors.toList());

// 2. Get average salary by department
Map<String, Double> avgSalaryByDept = employees.stream()
    .collect(Collectors.groupingBy(
        Employee::getDepartment,
        Collectors.averagingDouble(Employee::getSalary)
    ));

// 3. Find highest paid employee
Optional<Employee> highestPaid = employees.stream()
    .max(Comparator.comparing(Employee::getSalary));

// 4. Get names of employees earning more than 70k
List<String> highEarners = employees.stream()
    .filter(e -> e.getSalary() > 70000)
    .map(Employee::getName)
    .collect(Collectors.toList());

// 5. Sort employees by salary (descending) then by age
List<Employee> sorted = employees.stream()
    .sorted(Comparator.comparing(Employee::getSalary).reversed()
        .thenComparing(Employee::getAge))
    .collect(Collectors.toList());

// 6. Calculate total salary expense
double totalSalary = employees.stream()
    .mapToDouble(Employee::getSalary)
    .sum();

// 7. Check if any employee is over 45
boolean hasElderEmployee = employees.stream()
    .anyMatch(e -> e.getAge() > 45);

// 8. Group employees by age range
Map<String, List<Employee>> byAgeGroup = employees.stream()
    .collect(Collectors.groupingBy(e -> {
        if (e.getAge() < 30) return "Young";
        else if (e.getAge() < 40) return "Middle";
        else return "Senior";
    }));
```

### Example 2: E-Commerce Order Processing

```java
class Product {
    private String name;
    private double price;
    private String category;
    
    public Product(String name, double price, String category) {
        this.name = name;
        this.price = price;
        this.category = category;
    }
    
    public String getName() { return name; }
    public double getPrice() { return price; }
    public String getCategory() { return category; }
}

class Order {
    private String orderId;
    private List<Product> products;
    private String status; // "PENDING", "SHIPPED", "DELIVERED"
    
    public Order(String orderId, List<Product> products, String status) {
        this.orderId = orderId;
        this.products = products;
        this.status = status;
    }
    
    public String getOrderId() { return orderId; }
    public List<Product> getProducts() { return products; }
    public String getStatus() { return status; }
    
    public double getTotalPrice() {
        return products.stream()
            .mapToDouble(Product::getPrice)
            .sum();
    }
}

List<Order> orders = Arrays.asList(
    new Order("ORD001", Arrays.asList(
        new Product("Laptop", 999.99, "Electronics"),
        new Product("Mouse", 29.99, "Electronics")
    ), "DELIVERED"),
    new Order("ORD002", Arrays.asList(
        new Product("Book", 19.99, "Books"),
        new Product("Pen", 4.99, "Stationery")
    ), "SHIPPED"),
    new Order("ORD003", Arrays.asList(
        new Product("Phone", 699.99, "Electronics")
    ), "PENDING")
);

// 1. Get all delivered orders
List<Order> deliveredOrders = orders.stream()
    .filter(order -> order.getStatus().equals("DELIVERED"))
    .collect(Collectors.toList());

// 2. Calculate total revenue
double totalRevenue = orders.stream()
    .mapToDouble(Order::getTotalPrice)
    .sum();

// 3. Find most expensive product across all orders
Optional<Product> mostExpensive = orders.stream()
    .flatMap(order -> order.getProducts().stream())
    .max(Comparator.comparing(Product::getPrice));

// 4. Get all unique categories
Set<String> categories = orders.stream()
    .flatMap(order -> order.getProducts().stream())
    .map(Product::getCategory)
    .collect(Collectors.toSet());

// 5. Count products by category
Map<String, Long> productCountByCategory = orders.stream()
    .flatMap(order -> order.getProducts().stream())
    .collect(Collectors.groupingBy(Product::getCategory, Collectors.counting()));

// 6. Get average order value
double avgOrderValue = orders.stream()
    .mapToDouble(Order::getTotalPrice)
    .average()
    .orElse(0.0);

// 7. Find orders with total > $500
List<Order> expensiveOrders = orders.stream()
    .filter(order -> order.getTotalPrice() > 500)
    .collect(Collectors.toList());

// 8. Get all product names in electronics category
List<String> electronicsProducts = orders.stream()
    .flatMap(order -> order.getProducts().stream())
    .filter(p -> p.getCategory().equals("Electronics"))
    .map(Product::getName)
    .distinct()
    .collect(Collectors.toList());
```

### Example 3: String Processing

```java
List<String> sentences = Arrays.asList(
    "Java functional programming is powerful",
    "Lambda expressions make code concise",
    "Streams provide declarative data processing"
);

// 1. Convert all to uppercase
List<String> upperCase = sentences.stream()
    .map(String::toUpperCase)
    .collect(Collectors.toList());

// 2. Get all words from all sentences
List<String> allWords = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .collect(Collectors.toList());

// 3. Count total words
long wordCount = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .count();

// 4. Find longest word
Optional<String> longestWord = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .max(Comparator.comparing(String::length));

// 5. Get words starting with specific letter
List<String> wordsStartingWithP = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .filter(word -> word.toLowerCase().startsWith("p"))
    .collect(Collectors.toList());

// 6. Count word frequency
Map<String, Long> wordFrequency = sentences.stream()
    .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
    .map(String::toLowerCase)
    .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
```

### Example 4: Data Validation

```java
class User {
    private String username;
    private String email;
    private int age;
    
    public User(String username, String email, int age) {
        this.username = username;
        this.email = email;
        this.age = age;
    }
    
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public int getAge() { return age; }
}

// Define validation predicates
Predicate<User> isUsernameValid = user -> 
    user.getUsername() != null && user.getUsername().length() >= 3;

Predicate<User> isEmailValid = user -> 
    user.getEmail() != null && user.getEmail().contains("@");

Predicate<User> isAgeValid = user -> 
    user.getAge() >= 18 && user.getAge() <= 100;

// Combine all validations
Predicate<User> isUserValid = isUsernameValid
    .and(isEmailValid)
    .and(isAgeValid);

// Validate users
List<User> users = Arrays.asList(
    new User("john_doe", "john@example.com", 25),
    new User("ab", "invalid-email", 15),
    new User("alice", "alice@example.com", 30)
);

List<User> validUsers = users.stream()
    .filter(isUserValid)
    .collect(Collectors.toList());

// Get validation errors
users.forEach(user -> {
    if (!isUsernameValid.test(user)) {
        System.out.println("Invalid username: " + user.getUsername());
    }
    if (!isEmailValid.test(user)) {
        System.out.println("Invalid email: " + user.getEmail());
    }
    if (!isAgeValid.test(user)) {
        System.out.println("Invalid age: " + user.getAge());
    }
});
```

### Example 5: Custom Functional Interfaces

```java
// Calculator with multiple operations
@FunctionalInterface
interface MathOperation {
    double operate(double a, double b);
}

class Calculator {
    public static double calculate(double a, double b, MathOperation operation) {
        return operation.operate(a, b);
    }
    
    public static void main(String[] args) {
        MathOperation add = (a, b) -> a + b;
        MathOperation subtract = (a, b) -> a - b;
        MathOperation multiply = (a, b) -> a * b;
        MathOperation divide = (a, b) -> b != 0 ? a / b : 0;
        
        System.out.println("10 + 5 = " + calculate(10, 5, add));
        System.out.println("10 - 5 = " + calculate(10, 5, subtract));
        System.out.println("10 * 5 = " + calculate(10, 5, multiply));
        System.out.println("10 / 5 = " + calculate(10, 5, divide));
    }
}

// Event Handler
@FunctionalInterface
interface EventHandler<T> {
    void handle(T event);
}

class Button {
    private EventHandler<String> clickHandler;
    
    public void setOnClick(EventHandler<String> handler) {
        this.clickHandler = handler;
    }
    
    public void click() {
        if (clickHandler != null) {
            clickHandler.handle("Button clicked");
        }
    }
}

// Usage
Button button = new Button();
button.setOnClick(event -> System.out.println("Event: " + event));
button.click();

// Retry mechanism
@FunctionalInterface
interface RetryableOperation<T> {
    T execute() throws Exception;
}

class RetryUtil {
    public static <T> T retry(RetryableOperation<T> operation, int maxAttempts) {
        for (int i = 0; i < maxAttempts; i++) {
            try {
                return operation.execute();
            } catch (Exception e) {
                System.out.println("Attempt " + (i + 1) + " failed: " + e.getMessage());
                if (i == maxAttempts - 1) throw new RuntimeException("All attempts failed", e);
            }
        }
        return null;
    }
}

// Usage
String result = RetryUtil.retry(() -> {
    // Some operation that might fail
    return "Success";
}, 3);
```

---

## Best Practices

1. **Use appropriate functional interface** - Choose the right built-in interface instead of creating custom ones.

2. **Keep lambdas short** - If lambda is too complex, extract it to a method.

3. **Avoid side effects** - Lambda expressions should be stateless and not modify external state.

4. **Use method references** - When lambda only calls a method, use method reference for clarity.

5. **Stream operations should be stateless** - Don't rely on mutable state in stream operations.

6. **Parallel streams cautiously** - Use `.parallelStream()` only for large datasets and CPU-intensive operations.

7. **Handle Optional properly** - Don't use `.get()` without checking; use `.orElse()`, `.orElseGet()`, or `.orElseThrow()`.

```java
// Good
Optional<String> optional = Optional.ofNullable(getValue());
String result = optional.orElse("default");

// Bad
String result = optional.get(); // May throw NoSuchElementException
```

---

## Common Pitfalls

1. **Modifying collections during stream operations**
```java
// Bad - ConcurrentModificationException
list.stream().forEach(item -> list.remove(item));

// Good
list.removeIf(item -> condition);
```

2. **Reusing streams**
```java
// Bad - IllegalStateException
Stream<String> stream = list.stream();
stream.forEach(System.out::println);
stream.forEach(System.out::println); // Error: stream already operated upon

// Good - Create new stream
list.stream().forEach(System.out::println);
list.stream().forEach(System.out::println);
```

3. **Infinite streams without limit**
```java
// Bad - Never terminates
Stream.iterate(0, n -> n + 1).forEach(System.out::println);

// Good
Stream.iterate(0, n -> n + 1).limit(10).forEach(System.out::println);
```

---

## Performance Tips

1. **Lazy evaluation** - Intermediate operations are lazy; use this to optimize.

2. **Short-circuit operations** - Use `findFirst()`, `anyMatch()`, etc., to stop processing early.

3. **Primitive streams** - Use `IntStream`, `LongStream`, `DoubleStream` to avoid boxing overhead.

```java
// Better for primitives
int sum = IntStream.range(1, 100).sum();

// Instead of
int sum = Stream.iterate(1, n -> n + 1).limit(99).mapToInt(Integer::intValue).sum();
```

4. **Parallel streams for large data** - Use when dataset is large and operations are CPU-intensive.

```java
long count = largeList.parallelStream()
    .filter(expensive Operation)
    .count();
```

---

## Summary

**Key Takeaways:**
- Functional interfaces enable lambda expressions
- Lambda expressions provide concise syntax for anonymous functions
- Method references are shorthand for simple lambdas
- Stream API enables declarative data processing
- Built-in functional interfaces cover most common use cases
- Functional programming in Java promotes cleaner, more maintainable code

**When to Use:**
- ✅ Processing collections
- ✅ Event handlers
- ✅ Callbacks
- ✅ Filtering and transforming data
- ✅ Parallel processing

**When to Avoid:**
- ❌ Complex business logic (use regular methods)
- ❌ Multiple statements (extract to method)
- ❌ When readability suffers