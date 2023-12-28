package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ClientTag;
import com.sbm.sevenroomstohub.repository.ClientTagRepository;
import com.sbm.sevenroomstohub.service.ClientTagService;
import com.sbm.sevenroomstohub.service.dto.ClientTagDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientTagMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ClientTag}.
 */
@Service
@Transactional
public class ClientTagServiceImpl implements ClientTagService {

    private final Logger log = LoggerFactory.getLogger(ClientTagServiceImpl.class);

    private final ClientTagRepository clientTagRepository;

    private final ClientTagMapper clientTagMapper;

    public ClientTagServiceImpl(ClientTagRepository clientTagRepository, ClientTagMapper clientTagMapper) {
        this.clientTagRepository = clientTagRepository;
        this.clientTagMapper = clientTagMapper;
    }

    @Override
    public ClientTagDTO save(ClientTagDTO clientTagDTO) {
        log.debug("Request to save ClientTag : {}", clientTagDTO);
        ClientTag clientTag = clientTagMapper.toEntity(clientTagDTO);
        clientTag = clientTagRepository.save(clientTag);
        return clientTagMapper.toDto(clientTag);
    }

    @Override
    public ClientTagDTO update(ClientTagDTO clientTagDTO) {
        log.debug("Request to update ClientTag : {}", clientTagDTO);
        ClientTag clientTag = clientTagMapper.toEntity(clientTagDTO);
        clientTag = clientTagRepository.save(clientTag);
        return clientTagMapper.toDto(clientTag);
    }

    @Override
    public Optional<ClientTagDTO> partialUpdate(ClientTagDTO clientTagDTO) {
        log.debug("Request to partially update ClientTag : {}", clientTagDTO);

        return clientTagRepository
            .findById(clientTagDTO.getId())
            .map(existingClientTag -> {
                clientTagMapper.partialUpdate(existingClientTag, clientTagDTO);

                return existingClientTag;
            })
            .map(clientTagRepository::save)
            .map(clientTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientTagDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClientTags");
        return clientTagRepository.findAll(pageable).map(clientTagMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientTagDTO> findOne(Long id) {
        log.debug("Request to get ClientTag : {}", id);
        return clientTagRepository.findById(id).map(clientTagMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientTag : {}", id);
        clientTagRepository.deleteById(id);
    }
}
