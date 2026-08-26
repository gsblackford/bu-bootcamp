import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Test;
import org.junit.Before;

public class ContactTest {
  public Contact contact;
  
  @Before
  public void setUp() {
    System.out.println("Setting up test");
    contact = new Contact("Ada Lovelace", "+1 617 555 0101");
  } 
  
  @Test 
  public void constructor_setsNameCorrectly() { 
    assertEquals("Ada Lovelace", contact.getName()); 
  } 
 
  @Test
  public void constructor_setsPhoneCorrectly() { 
    assertEquals("+1 617 555 0101", contact.getPhoneNumber()); 
  } 
 
  @Test
  public void getName_returnsExactString_notTransformed() { 
    Contact c = new Contact("Grace Hopper", "555-0000"); 
    assertEquals("Grace Hopper", c.getName());
  } 
 
  @Test
  public void toString_containsName() { 
    assertTrue(contact.toString().contains("Ada Lovelace"));
  } 
 
  @Test
  public void toString_containsPhone() {
    assertTrue(contact.toString().contains("+1 617 555 0101"));
  }

  @Test
  public void setName_updatesNameCorrectly() {
    Contact c1 = new Contact("Alan Turing", "+44 20 7946 0958");
    Contact c2 = new Contact("Alan Turing", "+44 20 7946 0958");
    c1.setName("Alan Mathison Turing");
    
    assertFalse(c1.getName().equals(c2.getName()));
    assertEquals("Alan Mathison Turing", c1.getName());
  }

}
