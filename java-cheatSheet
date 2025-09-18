# Java Cheat Sheet with Explanations

---

## 1. Basic Syntax

```java
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```
- **Explanation:**  
  - `public class Main`: Declares a class named `Main`.
  - `public static void main(String[] args)`: Main method; the entry point of any Java application.
  - `System.out.println()`: Prints output to console.

---

## 2. Variables & Data Types

```java
int number = 10;         // Integer
double price = 9.99;     // Floating-point number
char letter = 'A';       // Character
boolean isValid = true;  // Boolean
String name = "Java";    // String (object)
```
- **Explanation:**  
  - Data types specify the type of data a variable can hold.

---

## 3. Control Structures

### If-Else

```java
if (number > 0) {
    System.out.println("Positive");
} else {
    System.out.println("Non-positive");
}
```

### Switch

```java
switch (day) {
    case 1: System.out.println("Sunday"); break;
    case 2: System.out.println("Monday"); break;
    default: System.out.println("Other day");
}
```

### For Loop

```java
for (int i = 0; i < 5; i++) {
    System.out.println(i);
}
```

### While Loop

```java
int i = 0;
while (i < 5) {
    System.out.println(i);
    i++;
}
```
- **Explanation:**  
  - Control structures are used to manage the flow of the program.

---

## 4. Methods (Functions)

```java
public static int add(int a, int b) {
    return a + b;
}
```
- **Explanation:**  
  - Methods are blocks of code that perform a specific task.
  - `static`: Can be called without creating an object.
  - `int add(int a, int b)`: Method named `add` returning an integer.

---

## 5. Arrays

```java
int[] numbers = {1, 2, 3, 4, 5};
System.out.println(numbers[0]); // Access first element
```
- **Explanation:**  
  - Arrays store multiple values of the same type.

---

## 6. Object-Oriented Concepts

### Class & Object

```java
class Dog {
    String name;
    void bark() {
        System.out.println(name + " says Woof!");
    }
}

Dog d = new Dog();
d.name = "Tommy";
d.bark();
```
- **Explanation:**  
  - Classes are blueprints for objects.
  - Objects are instances of classes.

### Inheritance

```java
class Animal {
    void eat() { System.out.println("Eating"); }
}
class Dog extends Animal {
    void bark() { System.out.println("Barking"); }
}
```
- **Explanation:**  
  - Inheritance lets a class acquire properties and methods of another class.

### Polymorphism

```java
Animal a = new Dog();
a.eat(); // Calls Animal's eat
```
- **Explanation:**  
  - Polymorphism allows objects to be treated as instances of their parent class.

---

## 7. Exception Handling

```java
try {
    int x = 10 / 0;
} catch (ArithmeticException e) {
    System.out.println("Cannot divide by zero");
} finally {
    System.out.println("Done");
}
```
- **Explanation:**  
  - `try`: Block of code to monitor for errors.
  - `catch`: Handles exceptions.
  - `finally`: Always executes.

---

## 8. Collections (ArrayList Example)

```java
import java.util.ArrayList;
ArrayList<String> list = new ArrayList<>();
list.add("Apple");
list.add("Banana");
System.out.println(list.get(0)); // Prints "Apple"
```
- **Explanation:**  
  - Collections are used to store groups of objects.

---

## 9. Input/Output

```java
import java.util.Scanner;
Scanner sc = new Scanner(System.in);
String str = sc.nextLine();
System.out.println(str);
```
- **Explanation:**  
  - `Scanner` is used for getting user input.

---

## 10. Useful Keywords

- `static`: Belongs to the class, not instance.
- `final`: Value cannot be changed.
- `this`: Refers to the current object.
- `super`: Refers to the parent class.

---

## 11. Common Built-in Methods

- `String.length()`: Returns length of string.
- `ArrayList.size()`: Returns number of elements.
- `Math.max(a, b)`: Returns maximum of two numbers.

---

## 12. Packages

```java
package mypackage;
import java.util.Scanner;
```
- **Explanation:**  
  - Packages group related classes together.

---

### Reference

- [Official Java Documentation](https://docs.oracle.com/en/java/)
- [Java Tutorials](https://www.w3schools.com/java/)
