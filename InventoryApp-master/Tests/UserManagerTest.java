package Tests;

import Java.Item;
import Java.UserManager;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Test pentru a determina daca un item cautat dupa cod se afla in baza de date sau nu.
 */

public class UserManagerTest {

    UserManager userManager = new UserManager();
    Item item = new Item("Something");
    Item itemCode = new Item(1200);

    @Test
    public void getString() {
        assertEquals(UserManager.getString(item), item.toString());
    }

    @Test
    public void findItembyCode() {
        assertEquals(userManager.findItem(itemCode), false);
    }
}