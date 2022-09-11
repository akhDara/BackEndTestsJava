package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPostInShoppingListResult {
        public int id;
        public String name;
        public Measures measures;
        public ArrayList<Object> usages;
        public ArrayList<Object> usageRecipeIds;
        public boolean pantryItem;
        public String aisle;
        public double cost;
        public int ingredientId;


}
