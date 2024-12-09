package Gestion.de.stage.Gestion.de.stage.Repository;

import Gestion.de.stage.Gestion.de.stage.Entity.CandidatureEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidatureRepo extends JpaRepository<CandidatureEntity,Long> {

    // Récupérer toutes les candidatures pour une offre de stage donnée
    List<CandidatureEntity> findByOffreDeStageId(Long offreDeStageId);

    // Récupérer toutes les candidatures soumises par un étudiant donné
    List<CandidatureEntity> findByEtudiantEntityId(Long etudiantId);

    // Vérifier l'existence d'une candidature pour une offre et un étudiant donnés
    boolean existsByOffreDeStageIdAndEtudiantEntityId(Long offreDeStageId, Long etudiantId);


    // Récupérer toutes les candidatures contenant un mot-clé dans la motivation
    List<CandidatureEntity> findByMotivationContaining(String keyword);

}
