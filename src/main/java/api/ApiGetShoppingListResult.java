package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiGetShoppingListResult {
        public ArrayList<Aisle> aisles;
        public double cost;
        public int startDate;
        public int endDate;

}
