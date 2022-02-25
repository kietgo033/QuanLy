package net.connectors;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import net.models.Catalog;
import net.models.Product;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ConnectorCatalog {

    private static MongoClient mongo = new MongoClient("127.0.0.1", 27017);


    public static List<Catalog> listCatalog() {
        MongoCredential credential;
        List<Catalog> listCata = new ArrayList<>();
        Catalog catalog;
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            MongoDatabase database = mongo.getDatabase("quanly");


            System.out.println("Credentials ::" + credential);

            MongoCollection<Document> collection = database.getCollection("catalog");
            System.out.println("Collection sampleCollection ------- vao day ------selected successfully");

            FindIterable<Document> iterDoc = collection.find();
            for (Document doc : iterDoc) {

                System.out.println(doc);

                catalog = new Catalog();
                catalog.setId(doc.getString("id"));
                catalog.setName(doc.getString("name"));
                System.out.println(catalog);
                listCata.add(catalog);
            }
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
        System.out.println(listCata);
        return listCata;
    }

    public static void deleteCatalog(String id) {
        MongoCredential credential;
        credential = MongoCredential.createCredential("sampleUser", "quanly",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        MongoDatabase database = mongo.getDatabase("quanly");

        System.out.println("Credentials ::" + credential);

        MongoCollection<Document> collection = database.getCollection("catalog");
        System.out.println("Collection sampleCollection ------- vao day ------selected successfully");
        // Deleting the documents
        collection.deleteOne(Filters.eq("id", id));
        System.out.println("Document deleted successfully...");

    }

    public static void insertCatalog(Catalog catalog) {
        MongoCredential credential;
        System.out.println("Vao insert trong Connector");
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            MongoDatabase database = mongo.getDatabase("quanly");


            System.out.println("Credentials ::" + credential);
            //database.createCollection("product");

            MongoCollection<Document> collection = database.getCollection("catalog");
            System.out.println("Collection sampleCollection selected successfully");
            Document document = convert(catalog);

            //Inserting document into the collection
            collection.insertOne(document);
            System.out.println("Document inserted successfully");
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }

    public static Document convert(Catalog ct) {
        Document dc = new Document();
        dc.append("id", ct.getId());
        dc.append("name", ct.getName());
        return dc;
    }

    public static void updateCatalog(Catalog catalog) {
        MongoCredential credential;
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            MongoDatabase database = mongo.getDatabase("quanly");


            System.out.println("Credentials ::" + credential);
            //database.createCollection("product");

            MongoCollection<Document> collection = database.getCollection("catalog");
            System.out.println("Collection sampleCollection selected successfully");


            Document document = convert(catalog);


            collection.updateOne(Filters.eq("id", catalog.getId()), Updates.set("name", catalog.getName()));

            System.out.println("Document updated successfully");
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }


}
