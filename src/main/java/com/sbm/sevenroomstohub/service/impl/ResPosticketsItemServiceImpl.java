package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ResPosticketsItem;
import com.sbm.sevenroomstohub.repository.ResPosticketsItemRepository;
import com.sbm.sevenroomstohub.service.ResPosticketsItemService;
import com.sbm.sevenroomstohub.service.dto.ResPosticketsItemDTO;
import com.sbm.sevenroomstohub.service.mapper.ResPosticketsItemMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ResPosticketsItem}.
 */
@Service
@Transactional
public class ResPosticketsItemServiceImpl implements ResPosticketsItemService {

    private final Logger log = LoggerFactory.getLogger(ResPosticketsItemServiceImpl.class);

    private final ResPosticketsItemRepository resPosticketsItemRepository;

    private final ResPosticketsItemMapper resPosticketsItemMapper;

    public ResPosticketsItemServiceImpl(
        ResPosticketsItemRepository resPosticketsItemRepository,
        ResPosticketsItemMapper resPosticketsItemMapper
    ) {
        this.resPosticketsItemRepository = resPosticketsItemRepository;
        this.resPosticketsItemMapper = resPosticketsItemMapper;
    }

    @Override
    public ResPosticketsItemDTO save(ResPosticketsItemDTO resPosticketsItemDTO) {
        log.debug("Request to save ResPosticketsItem : {}", resPosticketsItemDTO);
        ResPosticketsItem resPosticketsItem = resPosticketsItemMapper.toEntity(resPosticketsItemDTO);
        resPosticketsItem = resPosticketsItemRepository.save(resPosticketsItem);
        return resPosticketsItemMapper.toDto(resPosticketsItem);
    }

    @Override
    public ResPosticketsItemDTO update(ResPosticketsItemDTO resPosticketsItemDTO) {
        log.debug("Request to update ResPosticketsItem : {}", resPosticketsItemDTO);
        ResPosticketsItem resPosticketsItem = resPosticketsItemMapper.toEntity(resPosticketsItemDTO);
        resPosticketsItem = resPosticketsItemRepository.save(resPosticketsItem);
        return resPosticketsItemMapper.toDto(resPosticketsItem);
    }

    @Override
    public Optional<ResPosticketsItemDTO> partialUpdate(ResPosticketsItemDTO resPosticketsItemDTO) {
        log.debug("Request to partially update ResPosticketsItem : {}", resPosticketsItemDTO);

        return resPosticketsItemRepository
            .findById(resPosticketsItemDTO.getId())
            .map(existingResPosticketsItem -> {
                resPosticketsItemMapper.partialUpdate(existingResPosticketsItem, resPosticketsItemDTO);

                return existingResPosticketsItem;
            })
            .map(resPosticketsItemRepository::save)
            .map(resPosticketsItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResPosticketsItemDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResPosticketsItems");
        return resPosticketsItemRepository.findAll(pageable).map(resPosticketsItemMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResPosticketsItemDTO> findOne(Long id) {
        log.debug("Request to get ResPosticketsItem : {}", id);
        return resPosticketsItemRepository.findById(id).map(resPosticketsItemMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResPosticketsItem : {}", id);
        resPosticketsItemRepository.deleteById(id);
    }
}
