package net.models;

public class Product {
    private String id;
    private String name;
    private String image;
    private double price;
    private String catalogId;

    public Product(String id, String name, double price, String image, String catalogId) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;

        this.catalogId = catalogId;
    }

    public Product() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }
}
