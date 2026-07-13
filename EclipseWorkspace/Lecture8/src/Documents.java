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
interface IDocument  { }

// to represent ListOfDocument interface
interface ILoDocument  { }

// to represent ConsListOfDocument 
class ConsLoDocument implements ILoDocument  {
  IDocument  first;
  ILoDocument  rest;
  
  // the constructor
  ConsLoDocument(IDocument  first, ILoDocument rest) {
    this.first = first;
    this.rest = rest;
  }
}

// to represent MtListOfDocument
class MtLoDocument implements ILoDocument  {
  MtLoDocument() {}
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
  
}

// to represent wikiArticles class
class WikiArticles implements IDocument  {
  DocumentInfo info;
  String url;
  
  // the constructor 
  WikiArticles(DocumentInfo info, String url) {
    this.info = info;
    this.url = url;
  }
}

//to represent examplesDocument
class ExamplesDocuments {

  // examples of Author
  Author austen = new Author("Jane", "Austen", 1775);
  Author orwell = new Author("George", "Orwell", 1903);
  Author wikiContributor = new Author("Anonymous", "Contributor", 0);
  
  // examples of Publisher
  Publisher penguin = new Publisher("Penguin Books", 1935);
  Publisher secker = new Publisher("Secker & Warburg", 1855);
  
  // examples of ILoDocument 
  ILoDocument mtBibliography = new MtLoDocument();
  
  // examples of DocumentInfo
  DocumentInfo prideInfo = new DocumentInfo(austen, "Pride and Prejudice", mtBibliography);
  DocumentInfo javaWikiInfo = new DocumentInfo(wikiContributor, "Java (programming language)", mtBibliography);
  
  // examples of Book / WikiArticles
  Book prideBook = new Book(prideInfo, penguin);
  WikiArticles javaWiki = new WikiArticles(javaWikiInfo, "https://en.wikipedia.org/wiki/Java_(programming_language)");
  
  // a bibliography that cites the above two documents
  ILoDocument twoDocBibliography =
     new ConsLoDocument(prideBook,
         new ConsLoDocument(javaWiki, mtBibliography));
  
  // examples of DocumentInfo 
  DocumentInfo animalFarmInfo = new DocumentInfo(orwell, "Animal Farm", twoDocBibliography);
  
  // examples of Book 
  Book animalFarmBook = new Book(animalFarmInfo, secker);
  
  // examples of Document 
  IDocument  doc1 = prideBook;
  IDocument  doc2 = javaWiki;
  IDocument  doc3 = animalFarmBook;
}