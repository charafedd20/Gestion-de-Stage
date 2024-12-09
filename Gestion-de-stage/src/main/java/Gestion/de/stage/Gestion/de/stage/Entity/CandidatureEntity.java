package Gestion.de.stage.Gestion.de.stage.Entity;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "candidature")
public class CandidatureEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name="date_candidature",nullable = false)
    private LocalDate dateCandidature;

    @ManyToOne
    @JoinColumn(name = "offre_de_stage_id",nullable = false)
    private OffreDeStageEntity offreDeStage;

    @Column(name = "cv_path", nullable = false)
    private String cvPath;

    @ManyToOne
    @JoinColumn(name = "etudiant_id",referencedColumnName = "id")
    private EtudiantEntity etudiantEntity;

    @Column(nullable = false)
    private String motivation;
}
