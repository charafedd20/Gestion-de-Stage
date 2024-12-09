package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.EntrepriseDto;

import java.util.List;

public interface EntrepriseService {


    // Créer une entreprise
    EntrepriseDto createEntreprise(EntrepriseDto entrepriseDto);

    // Récupérer une entreprise par son ID
    EntrepriseDto getEntrepriseById(Long id);

    // Mettre à jour une entreprise par son ID
    EntrepriseDto updateEntreprise(Long id, EntrepriseDto entrepriseDto);

    // Supprimer une entreprise par ID
    void deleteEntreprise(Long id);

    // Récupérer toutes les entreprises
    List<EntrepriseDto> getAllEntreprises();

    // Récupérer une entreprise par son nom
    EntrepriseDto getEntrepriseByNom(String nom);
}
