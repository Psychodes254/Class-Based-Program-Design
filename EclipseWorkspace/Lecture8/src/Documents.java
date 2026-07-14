import tester.*;

// to represent Author class
class Author {
  String firstName;
  String lastName;
  int yob;
  
  // the constructor
  Author(String firstName, String lastName, int yob) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.yob = yob;
  }
  
  String formatName() {
    return this.lastName + ", " + this.firstName;
  }
  
}

// to represent Publisher class
class Publisher {
  String name;
  int est;
  
  // the constructor
  Publisher(String name, int est) {
    this.name = name;
    this.est = est;
  }
}

//to represent DocumentInfo class
class DocumentInfo {
Author author;
String title;
ILoDocument bibliography;

// the constructor
DocumentInfo(Author author, String title, ILoDocument bibliography) {
 this.author = author;
 this.title = title;
 this.bibliography = bibliography;
}
}

// to represent Document interface
interface IDocument  {
  boolean isBook();
  ILoDocument bibliography();
  ILoDocument allDocuments();
  String getTitle();
  String getAuthorLastName();
}

// to represent ListOfDocument interface
interface ILoDocument  {
  ILoDocument append(ILoDocument other);
  ILoDocument allDocumentHelper();
  ILoDocument onlyBooks();
  ILoDocument removeDuplicates();
  boolean contains(IDocument doc);
  ILoDocument removeAll(IDocument doc);
  ILoDocument sort();
  ILoDocument insert(IDocument doc);
}

// to represent ConsListOfDocument 
class ConsLoDocument implements ILoDocument  {
  IDocument  first;
  ILoDocument  rest;
  
  // the constructor
  ConsLoDocument(IDocument  first, ILoDocument rest) {
    this.first = first;
    this.rest = rest;
  }
  
  public ILoDocument append(ILoDocument other) {
    return new ConsLoDocument(this.first, this.rest.append(other));
  }
  
  public ILoDocument allDocumentHelper() {
    return new ConsLoDocument(this.first,
        this.first.allDocuments().append(this.rest.allDocumentHelper()));
  }
  
  public ILoDocument onlyBooks() {
    if (this.first.isBook())
      return new ConsLoDocument(this.first, this.rest.onlyBooks());
    else
      return this.rest.onlyBooks();
  }
  
   public ILoDocument removeDuplicates() {
     return new ConsLoDocument(this.first,
         this.rest.removeAll(this.first).removeDuplicates());
   }
  
   public boolean contains(IDocument doc) {
     return this.first.equals(doc) || this.rest.equals(doc);
   }
  
   public ILoDocument removeAll(IDocument doc) {
     if (this.first.equals(doc))
       return this.rest.removeAll(doc);
     else
       return new ConsLoDocument(this.first, this.rest.removeAll(doc));
   }
   
   public ILoDocument sort() {
     return this.rest.sort().insert(this.first);
   }

   public ILoDocument insert(IDocument doc) {
     if (doc.getAuthorLastName().compareTo(this.first.getAuthorLastName()) <= 0) {
       return new ConsLoDocument(doc, this);
     }
     else {
       return new ConsLoDocument(this.first, this.rest.insert(doc));
     }
   }
}

// to represent MtListOfDocument
class MtLoDocument implements ILoDocument  {
  MtLoDocument() {}
  
  public ILoDocument append(ILoDocument other) {
    return other;
  }
  
  public ILoDocument allDocumentHelper() {
    return this;
  }
  
  public ILoDocument onlyBooks() {
    return this;
  }
  
  public ILoDocument removeDuplicates() {
    return this;
  }

  public boolean contains(IDocument doc) {
    return false;
  }

  public ILoDocument removeAll(IDocument doc) {
    return this;
  }
  
  public ILoDocument sort() {
    return this;
  }

  public ILoDocument insert(IDocument doc) {
    return new ConsLoDocument(doc, this);
  }
}

