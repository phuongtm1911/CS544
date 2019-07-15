package edu.mum.cs544;

import javax.persistence.*;
import java.util.List;

@Entity
public class Authorities {
    @Id
    @GeneratedValue
    private Long id;
    @Column(nullable = false)
    private String authority;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "authority_id")})
    private List<Users> users;

    public Authorities() {
    }

    public Authorities(String authority) {
        this.authority = authority;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}
