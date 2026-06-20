import tester.*;

/*
                             +--------------+      
                             | Customer     |      
                             +--------------+      
                             | String name  |      
                             | int id       |     
                             +--------------+     
                                    |                     
                             ----------------
                                    |
                             +--------------+                
                             | IBankAccount |                
                             +--------------+                
                             +--------------+                
                                    |                     
                                   / \                    
                                   ---                    
                                    |                     
          ---------------------------------------------------           
          |                         |                       |  
          |                         |                +--------------+ 
          |                         |                | Date         | 
          |                         |                +--------------+  
          |                         |                | String month |  
          |                         |                | int day      | 
          |                         |                | int year     | 
          |                         |                +--------------+
          V                         V                         | 
+-------------------+    +-------------------+    +--------------------+
| Checking          |    | Savings           |    |  CD                |
+-------------------+    +-------------------+    +--------------------+
| Customer customer |    | Customer customer |    | Customer customer  |
| double balance    |    | double balance    |    | double balance     |
| double minBalance |    | double interest   |    | double interest    |
|                   |    |                   |    | Date maturityDate  |
+-------------------+    +-------------------+    +--------------------+  
*/

// to represent Customer class 
class Customer {
  String name;
  int id;
  
  // the constructor
  Customer(String name, int id) {
    this.name = name;
    this.id = id;
  }
}

// to represent BankAccount interface
interface IBankAccount { }

// to represent Checking account class
class Checking implements IBankAccount {
  Customer customer;
  double balance;
  double minimumBalance;
  
  // the constructor 
  Checking(Customer customer, double balance, double minimumBalance) {
    this.customer = customer;
    this.balance = balance;
    this.minimumBalance = minimumBalance;
  }
}

// to represent Savings account class
class Savings implements IBankAccount {
  Customer customer;
  double balance;
  double interestRate;
  
  // the constructor
  Savings(Customer customer, double balance, double interestRate) {
    this.customer = customer;
    this.balance = balance;
    this.interestRate = interestRate;
  }
}

//to represent Date class
class Date {
String month;
int day;
int year;

// the constructor
Date(String month, int day, int year) {
 this.month = month;
 this.day = day;
 this.year = year;
}
}

// to represent Certificate Deposit account class
class CD implements IBankAccount {
  Customer customer;
  double balance;
  double interestRate;
  Date maturityDate;
  
  // the constructor
  CD(Customer customer, double balance, double interestRate, Date maturityDate) {
    this.customer = customer;
    this.balance = balance;
    this.interestRate = interestRate;
    this.maturityDate = maturityDate;
  }
}

//examples for the class hierarchy that represents Bank Account
class ExamplesBankAccount {
  Customer gray = new Customer("Earl Gray", 1729);
  Customer flatt = new Customer("Ima Flatt", 4104);
  Customer proulx = new Customer("Annie Proulx", 2992);
  
  Date maturity1 = new Date("June", 1, 2005);
  
  Checking check1 = new Checking(this.gray, 1250, 500);
  Savings saving1 = new Savings(this.proulx, 800, 0.035);
  CD cd1 = new CD(this.flatt, 10123, 0.04, this.maturity1);
}