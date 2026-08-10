import tester.*;

// to represent IList interface
interface IList<T>{
    IList<T> find(IPredicate<T> predicate);
}

// to represent an empty list of IList
class MtList<T> implements IList<T>{
    public IList<T> find(IPredicate<T> predicate){
        return new MtList<T>();
    }
}

// to represent ConsList of IList
class ConsList<T> implements IList<T>{
    T first;
    IList<T> rest;

    ConsList(T first, IList<T> rest){
        this.first = first;
        this.rest = rest;
    }

    public IList<T> find(IPredicate<T> predicate){
        if (predicate.apply(this.first)){
            return new ConsList<T>(this.first, this.rest.find(predicate));
        }
        else{
            return this.rest.find(predicate);
        }
    }
}

// to represent class ExamplesIList 
class ExamplesIList{
    ExamplesIList(){}

    Runner johnny = new Runner("Kelly", 97, 999, true, 30, 360);
    Runner frank  = new Runner("Shorter", 32, 888, true, 245, 130);
    Runner bill = new Runner("Rogers", 36, 777, true, 119, 129);
    Runner joan = new Runner("Benoit", 29, 444, false, 18, 155);

    Book book1 = new Book("Harry Potter", "JKR", 1998, 15.0);
    Book book2 = new Book("Atomic Habits", "Cj", 2014, 35.0);
    Book book3 = new Book("Laws of Power", "RG", 2001, 40.0);

    IList<Runner> runnerList = new ConsList<Runner>(this.johnny,
                               new ConsList<Runner>(this.frank, 
                               new ConsList<Runner>(this.bill,
                               new ConsList<Runner>(this.joan,
                               new MtList<Runner>()))));

    IList<Book> bookList = new ConsList<Book>(this.book1,
                               new ConsList<Book>(this.book2, 
                               new ConsList<Book>(this.book3,
                               new MtList<Book>())));

    IList<Runner> under50Pos = new ConsList<Runner>(this.johnny,
                               new ConsList<Runner>(this.joan,
                               new MtList<Runner>()));

    IPredicate<Book> bookByAuthor = new BookByAuthor();
    IPredicate<Runner> runnerUnder50 = new PosUnder50();

    boolean testFind(Tester t){
        return
        t.checkExpect(this.bookList.find(this.bookByAuthor), 
                      new ConsList<Book>(book1, new MtList<Book>())) &&
        t.checkExpect(this.runnerList.find(this.runnerUnder50), under50Pos);
    }
}