package test.java.com.sbt.javaschool.rnd.lesson2;

import com.sbt.javaschool.rnd.lesson2.Person;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testEqualMan() {
        Person man1 = new Person(true, "Ivan Ivanov");
        Person man2 = new Person(true, "Petya Petrov");

       assertFalse(man1.marry(man2));
    }

    @Test
    void testMarry() {
        Person Mary = new Person(false, "Marry");
        Person Sam = new Person(true, "Sam");

        assertTrue(Mary.marry(Sam));
        assertEquals(Mary, Sam.getSpouse());
        assertEquals(Sam, Mary.getSpouse());
    }
    @Test
    void testDoubleMarry() {
        Person Mary = new Person(false, "Marry");
        Person Sam = new Person(true, "Sam");

        assertTrue(Mary.marry(Sam));
        assertFalse(Mary.marry(Sam));
    }

    @Test
    void testMaryAndDivorce() {
        Person Mary = new Person(false, "Marry");
        Person Sam = new Person(true, "Sam");
        Person Philip = new Person(true, "Philip");

        Mary.marry(Sam);

        assertTrue(Mary.marry(Philip));

        assertNull(Sam.getSpouse());
        assertEquals(Mary.getSpouse(), Philip);
        assertEquals(Philip.getSpouse(), Mary);
    }

    @Test
    void testDivorce() {
        Person Mary = new Person(false, "Marry");
        Person Sam = new Person(true, "Sam");

        assertFalse(Mary.divorce());

        Mary.marry(Sam);

        assertTrue(Mary.divorce());

    }
}