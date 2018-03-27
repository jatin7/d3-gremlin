package com.johnhunsley.graph.service;

import com.johnhunsley.graph.d3domain.Link;
import com.johnhunsley.graph.d3domain.Node;
import com.johnhunsley.graph.d3domain.Result;
import com.johnhunsley.graph.repo.GremlinRepository;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
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
        Result response = new Result();

        for (Object result : results) {
            Vertex vertex = (Vertex) result;
            Node node = new Node(vertex.id().toString());
            node.set_color("#59b5f2");
//            VertexProperty prop = vertex.property("id");
            node.setName(vertex.id().toString());
            response.addNode(node);
        }

        return response;
    }

    @Override
    public Result getAllEdges() {
        GraphTraversal t = graphRepository.getAllEdges();
        List results = t.toList();
        Result response = new Result();

        for(Object result : results) {
            //add the link
            Edge edge = (Edge) result;
            Link link = new Link(edge.id().toString());
            Vertex target = edge.outVertex();
            link.setTid(target.id().toString());
            Vertex source = edge.inVertex();
            link.setSid(source.id().toString());
            link.set_color("#90c6cc");
            response.addLink(link);

            //add the source and target nodes
//            Result nodesResult = getAllNodes();
//            response.setNodes(nodesResult.getNodes());
            Node targetNode = new Node(target.id().toString());
            targetNode.set_color("#59b5f2");
            targetNode.setName(target.id().toString());
            response.addNode(targetNode);
            Node sourceNode = new Node(source.id().toString());
            sourceNode.set_color("#59b5f2");
            sourceNode.setName(source.id().toString());
            response.addNode(sourceNode);
        }

        return response;

    }

    @Override
    public Result getNodeAndDirectRelations(final String nodeName) {
        graphRepository.getLevelOneRelationships(nodeName);
        return null;
    }
}
