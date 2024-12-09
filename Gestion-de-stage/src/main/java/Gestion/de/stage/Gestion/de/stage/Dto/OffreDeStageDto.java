package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class OffreDeStageDto {
    private Long id;
    private String titre;
    private String description;
    private LocalDate datePublication;
    private int duree; // Durée en mois
    private String competencesRequises;
    private StatutOffre statut;
    private Long entrepriseId; // ID de l'entreprise associée (clé étrangère)

    public enum StatutOffre {
        OUVERTE,
        FERMEE,
        EN_ATTENTE
    }

}
