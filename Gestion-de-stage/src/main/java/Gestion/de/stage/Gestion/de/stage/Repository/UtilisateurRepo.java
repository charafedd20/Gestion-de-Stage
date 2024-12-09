package Gestion.de.stage.Gestion.de.stage.Repository;

import Gestion.de.stage.Gestion.de.stage.Entity.UtilisateurEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisateurRepo extends JpaRepository<UtilisateurEntity,Long> {





    // Trouver un utilisateur par son identifiant (utile pour l'authentification)
    Optional<UtilisateurEntity> findByIdentifiant(String identifiant);

    // Trouver tous les utilisateurs ayant un rôle spécifique
    List<UtilisateurEntity> findByRole(UtilisateurEntity.Role role);

    // Vérifier si un utilisateur existe avec un identifiant donné
    boolean existsByIdentifiant(String identifiant);

    // Supprimer un utilisateur par son identifiant
    void deleteByIdentifiant(String identifiant);


}
