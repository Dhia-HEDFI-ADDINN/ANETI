package tn.aneti.inscription.mapper;

import org.mapstruct.*;
import tn.aneti.inscription.dto.ChercheurEmploiDTO;
import tn.aneti.inscription.entity.ChercheurEmploi;

@Mapper(componentModel = "spring")
public interface ChercheurEmploiMapper {
    ChercheurEmploiDTO toDto(ChercheurEmploi entity);
    ChercheurEmploi toEntity(ChercheurEmploiDTO dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ChercheurEmploiDTO dto, @MappingTarget ChercheurEmploi entity);
}
