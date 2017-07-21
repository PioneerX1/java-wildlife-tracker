import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class EndangeredAnimal extends Animal{
  public static final String DATABASE_TYPE = "endangered";

  public EndangeredAnimal(String name, String health, String age) {
    this.name = name;
    this.id = id;
    this.health = health;
    this.age = age;
    this.type = DATABASE_TYPE;
  }



  @Override
  public boolean equals(Object otherEndangeredAnimal) {
    if(!(otherEndangeredAnimal instanceof EndangeredAnimal)) {
      return false;
    } else {
      EndangeredAnimal newEndangeredAnimal = (EndangeredAnimal) otherEndangeredAnimal;
      return  this.getId() == newEndangeredAnimal.getId() &&
              this.getName().equals(newEndangeredAnimal.getName()) && this.getHealth().equals(newEndangeredAnimal.getHealth()) && this.getAge().equals(newEndangeredAnimal.getAge()) &&
              this.getType().equals(newEndangeredAnimal.getType());
    }
  }


  public static List<EndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE type = 'endangered';";
      return con.createQuery(sql)
        .executeAndFetch(EndangeredAnimal.class);
    }
  }

  public static EndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      EndangeredAnimal endangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(EndangeredAnimal.class);
      return endangeredanimal;
    }
  }


  public List<Sighting> getSightings() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM sightings WHERE animal_id=:id;";
        List<Sighting> sightings = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetch(Sighting.class);
      return sightings;
    }
  }


}
