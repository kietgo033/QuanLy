package net.beans;


import net.connectors.ConnectorProduct;
import net.models.Catalog;
import net.models.Product;
import net.ultis.DataFileUtils;
import net.ultis.Find;
import org.primefaces.model.file.UploadedFile;


//import javax.faces.bean.ViewScoped;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@ManagedBean(name = "productVieww")
@SessionScoped
public class ProductView extends BaseBean {
    private Product product;
    private List<Product> productList;
    private String productId;
    private String productName;
    private UploadedFile productImage;
    private double productPrice;
    private String productCatalogId;

    private Product productSelected;
    private Map<String, Catalog> catalogMap;
    private String nameProductSearch = null;
    private String nameCatalogSearch = null;

    private List<String> listID;

    public List<String> getListID() {
        return listID;
    }

    public void setListID(List<String> listID) {
        this.listID = listID;
    }

    public String getProductCatalogId() {
        return productCatalogId;
    }

    public void setProductCatalogId(String productCatalogId) {
        this.productCatalogId = productCatalogId;
    }

    public Product getProductSelected() {
        return productSelected;
    }

    public void setProductSelected(Product productSelected) {
        this.productSelected = productSelected;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public UploadedFile getProductImage() {
        return productImage;
    }

    public void setProductImage(UploadedFile productImage) {
        this.productImage = productImage;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    private final static String IMAGE_STORE = "C:\\Users\\Admin\\Desktop\\backup\\Demo\\Demo\\Web\\images";


    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public String deleteProduct(String id) {
        try {
            ConnectorProduct.deleteProduct(id);
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }

        // ko can lay du lai tu db
        productList.remove(Find.findProductvsId(id, productList));

        return "product?faces-redirect=true";

    }

    public void insertProduct() {

        Product pr = new Product();

        try {
            pr.setId(productId);
            pr.setName(productName);
            pr.setPrice(productPrice);
            pr.setCatalogId(productCatalogId);

            File file = null;
            if (null == productImage) {
                pr.setImage("Default.jpg");
            } else {
                String imageName = productImage.getFileName();
                file = new File(IMAGE_STORE, imageName);

                try {
                    DataFileUtils.saveToDickImage(file, productImage.getInputStream());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (null != file) {
                pr.setImage(file.getName());
            }

            ConnectorProduct.insertProduct(pr);
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
        addMessage("Them thanh cong!");
        productList.add(pr);
    }

    public void updateProduct() {

        Product pr = new Product();

        pr.setId(productId);
        pr.setName(productName);
        pr.setPrice(productPrice);
        pr.setCatalogId(productCatalogId);

        File file = null;
        if (null == productImage) {
            pr.setImage("Default.jpg");
            System.out.println("--anh null---");
        } else {
            String imageName = productImage.getFileName();
            file = new File(IMAGE_STORE, imageName);
            try {
                DataFileUtils.saveToDickImage(file, productImage.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (null != file) {
            pr.setImage(file.getName());
        }

        try {
            ConnectorProduct.updateProduct(pr);
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
        addMessage("Chinh sua thanh cong!");
        productList = ConnectorProduct.listProduct();
    }

    @PostConstruct
    public void init() {

        setProductList(ConnectorProduct.listProduct());
        setListID(ConnectorProduct.listId());
    }


}
