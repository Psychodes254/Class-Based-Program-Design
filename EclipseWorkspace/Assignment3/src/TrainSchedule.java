// to represent class Schedule
class Schedule{
  
}

// to represent class Route
class Route{
  
}

// to represent class Stop
class Stops{
  
}

// to represent class Train
class Train{
  Schedule schedule;
  Route route;
  
  Train(Schedule schedule, Route route){
    this.schedule = schedule;
    this.route = route;
  }
}

// to represent class ExpressTrain
class ExpressTrain extends Train{
  Stops stop;
  String name;
  
  ExpressTrain(Schedule schedule, Route route, Stops stop, String name){
    super(schedule, route);
    this.stop = stop;
    this.name = name;
  }
}
