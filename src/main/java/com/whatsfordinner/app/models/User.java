package com.whatsfordinner.app.models;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;

    @NotBlank(message = "Email must not be blank")
    @Email(message = "Invalid email address")
    @Column(name = "email")
    private String email;

    @NotBlank
    @Size(min = 5, message = "Password must be at least 5 characters long")
    @Column(name = "password")
    private String password;

    @Column(name = "status")
    private String status;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    @Column(name = "reset_token")
    private String resetToken;

//    @ManyToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "user_ingredient", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
//    private Set<Ingredient> ingredients;

//    @OneToMany(mappedBy = "user")
//    private Set<Ingredient> ingredients;

    public User() {
    }

    public User(String username, String email, String password) {
        this.email = email;
        this.password = password;
    }

    public int getUserId()  { return userId; }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public String getResetToken() {
        return resetToken;
    }

    public void setResetToken(String resetToken) {
        this.resetToken = resetToken;
    }
}
