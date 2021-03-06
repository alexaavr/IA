package DB;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

/**
 * Clasa se utilizeaza pentru a face conexiunea la baza de date MongoDB.
 * De asemenea, se pot accesa colectiile realizate pentru Admin, User si Item.
 */

public class ConnectionDB {
    public static String uri = "mongodb+srv://Alexa:alexa@mangodb-wcom9.mongodb.net/Login";
    public static MongoClientURI clientURI = new MongoClientURI(uri);
    public static MongoClient mongoClient = new MongoClient(clientURI);
    public static MongoDatabase mongoDatabase = mongoClient.getDatabase("MongoDB");
    public static MongoCollection collectionLogin = mongoDatabase.getCollection("Login");
    public static MongoCollection collectionAdmin = mongoDatabase.getCollection("Admin");
    public static MongoCollection collectionItem = mongoDatabase.getCollection("Items");
}