package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResCustomField;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.service.dto.ResCustomFieldDTO;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResCustomField} and its DTO {@link ResCustomFieldDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResCustomFieldMapper extends EntityMapper<ResCustomFieldDTO, ResCustomField> {
    @Mapping(target = "reservation", source = "reservation", qualifiedByName = "reservationId")
    ResCustomFieldDTO toDto(ResCustomField s);

    @Named("reservationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReservationDTO toDtoReservationId(Reservation reservation);
}
