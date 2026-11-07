
package com.klef.soa.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.klef.soa.entity.Sprint;
import com.klef.soa.service.SprintService;

@RestController
@RequestMapping("/sprint")
public class SprintController
{
    @Autowired
    private SprintService service;

    @GetMapping("/")
    public String test()
    {
        return "SOA Sprint REST API";
    }

    @GetMapping("/route")
    public String route()
    {
        return "SOA Sprint Project Route is Working";
    }

    @PostMapping("/add")
    public ResponseEntity<Sprint> addSprint(@RequestBody Sprint sprint)
    {
        Sprint s = service.addSprint(sprint);
        return ResponseEntity.status(201).body(s);
    }

    @GetMapping("/displayall")
    public ResponseEntity<List<Sprint>> displayAllSprints()
    {
        List<Sprint> sprints = service.displayAllSprints();
        return ResponseEntity.status(200).body(sprints);
    }

    @GetMapping("/display")
    public ResponseEntity<?> displaySprintById(@RequestParam Long id)
    {
        Sprint sprint = service.displaySprintById(id);

        if(sprint != null)
        {
            return ResponseEntity.status(200).body(sprint);
        }
        else
        {
            return ResponseEntity.status(404).body("Sprint ID Not Found");
        }
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSprint(@RequestBody Sprint sprint)
    {
        Sprint s = service.updateSprint(sprint);

        if(s != null)
        {
            return ResponseEntity.ok(s);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Sprint ID Not Found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSprintById(@PathVariable Long id)
    {
        String message = service.deleteSprintById(id);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @GetMapping("/displaybyname/{name}")
    public ResponseEntity<List<Sprint>> displaySprintsByName(@PathVariable String name)
    {
        List<Sprint> sprints = service.displaySprintsByName(name);
        return ResponseEntity.status(200).body(sprints);
    }

    @GetMapping("/displaybytype/{type}")
    public ResponseEntity<List<Sprint>> displaySprintsByType(@PathVariable String type)
    {
        List<Sprint> sprints = service.displaySprintsByType(type);
        return ResponseEntity.status(200).body(sprints);
    }
}
