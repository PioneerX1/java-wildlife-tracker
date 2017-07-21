import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class NonEndangeredAnimalTest {
  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void endangeredAnimal_instantiatesCorrectly_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals(true, testNonEndangeredAnimal instanceof NonEndangeredAnimal);
  }

  @Test
  public void getHealth_returnsHealthAttribute_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    assertEquals("Healthy", testNonEndangeredAnimal.getHealth());
  }

  @Test
  public void save_assignsIdAndSavesObjectToDatabase() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    testNonEndangeredAnimal.save();
    NonEndangeredAnimal savedNonEndangeredAnimal = NonEndangeredAnimal.all().get(0);
    assertEquals(testNonEndangeredAnimal.getId(), savedNonEndangeredAnimal.getId());
  }

  @Test
  public void all_returnsAllInstancesOfNonEndangeredAnimal_true() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Badger", "Okay", "Adult");
    secondNonEndangeredAnimal.save();
    assertEquals(true, NonEndangeredAnimal.all().get(0).equals(firstNonEndangeredAnimal));
    assertEquals(true, NonEndangeredAnimal.all().get(1).equals(secondNonEndangeredAnimal));
  }

  @Test
  public void find_returnsAnimalWithSameId_secondAnimal() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Badger", "Okay", "Adult");
    secondNonEndangeredAnimal.save();
    assertEquals(NonEndangeredAnimal.find(secondNonEndangeredAnimal.getId()), secondNonEndangeredAnimal);
  }

  @Test
  public void equals_comparesTwoAnimalObjectsAndDeterminesTheyAreDifferent_false() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    firstNonEndangeredAnimal.save();
    NonEndangeredAnimal secondNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    secondNonEndangeredAnimal.save();
    NonEndangeredAnimal oneSaved = NonEndangeredAnimal.find(firstNonEndangeredAnimal.getId());
    NonEndangeredAnimal twoSaved = NonEndangeredAnimal.find(secondNonEndangeredAnimal.getId());
    assertFalse(firstNonEndangeredAnimal.equals(secondNonEndangeredAnimal));
    assertFalse(oneSaved.equals(twoSaved));
  }

  @Test
  public void equals_comparesTwoAnimalTypesAsWellAsId_false() {
    NonEndangeredAnimal firstNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    firstNonEndangeredAnimal.save();
    EndangeredAnimal secondEndangeredAnimal = new EndangeredAnimal("Fox", "Healthy", "Young");
    secondEndangeredAnimal.save();
    NonEndangeredAnimal oneSaved = NonEndangeredAnimal.find(firstNonEndangeredAnimal.getId());
    EndangeredAnimal twoSaved = EndangeredAnimal.find(secondEndangeredAnimal.getId());
    assertFalse(firstNonEndangeredAnimal.equals(secondEndangeredAnimal));
    assertFalse(oneSaved.equals(twoSaved));
  }

  @Test
  public void update_updatesHealthAttribute_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    testNonEndangeredAnimal.save();
    testNonEndangeredAnimal.updateHealth("ill");
    assertEquals("ill", NonEndangeredAnimal.find(testNonEndangeredAnimal.getId()).getHealth());
  }

  @Test
  public void update_updatesAgeAttribute_true() {
    NonEndangeredAnimal testNonEndangeredAnimal = new NonEndangeredAnimal("Fox", "Healthy", "Young");
    testNonEndangeredAnimal.save();
    testNonEndangeredAnimal.updateAge("Adult");
    assertEquals("Adult", NonEndangeredAnimal.find(testNonEndangeredAnimal.getId()).getAge());
  }

}
