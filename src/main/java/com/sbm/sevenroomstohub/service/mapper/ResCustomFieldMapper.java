package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ResCustomField;
import com.sbm.sevenroomstohub.service.dto.ResCustomFieldDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ResCustomField} and its DTO {@link ResCustomFieldDTO}.
 */
@Mapper(componentModel = "spring")
public interface ResCustomFieldMapper extends EntityMapper<ResCustomFieldDTO, ResCustomField> {}
