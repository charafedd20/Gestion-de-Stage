package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

@Data
public class AdminEcoleDto {

    private long id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String respoStageAssocie; // Respect de la convention camelCase
    private Long utilisateurId; // ID de l'utilisateur associé
}
