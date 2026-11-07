package com.klef.soa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.soa.entity.Sprint;
import com.klef.soa.repository.SprintRepository;

@Service
public class SprintServiceImpl implements SprintService
{
    @Autowired
    private SprintRepository repo;

    @Override
    public Sprint addSprint(Sprint sprint)
    {
        return repo.save(sprint);
    }

    @Override
    public List<Sprint> displayAllSprints()
    {
        return repo.findAll();
    }

    @Override
    public Sprint displaySprintById(Long id)
    {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Sprint updateSprint(Sprint sprint)
    {
        Optional<Sprint> optional = repo.findById(sprint.getSprintId());

        if(optional.isPresent())
        {
            Sprint s = optional.get();

            s.setName(sprint.getName());
            s.setType(sprint.getType());
            s.setStatus(sprint.getStatus());
            s.setLocation(sprint.getLocation());

            return repo.save(s);
        }
        else
        {
            return null;
        }
    }

    @Override
    public String deleteSprintById(Long id)
    {
        boolean status = repo.existsById(id);

        if(status)
        {
            repo.deleteById(id);
            return "Sprint Deleted Successfully";
        }
        else
        {
            return "Sprint ID Not Found";
        }
    }

    @Override
    public List<Sprint> displaySprintsByName(String name)
    {
        return repo.findByName(name);
    }

    @Override
    public List<Sprint> displaySprintsByType(String type)
    {
        return repo.findByType(type);
    }
}