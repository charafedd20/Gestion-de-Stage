package Gestion.de.stage.Gestion.de.stage.Entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Entity
@Data
@Table(name = "utilisateur")
public class UtilisateurEntity implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true, nullable = false)
    private String identifiant; // Correspond au username pour l'authentification

    @Column(nullable = false)
    private String password; // Mot de passe encodé

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role; // Enumération des rôles

    public enum Role {
        ADMIN,
        ETUDIANT,
        ENTREPRISE,
        RESPONSABLE,
        ECOLE
    }



    // Implémentation des méthodes nécessaires pour Spring Security
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Retourne le rôle comme une autorité Spring Security
        return Collections.singleton(() -> "ROLE_" + role.name());
    }

    @Override
    public String getUsername() {
        return identifiant; // Utilisé comme nom d'utilisateur pour Spring Security
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Vous pouvez ajouter une logique pour vérifier l'expiration
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Vous pouvez ajouter une logique pour vérifier si le compte est verrouillé
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Vous pouvez ajouter une logique pour vérifier l'expiration des informations d'identification
    }

    @Override
    public boolean isEnabled() {
        return true; // Vous pouvez ajouter une logique pour vérifier si le compte est actif
    }
}
