package tests.Classes;

import Classes.DuplicateFunc;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test pentru a determina daca o adresa de mail este valida.
 * Test pentru a determina daca o parola se incadreaza in cerinte.
 */

public class DuplicateFuncTest {

    @Test
    public void isValidMail() {
        //String mail = "Somewrongmail";
        String mail = "alexandra.avram@yahoo.com";
        assertEquals(DuplicateFunc.isValidMail(mail), true);
    }

    @Test
    public void isValid() {
        String pass = "SomethingThatContainsAll1!";
        assertEquals(DuplicateFunc.isValid(pass), true);
    }
}