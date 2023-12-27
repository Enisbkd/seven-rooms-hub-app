package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.TableNumber;
import com.sbm.sevenroomstohub.service.dto.TableNumberDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link TableNumber} and its DTO {@link TableNumberDTO}.
 */
@Mapper(componentModel = "spring")
public interface TableNumberMapper extends EntityMapper<TableNumberDTO, TableNumber> {}
