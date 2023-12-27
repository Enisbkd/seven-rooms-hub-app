package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResPosticketsItem;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResPosticketsItem} and its DTO {@link ResPosticketsItemDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResPosticketsItemMapper extends EntityMapper<ResPosticketsItemDTO, ResPosticketsItem> {}
