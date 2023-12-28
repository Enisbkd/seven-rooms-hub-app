package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.Table;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import com.sbm.sevenroomstohub.service.dto.TableDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link Table} and its DTO {@link TableDTO}.
 */
@Mapper(componentModel = "spring")
public interface TableMapper extends EntityMapper<TableDTO, Table> {
    @Mapping(target = "reservation", source = "reservation", qualifiedByName = "reservationId")
    TableDTO toDto(Table s);

    @Named("reservationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReservationDTO toDtoReservationId(Reservation reservation);
}
