import tester.*;

// to represent Image class
class Image {
  int width;
  int height;
  String source;
  
  // the constructor
  Image(int width, int height, String source){
    this.width = width;
    this.height = height;
    this.source = source;
  }
  
  // to produce the size of the Image
  int imageSize() {
    return (this.width * this.height);
  }
  
  // produce the size according to the number of pixels in the image
  String sizeString() {
    if (this.imageSize() <= 10000)
      return "small";
    else if (this.imageSize() > 10000 && this.imageSize() <= 1000000)
      return "medium";
    else
      return 
          "large";
  }
}

class ExamplesImage {

    // Examples
    Image img1 = new Image(50, 100, "cat.png");    
    Image img2 = new Image(100, 100, "dog.png");   
    Image img3 = new Image(200, 100, "bird.png");
    Image img4 = new Image(1000, 1000, "city.png");
    Image img5 = new Image(2000, 1000, "space.png"); 

    // Tests for imageSize
    boolean testImageSize(Tester t) {
      return t.checkExpect(img1.imageSize(), 5000)
          && t.checkExpect(img2.imageSize(), 10000)
          && t.checkExpect(img3.imageSize(), 20000)
          && t.checkExpect(img4.imageSize(), 1000000)
          && t.checkExpect(img5.imageSize(), 2000000);
    }

    // Tests for sizeString
    boolean testSizeString(Tester t) {
      return t.checkExpect(img1.sizeString(), "small")
          && t.checkExpect(img2.sizeString(), "small")  
          && t.checkExpect(img3.sizeString(), "medium")
          && t.checkExpect(img4.sizeString(), "medium")   
          && t.checkExpect(img5.sizeString(), "large");
    }
  }