package com.tempolivre.api.entity;
import com.tempolivre.api.entity.enums.UserRole;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@Entity(name = "usuario")
@EqualsAndHashCode( of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @NotBlank(message = "O usuário deve ter um nome")
    private String name;

    @Column(unique = true)
    @NotBlank(message = "O usuário deve ter um email")
    private String email;

    @Column(unique = true)
    @NotBlank(message = "O usuário deve ter um usernome")
    private String username;

    @NotBlank(message = "O usuário deve ter uma senha")
    private String password;

    private UserRole role;

    public User (
            UserRole role,
            String name,
            String email,
            String username,
            String password
    ) {
        this.role = role;
        this.name = name;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    @Override
    public String getUsername() { return email; }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //return List.of();
        if(this.role == UserRole.ADMIN){
            return List.of(
                    new SimpleGrantedAuthority("ROLE_ADMIN"),
                    new SimpleGrantedAuthority("ROLE_USER")
            );
        } else {
            return List.of(new SimpleGrantedAuthority("ROLE_USER"));
        }
    }

    @Override
    public boolean isAccountNonExpired() {
        //return UserDetails.super.isAccountNonExpired();
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //return UserDetails.super.isAccountNonLocked();
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //return UserDetails.super.isCredentialsNonExpired();
        return true;
    }

    @Override
    public boolean isEnabled() {
        //return UserDetails.super.isEnabled();
        return true;
    }

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
