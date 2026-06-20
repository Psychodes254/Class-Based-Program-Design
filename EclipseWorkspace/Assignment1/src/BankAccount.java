import tester.*;

/*
                   +--------------+    +--------------+  
                   | Customer     |    | Date         |  
                   +--------------+    +--------------+  
                   | String name  |    | String month |  
                   | int id       |    | int day      |  
                   |              |    | int year     |
                   +--------------+    +--------------+ 
                          |                     |
                          -----------------------
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
+-------------------+    +-------------------+    +--------------------+
| Checking          |    | Savings           |    |  CD                |
+-------------------+    +-------------------+    +--------------------+
| Customer customer |    | Customer customer |    | Customer customer  |
| int balance       |    | int balance       |    | int balance        |
| int minBalance    |    | double interest   |    | double interest    |
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

// to represent Date class
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

// to represent BankAccount interface
interface IBankAccount { }

// to represent Checking account class
class Checking implements IBankAccount {
  Customer customer;
  int balance;
  int minimumBalance;
  
  // the constructor 
  Checking(Customer customer, int balance, int minimumBalance) {
    this.customer = customer;
    this.balance = balance;
    this.minimumBalance = minimumBalance;
  }
}

// to represent Savings account class
class Savings implements IBankAccount {
  Customer customer;
  int balance;
  double interest;
  
  // the constructor
  Savings(Customer customer, int balance, double interest) {
    this.customer = customer;
    this.balance = balance;
    this.interest = interest;
  }
}

// to represent Certificate Deposit account class
class CD implements IBankAccount {
  Customer customer;
  int balance;
  double interest;
  Date maturityDate;
  
  // the constructor
  CD(Customer customer, int balance, double interest, Date maturityDate) {
    this.customer = customer;
    this.balance = balance;
    this.interest = interest;
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
  Savings saving1 = new Savings(this.proulx, 800, 3.5);
  CD cd1 = new CD(this.flatt, 10123, 4, this.maturity1);
}