package tn.aneti.inscription.mapper;

import org.mapstruct.*;
import tn.aneti.inscription.dto.EntrepriseDTO;
import tn.aneti.inscription.entity.Entreprise;

@Mapper(componentModel = "spring")
public interface EntrepriseMapper {
    EntrepriseDTO toDto(Entreprise entity);
    Entreprise toEntity(EntrepriseDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(EntrepriseDTO dto, @MappingTarget Entreprise entity);
}
