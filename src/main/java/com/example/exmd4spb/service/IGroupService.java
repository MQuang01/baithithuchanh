package com.example.exmd4spb.service;

import com.example.exmd4spb.model.dto.req.GroupReqDto;
import com.example.exmd4spb.model.dto.res.GroupResDto;

import java.util.List;

public interface IGroupService {
    List<GroupResDto> findAll();
    void save(GroupReqDto groupReqDto);
}
