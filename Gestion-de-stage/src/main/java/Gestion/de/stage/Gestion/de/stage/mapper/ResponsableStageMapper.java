package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.ResponsableStageDto;
import Gestion.de.stage.Gestion.de.stage.Entity.ResponsableDeStageEntity;

public class ResponsableStageMapper {

    // Convertir une entité en DTO
    public static ResponsableStageDto toDto(ResponsableDeStageEntity entity) {
        if (entity == null) {
            return null;
        }

        ResponsableStageDto dto = new ResponsableStageDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setEmail(entity.getEmail());
        dto.setTelephone(entity.getTelephone());

        // Ajouter les IDs des relations
        if (entity.getUtilisateur() != null) {
            dto.setUtilisateurId(entity.getUtilisateur().getId());
        }
        if (entity.getEcole() != null) {
            dto.setEcoleId(entity.getEcole().getId());
        }

        return dto;
    }

    // Convertir un DTO en entité (sans gérer les relations)
    public static ResponsableDeStageEntity toEntity(ResponsableStageDto dto) {
        if (dto == null) {
            return null;
        }

        ResponsableDeStageEntity entity = new ResponsableDeStageEntity();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setEmail(dto.getEmail());
        entity.setTelephone(dto.getTelephone());

        // Les relations utilisateur et école sont gérées dans le service
        return entity;
    }
}
