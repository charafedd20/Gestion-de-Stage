package Gestion.de.stage.Gestion.de.stage.Dto;

import lombok.Data;

import java.util.List;

@Data
public class EntrepriseDto {

    private long id;
    private String nom;
    private String adresse;
    private String email;
    private String telephone;
    private String description;
    private String type;
    private Long utilisateurId; // ID de l'utilisateur associé
    private List<Long> offreDeStageIds; // possibilité d'Inclure les DTO complets des offres de stage :

}
