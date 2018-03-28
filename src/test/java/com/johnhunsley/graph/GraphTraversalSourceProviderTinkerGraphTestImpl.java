package com.johnhunsley.graph;

import com.johnhunsley.graph.provider.GraphTraversalSourceProvider;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

/**
 * <p>
 *     A {@link GraphTraversalSourceProvider} which provides a {@link GraphTraversalSource} for an in-memory
 *     TinkerGraph GraphML instance initialized from the air-routes-small.graphml
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@Configuration
@Profile("integrationTest")
public class GraphTraversalSourceProviderTinkerGraphTestImpl implements GraphTraversalSourceProvider  {

    final static String PATH = "src/test/test-resources/air-routes-small.graphml";

    @Bean
    @Override
    public GraphTraversalSource provideSource() throws IOException {
        TinkerGraph tg = TinkerGraph.open();

        try {
            tg.io(IoCore.graphml()).readGraph(PATH);
            return  tg.traversal();

        } catch(IOException e) {
            System.out.println("Graph "+PATH+" file not found");
            throw e;
        }
    }
}
