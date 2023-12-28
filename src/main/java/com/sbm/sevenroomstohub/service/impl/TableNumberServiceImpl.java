package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.TableNumber;
import com.sbm.sevenroomstohub.repository.TableNumberRepository;
import com.sbm.sevenroomstohub.service.TableNumberService;
import com.sbm.sevenroomstohub.service.dto.TableNumberDTO;
import com.sbm.sevenroomstohub.service.mapper.TableNumberMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.TableNumber}.
 */
@Service
@Transactional
public class TableNumberServiceImpl implements TableNumberService {

    private final Logger log = LoggerFactory.getLogger(TableNumberServiceImpl.class);

    private final TableNumberRepository tableNumberRepository;

    private final TableNumberMapper tableNumberMapper;

    public TableNumberServiceImpl(TableNumberRepository tableNumberRepository, TableNumberMapper tableNumberMapper) {
        this.tableNumberRepository = tableNumberRepository;
        this.tableNumberMapper = tableNumberMapper;
    }

    @Override
    public TableNumberDTO save(TableNumberDTO tableNumberDTO) {
        log.debug("Request to save TableNumber : {}", tableNumberDTO);
        TableNumber tableNumber = tableNumberMapper.toEntity(tableNumberDTO);
        tableNumber = tableNumberRepository.save(tableNumber);
        return tableNumberMapper.toDto(tableNumber);
    }

    @Override
    public TableNumberDTO update(TableNumberDTO tableNumberDTO) {
        log.debug("Request to update TableNumber : {}", tableNumberDTO);
        TableNumber tableNumber = tableNumberMapper.toEntity(tableNumberDTO);
        tableNumber = tableNumberRepository.save(tableNumber);
        return tableNumberMapper.toDto(tableNumber);
    }

    @Override
    public Optional<TableNumberDTO> partialUpdate(TableNumberDTO tableNumberDTO) {
        log.debug("Request to partially update TableNumber : {}", tableNumberDTO);

        return tableNumberRepository
            .findById(tableNumberDTO.getId())
            .map(existingTableNumber -> {
                tableNumberMapper.partialUpdate(existingTableNumber, tableNumberDTO);

                return existingTableNumber;
            })
            .map(tableNumberRepository::save)
            .map(tableNumberMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableNumberDTO> findAll() {
        log.debug("Request to get all TableNumbers");
        return tableNumberRepository.findAll().stream().map(tableNumberMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableNumberDTO> findOne(Long id) {
        log.debug("Request to get TableNumber : {}", id);
        return tableNumberRepository.findById(id).map(tableNumberMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableNumber : {}", id);
        tableNumberRepository.deleteById(id);
    }
}
