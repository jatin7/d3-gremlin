package com.johnhunsley.graph.repo;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 *         Time : 15:26
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

        for(int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }
    }
}
