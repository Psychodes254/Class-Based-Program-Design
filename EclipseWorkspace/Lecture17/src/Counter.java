import tester.*;

class Counter{
  int val;
  
  Counter(){
    this(0);
  }
  
  Counter(int initialVal){
    this.val = initialVal;
  }
  
  int get() {
    int ans = val;
    this.val = val + 1;
    return ans;
  }
}

class ExamplesCounter{
  ExamplesCounter(){}
  
  Counter counter1 = new Counter();
  Counter counter2 = new Counter(2);
  
  void testCounter(Tester t) {
      t.checkExpect(this.counter1.get(), 0);
      t.checkExpect(this.counter2.get(), 2);
      t.checkExpect(this.counter1.get() == counter1.get(), false);
      t.checkExpect(this.counter1.get() == counter2.get(), true);
      t.checkExpect(this.counter2.get() == counter1.get(), true);
      t.checkExpect(this.counter2.get() == counter2.get(), false);
      t.checkExpect(this.counter2.get() == counter1.get(), false);
  }
}