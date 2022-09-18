package api;

import lombok.Data;

import java.util.ArrayList;

@Data
public class Aisle {
    public String aisle;
    public ArrayList<Item> items;
}
