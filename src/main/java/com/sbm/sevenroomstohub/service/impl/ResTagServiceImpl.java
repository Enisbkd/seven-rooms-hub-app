package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ResTag;
import com.sbm.sevenroomstohub.repository.ResTagRepository;
import com.sbm.sevenroomstohub.service.ResTagService;
import com.sbm.sevenroomstohub.service.dto.ResTagDTO;
import com.sbm.sevenroomstohub.service.mapper.ResTagMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ResTag}.
 */
@Service
@Transactional
public class ResTagServiceImpl implements ResTagService {

    private final Logger log = LoggerFactory.getLogger(ResTagServiceImpl.class);

    private final ResTagRepository resTagRepository;

    private final ResTagMapper resTagMapper;

    public ResTagServiceImpl(ResTagRepository resTagRepository, ResTagMapper resTagMapper) {
        this.resTagRepository = resTagRepository;
        this.resTagMapper = resTagMapper;
    }

    @Override
    public ResTagDTO save(ResTagDTO resTagDTO) {
        log.debug("Request to save ResTag : {}", resTagDTO);
        ResTag resTag = resTagMapper.toEntity(resTagDTO);
        resTag = resTagRepository.save(resTag);
        return resTagMapper.toDto(resTag);
    }

    @Override
    public ResTagDTO update(ResTagDTO resTagDTO) {
        log.debug("Request to update ResTag : {}", resTagDTO);
        ResTag resTag = resTagMapper.toEntity(resTagDTO);
        resTag = resTagRepository.save(resTag);
        return resTagMapper.toDto(resTag);
    }

    @Override
    public Optional<ResTagDTO> partialUpdate(ResTagDTO resTagDTO) {
        log.debug("Request to partially update ResTag : {}", resTagDTO);

        return resTagRepository
            .findById(resTagDTO.getId())
            .map(existingResTag -> {
                resTagMapper.partialUpdate(existingResTag, resTagDTO);

                return existingResTag;
            })
            .map(resTagRepository::save)
            .map(resTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResTags");
        return resTagRepository.findAll(pageable).map(resTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResTagDTO> findOne(Long id) {
        log.debug("Request to get ResTag : {}", id);
        return resTagRepository.findById(id).map(resTagMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResTag : {}", id);
        resTagRepository.deleteById(id);
    }
}
