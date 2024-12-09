package Gestion.de.stage.Gestion.de.stage.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "offres_de_stage")
public class OffreDeStageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String titre;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private LocalDate datePublication;

    @Column(nullable = false)
    private int duree; // Durée en mois

    @Column(nullable = false)
    private String competencesRequises;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatutOffre statut;

    @ManyToOne
    @JoinColumn(name="entreprise_id",referencedColumnName = "id",nullable = false)
    private EntrepriseEntity entrepriseEntity;

    public enum StatutOffre{
        OUVERTE,
        FERMEE,
        EN_ATTENTE
    }
}
