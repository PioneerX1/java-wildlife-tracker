import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.text.DateFormat;
import java.util.Date;

public class SightingTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void sighting_instantiatesCorrectly_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    Sighting testSighting = new Sighting(firstEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    assertEquals(true, testSighting instanceof Sighting);
  }

  //Override equals not looking at Id
  @Test
  public void equals_returnsTrueIfLocationAndDescriptionAreSame_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    Sighting testSighting = new Sighting(firstEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    Sighting anotherSighting = new Sighting(firstEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    assertTrue(testSighting.equals(anotherSighting));
  }

  @Test
  public void save_insertsObjectIntoDatabase_Sighting() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    Sighting testSighting = new Sighting (firstEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
  }

  @Test
  public void all_returnsAllInstancesOfSighting_true() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    Sighting testSighting = new Sighting (firstEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", "Okay", "Adult");
    secondEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (secondEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Reese");
    secondTestSighting.save();
    assertEquals(true, Sighting.all().get(0).equals(testSighting));
    assertEquals(true, Sighting.all().get(1).equals(secondTestSighting));
  }

  @Test
  public void find_returnsSightingWithSameId_secondSighting() {
    EndangeredAnimal firstEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    firstEndangeredAnimal.save();
    Sighting testSighting = new Sighting (firstEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Avery");
    testSighting.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Badger", "Okay", "Adult");
    secondEndangeredAnimal.save();
    Sighting secondTestSighting = new Sighting (secondEndangeredAnimal.getId(), "45.472428, -121.946466", "Ranger Reese");
    secondTestSighting.save();
    assertEquals(Sighting.find(secondTestSighting.getId()), secondTestSighting);
  }

  @Test
  public void find_returnsNullWhenNoEndangeredAnimalFound_null() {
    assertTrue(EndangeredAnimal.find(999) == null);
  }

}
