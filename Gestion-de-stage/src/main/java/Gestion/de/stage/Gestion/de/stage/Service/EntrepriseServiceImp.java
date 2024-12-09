package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.EntrepriseDto;
import Gestion.de.stage.Gestion.de.stage.Entity.EntrepriseEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.UtilisateurEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.EntrepriseRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.OffreDeStageRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.UtilisateurRepo;
import Gestion.de.stage.Gestion.de.stage.mapper.EntrepriseMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EntrepriseServiceImp implements EntrepriseService{

    private final EntrepriseRepo entrepriseRepo;
    private final UtilisateurRepo utilisateurRepo;
    private final OffreDeStageRepo offreDeStageRepo;

    public EntrepriseServiceImp(EntrepriseRepo entrepriseRepo, UtilisateurRepo utilisateurRepo, OffreDeStageRepo offreDeStageRepo) {
        this.entrepriseRepo = entrepriseRepo;
        this.utilisateurRepo = utilisateurRepo;
        this.offreDeStageRepo = offreDeStageRepo;
    }

    @Override
    public EntrepriseDto createEntreprise(EntrepriseDto entrepriseDto) {
        // Vérifier si l'email de l'entreprise existe déjà
        if (entrepriseRepo.existsByEmail(entrepriseDto.getEmail())) {
            throw new IllegalArgumentException("Une entreprise avec cet email existe déjà.");
        }

        // Convertir le DTO en entité
        EntrepriseEntity entrepriseEntity = EntrepriseMapper.toEntity(entrepriseDto);

        // Gérer l'utilisateur associé
        if (entrepriseDto.getUtilisateurId() != null) {
            UtilisateurEntity utilisateur = utilisateurRepo.findById(entrepriseDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + entrepriseDto.getUtilisateurId()));
            entrepriseEntity.setUtilisateur(utilisateur);
        }

        // Gérer les offres de stage associées
        if (entrepriseDto.getOffreDeStageIds() != null && !entrepriseDto.getOffreDeStageIds().isEmpty()) {
            List<OffreDeStageEntity> offres = entrepriseDto.getOffreDeStageIds().stream()
                    .map(id -> offreDeStageRepo.findById(id)
                            .orElseThrow(() -> new RuntimeException("Offre de stage non trouvée pour l'ID : " + id)))
                    .collect(Collectors.toList());
            entrepriseEntity.setOffreDeStageEntityList(offres);
        }

        // Sauvegarder l'entreprise
        EntrepriseEntity savedEntity = entrepriseRepo.save(entrepriseEntity);
        return EntrepriseMapper.toDto(savedEntity);
    }

    @Override
    public EntrepriseDto getEntrepriseById(Long id) {
        EntrepriseEntity entrepriseEntity = entrepriseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec l'ID : " + id));
        return EntrepriseMapper.toDto(entrepriseEntity);
    }

    @Override
    public EntrepriseDto updateEntreprise(Long id, EntrepriseDto entrepriseDto) {
        EntrepriseEntity existingEntity = entrepriseRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée avec l'ID : " + id));

        // Mise à jour des champs
        existingEntity.setNom(entrepriseDto.getNom());
        existingEntity.setAdresse(entrepriseDto.getAdresse());
        existingEntity.setEmail(entrepriseDto.getEmail());
        existingEntity.setTelephone(entrepriseDto.getTelephone());
        existingEntity.setDescription(entrepriseDto.getDescription());
        existingEntity.setType(entrepriseDto.getType());

        // Mise à jour de l'utilisateur associé si nécessaire
        if (entrepriseDto.getUtilisateurId() != null) {
            UtilisateurEntity utilisateur = utilisateurRepo.findById(entrepriseDto.getUtilisateurId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé avec l'ID : " + entrepriseDto.getUtilisateurId()));
            existingEntity.setUtilisateur(utilisateur);
        }

        // Mise à jour des offres de stage associées si nécessaire
        if (entrepriseDto.getOffreDeStageIds() != null && !entrepriseDto.getOffreDeStageIds().isEmpty()) {
            List<OffreDeStageEntity> offres = entrepriseDto.getOffreDeStageIds().stream()
                    .map(offreId -> offreDeStageRepo.findById(offreId)
                            .orElseThrow(() -> new RuntimeException("Offre de stage non trouvée avec l'ID : " + offreId)))
                    .collect(Collectors.toList());
            existingEntity.setOffreDeStageEntityList(offres);
        }

        // Sauvegarder l'entreprise mise à jour
        EntrepriseEntity updatedEntity = entrepriseRepo.save(existingEntity);
        return EntrepriseMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteEntreprise(Long id) {
        if (!entrepriseRepo.existsById(id)) {
            throw new RuntimeException("Entreprise non trouvée avec l'ID : " + id);
        }
        entrepriseRepo.deleteById(id);
    }

    @Override
    public List<EntrepriseDto> getAllEntreprises() {
        List<EntrepriseEntity> entrepriseEntities = entrepriseRepo.findAll();
        return entrepriseEntities.stream()
                .map(EntrepriseMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public EntrepriseDto getEntrepriseByNom(String nom) {
        EntrepriseEntity entrepriseEntity = entrepriseRepo.findByNom(nom);
        if (entrepriseEntity == null) {
            throw new RuntimeException("Entreprise non trouvée avec le nom : " + nom);
        }
        return EntrepriseMapper.toDto(entrepriseEntity);
    }
}
