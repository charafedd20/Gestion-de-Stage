package Gestion.de.stage.Gestion.de.stage.Repository;

import Gestion.de.stage.Gestion.de.stage.Entity.AdminEcoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AdminEcolRepo extends JpaRepository<AdminEcoleEntity,Long> {

    // Trouver un administrateur d'école par email
    Optional<AdminEcoleEntity> findByEmail(String email);

    // Trouver un administrateur d'école par nom
    Optional<AdminEcoleEntity> findByNom(String nom);

    // Trouver tous les administrateurs associés à une adresse spécifique
    List<AdminEcoleEntity> findByAdresseContaining(String adresse);

    // Trouver un administrateur par utilisateur (relation avec UtilisateurEntity)
    Optional<AdminEcoleEntity> findByUtilisateurId(Long utilisateurId);

    // Vérifier l'existence d'un administrateur avec un email donné
    boolean existsByEmail(String email);

    // Supprimer un administrateur par email
    void deleteByEmail(String email);
}
