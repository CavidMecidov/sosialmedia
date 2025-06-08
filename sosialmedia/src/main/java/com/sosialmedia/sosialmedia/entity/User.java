package com.sosialmedia.sosialmedia.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sosialmedia.sosialmedia.enums.Gender;
import com.sosialmedia.sosialmedia.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
@Builder
@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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
    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;
    @LastModifiedDate
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    private boolean locked;
    private boolean enabled=true;
    @Enumerated(EnumType.STRING)
    private Role role;
    public Long getId() {
        return this.userid;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {//Yetki
        return List.of(new SimpleGrantedAuthority("ROLE_" +  role.name()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }
}
