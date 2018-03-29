# d3-gremlin
A Java (Spring Boot) REST API which executes queries using gremlin on graph data resources to produce data, nodes, links and options, in a format acceptable to the [vue-d3-network](https://github.com/emiliorizzo/vue-d3-network) component

# Execution

This is a Spring Boot app which can be executed with the included spring-boot maven plugin like so

`<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
      <jvmArguments>
        -Dspring.profiles.active=localGraph -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=5005
      </jvmArguments>
    </configuration>
 </plugin>`


Or by executing the package .jar file from from the terminal and specifying a complete set of production configurations

`java -jar d3-gramlin-<version> --spring.config.name=/path/to/app/properties
             `
Or just the profile which defines the connection type to the underlying graphDB

`java -jar d3-gramlin-<version> --spring.profiles.active=localGraph`

There are various built in profiles which will provide a connection to a graphDB and return a traversal source via the GraphTraversalSourceProvider stereotype

 - localGraph
 - remoteGraph

localGraph uses the GraphTraversalSourceProviderTinkerGraphImpl class which will load a TinkerGraph from a graphML file at the location specified by the tinkergraph.graphml.file.path property
remoteGraph uses the GraphTraversalSourceProviderRemoteImpl class which will provide a connection to a remote graph at the end point and port specified in the following properties

 - gremlin.cluster.end-point
 - gremlin.cluster.port

Note: the integrationTest profile will run a local TinkerGraph at a fixed location specified in test-resources

For EC2 UserData - execution on start up, use nohup in the following script
 `#!/bin/bash
 nohup /usr/bin/java -jar /home/ec2-user/returns-1.0.jar --spring.config.name=/home/ec2-user/lymm.application.properties & > /home/ec2-user/nohup.out
 `
