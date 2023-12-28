package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ResPosTicket;
import com.sbm.sevenroomstohub.repository.ResPosTicketRepository;
import com.sbm.sevenroomstohub.service.ResPosTicketService;
import com.sbm.sevenroomstohub.service.dto.ResPosTicketDTO;
import com.sbm.sevenroomstohub.service.mapper.ResPosTicketMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ResPosTicket}.
 */
@Service
@Transactional
public class ResPosTicketServiceImpl implements ResPosTicketService {

    private final Logger log = LoggerFactory.getLogger(ResPosTicketServiceImpl.class);

    private final ResPosTicketRepository resPosTicketRepository;

    private final ResPosTicketMapper resPosTicketMapper;

    public ResPosTicketServiceImpl(ResPosTicketRepository resPosTicketRepository, ResPosTicketMapper resPosTicketMapper) {
        this.resPosTicketRepository = resPosTicketRepository;
        this.resPosTicketMapper = resPosTicketMapper;
    }

    @Override
    public ResPosTicketDTO save(ResPosTicketDTO resPosTicketDTO) {
        log.debug("Request to save ResPosTicket : {}", resPosTicketDTO);
        ResPosTicket resPosTicket = resPosTicketMapper.toEntity(resPosTicketDTO);
        resPosTicket = resPosTicketRepository.save(resPosTicket);
        return resPosTicketMapper.toDto(resPosTicket);
    }

    @Override
    public ResPosTicketDTO update(ResPosTicketDTO resPosTicketDTO) {
        log.debug("Request to update ResPosTicket : {}", resPosTicketDTO);
        ResPosTicket resPosTicket = resPosTicketMapper.toEntity(resPosTicketDTO);
        resPosTicket = resPosTicketRepository.save(resPosTicket);
        return resPosTicketMapper.toDto(resPosTicket);
    }

    @Override
    public Optional<ResPosTicketDTO> partialUpdate(ResPosTicketDTO resPosTicketDTO) {
        log.debug("Request to partially update ResPosTicket : {}", resPosTicketDTO);

        return resPosTicketRepository
            .findById(resPosTicketDTO.getId())
            .map(existingResPosTicket -> {
                resPosTicketMapper.partialUpdate(existingResPosTicket, resPosTicketDTO);

                return existingResPosTicket;
            })
            .map(resPosTicketRepository::save)
            .map(resPosTicketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ResPosTicketDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ResPosTickets");
        return resPosTicketRepository.findAll(pageable).map(resPosTicketMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ResPosTicketDTO> findOne(Long id) {
        log.debug("Request to get ResPosTicket : {}", id);
        return resPosTicketRepository.findById(id).map(resPosTicketMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ResPosTicket : {}", id);
        resPosTicketRepository.deleteById(id);
    }
}
