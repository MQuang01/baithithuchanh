package com.example.exmd4spb.service.Impl;

import com.example.exmd4spb.model.Group;
import com.example.exmd4spb.model.dto.req.GroupReqDto;
import com.example.exmd4spb.model.dto.res.GroupResDto;
import com.example.exmd4spb.repository.IGroupRepository;
import com.example.exmd4spb.service.IGroupService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GroupServiceImpl implements IGroupService {
    private IGroupRepository groupRepository;
    @Override
    public List<GroupResDto> findAll() {
        return groupRepository.findAll()
                .stream()
                .map(group -> new GroupResDto(group.getId(), group.getName())).toList();
    }

    @Override
    public void save(GroupReqDto groupReqDto) {
        Group group = new Group();
        group.setName(groupReqDto.getName());
        groupRepository.save(group);
    }
}
