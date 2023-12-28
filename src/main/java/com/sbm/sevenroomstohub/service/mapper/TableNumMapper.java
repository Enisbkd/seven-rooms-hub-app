package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.Reservation;
import com.sbm.sevenroomstohub.domain.TableNum;
import com.sbm.sevenroomstohub.service.dto.ReservationDTO;
import com.sbm.sevenroomstohub.service.dto.TableNumDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TableNum} and its DTO {@link TableNumDTO}.
 */
@Mapper(componentModel = "spring")
public interface TableNumMapper extends EntityMapper<TableNumDTO, TableNum> {
    @Mapping(target = "reservation", source = "reservation", qualifiedByName = "reservationId")
    TableNumDTO toDto(TableNum s);

    @Named("reservationId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ReservationDTO toDtoReservationId(Reservation reservation);
}
