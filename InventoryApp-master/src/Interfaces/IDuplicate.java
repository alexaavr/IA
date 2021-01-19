package Interfaces;

import Java.Item;
import Java.User;

/**
 * Interfata folosita pentru functiile duplicate
 */

public interface IDuplicate {
    void updateItem(Item item, Item itemUp);

    void deleteUser(User user);
}
