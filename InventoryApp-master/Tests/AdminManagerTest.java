package Tests;

import Java.AdminManager;
import Java.Item;
import Java.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Test pentru a determina daca un anumit item se regaseste sau nu in baza de date.
 * Test pentru a determina daca un User se gaseste sau nu in baza de date.
 */

public class AdminManagerTest {

    AdminManager adminManager = new AdminManager();
    Item item = new Item("Something");
    User user = new User("something wrong");

    @Test
    public void findItem() {

        assertEquals(adminManager.findItem(item), false);
    }

    @Test
    public void displayItem() {
        assertEquals(adminManager.displayItem(item), item.toString());
    }

    @Test
    public void findUser() {
        assertEquals(adminManager.findUser(user), false);
    }
}