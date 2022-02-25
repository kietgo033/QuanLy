package net.beans;


import net.connectors.ConnectorCatalog;
import net.connectors.ConnectorProduct;
import net.models.Catalog;
import net.models.Product;
import net.ultis.DataFileUtils;
import net.ultis.Find;
import sun.awt.geom.AreaOp;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@ManagedBean(name = "catalogVieww")
@SessionScoped
public class CatalogView extends BaseBean {
    private List<Catalog> catalogList;
    private String catalogId;
    private String catalogName;

    public List<Catalog> getCatalogList() {
        return catalogList;
    }

    public void setCatalogList(List<Catalog> catalogList) {
        this.catalogList = catalogList;
    }

    public String getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(String catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }


    @PostConstruct
    public void init() {
        setCatalogList(ConnectorCatalog.listCatalog());

    }


    public String deleteProduct(String id) {
        try {
            ConnectorCatalog.deleteCatalog(id);
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }

        // ko can lay du lai tu db
        catalogList.remove(Find.findCatalogvsId(id, catalogList));
        return "catalog?faces-redirect=true";

    }

    public void insertCatalog() {

        System.out.println("--vao inser--");

        Catalog ct = new Catalog();

        try {
            ct.setId(catalogId);
            ct.setName(catalogName);

            ConnectorCatalog.insertCatalog(ct);
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
        addMessage("Them thanh cong!");
        catalogList.add(ct);
    }

    public void updateCatalog(String id) {


        Catalog ct = new Catalog();

        ct.setId(id);
        ct.setName(catalogName);

        try {
            ConnectorCatalog.updateCatalog(ct);
        } catch (Exception ex) {
            addError("Exception: " + ex.getMessage());
        }
        addMessage("Chinh sua thanh cong!");
        catalogList.remove(Find.findCatalogvsId(id, catalogList));
        catalogList.add(ct);
    }


}

