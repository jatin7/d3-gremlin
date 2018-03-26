package com.johnhunsley.graph.repo;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integrationTest")
public class GremlinRepositoryIntegrationTest {

    @Autowired
    private GremlinRepository gremlinRepository;

    @Test
    public void testGetAllNodes() {
        GraphTraversal t = gremlinRepository.getAllNodes();
        List results = t.toList();

        for (Object result : results) {
            System.out.println(result.toString());
        }

        assertTrue(results.size() == 47);
    }

    @Test
    public void testGetAllEdges() {
        GraphTraversal t = gremlinRepository.getAllEdges();
        List results = t.toList();

        for (Object result : results) {
            System.out.println(result.toString());
        }

        assertTrue(results.size() == 1326);
    }
}
