package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.Table;
import com.sbm.sevenroomstohub.repository.TableRepository;
import com.sbm.sevenroomstohub.service.TableService;
import com.sbm.sevenroomstohub.service.dto.TableDTO;
import com.sbm.sevenroomstohub.service.mapper.TableMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.Table}.
 */
@Service
@Transactional
public class TableServiceImpl implements TableService {

    private final Logger log = LoggerFactory.getLogger(TableServiceImpl.class);

    private final TableRepository tableRepository;

    private final TableMapper tableMapper;

    public TableServiceImpl(TableRepository tableRepository, TableMapper tableMapper) {
        this.tableRepository = tableRepository;
        this.tableMapper = tableMapper;
    }

    @Override
    public TableDTO save(TableDTO tableDTO) {
        log.debug("Request to save Table : {}", tableDTO);
        Table table = tableMapper.toEntity(tableDTO);
        table = tableRepository.save(table);
        return tableMapper.toDto(table);
    }

    @Override
    public TableDTO update(TableDTO tableDTO) {
        log.debug("Request to update Table : {}", tableDTO);
        Table table = tableMapper.toEntity(tableDTO);
        table = tableRepository.save(table);
        return tableMapper.toDto(table);
    }

    @Override
    public Optional<TableDTO> partialUpdate(TableDTO tableDTO) {
        log.debug("Request to partially update Table : {}", tableDTO);

        return tableRepository
            .findById(tableDTO.getId())
            .map(existingTable -> {
                tableMapper.partialUpdate(existingTable, tableDTO);

                return existingTable;
            })
            .map(tableRepository::save)
            .map(tableMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableDTO> findAll() {
        log.debug("Request to get all Tables");
        return tableRepository.findAll().stream().map(tableMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableDTO> findOne(Long id) {
        log.debug("Request to get Table : {}", id);
        return tableRepository.findById(id).map(tableMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Table : {}", id);
        tableRepository.deleteById(id);
    }
}
