package com.johnhunsley.graph.service;

import com.johnhunsley.graph.d3domain.Result;

import java.util.Iterator;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
public interface D3Service {

    /**
     * <p>
     *     Get all the {@link com.johnhunsley.graph.d3domain.Node}s in the Graph
     * </p>
     * @return {@link Result} containing the resulting nodes
     */
    Result getAllNodes();

    /**
     * <p>
     *     Get all the Edges and Node ids
     * </p>
     * @return
     */
    Result getAllEdges();

    /**
     * <p>
     *     Get all the directly related {@link com.johnhunsley.graph.d3domain.Node}s to the given node name
     *     related by out or in relationships
     * </p>
     * @param nodeName
     * @param response
     * @return @return {@link Result} containing the resulting nodes and links
     */
    Result getFirstOrderRelatedNodes(String nodeName, Result response);

    /**
     * <p>
     *     Gets the Node and Links returned in the {@link org.apache.tinkerpop.gremlin.process.traversal.Path}
     *     instances for a query to find the shortest paths, showing both in and out links, between the two
     *     Nodes with the given names as 'desc' properties on the Verticies.
     * </p>
     * @param sourceNodeName
     * @param targetNodeName
     * @return
     * @throws ClassNotFoundException
     */
    Result getShortestPath(String sourceNodeName, String targetNodeName) throws ClassNotFoundException;

    /**
     * <p>
     *     Gets the shortest path and also the nearest nodes, and links to those nodes, of all the nodes in the path
     * </p>
     * @param sourceNodeName
     * @param targetNodeName
     * @return
     * @throws ClassNotFoundException
     */
    Result getShortestPathAndNearestNodes(String sourceNodeName,
                                          String targetNodeName) throws ClassNotFoundException;
}
