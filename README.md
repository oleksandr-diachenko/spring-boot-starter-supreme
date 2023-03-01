# **Spring Boot Starter Supreme**

Spring Boot Starter Supreme is a logging starter that includes annotations for logging various methods.
This makes it easy to add logging to your project without the need to writing additional code.

## **Available annotation**

```java
@LogInfo

@LogDebug

@LogTrace

@LogWarn

@LogError
```

## **Installation and Usage**

### **Installation**

To use Spring Boot Starter Supreme in your project, add the dependency to your pom.xml file:

```xml
<dependency>
    <groupId>com.github.oleksandr-diachenko</groupId>
    <artifactId>spring-boot-starter-supreme</artifactId>
    <version>1.0.0</version>
</dependency>
```

### **Usage**

To use the logging annotations in your code, simply add them to the desired methods. Here's an example:

```java
    @LogDebug
    public void print(String message) {
        // logic
    }
    
    @LogDebug(message = "The sum of two numbers {0} and {1} is {retVal}")
    public int sum(int a, int b) {
        return a + b;
    }
    
```

## **License**

This project is licensed under the MIT License - see the LICENSE.md file for details.

## **Author**

Oleksandr Diachenko
