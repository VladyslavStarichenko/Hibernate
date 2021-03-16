package ua.com.alevel;


import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.dao.ProductDao;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;

import java.util.List;

public class App {
    public static void main(String[] args) {

//        Product product = createProductWithCategory();
        ProductDao productDao = new ProductDao();
//        productDao.createProduct(product);
//        CategoryDao categoryDao = new CategoryDao();
//        categoryDao.showCategories();

        productDao.deleteProductWithCategory( "Protein");
//        categoryDao.deleteCategory("Protein");
    }

    private static Product createProductWithCategory() {
        Category category = new Category();
        category.setCategoryName("Protein");

        Product product = new Product();
        product.setProductName("TestProteinProduct");
        product.setPrice(2111);
        product.setCategoryId(category);

        return product;
    }

}
