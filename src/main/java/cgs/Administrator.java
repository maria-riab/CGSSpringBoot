package cgs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Administrator {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_admin")
    @SequenceGenerator(name = "gen_admin", sequenceName = "SEQ_ADMIN", allocationSize = 1)
    private long administratorID;
    private String username;
    private String password;
    private String role;

    public Administrator(String username, String password, String role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public Administrator(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
