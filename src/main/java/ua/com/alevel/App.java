package ua.com.alevel;


import ua.com.alevel.dao.CategoryDao;
import ua.com.alevel.dao.ProductDao;
import ua.com.alevel.helpers.HelperCategory;
import ua.com.alevel.model.Category;
import ua.com.alevel.model.Product;

public class App {
    public static void main(String[] args) {

//        Product product = createProductWithCategory();
//        ProductDao productDao = new ProductDao();
//        productDao.createProduct(product);
//        CategoryDao categoryDao = new CategoryDao();
//        categoryDao.showCategories();

//        productDao.deleteProductWithCategory( "Protein");
//        categoryDao.deleteCategory("Protein");
//     categoryDao.deletingCategory("Protein");

        HelperCategory.methodsCategory(HelperCategory.suggestCategoryMethod());
    }

//    private static Product createProductWithCategory() {
//        Category category = new Category();
//        category.setCategoryName("Protein");
//
//        Product product = new Product();
//        product.setProductName("TestProteinProduct");
//        product.setPrice(2111);
//        product.setCategoryId(category);
//
//        return product;
//    }

}
