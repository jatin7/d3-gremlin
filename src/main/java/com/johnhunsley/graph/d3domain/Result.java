package com.johnhunsley.graph.d3domain;

import java.util.HashSet;
import java.util.Set;

/**
 * <p>
 *     The API response to a query request which will contain the resulting {@link Node} objects,
 *     {@Link Link} objects and the {@Link Options} object which are used to populate the vue-d3-network component
 *
 *     https://github.com/emiliorizzo/vue-d3-network
 *
 * </p>
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
public class Result {
    private Set<Node> nodes;
    private Set<Link> links;
    private Options options;

    public Set<Node> getNodes() {
        return nodes;
    }

    public void setNodes(Set<Node> nodes) {
        this.nodes = nodes;
    }

    public Set<Link> getLinks() {
        return links;
    }

    public void setLinks(Set<Link> links) {
        this.links = links;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    public void addNode(Node node) {
        if(this.nodes == null)
            this.nodes = new HashSet<>();

        this.nodes.add(node);
    }

    public void addLink(Link link) {
        if(this.links == null)
            this.links = new HashSet<>();

        this.links.add(link);
    }
}
