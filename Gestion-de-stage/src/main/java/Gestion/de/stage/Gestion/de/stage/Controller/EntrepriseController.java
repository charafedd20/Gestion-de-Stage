package Gestion.de.stage.Gestion.de.stage.Controller;

import Gestion.de.stage.Gestion.de.stage.Dto.EntrepriseDto;
import Gestion.de.stage.Gestion.de.stage.Service.EntrepriseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/entreprises")
public class EntrepriseController {

    private final EntrepriseService entrepriseService;

    public EntrepriseController(EntrepriseService entrepriseService) {
        this.entrepriseService = entrepriseService;
    }

    // **Créer une entreprise**
    @PostMapping
    public ResponseEntity<EntrepriseDto> createEntreprise(@RequestBody EntrepriseDto entrepriseDto) {
        EntrepriseDto createdEntreprise = entrepriseService.createEntreprise(entrepriseDto);
        return ResponseEntity.ok(createdEntreprise);
    }

    // **Récupérer une entreprise par ID**
    @GetMapping("/{id}")
    public ResponseEntity<EntrepriseDto> getEntrepriseById(@PathVariable Long id) {
        EntrepriseDto entreprise = entrepriseService.getEntrepriseById(id);
        return ResponseEntity.ok(entreprise);
    }

    // **Mettre à jour une entreprise**
    @PutMapping("/{id}")
    public ResponseEntity<EntrepriseDto> updateEntreprise(@PathVariable Long id, @RequestBody EntrepriseDto entrepriseDto) {
        EntrepriseDto updatedEntreprise = entrepriseService.updateEntreprise(id, entrepriseDto);
        return ResponseEntity.ok(updatedEntreprise);
    }

    // **Supprimer une entreprise**
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEntreprise(@PathVariable Long id) {
        entrepriseService.deleteEntreprise(id);
        return ResponseEntity.noContent().build();
    }

    // **Récupérer toutes les entreprises**
    @GetMapping
    public ResponseEntity<List<EntrepriseDto>> getAllEntreprises() {
        List<EntrepriseDto> entreprises = entrepriseService.getAllEntreprises();
        return ResponseEntity.ok(entreprises);
    }

    // **Rechercher une entreprise par nom**
    @GetMapping("/search-by-nom")
    public ResponseEntity<EntrepriseDto> getEntrepriseByNom(@RequestParam String nom) {
        EntrepriseDto entreprise = entrepriseService.getEntrepriseByNom(nom);
        return ResponseEntity.ok(entreprise);
    }
}
