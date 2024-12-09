package Gestion.de.stage.Gestion.de.stage.Controller;

import Gestion.de.stage.Gestion.de.stage.Dto.OffreDeStageDto;
import Gestion.de.stage.Gestion.de.stage.Service.OffreDeStageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/offres")
public class OffreDeStageController {

    private final OffreDeStageService offreDeStageService;

    public OffreDeStageController(OffreDeStageService offreDeStageService) {
        this.offreDeStageService = offreDeStageService;
    }

    // **Créer une offre de stage**
    @PostMapping("/create/{entrepriseId}")
    public ResponseEntity<OffreDeStageDto> createOffre(@PathVariable Long entrepriseId, @RequestBody OffreDeStageDto offreDeStageDto) {
        OffreDeStageDto createdOffre = offreDeStageService.createOffre(offreDeStageDto, entrepriseId);
        return ResponseEntity.ok(createdOffre);
    }

    // **Récupérer toutes les offres avec un statut donné**
    @GetMapping("/statut/{statut}")
    public ResponseEntity<List<OffreDeStageDto>> getOffresByStatut(@PathVariable String statut) {
        List<OffreDeStageDto> offres = offreDeStageService.getOffresByStatut(statut);
        return ResponseEntity.ok(offres);
    }

    // **Récupérer toutes les offres publiées après une date**
    @GetMapping("/date-publication")
    public ResponseEntity<List<OffreDeStageDto>> getOffresByDatePublication(@RequestParam("date") String date) {
        LocalDate parsedDate = LocalDate.parse(date);
        List<OffreDeStageDto> offres = offreDeStageService.getOffresByDatePublicationAfter(parsedDate);
        return ResponseEntity.ok(offres);
    }

    // **Récupérer toutes les offres d'une entreprise donnée**
    @GetMapping("/entreprise/{entrepriseId}")
    public ResponseEntity<List<OffreDeStageDto>> getOffresByEntreprise(@PathVariable Long entrepriseId) {
        List<OffreDeStageDto> offres = offreDeStageService.getOffresByEntreprise(entrepriseId);
        return ResponseEntity.ok(offres);
    }

    // **Rechercher des offres par mot-clé**
    @GetMapping("/search")
    public ResponseEntity<List<OffreDeStageDto>> searchOffres(@RequestParam String keyword) {
        List<OffreDeStageDto> offres = offreDeStageService.searchOffres(keyword);
        return ResponseEntity.ok(offres);
    }

    // **Mettre à jour une offre de stage**
    @PutMapping("/{id}")
    public ResponseEntity<OffreDeStageDto> updateOffre(@PathVariable Long id, @RequestBody OffreDeStageDto offreDeStageDto) {
        OffreDeStageDto updatedOffre = offreDeStageService.updateOffre(id, offreDeStageDto);
        return ResponseEntity.ok(updatedOffre);
    }

    // **Supprimer une offre de stage**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffre(@PathVariable Long id) {
        offreDeStageService.deleteOffre(id);
        return ResponseEntity.noContent().build();
    }

}
