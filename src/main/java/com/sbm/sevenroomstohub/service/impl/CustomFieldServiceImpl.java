package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.CustomField;
import com.sbm.sevenroomstohub.repository.CustomFieldRepository;
import com.sbm.sevenroomstohub.service.CustomFieldService;
import com.sbm.sevenroomstohub.service.dto.CustomFieldDTO;
import com.sbm.sevenroomstohub.service.mapper.CustomFieldMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.CustomField}.
 */
@Service
@Transactional
public class CustomFieldServiceImpl implements CustomFieldService {

    private final Logger log = LoggerFactory.getLogger(CustomFieldServiceImpl.class);

    private final CustomFieldRepository customFieldRepository;

    private final CustomFieldMapper customFieldMapper;

    public CustomFieldServiceImpl(CustomFieldRepository customFieldRepository, CustomFieldMapper customFieldMapper) {
        this.customFieldRepository = customFieldRepository;
        this.customFieldMapper = customFieldMapper;
    }

    @Override
    public CustomFieldDTO save(CustomFieldDTO customFieldDTO) {
        log.debug("Request to save CustomField : {}", customFieldDTO);
        CustomField customField = customFieldMapper.toEntity(customFieldDTO);
        customField = customFieldRepository.save(customField);
        return customFieldMapper.toDto(customField);
    }

    @Override
    public CustomFieldDTO update(CustomFieldDTO customFieldDTO) {
        log.debug("Request to update CustomField : {}", customFieldDTO);
        CustomField customField = customFieldMapper.toEntity(customFieldDTO);
        customField = customFieldRepository.save(customField);
        return customFieldMapper.toDto(customField);
    }

    @Override
    public Optional<CustomFieldDTO> partialUpdate(CustomFieldDTO customFieldDTO) {
        log.debug("Request to partially update CustomField : {}", customFieldDTO);

        return customFieldRepository
            .findById(customFieldDTO.getId())
            .map(existingCustomField -> {
                customFieldMapper.partialUpdate(existingCustomField, customFieldDTO);

                return existingCustomField;
            })
            .map(customFieldRepository::save)
            .map(customFieldMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CustomFieldDTO> findAll(Pageable pageable) {
        log.debug("Request to get all CustomFields");
        return customFieldRepository.findAll(pageable).map(customFieldMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CustomFieldDTO> findOne(Long id) {
        log.debug("Request to get CustomField : {}", id);
        return customFieldRepository.findById(id).map(customFieldMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete CustomField : {}", id);
        customFieldRepository.deleteById(id);
    }
}
