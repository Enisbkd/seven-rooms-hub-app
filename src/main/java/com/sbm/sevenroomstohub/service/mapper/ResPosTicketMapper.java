package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResPosTicket;
import com.sbm.sevenroomstohub.service.dto.ResPosTicketDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResPosTicket} and its DTO {@link ResPosTicketDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResPosTicketMapper extends EntityMapper<ResPosTicketDTO, ResPosTicket> {}
