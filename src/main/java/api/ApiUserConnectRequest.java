package api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiUserConnectRequest {
    private String username;
    private String firstName;
    private String lastName;
    private String email;
}
