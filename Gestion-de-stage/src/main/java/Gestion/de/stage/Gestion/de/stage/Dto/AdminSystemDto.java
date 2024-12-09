package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

@Data
public class AdminSystemDto {

    private Long id;
    private String nom;
    private String email;
    private Long utilisateurId; // ID de l'utilisateur associé
}
