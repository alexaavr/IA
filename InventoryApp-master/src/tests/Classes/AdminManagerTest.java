package tests.Classes;

import Classes.AdminManager;
import Classes.Item;
import Classes.User;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


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