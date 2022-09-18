package Builder;


import api.*;

public class Main {
    public static void main(String[] args) {
        SpoonaccularService spoonaccularService = new SpoonaccularService();
        ApiSearchResult recipes = spoonaccularService.findRecipes("Bread", 3);
        System.out.println(recipes);


        ApiGetShoppingListResult apiGetShoppingListResult = spoonaccularService.shoppingResults();
        System.out.println(apiGetShoppingListResult);


        ApiDeleteShoppingListResult apiDeleteShoppingListResult = spoonaccularService.deleteShoppingList();
        System.out.println(apiDeleteShoppingListResult);

        ApiPostInShoppingListResult postMethode = spoonaccularService.connect(new ApiPostInShoppingListRequest("1 package baking powder", "Baking", true));
        System.out.println(postMethode);

    }
}
