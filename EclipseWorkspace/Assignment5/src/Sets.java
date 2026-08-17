import tester.*;

// represents a set of two numbers
class Set2 {
  private int one;
  private int two;
  public Set2(int one, int two) {
    this.one = one;
    this.two = two;
  }
  // does this set contain x?
  public boolean contains(int x) {
    return (x == this.one) || (x == this.two);
  }
  // is this the same Set2 as other?
  public boolean same(Set2 other) {
    return
      other.contains(this.one)
      && other.contains(this.two)
      && this.contains(other.one)
      && this.contains(other.two);
    }
}

// to represent examples and tests for ExamplesSet2
class ExamplesSet2{
    ExamplesSet2(){}

    Set2 firstSet = new Set2(3, 4);
    Set2 secondSet = new Set2(20, 40);
    Set2 thirdSet = new Set2(11, 99);

    Set2 fourthSet = new Set2(20, 4);
    Set2 fifthSet = new Set2(4, 11);
    Set2 sixthSet = new Set2(3, 99);


    // to test the contains method
    boolean testContains(Tester t){
        return
        t.checkExpect(this.firstSet.contains(4), true) &&
        t.checkExpect(this.secondSet.contains(20), true) &&
        t.checkExpect(this.thirdSet.contains(99), true);
    }

    // to test the sameness method
    boolean testSame(Tester t){
        return
        t.checkExpect(this.firstSet.same(fourthSet), false) &&
        t.checkExpect(this.secondSet.same(secondSet), true) &&
        t.checkExpect(this.sixthSet.same(sixthSet), true) &&
        t.checkExpect(this.secondSet.same(firstSet), false) &&
        t.checkExpect(this.thirdSet.same(sixthSet), false) &&
        t.checkExpect(this.firstSet.same(firstSet), true);
    }
}