import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;

public class NonEndangeredAnimal extends Animal{
  public static final String DATABASE_TYPE = "nonendangered";

  public NonEndangeredAnimal(String name, String health, String age) {
    if (name.equals("")) {
      throw new IllegalArgumentException("No species/name listed!");
    }
    this.name = name;
    this.id = id;
    this.health = health;
    this.age = age;
    this.type = DATABASE_TYPE;
  }



  @Override
  public boolean equals(Object otherNonEndangeredAnimal) {
    if(!(otherNonEndangeredAnimal instanceof NonEndangeredAnimal)) {
      return false;
    } else {
      NonEndangeredAnimal newNonEndangeredAnimal = (NonEndangeredAnimal) otherNonEndangeredAnimal;
      return  this.getId() == newNonEndangeredAnimal.getId() &&
              this.getName().equals(newNonEndangeredAnimal.getName()) && this.getHealth().equals(newNonEndangeredAnimal.getHealth()) && this.getAge().equals(newNonEndangeredAnimal.getAge()) &&
              this.getType().equals(newNonEndangeredAnimal.getType());
    }
  }


  public static List<NonEndangeredAnimal> all() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE type = 'nonendangered';";
      return con.createQuery(sql)
        .executeAndFetch(NonEndangeredAnimal.class);
    }
  }

  public static NonEndangeredAnimal find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM animals WHERE id=:id;";
      NonEndangeredAnimal nonendangeredanimal = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(NonEndangeredAnimal.class);
      return nonendangeredanimal;
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
