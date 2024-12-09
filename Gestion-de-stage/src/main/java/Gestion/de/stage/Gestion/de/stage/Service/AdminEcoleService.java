package Gestion.de.stage.Gestion.de.stage.Service;

import Gestion.de.stage.Gestion.de.stage.Dto.AdminEcoleDto;

import java.util.List;

public interface AdminEcoleService {


    // Gestion des administrateurs d'école
    AdminEcoleDto createAdminEcole(AdminEcoleDto adminEcoleDto);

    AdminEcoleDto updateAdminEcole(Long id, AdminEcoleDto adminEcoleDto);

    void deleteAdminEcole(Long id);

    AdminEcoleDto getAdminEcoleById(Long id);

    List<AdminEcoleDto> getAllAdminEcoles();
}
