package com.johnhunsley.graph.provider;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

/**
 * <p>
 *     A {@link GraphTraversalSourceProvider} which provides a {@link GraphTraversalSource} for an in-memory
 *     TinkerGraph GraphML instance initialized from a path to a graphML file specified in application.properties as
 *
 *     tinkerpop.graphml.file.path
 *
 *     Marked as localGraph profile.
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@Configuration
@Profile("localGraph")
public class GraphTraversalSourceProviderTinkerGraphImpl implements GraphTraversalSourceProvider {
    @Value("${tinkergraph.graphml.file.path}")
    protected String path;

    @Bean
    @Override
    public GraphTraversalSource provideSource() throws IOException {
        System.out.println("######### Providing local Graph Traversal Source from path "+path);
        TinkerGraph tg = TinkerGraph.open();

        try {
            tg.io(IoCore.graphml()).readGraph(path);
            return  tg.traversal();

        } catch(IOException e) {
            System.out.println("Graph "+path+" file not found");
            throw e;
        }
    }
}
