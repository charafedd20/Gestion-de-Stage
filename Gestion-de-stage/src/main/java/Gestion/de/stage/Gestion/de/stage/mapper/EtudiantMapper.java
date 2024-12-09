package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.EtudiantDto;
import Gestion.de.stage.Gestion.de.stage.Entity.EtudiantEntity;

public class EtudiantMapper {

    // Convertir une entité en DTO
    public static EtudiantDto toDto(EtudiantEntity etudiantEntity) {
        if (etudiantEntity == null) {
            return null;
        }

        EtudiantDto etudiantDto = new EtudiantDto();
        etudiantDto.setId(etudiantEntity.getId());
        etudiantDto.setNom(etudiantEntity.getNom());
        etudiantDto.setPrenom(etudiantEntity.getPrenom());
        etudiantDto.setEmail(etudiantEntity.getEmail());
        etudiantDto.setTelephone(etudiantEntity.getTelephone());
        etudiantDto.setStatut(etudiantEntity.getStatut());
        etudiantDto.setFiliere(etudiantEntity.getFiliere());
        etudiantDto.setAnneeEtude(etudiantEntity.getAnneeEtude());
        etudiantDto.setEcole(etudiantEntity.getEcole());
        if (etudiantEntity.getUtilisateur() != null) {
            etudiantDto.setUtilisateurId(etudiantEntity.getUtilisateur().getId());
        }
        return etudiantDto;
    }

    // Convertir un DTO en entité
    public static EtudiantEntity toEntity(EtudiantDto etudiantDto) {
        if (etudiantDto == null) {
            return null;
        }

        EtudiantEntity etudiantEntity = new EtudiantEntity();
        etudiantEntity.setId(etudiantDto.getId());
        etudiantEntity.setNom(etudiantDto.getNom());
        etudiantEntity.setPrenom(etudiantDto.getPrenom());
        etudiantEntity.setEmail(etudiantDto.getEmail());
        etudiantEntity.setTelephone(etudiantDto.getTelephone());
        etudiantEntity.setStatut(etudiantDto.getStatut());
        etudiantEntity.setFiliere(etudiantDto.getFiliere());
        etudiantEntity.setAnneeEtude(etudiantDto.getAnneeEtude());
        etudiantEntity.setEcole(etudiantDto.getEcole());
        // Note : La gestion de l'entité Utilisateur (relation OneToOne) est laissée à la couche service si nécessaire
        return etudiantEntity;
    }

}
