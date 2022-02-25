package net.connectors;


import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import net.models.Product;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

public class ConnectorProduct {

    // Creating a Mongo client
    private static MongoClient mongo = new MongoClient("127.0.0.1", 27017);


    public static List<Product> listProduct() {
        MongoCredential credential;
        List<Product> listPro = new ArrayList<>();
        Product product;
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            MongoDatabase database = mongo.getDatabase("quanly");


            System.out.println("Credentials ::" + credential);

            MongoCollection<Document> collection = database.getCollection("product");
            System.out.println("Collection sampleCollection ------- vao day ------selected successfully");

            FindIterable<Document> iterDoc = collection.find();
            for (Document doc : iterDoc) {

                System.out.println(doc);

                product = new Product();
                product.setId(doc.getString("id"));
                product.setName(doc.getString("name"));
                product.setPrice((Double) doc.get("price"));
                product.setImage(doc.getString("image"));
                product.setCatalogId(doc.getString("catalogId"));
                System.out.println(product);
                listPro.add(product);
            }
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
        System.out.println(listPro);
        return listPro;
    }


    public static List<String> listId() {
        MongoCredential credential;
        List<String> listId = new ArrayList<>();
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            MongoDatabase database = mongo.getDatabase("quanly");


            System.out.println("Credentials ::" + credential);

            MongoCollection<Document> collection = database.getCollection("product");
            System.out.println("Collection sampleCollection ------- vao day ------selected successfully");

            FindIterable<Document> iterDoc = collection.find();
            for (Document doc : iterDoc) {

                System.out.println(doc);


                listId.add(doc.getString("id"));
            }
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
        System.out.println(listId);
        return listId;
    }

    public static void deleteProduct(String id) {
        MongoCredential credential;

        credential = MongoCredential.createCredential("sampleUser", "quanly",
                "password".toCharArray());
        System.out.println("Connected to the database successfully");

        MongoDatabase database = mongo.getDatabase("quanly");

        System.out.println("Credentials ::" + credential);

        MongoCollection<Document> collection = database.getCollection("product");
        System.out.println("Collection sampleCollection ------- vao day ------selected successfully");
        // Deleting the documents
        collection.deleteOne(Filters.eq("id", id));
        System.out.println("Document deleted successfully...");

    }

    public static void insertProduct(Product product) {
        MongoCredential credential;
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");

            MongoDatabase database = mongo.getDatabase("quanly");


            System.out.println("Credentials ::" + credential);
            //database.createCollection("product");

            MongoCollection<Document> collection = database.getCollection("product");
            System.out.println("Collection sampleCollection selected successfully");
            Document document = convert(product);

            //Inserting document into the collection
            collection.insertOne(document);
            System.out.println("Document inserted successfully");
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }

    public static Document convert(Product product) {
        Document dc = new Document();
        dc.append("id", product.getId());
        dc.append("name", product.getName());
        dc.append("price", (double) product.getPrice());
        dc.append("image", product.getImage());
        dc.append("catalogId", product.getCatalogId());
        return dc;
    }

    public static void updateProduct(Product product) {
        MongoCredential credential;
        try {
            credential = MongoCredential.createCredential("sampleUser", "quanly",
                    "password".toCharArray());
            System.out.println("Connected to the database successfully");
            MongoDatabase database = mongo.getDatabase("quanly");

            System.out.println("Credentials ::" + credential);
            //database.createCollection("product");

            MongoCollection<Document> collection = database.getCollection("product");
            System.out.println("Collection sampleCollection selected successfully");


            Document document = convert(product);


            collection.updateOne(Filters.eq("id", product.getId()), Updates.set("name", product.getName()));
            collection.updateOne(Filters.eq("id", product.getId()), Updates.set("price", product.getPrice()));
            collection.updateOne(Filters.eq("id", product.getId()), Updates.set("image", product.getImage()));
            collection.updateOne(Filters.eq("id", product.getId()), Updates.set("catalogId", product.getCatalogId()));

            System.out.println("Document updated successfully");
        } catch (Exception ex) {
            System.out.println(ex.fillInStackTrace());
        }
    }

}

