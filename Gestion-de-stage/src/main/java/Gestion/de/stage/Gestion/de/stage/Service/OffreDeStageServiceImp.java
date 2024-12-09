package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.OffreDeStageDto;
import Gestion.de.stage.Gestion.de.stage.Entity.EntrepriseEntity;
import Gestion.de.stage.Gestion.de.stage.Entity.OffreDeStageEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.EntrepriseRepo;
import Gestion.de.stage.Gestion.de.stage.Repository.OffreDeStageRepo;
import Gestion.de.stage.Gestion.de.stage.mapper.OffreDeStageMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class OffreDeStageServiceImp implements OffreDeStageService{


    private final OffreDeStageRepo offreDeStageRepo;

    private final EntrepriseRepo entrepriseRepo;


    public OffreDeStageServiceImp(OffreDeStageRepo offreDeStageRepo, EntrepriseRepo entrepriseRepo) {
        this.offreDeStageRepo = offreDeStageRepo;
        this.entrepriseRepo = entrepriseRepo;
    }

    @Override
    public OffreDeStageDto createOffre(OffreDeStageDto offreDeStageDto, Long entrepriseId) {
        // Vérifier si l'entreprise existe
        EntrepriseEntity entreprise = entrepriseRepo.findById(entrepriseId)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée pour l'ID : " + entrepriseId));

        // Convertir le DTO en entité et associer l'entreprise
        OffreDeStageEntity offreDeStageEntity = OffreDeStageMapper.toEntity(offreDeStageDto);
        offreDeStageEntity.setEntrepriseEntity(entreprise);

        // Sauvegarder l'entité et retourner le DTO
        OffreDeStageEntity savedEntity = offreDeStageRepo.save(offreDeStageEntity);
        return OffreDeStageMapper.toDto(savedEntity);
    }

    @Override
    public List<OffreDeStageDto> getOffresByStatut(String statut) {
        return offreDeStageRepo.findByStatut(OffreDeStageEntity.StatutOffre.valueOf(statut.toUpperCase()))
                .stream()
                .map(OffreDeStageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OffreDeStageDto> getOffresByDatePublicationAfter(LocalDate date) {
        return offreDeStageRepo.findByDatePublicationAfter(date)
                .stream()
                .map(OffreDeStageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OffreDeStageDto> getOffresByEntreprise(Long entrepriseId) {
        // Vérifier si l'entreprise existe
        EntrepriseEntity entreprise = entrepriseRepo.findById(entrepriseId)
                .orElseThrow(() -> new RuntimeException("Entreprise non trouvée pour l'ID : " + entrepriseId));

        return offreDeStageRepo.findByEntrepriseEntityId(entreprise.getId())
                .stream()
                .map(OffreDeStageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<OffreDeStageDto> searchOffres(String keyword) {
        // Récupérer les offres par titre et description
        List<OffreDeStageEntity> byTitle = offreDeStageRepo.findByTitreContaining(keyword);
        List<OffreDeStageEntity> byDescription = offreDeStageRepo.findByDescriptionContaining(keyword);

        // Combiner les résultats et supprimer les doublons
        return byTitle.stream()
                .filter(byDescription::contains)
                .distinct()
                .map(OffreDeStageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public OffreDeStageDto updateOffre(Long id, OffreDeStageDto offreDeStageDto) {
        // Vérifier si l'offre existe
        OffreDeStageEntity existingEntity = offreDeStageRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Offre non trouvée pour l'ID : " + id));

        // Mettre à jour les champs de l'offre
        existingEntity.setTitre(offreDeStageDto.getTitre());
        existingEntity.setDescription(offreDeStageDto.getDescription());
        existingEntity.setDatePublication(offreDeStageDto.getDatePublication());
        existingEntity.setDuree(offreDeStageDto.getDuree());
        existingEntity.setCompetencesRequises(offreDeStageDto.getCompetencesRequises());
        existingEntity.setStatut(OffreDeStageEntity.StatutOffre.valueOf(offreDeStageDto.getStatut().name()));

        // Sauvegarder les modifications
        OffreDeStageEntity updatedEntity = offreDeStageRepo.save(existingEntity);
        return OffreDeStageMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteOffre(Long id) {
        // Vérifier si l'offre existe avant suppression
        if (!offreDeStageRepo.existsById(id)) {
            throw new RuntimeException("Offre non trouvée pour l'ID : " + id);
        }
        offreDeStageRepo.deleteById(id);
    }
}
