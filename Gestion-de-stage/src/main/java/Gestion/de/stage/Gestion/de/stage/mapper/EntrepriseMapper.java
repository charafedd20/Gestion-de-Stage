package Gestion.de.stage.Gestion.de.stage.mapper;

import Gestion.de.stage.Gestion.de.stage.Dto.EntrepriseDto;
import Gestion.de.stage.Gestion.de.stage.Entity.EntrepriseEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;


import java.util.stream.Collectors;

public class EntrepriseMapper {

    // Convertir une entité en DTO
    public static EntrepriseDto toDto(EntrepriseEntity entrepriseEntity) {
        if (entrepriseEntity == null) {
            return null;
        }

        EntrepriseDto entrepriseDto = new EntrepriseDto();
        entrepriseDto.setId(entrepriseEntity.getId());
        entrepriseDto.setNom(entrepriseEntity.getNom());
        entrepriseDto.setAdresse(entrepriseEntity.getAdresse());
        entrepriseDto.setEmail(entrepriseEntity.getEmail());
        entrepriseDto.setTelephone(entrepriseEntity.getTelephone());
        entrepriseDto.setDescription(entrepriseEntity.getDescription());
        entrepriseDto.setType(entrepriseEntity.getType());

        if (entrepriseEntity.getUtilisateur() != null) {
            entrepriseDto.setUtilisateurId(entrepriseEntity.getUtilisateur().getId());
        }

        // Convertir la liste des offres en une liste d'IDs
        if (entrepriseEntity.getOffreDeStageEntityList() != null) {
            entrepriseDto.setOffreDeStageIds(
                    entrepriseEntity.getOffreDeStageEntityList()
                            .stream()
                            .map(OffreDeStageEntity::getId)
                            .collect(Collectors.toList())
            );
        }

        return entrepriseDto;
    }

    // Convertir un DTO en entité
    public static EntrepriseEntity toEntity(EntrepriseDto entrepriseDto) {
        if (entrepriseDto == null) {
            return null;
        }

        EntrepriseEntity entrepriseEntity = new EntrepriseEntity();
        entrepriseEntity.setId(entrepriseDto.getId());
        entrepriseEntity.setNom(entrepriseDto.getNom());
        entrepriseEntity.setAdresse(entrepriseDto.getAdresse());
        entrepriseEntity.setEmail(entrepriseDto.getEmail());
        entrepriseEntity.setTelephone(entrepriseDto.getTelephone());
        entrepriseEntity.setDescription(entrepriseDto.getDescription());
        entrepriseEntity.setType(entrepriseDto.getType());

        // La relation utilisateur et les offres sont généralement gérées par la couche service
        return entrepriseEntity;
    }


}
