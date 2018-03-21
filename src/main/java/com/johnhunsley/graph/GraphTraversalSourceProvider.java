package com.johnhunsley.graph;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

import java.io.IOException;

/**
 * <p>
 *     Provides a {@link GraphTraversalSource}
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 *         Time : 16:02
 */

public interface GraphTraversalSourceProvider {

    /**
     *
     * @return {@link GraphTraversalSource}
     */
    GraphTraversalSource provideSource() throws IOException;
}
