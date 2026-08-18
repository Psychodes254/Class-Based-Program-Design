import tester.*;
 
// generic list
interface IList<T> {
  // map over a list, and produce a new list with a (possibly different)
  // element type
  <U> IList<U> map(IFunc<T, U> f);
  <U> U foldr(IFunc2<T, U, U> function, U initialValue);
  <U> U findSolutionOrElse(IFunc<T, U> convert, IPred<U> pred, U backup);
}
 
// empty generic list
class MtList<T> implements IList<T> {
  public <U> IList<U> map(IFunc<T, U> f) {
    return new MtList<U>();
  }
  
  public <U> U foldr(IFunc2<T, U, U> function, U initialValue) {
    return initialValue;
  }

  public <U> U findSolutionOrElse(IFunc<T, U> convert, IPred<U> pred, U backup){
    return backup;
  }
}
 
// non-empty generic list
class ConsList<T> implements IList<T> {
  T first;
  IList<T> rest;
 
  ConsList(T first, IList<T> rest) {
    this.first = first;
    this.rest = rest;
  }
 
  public <U> IList<U> map(IFunc<T, U> f) {
    return new ConsList<U>(f.apply(this.first), this.rest.map(f));
  }
  
  public <U> U foldr(IFunc2<T, U, U> function, U initialValue) {
    return function.apply(this.first, this.rest.foldr(function, initialValue));
  }

  public <U> U findSolutionOrElse(IFunc<T, U> convert, IPred<U> pred, U backup){
    U converted = convert.apply(this.first);
    if (pred.apply(converted)){
      return converted;
    }
    else{
      return this.rest.findSolutionOrElse(convert, pred, backup);
    }
  }
}

// to represent an examples and tests class
class ExamplesIList{
  ExamplesIList(){}

  IList<Integer> list1 = new ConsList<Integer>(2, new ConsList<Integer>(4, new ConsList<Integer>(20, new MtList<Integer>())));
  IList<Integer> list2 = new ConsList<Integer>(100, new ConsList<Integer>(200, new ConsList<Integer>(300, new MtList<Integer>())));
  
  // test the sum of the values in the list
  boolean testFoldSum(Tester t) {
    return
        t.checkExpect(this.list1.foldr(new SumIntegers(), 0), 26) &&
        t.checkExpect(this.list2.foldr(new SumIntegers(), 0), 600);
  }
}