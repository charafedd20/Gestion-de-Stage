package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.CandidaturDto;
import Gestion.de.stage.Gestion.de.stage.Entity.CandidatureEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.EtudiantEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;

public class CandidatureMapper {

    // Convertir une entité en DTO
    public static CandidaturDto toDto(CandidatureEntity candidatureEntity) {
        if (candidatureEntity == null) {
            return null;
        }

        CandidaturDto candidatureDto = new CandidaturDto();
        candidatureDto.setId(candidatureEntity.getId());
        candidatureDto.setDateCandidature(candidatureEntity.getDateCandidature());
        candidatureDto.setCvPath(candidatureEntity.getCvPath());
        candidatureDto.setMotivation(candidatureEntity.getMotivation());

        // Ajouter l'ID de l'offre de stage associée
        if (candidatureEntity.getOffreDeStage() != null) {
            candidatureDto.setOffreDeStageId(candidatureEntity.getOffreDeStage().getId());
        }

        // Ajouter l'ID de l'étudiant associé
        if (candidatureEntity.getEtudiantEntity() != null) {
            candidatureDto.setEtudiantId(candidatureEntity.getEtudiantEntity().getId());
        }

        return candidatureDto;
    }

    // Convertir un DTO en entité
    public static CandidatureEntity toEntity(CandidaturDto candidatureDto, OffreDeStageEntity offreDeStageEntity, EtudiantEntity etudiantEntity) {
        if (candidatureDto == null) {
            return null;
        }

        CandidatureEntity candidatureEntity = new CandidatureEntity();
        candidatureEntity.setId(candidatureDto.getId());
        candidatureEntity.setDateCandidature(candidatureDto.getDateCandidature());
        candidatureEntity.setCvPath(candidatureDto.getCvPath());
        candidatureEntity.setMotivation(candidatureDto.getMotivation());

        // Les relations avec OffreDeStageEntity et EtudiantEntity sont gérées au niveau du Service
        return candidatureEntity;
    }

}
