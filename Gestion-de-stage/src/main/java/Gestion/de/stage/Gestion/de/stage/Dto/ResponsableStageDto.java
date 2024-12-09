package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

@Data
public class ResponsableStageDto {

    private long id;
    private String nom;
    private String email;
    private String telephone;
    private Long utilisateurId;  // ID de l'utilisateur associé
    private Long ecoleId;        // ID de l'école associée

}
