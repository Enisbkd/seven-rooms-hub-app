package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.ClientPhoto;
import com.sbm.sevenroomstohub.repository.ClientPhotoRepository;
import com.sbm.sevenroomstohub.service.ClientPhotoService;
import com.sbm.sevenroomstohub.service.dto.ClientPhotoDTO;
import com.sbm.sevenroomstohub.service.mapper.ClientPhotoMapper;
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
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.ClientPhoto}.
 */
@Service
@Transactional
public class ClientPhotoServiceImpl implements ClientPhotoService {

    private final Logger log = LoggerFactory.getLogger(ClientPhotoServiceImpl.class);

    private final ClientPhotoRepository clientPhotoRepository;

    private final ClientPhotoMapper clientPhotoMapper;

    public ClientPhotoServiceImpl(ClientPhotoRepository clientPhotoRepository, ClientPhotoMapper clientPhotoMapper) {
        this.clientPhotoRepository = clientPhotoRepository;
        this.clientPhotoMapper = clientPhotoMapper;
    }

    @Override
    public ClientPhotoDTO save(ClientPhotoDTO clientPhotoDTO) {
        log.debug("Request to save ClientPhoto : {}", clientPhotoDTO);
        ClientPhoto clientPhoto = clientPhotoMapper.toEntity(clientPhotoDTO);
        clientPhoto = clientPhotoRepository.save(clientPhoto);
        return clientPhotoMapper.toDto(clientPhoto);
    }

    @Override
    public ClientPhotoDTO update(ClientPhotoDTO clientPhotoDTO) {
        log.debug("Request to update ClientPhoto : {}", clientPhotoDTO);
        ClientPhoto clientPhoto = clientPhotoMapper.toEntity(clientPhotoDTO);
        clientPhoto = clientPhotoRepository.save(clientPhoto);
        return clientPhotoMapper.toDto(clientPhoto);
    }

    @Override
    public Optional<ClientPhotoDTO> partialUpdate(ClientPhotoDTO clientPhotoDTO) {
        log.debug("Request to partially update ClientPhoto : {}", clientPhotoDTO);

        return clientPhotoRepository
            .findById(clientPhotoDTO.getId())
            .map(existingClientPhoto -> {
                clientPhotoMapper.partialUpdate(existingClientPhoto, clientPhotoDTO);

                return existingClientPhoto;
            })
            .map(clientPhotoRepository::save)
            .map(clientPhotoMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ClientPhotoDTO> findAll(Pageable pageable) {
        log.debug("Request to get all ClientPhotos");
        return clientPhotoRepository.findAll(pageable).map(clientPhotoMapper::toDto);
    }

    /**
     *  Get all the clientPhotos where Client is {@code null}.
     *  @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ClientPhotoDTO> findAllWhereClientIsNull() {
        log.debug("Request to get all clientPhotos where Client is null");
        return StreamSupport
            .stream(clientPhotoRepository.findAll().spliterator(), false)
            .filter(clientPhoto -> clientPhoto.getClient() == null)
            .map(clientPhotoMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ClientPhotoDTO> findOne(Long id) {
        log.debug("Request to get ClientPhoto : {}", id);
        return clientPhotoRepository.findById(id).map(clientPhotoMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete ClientPhoto : {}", id);
        clientPhotoRepository.deleteById(id);
    }
}
