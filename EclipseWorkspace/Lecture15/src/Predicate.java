// to represent IPredicate interface
interface IPredicate<T>{
    boolean apply(T t);
}

// to represent IFunction interface having a return type as a parameter
interface IFunction<A, R>{
  R apply(A arg);
}

// to represent IFunction2 interface consisting of two arguments
interface IFunction2<A1, A2, R>{
  R apply(A1 arg1, A2 arg2);
}

// to represent class TotalRunnersAge to return the total age in a list
class TotalRunnersAge implements IFunction2<Runner, Integer, Integer>{
  public Integer apply(Runner r, Integer sum){
    return r.age + sum;
  }
}

// to represent class TotalPriceBook
class TotalPriceBook implements IFunction2<Book, Double, Double>{
  public Double apply(Book b, Double total){
    return b.price + total;
  }
}

// to represent RunnerName class 
class RunnerName implements IFunction<Runner, String>{
  public String apply(Runner r) {
    return r.name;
  }
}

// to represent BookByYear class
class BookByYear implements IFunction<Book, Integer>{
  public Integer apply(Book b) {
    return b.year;
  }
}

// to represent class BookByAuthor
class BookByAuthor implements IPredicate<Book>{
    public boolean apply(Book b){
        return b.author.equals("JKR");
    }
}

// to represent class PosUnder50
class PosUnder50 implements IPredicate<Runner>{
    public boolean apply(Runner r){
        return r.position <= 50;
    }
}