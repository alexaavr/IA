package tests.Classes;

import Classes.Item;
import Classes.UserManager;
import org.junit.Test;

import static org.junit.Assert.*;

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