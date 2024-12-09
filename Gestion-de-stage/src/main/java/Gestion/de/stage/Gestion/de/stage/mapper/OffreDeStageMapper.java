package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.OffreDeStageDto;
import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;

public class OffreDeStageMapper {

    // Convertir une entité en DTO
    public static OffreDeStageDto toDto(OffreDeStageEntity offreDeStageEntity) {
        if (offreDeStageEntity == null) {
            return null;
        }

        OffreDeStageDto offreDeStageDto = new OffreDeStageDto();
        offreDeStageDto.setId(offreDeStageEntity.getId());
        offreDeStageDto.setTitre(offreDeStageEntity.getTitre());
        offreDeStageDto.setDescription(offreDeStageEntity.getDescription());
        offreDeStageDto.setDatePublication(offreDeStageEntity.getDatePublication());
        offreDeStageDto.setDuree(offreDeStageEntity.getDuree());
        offreDeStageDto.setCompetencesRequises(offreDeStageEntity.getCompetencesRequises());
        // Conversion de l'énumération
        offreDeStageDto.setStatut(OffreDeStageDto.StatutOffre.valueOf(offreDeStageEntity.getStatut().name()));
        if (offreDeStageEntity.getEntrepriseEntity() != null) {
            offreDeStageDto.setEntrepriseId(offreDeStageEntity.getEntrepriseEntity().getId());
        }
        return offreDeStageDto;
    }

    // Convertir un DTO en entité
    public static OffreDeStageEntity toEntity(OffreDeStageDto offreDeStageDto) {
        if (offreDeStageDto == null) {
            return null;
        }

        OffreDeStageEntity offreDeStageEntity = new OffreDeStageEntity();
        offreDeStageEntity.setId(offreDeStageDto.getId());
        offreDeStageEntity.setTitre(offreDeStageDto.getTitre());
        offreDeStageEntity.setDescription(offreDeStageDto.getDescription());
        offreDeStageEntity.setDatePublication(offreDeStageDto.getDatePublication());
        offreDeStageEntity.setDuree(offreDeStageDto.getDuree());
        offreDeStageEntity.setCompetencesRequises(offreDeStageDto.getCompetencesRequises());
        // Conversion de l'énumération
        offreDeStageEntity.setStatut(OffreDeStageEntity.StatutOffre.valueOf(offreDeStageDto.getStatut().name()));
        // La gestion de la relation `EntrepriseEntity` est laissée à la couche service si nécessaire
        return offreDeStageEntity;
    }
}
