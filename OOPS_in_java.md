# OOP (Object-Oriented Programming) Concepts in Java with Explanations

---

## 1. **Class & Object**

### Class
A class is a blueprint for creating objects (instances), defining properties (fields) and behaviors (methods).

```java
class Car {
    String color;
    void drive() {
        System.out.println("Driving the car!");
    }
}
```

### Object
An object is an instance of a class.

```java
Car myCar = new Car();
myCar.color = "Red";
myCar.drive();
```
**Explanation:**  
- `Car` is a class.
- `myCar` is an object of `Car` with its own `color` property and access to `drive()` method.

---

## 2. **Inheritance**

Inheritance lets one class acquire the properties and methods of another (parent) class.

```java
class Animal {
    void eat() {
        System.out.println("Animal eats");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks");
    }
}
```
**Explanation:**  
- `Dog` inherits `eat()` from `Animal`.
- `extends` keyword is used for inheritance.

---

## 3. **Polymorphism**

Polymorphism allows objects to take many forms, mainly via method overriding and overloading.

### Method Overriding

```java
class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Cat extends Animal {
    void sound() {
        System.out.println("Meow");
    }
}

Animal a = new Cat();
a.sound(); // Output: Meow
```

### Method Overloading

```java
class MathUtils {
    int add(int a, int b) { return a + b; }
    double add(double a, double b) { return a + b; }
}
```
**Explanation:**  
- Overriding: Subclass provides specific implementation of a method.
- Overloading: Multiple methods with same name but different parameters.

---

## 4. **Encapsulation**

Encapsulation is the technique of wrapping data (fields) and methods into a single unit (class), and restricting access using access modifiers.

```java
class Person {
    private String name; // private variable

    public String getName() { // public getter
        return name;
    }

    public void setName(String name) { // public setter
        this.name = name;
    }
}
```
**Explanation:**  
- `private`: restricts direct access to fields.
- Getters and setters provide controlled access.

---

## 5. **Abstraction**

Abstraction means hiding complex implementation details and showing only the necessary features.

### Abstract Class

```java
abstract class Shape {
    abstract void draw();
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing Circle");
    }
}
```

### Interface

```java
interface Animal {
    void makeSound();
}

class Dog implements Animal {
    public void makeSound() {
        System.out.println("Bark");
    }
}
```
**Explanation:**  
- Abstract classes and interfaces define what subclasses should do but not how.
- `abstract` keyword is used for abstract classes.
- `implements` keyword is used for interfaces.

---

## 6. **Access Modifiers**

- `public`: Accessible from anywhere.
- `private`: Accessible only within the class.
- `protected`: Accessible within the package & subclasses.
- _default_ (no modifier): Accessible within the package.

---

## 7. **Constructor**

A constructor initializes new objects.

```java
class Book {
    String title;
    Book(String t) { // constructor
        title = t;
    }
}
Book b = new Book("Java Guide");
```

---

## 8. **'this' and 'super' Keywords**

- `this` refers to the current object.
- `super` refers to the parent class.

```java
class Parent { void show() { System.out.println("Parent"); } }
class Child extends Parent {
    void show() {
        super.show(); // Calls Parent's show()
        System.out.println("Child");
    }
}
```

---

## 9. **Static Keyword**

Belongs to the class, not instance.

```java
class Counter {
    static int count = 0;
    Counter() { count++; }
}
```

---

## Summary Table

| Concept        | Keyword        | Purpose                                   |
|----------------|---------------|-------------------------------------------|
| Class/Object   | class, new    | Create and use data & behavior            |
| Inheritance    | extends       | Reuse code, hierarchical relationships    |
| Polymorphism   | override, overload | Multi-form functions                   |
| Encapsulation  | private, public, getters/setters | Data hiding        |
| Abstraction    | abstract, interface | Hide details, show essentials          |

---

### References

- [Java OOP Official Documentation](https://docs.oracle.com/javase/tutorial/java/concepts/)
- [Java OOP Tutorial](https://www.w3schools.com/java/java_oop.asp)
