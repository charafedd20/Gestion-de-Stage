package Gestion.de.stage.Gestion.de.stage.Repository;

import Gestion.de.stage.Gestion.de.stage.Entity.EtudiantEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EtudiantRepo extends JpaRepository<EtudiantEntity,Long> {

    // Trouver un étudiant par email
    Optional<EtudiantEntity> findByEmail(String email);

    // Trouver un étudiant par nom
    List<EtudiantEntity> findByNom(String nom);

    // Trouver tous les étudiants d'une filière spécifique
    List<EtudiantEntity> findByFiliere(String filiere);

    // Trouver tous les étudiants d'une école spécifique
    List<EtudiantEntity> findByEcole(String ecole);

    // Trouver tous les étudiants par année d'étude
    List<EtudiantEntity> findByAnneeEtude(String anneeEtude);

    // Trouver un étudiant par utilisateur (relation avec UtilisateurEntity)
    Optional<EtudiantEntity> findByUtilisateurId(Long utilisateurId);

    // Vérifier l'existence d'un étudiant avec un email donné
    boolean existsByEmail(String email);

    // Trouver tous les étudiants avec un statut spécifique (par exemple : "Actif", "Diplômé")
    List<EtudiantEntity> findByStatut(String statut);

    // Supprimer un étudiant par son email
    void deleteByEmail(String email);
}
