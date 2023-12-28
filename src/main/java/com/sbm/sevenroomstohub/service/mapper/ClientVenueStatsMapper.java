package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientVenueStats} and its DTO {@link ClientVenueStatsDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientVenueStatsMapper extends EntityMapper<ClientVenueStatsDTO, ClientVenueStats> {}
