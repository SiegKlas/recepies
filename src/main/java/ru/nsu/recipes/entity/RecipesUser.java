package ru.nsu.recipes.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED, force = true)
@RequiredArgsConstructor
public class RecipesUser implements UserDetails {

    private final String username;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private final List<Feedback> feedbacks = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> favouriteProducts = new ArrayList<>();
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Product> undesirableProducts = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
