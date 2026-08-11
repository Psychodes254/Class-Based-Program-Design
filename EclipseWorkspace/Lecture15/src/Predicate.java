// to represent IPredicate interface
interface IPredicate<T>{
    boolean apply(T t);
}

// to represent IFunction interface
interface IFunction<T, R>{
  R apply(T arg);
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