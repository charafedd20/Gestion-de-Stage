package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Entity.UtilisateurEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.UtilisateurRepo;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
public class UtilisateurDetailsService implements UserDetailsService {

    private final UtilisateurRepo utilisateurRepo;

    public UtilisateurDetailsService(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UtilisateurEntity utilisateur = utilisateurRepo.findByIdentifiant(username)
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé avec le nom : " + username));

        var authorities = Collections.singleton(
                new SimpleGrantedAuthority("ROLE_" + utilisateur.getRole().name()) // Utilise role.name()
        );

        return User.builder()
                .username(utilisateur.getUsername())
                .password(utilisateur.getPassword()) // Assurez-vous que le mot de passe est encodé
                .authorities(authorities)

                .build();
    }
}
