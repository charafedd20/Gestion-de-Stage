package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CandidaturDto {

    private long id;
    private LocalDate dateCandidature;
    private Long offreDeStageId;  // ID de l'offre de stage associée
    private String cvPath;
    private Long etudiantId;      // ID de l'étudiant associé
    private String motivation;

}
