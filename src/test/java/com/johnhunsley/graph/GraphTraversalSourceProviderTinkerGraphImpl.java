package com.johnhunsley.graph;

import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.io.IoCore;
import org.apache.tinkerpop.gremlin.tinkergraph.structure.TinkerGraph;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 *         Time : 16:11
 */
@Configuration
@Profile("integrationTest")
public class GraphTraversalSourceProviderTinkerGraphImpl implements GraphTraversalSourceProvider {
    final String path = "air-routes-small.graphml";

    @Bean
    @Override
    public GraphTraversalSource provideSource() throws IOException {
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
