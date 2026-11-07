package com.klef.soa.test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import com.klef.soa.entity.Sprint;
import com.klef.soa.repository.SprintRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SprintRepositoryTest
{
    @Autowired
    private SprintRepository repository;

    @Test
    void testFindAllSprints()
    {
        Sprint s = new Sprint();
        s.setName("Java Sprint");
        s.setType("Technical");
        s.setStatus("Active");
        s.setLocation("Vijayawada");

        Sprint saved = repository.save(s);

        List<Sprint> sprints = repository.findAll();

        assertNotNull(sprints);
        assertTrue(sprints.stream()
                .anyMatch(sprint -> sprint.getSprintId().equals(saved.getSprintId())));

        System.out.println("Total Sprints: " + sprints.size());
    }

    @Test
    void testFindSprintById()
    {
        Sprint s = new Sprint();
        s.setName("Java Sprint");
        s.setType("Technical");
        s.setStatus("Active");
        s.setLocation("Vijayawada");

        Sprint saved = repository.save(s);

        Optional<Sprint> sprint =
                repository.findById(saved.getSprintId());

        assertTrue(sprint.isPresent());
        assertEquals(saved.getSprintId(), sprint.get().getSprintId());

        Optional<Sprint> invalid =
                repository.findById(-1L);

        assertFalse(invalid.isPresent());

        System.out.println("Sprint ID: " + sprint.get().getSprintId());
    }

    @Test
    void testFindSprintByName()
    {
        Sprint s = new Sprint();
        s.setName("Testing Sprint");
        s.setType("Development");
        s.setStatus("Active");
        s.setLocation("Hyderabad");

        repository.save(s);

        List<Sprint> sprints =
                repository.findByName("Testing Sprint");

        assertNotNull(sprints);
        assertFalse(sprints.isEmpty());
        assertEquals("Testing Sprint", sprints.get(0).getName());

        List<Sprint> types =
                repository.findByType("Development");

        assertNotNull(types);
        assertFalse(types.isEmpty());
        assertEquals("Development", types.get(0).getType());

        System.out.println("Sprint Name: " + sprints.get(0).getName());
        System.out.println("Sprint Type: " + types.get(0).getType());
    }

    // TEST 4 - COUNT
    @Test
    void testTotalSprintCount()
    {
        long before = repository.count();

        Sprint s = new Sprint();
        s.setName("Count Sprint");
        s.setType("Testing");
        s.setStatus("Active");
        s.setLocation("Vijayawada");

        repository.save(s);

        long after = repository.count();

        assertEquals(before + 1, after);

        System.out.println("Total Sprints: " + after);
    }

    // TEST 5 - ENTITY DETAILS
    @Test
    void testSprintDetails()
    {
        Sprint s = new Sprint();
        s.setName("Final Sprint");
        s.setType("Development");
        s.setStatus("Active");
        s.setLocation("Hyderabad");

        Sprint saved = repository.save(s);

        Optional<Sprint> sprint =
                repository.findById(saved.getSprintId());

        assertTrue(sprint.isPresent());

        Sprint foundSprint = sprint.get();

        assertNotNull(foundSprint.getSprintId());
        assertNotNull(foundSprint.getName());
        assertNotNull(foundSprint.getType());
        assertNotNull(foundSprint.getStatus());
        assertNotNull(foundSprint.getLocation());

        System.out.println("Sprint ID: " + foundSprint.getSprintId());
        System.out.println("Name: " + foundSprint.getName());
        System.out.println("Type: " + foundSprint.getType());
        System.out.println("Status: " + foundSprint.getStatus());
        System.out.println("Location: " + foundSprint.getLocation());
    }
}
