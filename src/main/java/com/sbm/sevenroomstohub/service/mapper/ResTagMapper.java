package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResTag;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.service.dto.ResTagDTO;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResTag} and its DTO {@link ResTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResTagMapper extends EntityMapper<ResTagDTO, ResTag> {
    @Mapping(target = "reservation", source = "reservation", qualifiedByName = "reservationId")
    ResTagDTO toDto(ResTag s);

    @Named("reservationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReservationDTO toDtoReservationId(Reservation reservation);
}
