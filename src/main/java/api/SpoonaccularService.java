package api;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;

public class SpoonaccularService {

    private SpoonaccularApi api;
    private static final String API_KEY = "8a31eefd36604b8ca910047c1b6a1ce0";
    private static final String HASH = "c2d28ebfb0fb500f5d5cc658ec7ab04b8570f0f3";
    private static final String CONTENT_TYPE = "application/json";




    public SpoonaccularService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.spoonacular.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(SpoonaccularApi.class);

    }

    public ApiSearchResult findRecipes(String query, Integer number) {
        Call<ApiSearchResult> call =  api.findRecipes(API_KEY, query, number);
        return RetrofitUtils.execute(call);
    }

    public ApiGetShoppingListResult shoppingResults() {
        Call<ApiGetShoppingListResult > call =  api.shoppingResults(API_KEY,HASH, CONTENT_TYPE);
        return RetrofitUtils.execute(call);
    }


    public ApiDeleteShoppingListResult deleteShoppingList() {
        Call<ApiDeleteShoppingListResult > call =  api.deleteShoppingList(API_KEY,HASH, CONTENT_TYPE);
        return RetrofitUtils.execute(call);
    }


    public ApiPostInShoppingListResult connect(@Body ApiPostInShoppingListRequest request ) {
        Call<ApiPostInShoppingListResult> call =  api.connect(request, API_KEY, HASH, CONTENT_TYPE);
        return RetrofitUtils.execute(call);
    }

}
