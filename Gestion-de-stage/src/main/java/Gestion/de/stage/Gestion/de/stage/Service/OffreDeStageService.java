package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.OffreDeStageDto;

import java.time.LocalDate;
import java.util.List;

public interface OffreDeStageService {

    // Créer une offre de stage
    OffreDeStageDto createOffre(OffreDeStageDto offreDeStageDto, Long entrepriseId);

    // Récupérer toutes les offres par le statut
    List<OffreDeStageDto> getOffresByStatut(String statut);

    // Récupérer toutes les offres publiées après une certaine date
    List<OffreDeStageDto> getOffresByDatePublicationAfter(LocalDate date);

    // Récupérer toutes les offres d'une entreprise spécifique
    List<OffreDeStageDto> getOffresByEntreprise(Long entrepriseId);

    // Rechercher des offres par mot-clé dans le titre ou la description
    List<OffreDeStageDto> searchOffres(String keyword);

    // Mettre à jour une offre par ID
    OffreDeStageDto updateOffre(Long id, OffreDeStageDto offreDeStageDto);

    // Supprimer une offre par ID
    void deleteOffre(Long id);
}
