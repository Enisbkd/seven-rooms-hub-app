package com.sbm.sevenroomstohub.service.impl;

import com.sbm.sevenroomstohub.domain.MemberGroup;
import com.sbm.sevenroomstohub.repository.MemberGroupRepository;
import com.sbm.sevenroomstohub.service.MemberGroupService;
import com.sbm.sevenroomstohub.service.dto.MemberGroupDTO;
import com.sbm.sevenroomstohub.service.mapper.MemberGroupMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.sbm.sevenroomstohub.domain.MemberGroup}.
 */
@Service
@Transactional
public class MemberGroupServiceImpl implements MemberGroupService {

    private final Logger log = LoggerFactory.getLogger(MemberGroupServiceImpl.class);

    private final MemberGroupRepository memberGroupRepository;

    private final MemberGroupMapper memberGroupMapper;

    public MemberGroupServiceImpl(MemberGroupRepository memberGroupRepository, MemberGroupMapper memberGroupMapper) {
        this.memberGroupRepository = memberGroupRepository;
        this.memberGroupMapper = memberGroupMapper;
    }

    @Override
    public MemberGroupDTO save(MemberGroupDTO memberGroupDTO) {
        log.debug("Request to save MemberGroup : {}", memberGroupDTO);
        MemberGroup memberGroup = memberGroupMapper.toEntity(memberGroupDTO);
        memberGroup = memberGroupRepository.save(memberGroup);
        return memberGroupMapper.toDto(memberGroup);
    }

    @Override
    public MemberGroupDTO update(MemberGroupDTO memberGroupDTO) {
        log.debug("Request to update MemberGroup : {}", memberGroupDTO);
        MemberGroup memberGroup = memberGroupMapper.toEntity(memberGroupDTO);
        memberGroup = memberGroupRepository.save(memberGroup);
        return memberGroupMapper.toDto(memberGroup);
    }

    @Override
    public Optional<MemberGroupDTO> partialUpdate(MemberGroupDTO memberGroupDTO) {
        log.debug("Request to partially update MemberGroup : {}", memberGroupDTO);

        return memberGroupRepository
            .findById(memberGroupDTO.getId())
            .map(existingMemberGroup -> {
                memberGroupMapper.partialUpdate(existingMemberGroup, memberGroupDTO);

                return existingMemberGroup;
            })
            .map(memberGroupRepository::save)
            .map(memberGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MemberGroupDTO> findAll(Pageable pageable) {
        log.debug("Request to get all MemberGroups");
        return memberGroupRepository.findAll(pageable).map(memberGroupMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<MemberGroupDTO> findOne(Long id) {
        log.debug("Request to get MemberGroup : {}", id);
        return memberGroupRepository.findById(id).map(memberGroupMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete MemberGroup : {}", id);
        memberGroupRepository.deleteById(id);
    }
}
