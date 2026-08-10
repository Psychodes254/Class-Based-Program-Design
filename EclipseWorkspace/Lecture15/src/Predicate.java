// to represent IPredicate interface
interface IPredicate<T>{
    boolean apply(T t);
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