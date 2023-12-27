package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResTag;
import com.sbm.sevenroomstohub.service.dto.ResTagDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResTag} and its DTO {@link ResTagDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResTagMapper extends EntityMapper<ResTagDTO, ResTag> {}
