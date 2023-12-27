package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ClientVenueStats;
import com.sbm.sevenroomstohub.repository.ClientVenueStatsRepository;
import com.sbm.sevenroomstohub.service.ClientVenueStatsService;
import com.sbm.sevenroomstohub.service.dto.ClientVenueStatsDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientVenueStatsMapper;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ClientVenueStats}.
 */
@Service
@Transactional
public class ClientVenueStatsServiceImpl implements ClientVenueStatsService {

    private final Logger log = LoggerFactory.getLogger(ClientVenueStatsServiceImpl.class);

    private final ClientVenueStatsRepository clientVenueStatsRepository;

    private final ClientVenueStatsMapper clientVenueStatsMapper;

    public ClientVenueStatsServiceImpl(
        ClientVenueStatsRepository clientVenueStatsRepository,
        ClientVenueStatsMapper clientVenueStatsMapper
    ) {
        this.clientVenueStatsRepository = clientVenueStatsRepository;
        this.clientVenueStatsMapper = clientVenueStatsMapper;
    }

    @Override
    public ClientVenueStatsDTO save(ClientVenueStatsDTO clientVenueStatsDTO) {
        log.debug("Request to save ClientVenueStats : {}", clientVenueStatsDTO);
        ClientVenueStats clientVenueStats = clientVenueStatsMapper.toEntity(clientVenueStatsDTO);
        clientVenueStats = clientVenueStatsRepository.save(clientVenueStats);
        return clientVenueStatsMapper.toDto(clientVenueStats);
    }

    @Override
    public ClientVenueStatsDTO update(ClientVenueStatsDTO clientVenueStatsDTO) {
        log.debug("Request to update ClientVenueStats : {}", clientVenueStatsDTO);
        ClientVenueStats clientVenueStats = clientVenueStatsMapper.toEntity(clientVenueStatsDTO);
        clientVenueStats = clientVenueStatsRepository.save(clientVenueStats);
        return clientVenueStatsMapper.toDto(clientVenueStats);
    }

    @Override
    public Optional<ClientVenueStatsDTO> partialUpdate(ClientVenueStatsDTO clientVenueStatsDTO) {
        log.debug("Request to partially update ClientVenueStats : {}", clientVenueStatsDTO);

        return clientVenueStatsRepository
            .findById(clientVenueStatsDTO.getId())
            .map(existingClientVenueStats -> {
                clientVenueStatsMapper.partialUpdate(existingClientVenueStats, clientVenueStatsDTO);

                return existingClientVenueStats;
            })
            .map(clientVenueStatsRepository::save)
            .map(clientVenueStatsMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientVenueStatsDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClientVenueStats");
        return clientVenueStatsRepository.findAll(pageable).map(clientVenueStatsMapper::toDto);
    }

    /**
     *  Get all the clientVenueStats where Client is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClientVenueStatsDTO> findAllWhereClientIsNull() {
        log.debug("Request to get all clientVenueStats where Client is null");
        return StreamSupport
            .stream(clientVenueStatsRepository.findAll().spliterator(), false)
            .filter(clientVenueStats -> clientVenueStats.getClient() == null)
            .map(clientVenueStatsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientVenueStatsDTO> findOne(Long id) {
        log.debug("Request to get ClientVenueStats : {}", id);
        return clientVenueStatsRepository.findById(id).map(clientVenueStatsMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientVenueStats : {}", id);
        clientVenueStatsRepository.deleteById(id);
    }
}
