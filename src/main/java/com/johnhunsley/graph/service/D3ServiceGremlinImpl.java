package com.johnhunsley.graph.service;

import com.johnhunsley.graph.d3domain.Link;
import com.johnhunsley.graph.d3domain.Node;
import com.johnhunsley.graph.d3domain.Result;
import com.johnhunsley.graph.repo.GremlinRepository;
import org.apache.commons.collections.SetUtils;
import org.apache.tinkerpop.gremlin.process.traversal.Path;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversal;
import org.apache.tinkerpop.gremlin.structure.Edge;
import org.apache.tinkerpop.gremlin.structure.Vertex;
import org.apache.tinkerpop.gremlin.structure.VertexProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
@Scope("prototype")
public class D3ServiceGremlinImpl implements D3Service {

    @Autowired
    private GremlinRepository graphRepository;

    @Value("${graph.node.name.label}")
    private String nodeNameLabel;


    public Result getAllNodes() {
        GraphTraversal t = graphRepository.getAllNodes();
        List results = t.toList();
        Result response = new Result();

        for (Object result : results) {
            Vertex vertex = (Vertex) result;
            response.addNode(transformVertex(vertex, "#59b5f2", nodeNameLabel));
        }

        return response;
    }

    @Override
    public Result getAllEdges() {
        GraphTraversal t = graphRepository.getAllEdges();
        List results = t.toList();
        Result response = new Result();

        for(Object result : results) {
            Edge edge = (Edge)result;
            response.addLink(transformEdge(edge, "#90c6cc"));

            //add the source and target nodes
            Vertex target = edge.outVertex();
            response.addNode(transformVertex(target, "#59b5f2", nodeNameLabel));

            Vertex source = edge.inVertex();
            response.addNode(transformVertex(source, "#59b5f2", nodeNameLabel));
        }

        return response;
    }

    /**
     * <p>
     *     Create an {@link Link} instance from the given {@link Edge}.
     * </p>
     * @param edge
     * @param colour
     * @return {@link Link}
     */
    private Link transformEdge(Edge edge, final String colour) {
        Link link = new Link(edge.id().toString());
        Vertex target = edge.outVertex();
        link.setTid(target.id().toString());
        Vertex source = edge.inVertex();
        link.setSid(source.id().toString());
        link.set_color(colour);
        link.setName(edge.label());
        return link;
    }

    /**
     * <p>
     *    Create and {@link Node} instance from the given {@link Vertex}
     * </p>
     * @param vertex
     * @param colour
     * @param nameProperty
     * @return {@link Node}
     */
    private Node transformVertex(Vertex vertex, final String colour, final String nameProperty) {
        Node node = new Node(vertex.id().toString());
        node.set_color(colour);
        node.setName(vertex.property(nameProperty).value().toString());
        return node;
    }

    /**
     * <p>
     *
     * </p>
     * @param path
     * @param result
     * @param nodeColour
     * @param nodeNaneProperty
     * @param edgeColour
     * @return
     */
    private Result transformPath(Path path, Result result,
                                 final String nodeColour,
                                 final String nodeNaneProperty,
                                 final String edgeColour) throws ClassNotFoundException {
        if(result == null) result = new Result();

        List list = path.objects();

        for(Object object : list) {
            if(object instanceof Vertex) {
                result.addNode(transformVertex((Vertex)object, nodeColour, nodeNaneProperty));
            }

            else if(object instanceof Edge) {
                result.addLink(transformEdge((Edge)object, edgeColour));
            }

            else throw new ClassNotFoundException(
                        "Cannot create a Path instance which contains objects of type - "
                                + object.getClass().getName());
        }

        return result;
    }

    @Override
    public Result getFirstOrderRelatedNodes(final String nodeName, Result response) {
        GraphTraversal t = graphRepository.getLevelOneRelationships(nodeName, nodeNameLabel);
        List results = t.toList();
        if(response == null) response = new Result();

        for(Object result : results) {
            //add the link
            Edge edge = (Edge)result;
            response.addLink(transformEdge(edge, "#90c6cc"));

            //add the source and target nodes
            Vertex target = edge.outVertex();
            response.addNode(transformVertex(target, "#59b5f2", nodeNameLabel));

            Vertex source = edge.inVertex();
            response.addNode(transformVertex(source, "#59b5f2", nodeNameLabel));
        }

        return response;

    }

    @Override
    public Result getShortestPath(final String sourceNodeName,
                                  final String targetNodeName, final int limit) throws ClassNotFoundException {
        GraphTraversal t = graphRepository.getShortestPath(sourceNodeName, targetNodeName, nodeNameLabel, limit);
        Result result = new Result();

        for(Object obj : t.toList()) {
            transformPath((Path)obj, result, "#90c6cc", nodeNameLabel, "#ed4754");
        }

        return result;
    }

    @Override
    public Result getShortestPathAndNearestNodes(final String sourceNodeName,
                                                 final String targetNodeName, final int limit) throws ClassNotFoundException {
        Result result = getShortestPath(sourceNodeName, targetNodeName, limit);
        Set<Node> pathNodes = new HashSet<>();
        pathNodes.addAll(result.getNodes());

        for(Node node : pathNodes) {
            getFirstOrderRelatedNodes(node.getName(), result);
        }

        return result;
    }
}
