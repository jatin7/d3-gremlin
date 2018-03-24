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
     * @return @return {@link Result} containing the resulting nodes and links
     */
    Result getNodeAndDirectRelations(String nodeName);
}
