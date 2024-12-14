package parking;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class ConnectDatabase {
    private static final String URI = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "SYTEM_PARKING";

    private static MongoClient mongoClient;

    // Método para obtener la instancia de MongoDatabase
    public static MongoDatabase getDatabase() {
        if (mongoClient == null) {
            mongoClient = MongoClients.create(URI);
        }
        return mongoClient.getDatabase(DATABASE_NAME);
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (mongoClient != null) {
            mongoClient.close();
        }
    }
}
