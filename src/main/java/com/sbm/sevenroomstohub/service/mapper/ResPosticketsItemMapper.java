package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResPosTicket;
import com.sbm.sevenroomstohub.domain.ResPosticketsItem;
import com.sbm.sevenroomstohub.service.dto.ResPosTicketDTO;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResPosticketsItem} and its DTO {@link ResPosticketsItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResPosticketsItemMapper extends EntityMapper<ResPosticketsItemDTO, ResPosticketsItem> {
    @Mapping(target = "resPosTicket", source = "resPosTicket", qualifiedByName = "resPosTicketId")
    ResPosticketsItemDTO toDto(ResPosticketsItem s);

    @Named("resPosTicketId")
    @BeanMapping(ignoreByDefault = true)
    @Mapping(target = "id", source = "id")
    ResPosTicketDTO toDtoResPosTicketId(ResPosTicket resPosTicket);
}
