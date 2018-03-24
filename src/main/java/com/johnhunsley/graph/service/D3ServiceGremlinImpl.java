package com.johnhunsley.graph.service;

import com.johnhunsley.graph.d3domain.Result;
import com.johnhunsley.graph.repo.GremlinRepository;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *     Accepts the parameters of the D3 {@link com.johnhunsley.graph.d3domain.Request} instance
 *     and uses them to query the {@link GremlinRepository}
 *     The resulting gremlin objects are translated into a D3 {@link Result}
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@Service
public class D3ServiceGremlinImpl implements D3Service {

    @Autowired
    private GremlinRepository graphRepository;

    public Result getAllNodes() {
        GraphTraversal t = graphRepository.getAllNodes();
        List results = t.toList();

        for(int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i));
        }

        return new Result();//translate the results from the traversal to d3 domain objects
    }

    @Override
    public Result getNodeAndDirectRelations(final String nodeName) {
        graphRepository.getLevelOneRelationships(nodeName);
        return null;
    }
}
