package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.AdminSystemDto;
import Gestion.de.stage.Gestion.de.stage.Entity.AdminSystemEntiy;

public class AdminSystemMapper {

    // Convertir une entité en DTO
    public static AdminSystemDto toDto(AdminSystemEntiy entity) {
        if (entity == null) {
            return null;
        }

        AdminSystemDto dto = new AdminSystemDto();
        dto.setId(entity.getId());
        dto.setNom(entity.getNom());
        dto.setEmail(entity.getEmail());

        // Ajouter l'ID de l'utilisateur associé
        if (entity.getUtilisateur() != null) {
            dto.setUtilisateurId(entity.getUtilisateur().getId());
        }

        return dto;
    }

    // Convertir un DTO en entité (sans gérer la relation utilisateur)
    public static AdminSystemEntiy toEntity(AdminSystemDto dto) {
        if (dto == null) {
            return null;
        }

        AdminSystemEntiy entity = new AdminSystemEntiy();
        entity.setId(dto.getId());
        entity.setNom(dto.getNom());
        entity.setEmail(dto.getEmail());

        // La relation avec l'utilisateur sera gérée dans la couche service
        return entity;
    }
}
