// to represent class Place
class Place{
  
}

// to represent class Restaurant
class Restaurant{
  String name;
  String price;
  Place place;
  
  Restaurant(String name, String price, Place place){
    this.name = name;
    this.price = price;
    this.place = place;
  }
}

// to represent class ChineseRestaurant
class ChineseRestaurant extends Restaurant{
  boolean usesMSG;
  
  ChineseRestaurant(String name, String price, Place place, boolean usesMSG){
    super(name, price, place);
    this.usesMSG = usesMSG;
  }
}