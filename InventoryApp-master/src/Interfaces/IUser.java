package Interfaces;

import Java.Item;
import Java.User;

/**
 * Interfata este utilizata pentru a clarifica atributiile unui User
 * si pentru a le separa de cele ale unui Admin.
 */

public interface IUser {
    boolean findItem(Item item);

    boolean findItemByCode(Item item);

    String displayItem(Item item);

    void updateItem(Item item, Item itemUp);

    void deleteUser(User user);
}
