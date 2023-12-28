package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.TableNum;
import com.sbm.sevenroomstohub.repository.TableNumRepository;
import com.sbm.sevenroomstohub.service.TableNumService;
import com.sbm.sevenroomstohub.service.dto.TableNumDTO;
import com.sbm.sevenroomstohub.service.mapper.TableNumMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.TableNum}.
 */
@Service
@Transactional
public class TableNumServiceImpl implements TableNumService {

    private final Logger log = LoggerFactory.getLogger(TableNumServiceImpl.class);

    private final TableNumRepository tableNumRepository;

    private final TableNumMapper tableNumMapper;

    public TableNumServiceImpl(TableNumRepository tableNumRepository, TableNumMapper tableNumMapper) {
        this.tableNumRepository = tableNumRepository;
        this.tableNumMapper = tableNumMapper;
    }

    @Override
    public TableNumDTO save(TableNumDTO tableNumDTO) {
        log.debug("Request to save TableNum : {}", tableNumDTO);
        TableNum tableNum = tableNumMapper.toEntity(tableNumDTO);
        tableNum = tableNumRepository.save(tableNum);
        return tableNumMapper.toDto(tableNum);
    }

    @Override
    public TableNumDTO update(TableNumDTO tableNumDTO) {
        log.debug("Request to update TableNum : {}", tableNumDTO);
        TableNum tableNum = tableNumMapper.toEntity(tableNumDTO);
        tableNum = tableNumRepository.save(tableNum);
        return tableNumMapper.toDto(tableNum);
    }

    @Override
    public Optional<TableNumDTO> partialUpdate(TableNumDTO tableNumDTO) {
        log.debug("Request to partially update TableNum : {}", tableNumDTO);

        return tableNumRepository
            .findById(tableNumDTO.getId())
            .map(existingTableNum -> {
                tableNumMapper.partialUpdate(existingTableNum, tableNumDTO);

                return existingTableNum;
            })
            .map(tableNumRepository::save)
            .map(tableNumMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TableNumDTO> findAll() {
        log.debug("Request to get all TableNums");
        return tableNumRepository.findAll().stream().map(tableNumMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TableNumDTO> findOne(Long id) {
        log.debug("Request to get TableNum : {}", id);
        return tableNumRepository.findById(id).map(tableNumMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete TableNum : {}", id);
        tableNumRepository.deleteById(id);
    }
}
