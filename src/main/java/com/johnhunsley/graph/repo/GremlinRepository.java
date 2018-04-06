package com.johnhunsley.graph.repo;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.bothE;
import static org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.__.has;

/**
 * <p>
 *     Executes Graph Traversal Queries on a provided {@link GraphTraversalSource} to return  {@link GraphTraversal}
 *     instances which can be iterated
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@Component
public class GremlinRepository {

    @Autowired
    private GraphTraversalSource g;


    public GraphTraversal getAllNodes() {
        return  g.V();
    }

    public GraphTraversal getAllEdges() {
        return g.E();
    }

    /**
     * <p>
     *      g.V().has("desc","nodeName").bothE()
     * </p>
     * @param nodeName
     * @return
     */
    public GraphTraversal getLevelOneRelationships(final String nodeName) {
        return g.V().has("desc", nodeName).bothE();
    }

    /**
     * <p>
     *     g.V(sourceId).repeat(bothE().bothV()).until(hasId(targetId)).path().limit(4)
     * </p>
     * @param sourceNodeName
     * @param targetNodeName
     * @return
     */
    public GraphTraversal getShortestPath(final String sourceNodeName, final String targetNodeName) {
        return g.V().has("desc", sourceNodeName)
                .repeat(bothE().bothV()).until(has("desc", targetNodeName)).path().limit(4);
    }
}
