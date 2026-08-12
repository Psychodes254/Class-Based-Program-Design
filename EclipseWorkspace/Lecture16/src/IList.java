// to represent IList interface
interface IList<T>{
  IList<T> find(IPredicate<T> predicate);
  <U> IList<U> map(IFunction<T, U> function);
  <U> U foldr(IFunction2<T, U, U> function, U initialValue);
}

// to represent MtList class
class MtList<T> implements IList<T>{
  public <U> IList<U> map(IFunction<T, U> function){
    return new MtList<U>();
  }
  
  public IList<T> find(IPredicate<T> predicate){
    return this;
  }
  
  public <U> U foldr(IFunction2<T, U, U>function, U initialValue) {
    return initialValue;
  }
}

// to represent ConsList class 
class ConsList<T> implements IList<T>{
  T first;
  IList<T> rest;
  
  ConsList(T first, IList<T> rest){
    this.first = first;
    this.rest = rest;
  }
  
  public <U> IList<U> map(IFunction<T, U> function){
    return new ConsList<U>(function.apply(this.first), this.rest.map(function));
  }
  
  public IList<T> find(IPredicate<T> predicate){
    if (predicate.apply(this.first)) {
      return new ConsList<T>(this.first, this.rest.find(predicate));
    }
    else {
      return this.rest.find(predicate);
    }
  }
  
  public <U> U foldr(IFunction2<T, U, U>function, U initialValue) {
    return function.apply(this.first, this.rest.foldr(function, initialValue));
  }
}

// to represent ExamplesIList class 
class ExamplesIList{}