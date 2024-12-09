package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.AdminEcoleDto;
import Gestion.de.stage.Gestion.de.stage.Entity.AdminEcoleEntity;

public class AdminEcoleMapper {

    // Convertir une entité en DTO
    public static AdminEcoleDto toDto(AdminEcoleEntity adminEcoleEntity) {
        if (adminEcoleEntity == null) {
            return null;
        }

        AdminEcoleDto adminEcoleDto = new AdminEcoleDto();
        adminEcoleDto.setId(adminEcoleEntity.getId());
        adminEcoleDto.setNom(adminEcoleEntity.getNom());
        adminEcoleDto.setAdresse(adminEcoleEntity.getAdresse());
        adminEcoleDto.setEmail(adminEcoleEntity.getEmail());
        adminEcoleDto.setTelephone(adminEcoleEntity.getTelephone());
        adminEcoleDto.setRespoStageAssocie(adminEcoleEntity.getRespo_Stage_Associé());
        if (adminEcoleEntity.getUtilisateur() != null) {
            adminEcoleDto.setUtilisateurId(adminEcoleEntity.getUtilisateur().getId());
        }
        return adminEcoleDto;
    }

    // Convertir un DTO en entité
    public static AdminEcoleEntity toEntity(AdminEcoleDto adminEcoleDto) {
        if (adminEcoleDto == null) {
            return null;
        }

        AdminEcoleEntity adminEcoleEntity = new AdminEcoleEntity();
        adminEcoleEntity.setId(adminEcoleDto.getId());
        adminEcoleEntity.setNom(adminEcoleDto.getNom());
        adminEcoleEntity.setAdresse(adminEcoleDto.getAdresse());
        adminEcoleEntity.setEmail(adminEcoleDto.getEmail());
        adminEcoleEntity.setTelephone(adminEcoleDto.getTelephone());
        adminEcoleEntity.setRespo_Stage_Associé(adminEcoleDto.getRespoStageAssocie());
        // La gestion de la relation `UtilisateurEntity` (user_id) est laissée à la couche service si nécessaire
        return adminEcoleEntity;
    }


}
