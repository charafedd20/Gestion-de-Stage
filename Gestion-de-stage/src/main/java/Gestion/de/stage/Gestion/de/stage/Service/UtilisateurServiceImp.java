package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.UserDto;
import Gestion.de.stage.Gestion.de.stage.Entity.UtilisateurEntity;
import Gestion.de.stage.Gestion.de.stage.Repository.UtilisateurRepo;
import Gestion.de.stage.Gestion.de.stage.mapper.UtilisateurMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UtilisateurServiceImp implements UtilisateurService{

    private final UtilisateurRepo utilisateurRepo;


    @Autowired
    public UtilisateurServiceImp(UtilisateurRepo utilisateurRepo) {
        this.utilisateurRepo = utilisateurRepo;

    }

    @Override
    public UserDto createUser(UserDto userDto) {
        if (utilisateurRepo.existsByIdentifiant(userDto.getIdentifiant())) {
            throw new IllegalArgumentException("Un utilisateur avec cet identifiant existe déjà.");
        }

        UtilisateurEntity utilisateurEntity = UtilisateurMapper.toEntity(userDto);

        // Hacher le mot de passe avant de sauvegarder
        utilisateurEntity.setPassword(userDto.getPassword());

        UtilisateurEntity savedEntity = utilisateurRepo.save(utilisateurEntity);
        return UtilisateurMapper.toDto(savedEntity);
    }

    @Override
    public UserDto getUserById(Long id) {
        UtilisateurEntity utilisateurEntity = utilisateurRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id));
        return UtilisateurMapper.toDto(utilisateurEntity);
    }

    @Override
    public UserDto getUserByIdentifiant(String identifiant) {
        UtilisateurEntity utilisateurEntity = utilisateurRepo.findByIdentifiant(identifiant)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'identifiant : " + identifiant));
        return UtilisateurMapper.toDto(utilisateurEntity);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        UtilisateurEntity existingEntity = utilisateurRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id));

        // Mettre à jour les champs nécessaires
        existingEntity.setIdentifiant(userDto.getIdentifiant());
        existingEntity.setRole(UtilisateurEntity.Role.valueOf(userDto.getRole()));

        // Mettre à jour le mot de passe si présent
        if (userDto.getPassword() != null && !userDto.getPassword().isEmpty()) {
            existingEntity.setPassword(userDto.getPassword());
        }

        UtilisateurEntity updatedEntity = utilisateurRepo.save(existingEntity);
        return UtilisateurMapper.toDto(updatedEntity);
    }

    @Override
    public void deleteUser(Long id) {
        if (!utilisateurRepo.existsById(id)) {
            throw new IllegalArgumentException("Utilisateur non trouvé avec l'ID : " + id);
        }
        utilisateurRepo.deleteById(id);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return utilisateurRepo.findAll()
                .stream()
                .map(UtilisateurMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getUsersByRole(String role) {
        return utilisateurRepo.findByRole(UtilisateurEntity.Role.valueOf(role.toUpperCase()))
                .stream()
                .map(UtilisateurMapper::toDto)
                .collect(Collectors.toList());
    }
}
