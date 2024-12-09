package Gestion.de.stage.Gestion.de.stage.Repository;

import Gestion.de.stage.Gestion.de.stage.Entity.EntrepriseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntrepriseRepo extends JpaRepository<EntrepriseEntity,Long> {

    // Récupérer une entreprise par son email
    EntrepriseEntity findByEmail(String email);

    // Récupérer une entreprise par son nom
    EntrepriseEntity findByNom(String nom);

    // Récupérer toutes les entreprises d'un type donné (par exemple : IT, Finance)
    List<EntrepriseEntity> findByType(String type);

    // Récupérer toutes les entreprises ayant une adresse spécifique
    List<EntrepriseEntity> findByAdresseContaining(String adresse);

    // Vérifier l'existence d'une entreprise par email (utile pour éviter les doublons)
    boolean existsByEmail(String email);

}
