# This is an Example for How to create a Customized Sprint Boot Starter

1. **Create a Maven/Gradle Project**
2. **Add Dependencies**
    1. autoconfigure
    2. configuration processor
    
    ```xml
    <project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
      xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
      <modelVersion>4.0.0</modelVersion>
    
      <groupId>com.creaturelove</groupId>
      <artifactId>MySprintBootStarter</artifactId>
      <version>1.0-SNAPSHOT</version>
      <packaging>jar</packaging>
    
      <name>MySprintBootStarter</name>
      <url>http://maven.apache.org</url>
    
      <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <spring-boot.version>2.7.18</spring-boot.version> <java.version>11</java.version> </properties>
    
      <dependencyManagement>
        <dependencies>
          <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-dependencies</artifactId>
            <version>${spring-boot.version}</version>
            <type>pom</type>
            <scope>import</scope>
          </dependency>
        </dependencies>
      </dependencyManagement>
      <dependencies>
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-autoconfigure</artifactId>
        </dependency>
    
        <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-configuration-processor</artifactId>
          <optional>true</optional> </dependency>
        <dependency>
          <groupId>junit</groupId>
          <artifactId>junit</artifactId>
          <version>3.8.1</version>
          <scope>test</scope>
        </dependency>
      </dependencies>
    </project>
    
    ```
    
3. **Remove Spring Boot Maven Plugins**

   Since Starter is a library not an executable application, we don't need `spring-boot-maven-plugin` to package it as an executable JAR.
   Delete the following part in the Pom.xml:
    
    ```xml
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>
    ```
    
5. **Create Bussiness Logic Class**
6. **Register Configuration Class**
    1. Create a `org.springframework.boot.autoconfigure.AutoConfiguration.imports` file under `src/main/resources/META-INF/spring/` 
    2.Content is the whole name of autoconfiguration class:
        
        ```xml
        com.creaturelove.GreetingAutoConfiguration
        ```
        
7. **Build and Install Starter**
    
    Maven Clean Install
    
8. **Using the Starter**
    1. Add Dependency
        
        ```xml
        <dependency>
            <groupId>com.creaturelove</groupId>
            <artifactId>MySprintBootStarter</artifactId>
            <version>1.0-SNAPSHOT</version>
        </dependency>
        ```
        
    2. Set up Environment Variable
        
        Add Properties in the `application.yml` or `application.properties`
        
        ```yaml
        spring.application.name=TryStarter
        greeting.service.prefix=Hi
        ```
        
    3. Dependency Injection
        
        ```java
        @Component
            public class MyRunner implements CommandLineRunner {
        
                private final GreetingService greetingService;
        
                // Inject the service bean automatically configured by the starter
                @Autowired
                public MyRunner(GreetingService greetingService) {
                    this.greetingService = greetingService;
                }
        
                @Override
                public void run(String... args) throws Exception {
                    System.out.println("=========================================");
                    System.out.println(greetingService.greet("Developer")); // Use the service
                    System.out.println(greetingService.greet(null));      // Test with null name
                    System.out.println("=========================================");
                }
            }
        }
        ```
