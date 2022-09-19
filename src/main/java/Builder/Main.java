package Builder;


import com.geekbrains.db.dao.CategoriesMapper;
import com.geekbrains.db.dao.ProductsMapper;
import com.geekbrains.db.model.Categories;
import com.geekbrains.db.model.Products;
import com.geekbrains.db.model.ProductsExample;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        //SpoonaccularService spoonaccularService = new SpoonaccularService();
        //ApiSearchResult recipes = spoonaccularService.findRecipes("Bread", 3);
        //System.out.println(recipes);
        //ApiGetShoppingListResult apiGetShoppingListResult = spoonaccularService.shoppingResults();
        //System.out.println(apiGetShoppingListResult);
        //ApiDeleteShoppingListResult apiDeleteShoppingListResult = spoonaccularService.deleteShoppingList();
        //System.out.println(apiDeleteShoppingListResult);
        //ApiPostInShoppingListResult postMethode = spoonaccularService.connect(new ApiPostInShoppingListRequest("1 package baking powder", "Baking", true));
       // System.out.println(postMethode);

        SqlSessionFactory sessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("myBatisConfig.xml"));

        try (SqlSession session = sessionFactory.openSession()) {
            ProductsMapper productsMapper = session.getMapper(ProductsMapper.class);
            CategoriesMapper categoriesMapper = session.getMapper(CategoriesMapper.class);
            Products product = productsMapper.selectByPrimaryKey(1269L);
            System.out.println(product.getId());
            int deleteProducts= productsMapper.deleteByPrimaryKey(1269L);
            System.out.println(deleteProducts);
            Categories category = categoriesMapper.selectByPrimaryKey(product.getCategoryId());
            System.out.println(category);
            session.commit();



        }finally {

        }

    }
}
