package net.ultis;

import net.models.Catalog;
import net.models.Product;

import java.util.List;

public class Find {

    public static Product findProductvsId(String id, List<Product> productList) {
        for (Product pr : productList) {
            if (!pr.getId().equals(id))
                return pr;
        }
        return null;
    }

    public static Catalog findCatalogvsId(String id, List<Catalog> catalogList) {
        for (Catalog ct : catalogList) {
            if (!ct.getId().equals(id))
                return ct;
        }
        return null;
    }

}
