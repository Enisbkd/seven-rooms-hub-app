package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ResCustomField;
import com.sbm.sevenroomstohub.repository.ResCustomFieldRepository;
import com.sbm.sevenroomstohub.service.ResCustomFieldService;
import com.sbm.sevenroomstohub.service.dto.ResCustomFieldDTO;
import com.sbm.sevenroomstohub.service.mapper.ResCustomFieldMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ResCustomField}.
 */
@Service
@Transactional
public class ResCustomFieldServiceImpl implements ResCustomFieldService {

    private final Logger log = LoggerFactory.getLogger(ResCustomFieldServiceImpl.class);

    private final ResCustomFieldRepository resCustomFieldRepository;

    private final ResCustomFieldMapper resCustomFieldMapper;

    public ResCustomFieldServiceImpl(ResCustomFieldRepository resCustomFieldRepository, ResCustomFieldMapper resCustomFieldMapper) {
        this.resCustomFieldRepository = resCustomFieldRepository;
        this.resCustomFieldMapper = resCustomFieldMapper;
    }

    @Override
    public ResCustomFieldDTO save(ResCustomFieldDTO resCustomFieldDTO) {
        log.debug("Request to save ResCustomField : {}", resCustomFieldDTO);
        ResCustomField resCustomField = resCustomFieldMapper.toEntity(resCustomFieldDTO);
        resCustomField = resCustomFieldRepository.save(resCustomField);
        return resCustomFieldMapper.toDto(resCustomField);
    }

    @Override
    public ResCustomFieldDTO update(ResCustomFieldDTO resCustomFieldDTO) {
        log.debug("Request to update ResCustomField : {}", resCustomFieldDTO);
        ResCustomField resCustomField = resCustomFieldMapper.toEntity(resCustomFieldDTO);
        resCustomField = resCustomFieldRepository.save(resCustomField);
        return resCustomFieldMapper.toDto(resCustomField);
    }

    @Override
    public Optional<ResCustomFieldDTO> partialUpdate(ResCustomFieldDTO resCustomFieldDTO) {
        log.debug("Request to partially update ResCustomField : {}", resCustomFieldDTO);

        return resCustomFieldRepository
            .findById(resCustomFieldDTO.getId())
            .map(existingResCustomField -> {
                resCustomFieldMapper.partialUpdate(existingResCustomField, resCustomFieldDTO);

                return existingResCustomField;
            })
            .map(resCustomFieldRepository::save)
            .map(resCustomFieldMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResCustomFieldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResCustomFields");
        return resCustomFieldRepository.findAll(pageable).map(resCustomFieldMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResCustomFieldDTO> findOne(Long id) {
        log.debug("Request to get ResCustomField : {}", id);
        return resCustomFieldRepository.findById(id).map(resCustomFieldMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResCustomField : {}", id);
        resCustomFieldRepository.deleteById(id);
    }
}
