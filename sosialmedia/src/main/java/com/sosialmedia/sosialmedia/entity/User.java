package com.sosialmedia.sosialmedia.entity;

import com.sosialmedia.sosialmedia.enums.Gender;
import com.sosialmedia.sosialmedia.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
@Builder
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements UserDetails {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userid;
    private String name;
    private String surname;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    private LocalDate birthdate;
    private String bio;
    private boolean isActive = true;
    private String email;
    private String phoneNumber;
    private String username;
    private String password;
    private String profilePictureUrl;
    private LocalDate createdDate;
    private LocalDate modifiedDate;
    private boolean locked;
    private boolean enabled;
    @Enumerated(EnumType.STRING)
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }
}
