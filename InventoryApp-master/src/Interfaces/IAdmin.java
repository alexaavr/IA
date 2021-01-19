package Interfaces;

import Java.Item;
import Java.User;

/**
 * Interfata este utilizata pentru a clarifica atributiile unui Admin
 * si pentru a le separa de cele ale unui User.
 */

public interface IAdmin {
    void addItem(Item item);

    void deleteItem(Item item);

    boolean findItem(Item item);

    String displayItem(Item item);

    void updateItem(Item item, Item itemUp);

    void deleteUser(User user);

    void addUser(User user);

    void updateUser(User user, User userUp);

    boolean findUser(User user);

    String displayUser(User user);
}
