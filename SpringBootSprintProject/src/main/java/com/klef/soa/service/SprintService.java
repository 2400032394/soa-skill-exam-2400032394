package com.klef.soa.service;

import java.util.List;

import com.klef.soa.entity.Sprint;

public interface SprintService
{
    Sprint addSprint(Sprint sprint);
    List<Sprint> displayAllSprints();
    Sprint displaySprintById(Long id);
    Sprint updateSprint(Sprint sprint);
    String deleteSprintById(Long id);
    List<Sprint> displaySprintsByName(String name);
    List<Sprint> displaySprintsByType(String type);
}