package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.CandidaturDto;

import java.util.List;

public interface CandidatureService {


    // Créer une candidature
    CandidaturDto createCandidature(CandidaturDto candidatureDto);

    // Récupérer une candidature par ID
    CandidaturDto getCandidatureById(Long id);

    // Mettre à jour une candidature
    CandidaturDto updateCandidature(Long id, CandidaturDto candidatureDto);

    // Supprimer une candidature par ID
    void deleteCandidature(Long id);

    // Récupérer toutes les candidatures pour une offre de stage
    List<CandidaturDto> getCandidaturesByOffre(Long offreDeStageId);

    // Récupérer toutes les candidatures d'un étudiant
    List<CandidaturDto> getCandidaturesByEtudiant(Long etudiantId);

    // Rechercher des candidatures par mot-clé dans la motivation
    List<CandidaturDto> searchCandidaturesByMotivation(String keyword);
}
