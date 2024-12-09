package Gestion.de.stage.Gestion.de.stage.Repository;

import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OffreDeStageRepo extends JpaRepository<OffreDeStageEntity,Long> {

    // Récupérer toutes les offres par le statut (OUVERTE, FERMEE, EN_ATTENTE)
    List<OffreDeStageEntity> findByStatut(OffreDeStageEntity.StatutOffre statut);

    // Récupérer toutes les offres publiées après une certaine date
    List<OffreDeStageEntity> findByDatePublicationAfter(LocalDate date);

    // Récupérer toutes les offres d'une entreprise spécifique
    List<OffreDeStageEntity> findByEntrepriseEntityId(Long entrepriseId);

    // Récupérer toutes les offres contenant un mot-clé dans le titre
    List<OffreDeStageEntity> findByTitreContaining(String keyword);

    // Récupérer toutes les offres contenant un mot-clé dans la description
    List<OffreDeStageEntity> findByDescriptionContaining(String keyword);

    // Récupérer toutes les offres publiées entre deux dates
    List<OffreDeStageEntity> findByDatePublicationBetween(LocalDate startDate, LocalDate endDate);

    // Vérifier l'existence d'une offre avec un titre spécifique dans une entreprise
    boolean existsByTitreAndEntrepriseEntityId(String titre, Long entrepriseId);
}
