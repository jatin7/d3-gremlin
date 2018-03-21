package com.johnhunsley.graph.repo;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 *         Time : 10:11
 */
@Component
public class GremlinRepository {

    @Autowired
    private GraphTraversalSource g;


    public GraphTraversal getAllNodes() {
        return  g.V();
    }
}
