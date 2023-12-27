package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResPosticketsItem;
import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResPosticketsItem} and its DTO {@link ResPosticketsItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResPosticketsItemMapper extends EntityMapper<ResPosticketsItemDTO, ResPosticketsItem> {
    @Mapping(target = "reservation", source = "reservation", qualifiedByName = "reservationId")
    ResPosticketsItemDTO toDto(ResPosticketsItem s);

    @Named("reservationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReservationDTO toDtoReservationId(Reservation reservation);
}
