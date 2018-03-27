package com.johnhunsley.graph;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;

import java.io.IOException;

/**
 * <p>
 *     Provides a {@link GraphTraversalSource} as a {@link org.springframework.context.annotation.Bean}
 *     in a {@link org.springframework.context.annotation.Configuration} which should be activated by a specific
 *     {@link org.springframework.context.annotation.Profile}. Implementations of this interface should be annotated
 *     as such -
 *
 *      @Configuration
 *      @Profile("myprofile")
 *
 *      ...
 *      ....
 *      @Bean
 *      public GraphTraversalSource provideSource() { ....
 *
 *      The {@link GraphTraversalSource} may then be autowired and is injected according to the provider
 *      which is active for the executed profile
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */

public interface GraphTraversalSourceProvider {

    /**
     *
     * @return {@link GraphTraversalSource}
     */
    GraphTraversalSource provideSource() throws IOException;
}
