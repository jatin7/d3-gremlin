package com.johnhunsley.graph.d3domain;

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
}
