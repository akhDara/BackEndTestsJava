package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiPostInShoppingListRequest {
    private String item;
    private String aisle;
    private boolean parse;
}