// to represent book class
class Book implements IDocument  {
  DocumentInfo info;
  Publisher publisher;
  
  // the constructor
  Book(DocumentInfo info, Publisher publisher) {
    this.info = info;
    this.publisher = publisher;
  }
  
  public boolean isBook() {
    return true;
  }
  
  public ILoDocument bibliography() {
    return this.info.bibliography;
  }
  
  public ILoDocument allDocuments() {
    return this.bibliography().allDocumentHelper();
  }  
  
  public String getTitle() {
    return this.info.title;
  }
  
  public String getAuthorLastName() {
    return this.info.author.lastName;
  }
}

// to represent wikiArticles class
class WikiArticle implements IDocument  {
  DocumentInfo info;
  String url;
  
  // the constructor 
  WikiArticle(DocumentInfo info, String url) {
    this.info = info;
    this.url = url;
  }
  
  public boolean isBook() {
    return false;
  }
  
  public ILoDocument bibliography() {
    return this.info.bibliography;
  }
  
  public ILoDocument allDocuments() {
    return this.bibliography().allDocumentHelper();
  }
  
  public String getTitle() {
    return this.info.title;
  }

  public String getAuthorLastName() {
   return this.info.author.lastName;
  }
}

//to represent examples of Documents
class ExamplesDocuments {

  // examples of Author
  Author austen = new Author("Jane", "Austen", 1775);
  Author orwell = new Author("George", "Orwell", 1903);
  Author tolkien = new Author("John", "Tolkien", 1892);
  Author dickens = new Author("Charles", "Dickens", 1812);
  Author bloom = new Author("Harold", "Bloom", 1930);
  Author wikiContributor = new Author("Anonymous", "Contributor", 0);
  
  // examples of Publisher
  Publisher penguin = new Publisher("Penguin Books", 1935);
  Publisher secker = new Publisher("Secker & Warburg", 1855);
  Publisher oxford = new Publisher("Oxford University Press", 1586);
  
  // empty bibliography
  ILoDocument mtBibliography = new MtLoDocument();
  
  // LEAF DOCUMENTS (no references)
  DocumentInfo prideInfo =
     new DocumentInfo(austen, "Pride and Prejudice", mtBibliography);
  Book prideBook = new Book(prideInfo, penguin);
  
  DocumentInfo emmaInfo =
     new DocumentInfo(austen, "Emma", mtBibliography);
  Book emmaBook = new Book(emmaInfo, penguin);
  
  DocumentInfo hobbitInfo =
     new DocumentInfo(tolkien, "The Hobbit", mtBibliography);
  Book hobbitBook = new Book(hobbitInfo, penguin);
  
  DocumentInfo taleInfo =
     new DocumentInfo(dickens, "A Tale of Two Cities", mtBibliography);
  Book taleBook = new Book(taleInfo, penguin);
  
  DocumentInfo javaWikiInfo =
     new DocumentInfo(wikiContributor,
         "Java (programming language)",
         mtBibliography);
  
  WikiArticle javaWiki =
     new WikiArticle(javaWikiInfo,
         "https://en.wikipedia.org/wiki/Java_(programming_language)");
  
  // BOOK WITH REFERENCES
  ILoDocument animalFarmBibliography =
     new ConsLoDocument(prideBook, new ConsLoDocument(javaWiki, mtBibliography));
  
  DocumentInfo animalFarmInfo =
     new DocumentInfo(orwell, "Animal Farm", animalFarmBibliography);
  
  Book animalFarmBook =
     new Book(animalFarmInfo, secker);
  
  // WIKI REFERENCING A BOOK
  ILoDocument pythonWikiBibliography =
     new ConsLoDocument(animalFarmBook, mtBibliography);
  
  DocumentInfo pythonWikiInfo =
     new DocumentInfo(wikiContributor, "Python (programming language)", pythonWikiBibliography);
  
