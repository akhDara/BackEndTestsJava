package api;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Item {
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
