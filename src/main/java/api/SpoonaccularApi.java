package api;


import retrofit2.Call;
import retrofit2.http.*;

public interface SpoonaccularApi {

    @GET("/recipes/complexSearch")
    Call<ApiSearchResult> findRecipes(
            @Query("apiKey") String apiKey,
            @Query("query")String query,
            @Query("number")Integer number

    );


    @POST("/mealplanner/myusername1/shopping-list/items")
    Call<ApiPostInShoppingListResult> connect( @Body ApiPostInShoppingListRequest request,
            @Query("apiKey") String apiKey,
            @Query("hash") String hash,
            @Query("Content-Type") String Content_Type);

   @GET("/mealplanner/myusername1/shopping-list")
    Call<ApiGetShoppingListResult> shoppingResults (
            @Query("apiKey") String apiKey,
            @Query("hash") String hash,
            @Query("Content-Type") String Content_Type
   );

   @DELETE("/mealplanner/myusername1/shopping-list/items/1301279")
    Call<ApiDeleteShoppingListResult> deleteShoppingList (
           @Query("apiKey") String apiKey,
           @Query("hash") String hash,
           @Query("Content-Type") String Content_Type
   );

}
