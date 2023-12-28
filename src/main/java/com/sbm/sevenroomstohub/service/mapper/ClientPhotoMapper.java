package com.sbm.sevenroomstohub.service.mapper;

import com.sbm.sevenroomstohub.domain.ClientPhoto;
import com.sbm.sevenroomstohub.service.dto.ClientPhotoDTO;
import org.mapstruct.*;

/**
 * Mapper for the entity {@link ClientPhoto} and its DTO {@link ClientPhotoDTO}.
 */
@Mapper(componentModel = "spring")
public interface ClientPhotoMapper extends EntityMapper<ClientPhotoDTO, ClientPhoto> {}
