package com.example.exmd4spb.controller.rest;

import com.example.exmd4spb.model.dto.req.GroupReqDto;
import com.example.exmd4spb.service.IGroupService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/groups")
@AllArgsConstructor
public class GroupRestController {
    private final IGroupService groupService;
    @GetMapping
    public ResponseEntity<?> getGroups() {
        return ResponseEntity.ok(groupService.findAll());
    }

    @PostMapping
    public ResponseEntity<Void> createGroup(@RequestBody GroupReqDto groupReqDto) {
        groupService.save(groupReqDto);
        return ResponseEntity.ok().build();
    }
}
