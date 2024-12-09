package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.EtudiantDto;

import java.util.List;

public interface EtudiantService{

    // Créer un nouvel étudiant
    EtudiantDto createEtudiant(EtudiantDto etudiantDto);

    // Mettre à jour un étudiant existant
    EtudiantDto updateEtudiant(Long id, EtudiantDto etudiantDto);

    // Supprimer un étudiant par son ID
    void deleteEtudiant(Long id);

    // Trouver un étudiant par ID
    EtudiantDto getEtudiantById(Long id);

    // Trouver un étudiant par email
    EtudiantDto getEtudiantByEmail(String email);

    // Obtenir tous les étudiants
    List<EtudiantDto> getAllEtudiants();

    // Trouver des étudiants par filière
    List<EtudiantDto> getEtudiantsByFiliere(String filiere);

    // Trouver des étudiants par école
    List<EtudiantDto> getEtudiantsByEcole(String ecole);

    // Trouver des étudiants par année d'étude
    List<EtudiantDto> getEtudiantsByAnneeEtude(String anneeEtude);

    // Vérifier l'existence d'un étudiant avec un email donné
    boolean existsByEmail(String email);
}