  WikiArticle pythonWiki =
     new WikiArticle(pythonWikiInfo, "https://en.wikipedia.org/wiki/Python_(programming_language)");
  
  // DUPLICATE REFERENCES
  ILoDocument duplicateBibliography =
     new ConsLoDocument(prideBook, new ConsLoDocument(prideBook, new ConsLoDocument(javaWiki, mtBibliography)));
  
  DocumentInfo duplicateSurveyInfo =
     new DocumentInfo(bloom, "Duplicate Survey", duplicateBibliography);
  
  Book duplicateSurveyBook =
     new Book(duplicateSurveyInfo, oxford);
  
  // DEEP RECURSION
  ILoDocument literatureSurveyBibliography =
     new ConsLoDocument(animalFarmBook,
         new ConsLoDocument(emmaBook,
             new ConsLoDocument(hobbitBook,
                 new ConsLoDocument(taleBook,
                     mtBibliography))));
  
  DocumentInfo literatureSurveyInfo =
     new DocumentInfo(bloom, "Literature Survey", literatureSurveyBibliography);
  
  Book literatureSurveyBook =
     new Book(literatureSurveyInfo, oxford);
  
  // Examples of IDocument
  IDocument doc1 = prideBook;
  IDocument doc2 = emmaBook;
  IDocument doc3 = hobbitBook;
  IDocument doc4 = taleBook;
  IDocument doc5 = animalFarmBook;
  IDocument doc6 = javaWiki;
  IDocument doc7 = pythonWiki;
  IDocument doc8 = duplicateSurveyBook;
  IDocument doc9 = literatureSurveyBook;
  
  boolean testAuthorName(Tester t) {
    return t.checkExpect(
        this.austen.formatName(),
        "Austen, Jane");
  }
  
  boolean testBookIsBook(Tester t) {
    return t.checkExpect(
        this.prideBook.isBook(),
        true);
  }

  boolean testWikiIsBook(Tester t) {
    return t.checkExpect(
        this.javaWiki.isBook(),
        false);
  }
  
  boolean testAnimalFarmBibliography(Tester t) {
    return t.checkExpect(
        this.animalFarmBook.bibliography(),
        this.animalFarmBibliography);
  }
  
  ILoDocument expectedAllDocuments =
      new ConsLoDocument(animalFarmBook,
          new ConsLoDocument(prideBook,
              new ConsLoDocument(javaWiki,
                  new ConsLoDocument(emmaBook,
                      new ConsLoDocument(hobbitBook,
                          new ConsLoDocument(taleBook, mtBibliography))))));
  
  boolean testAllDocuments(Tester t) {
    return t.checkExpect(
        this.literatureSurveyBook.allDocuments(), expectedAllDocuments);
  }
  
  boolean testOnlyBooks(Tester t) {
    return t.checkExpect(
        this.animalFarmBibliography.onlyBooks(),
        new ConsLoDocument(
            this.prideBook,
            new MtLoDocument()));
  }
  
  boolean testRemoveDuplicates(Tester t) {
    return t.checkExpect(
        this.duplicateBibliography.removeDuplicates(),
        new ConsLoDocument(
            this.prideBook,
            new ConsLoDocument(
                this.javaWiki,
                new MtLoDocument())));
  }
  
  //unsorted
  ILoDocument unsortedBibliography =
     new ConsLoDocument(hobbitBook,
         new ConsLoDocument(prideBook,
             new ConsLoDocument(taleBook,
                 new ConsLoDocument(animalFarmBook, mtBibliography))));
  
  //sorted alphabetically by author's last name
  ILoDocument sortedBibliography =
     new ConsLoDocument(prideBook,
         new ConsLoDocument(taleBook,
             new ConsLoDocument(animalFarmBook,
                 new ConsLoDocument(hobbitBook, mtBibliography))));
  
  boolean testSort(Tester t) {
   return t.checkExpect(
       this.unsortedBibliography.sort(),
       this.sortedBibliography);
  }
}