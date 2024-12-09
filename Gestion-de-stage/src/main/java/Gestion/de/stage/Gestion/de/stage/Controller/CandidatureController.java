package Gestion.de.stage.Gestion.de.stage.Controller;

import Gestion.de.stage.Gestion.de.stage.Dto.CandidaturDto;
import Gestion.de.stage.Gestion.de.stage.Service.CandidatureService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/candidatures")
public class CandidatureController {

    private final CandidatureService candidatureService;

    public CandidatureController(CandidatureService candidatureService) {
        this.candidatureService = candidatureService;
    }

    // **Créer une candidature**
    @PostMapping
    public ResponseEntity<CandidaturDto> createCandidature(@RequestBody CandidaturDto candidatureDto) {
        CandidaturDto createdCandidature = candidatureService.createCandidature(candidatureDto);
        return ResponseEntity.ok(createdCandidature);
    }

    // **Récupérer une candidature par ID**
    @GetMapping("/{id}")
    public ResponseEntity<CandidaturDto> getCandidatureById(@PathVariable Long id) {
        CandidaturDto candidature = candidatureService.getCandidatureById(id);
        return ResponseEntity.ok(candidature);
    }

    // **Mettre à jour une candidature**
    @PutMapping("/{id}")
    public ResponseEntity<CandidaturDto> updateCandidature(@PathVariable Long id, @RequestBody CandidaturDto candidatureDto) {
        CandidaturDto updatedCandidature = candidatureService.updateCandidature(id, candidatureDto);
        return ResponseEntity.ok(updatedCandidature);
    }

    // **Supprimer une candidature**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCandidature(@PathVariable Long id) {
        candidatureService.deleteCandidature(id);
        return ResponseEntity.noContent().build();
    }

    // **Récupérer toutes les candidatures pour une offre de stage**
    @GetMapping("/offre/{offreDeStageId}")
    public ResponseEntity<List<CandidaturDto>> getCandidaturesByOffre(@PathVariable Long offreDeStageId) {
        List<CandidaturDto> candidatures = candidatureService.getCandidaturesByOffre(offreDeStageId);
        return ResponseEntity.ok(candidatures);
    }

    // **Récupérer toutes les candidatures d'un étudiant**
    @GetMapping("/etudiant/{etudiantId}")
    public ResponseEntity<List<CandidaturDto>> getCandidaturesByEtudiant(@PathVariable Long etudiantId) {
        List<CandidaturDto> candidatures = candidatureService.getCandidaturesByEtudiant(etudiantId);
        return ResponseEntity.ok(candidatures);
    }

    // **Rechercher des candidatures par mot-clé dans la motivation**
    @GetMapping("/search")
    public ResponseEntity<List<CandidaturDto>> searchCandidaturesByMotivation(@RequestParam String keyword) {
        List<CandidaturDto> candidatures = candidatureService.searchCandidaturesByMotivation(keyword);
        return ResponseEntity.ok(candidatures);
    }

}
