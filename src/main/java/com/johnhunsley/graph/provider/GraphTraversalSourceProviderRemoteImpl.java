package com.johnhunsley.graph.provider;

import org.apache.tinkerpop.gremlin.driver.Cluster;
import org.apache.tinkerpop.gremlin.driver.remote.DriverRemoteConnection;
import org.apache.tinkerpop.gremlin.process.traversal.dsl.graph.GraphTraversalSource;
import org.apache.tinkerpop.gremlin.structure.util.empty.EmptyGraph;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 *
 * in application.properties where a remote Graph end point is used ensure the following profile is active
 *
 * spring.profiles.active=remoteGraph
 * gremlin.cluster.end-point=my-tinkerpop-endpoint
 * gremlin.cluster.port=mytinkerpop-port
 *
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@Configuration
@Profile("remoteGraph")
public class GraphTraversalSourceProviderRemoteImpl implements GraphTraversalSourceProvider {

    @Value("${gremlin.cluster.end-point}")
    private String clusterEndPoint;

    @Value("${gremlin.cluster.port}")
    private int clusterPort;

    @Bean
    @Override
    public GraphTraversalSource provideSource() {
        System.out.println("######### Providing remote Graph Traversal Source from "+clusterEndPoint+":"+clusterPort);
        Cluster.Builder builder = Cluster.build();
        builder.addContactPoint(clusterEndPoint);
        builder.port(clusterPort);
        Cluster cluster = builder.create();
        return EmptyGraph.instance().traversal().withRemote(DriverRemoteConnection.using(cluster));
    }
}
