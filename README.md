# 1 Description
Review your assignment projects and refactor your solutions for one of the past application assignments
1, 2, 3 or 4.

# 2 Assignment Tasks
* Find and apply at least 3 refactoring issues.
* You need to find 3 different types of refactoring and apply refactoring on your code (Not just 3
instances of the same type of refactoring)
* If you can not find 3 issues in one of your projects, you can search in your past other solutions.

# 2.1 Task 1 : Review your code and finding refactoring points (10 points)
* Provide a description of your refactoring process. Describe what do you want to change and why
do you want to change it.

## Refactoring Type 1, Code Smells: Dispensable
### Clean up dead attributes and methods
* There are a handful of methods and attributes that I did not have time to write full
  implementations for and cleaning these up will make my code easier to understand
    * Beverage class
        * caffeine and calories attributes and associated methods
    * Coffee class
        * grindSize and agitation and associated methods
    * HotBeverage
        * waterTemperature and associated methods
    * Makes code easier to read and maintain, eliminating dead code.


## Refactoring Type 2, Big Refactoring: Converting Procedural Design to objects
* Added the command pattern to the Customer package.
    * This increases the number of classes, however makes the code base much more
      maintainable because - before in the simulations, we would have to manually call these
      command methods (i.e. ```customer.chooseBeverage()``` etc.) and now these commands are
      encapsulated within Choose classes which implement the Choose interface.
    * The choice method automates binding the Customer's choice to the HotBeverage object
      attributes and returns the updated beverage for easy printing.
    * There is also an ```undoOrder()``` method which is important to the Command Pattern. 

## Refactoring Type 3, Composing Methods: Simplifying code by factoring out methods
* As it turns out I had in fact had a HotBeverage class attribute HashMap for condiments previously
    * This data structure was being used in the the same class' ```addCondiments()``` method
      however was not being accessed in the simulation.
    * I greatly simplified the simulation by adding a ```toString()``` method for HotBeverage
      which returns a HotBeverage description based on it's type and the contents of the condiments
      class attribute HashMap.
    * I further Changed the Coffee and Tea subclasses of HotBeverage to set and get the
      type attribute to their superclass to enhance the power of HotBeverages ```toString``` and
      removed the subclasses Coffee and Tea's class attribute ```type``` to eliminate code smell.
    
## Refactoring Note:
* I challenged myself to complete the above three refactoring steps without changing a single test from assignment 1. 
  * This was accomplished showing that true refactoring was accomplished. 
    * No implementation was changed without changing any functionality. 
# How to compile the project

We use Apache Maven to compile and run this project. 

You need to install Apache Maven (https://maven.apache.org/)  on your system. 

Type on the command line: 

```bash
mvn clean compile
```

# How to create a binary runnable package 


```bash
mvn clean compile assembly:single
```


# How to run

```bash
mvn -q clean compile exec:java -Dexec.executable="edu.bu.met.cs665.Main" -Dlog4j.configuration="file:log4j.properties"
```

# Run all the unit test classes.


```bash
mvn clean compile test checkstyle:check  spotbugs:check
```

# Using Spotbugs to find bugs in your project 

To see bug detail using the Findbugs GUI, use the following command "mvn findbugs:gui"

Or you can create a XML report by using  


```bash
mvn spotbugs:gui 
```

or 


```bash
mvn spotbugs:spotbugs
```


```bash
mvn spotbugs:check 
```

check goal runs analysis like spotbugs goal, and make the build failed if it found any bugs. 


For more info see 
https://spotbugs.readthedocs.io/en/latest/maven.html


SpotBugs https://spotbugs.github.io/ is the spiritual successor of FindBugs.


# Run Checkstyle 

CheckStyle code styling configuration files are in config/ directory. Maven checkstyle plugin is set to use google code style. 
You can change it to other styles like sun checkstyle. 

To analyze this example using CheckStyle run 

```bash
mvn checkstyle:check
```

This will generate a report in XML format


```bash
target/checkstyle-checker.xml
target/checkstyle-result.xml
```

and the following command will generate a report in HTML format that you can open it using a Web browser. 

```bash
mvn checkstyle:checkstyle
```

```bash
target/site/checkstyle.html
```




