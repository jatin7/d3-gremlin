package com.johnhunsley.graph.api;

import com.johnhunsley.graph.d3domain.Result;
import com.johnhunsley.graph.service.D3Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 21/03/2018
 */
@Controller
@RequestMapping("api/")
public class D3GremlinController {

    @Autowired
    private D3Service d3Service;


    /**
     * <p>
     *     Find all the {@Link com.johnhunsley.graph.d3domain.Node} instances which are directly related to the node with the
     *     given name. This will search the graph by traversal and find the directly related nodes which have both in and out
     *     relationships. Those relationships are returned as {@link com.johnhunsley.graph.d3domain.Link} instances.
     *     The {@link com.johnhunsley.graph.d3domain.Node} with the given name is also returned.
     * </p>
     * @param nodeName
     * @return {@Link ResponseEntity} containing the query {@Link Result}
     */
    @CrossOrigin
    @RequestMapping(value = "relatedNodes/{nodeName}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Result> getNodeAndDirectRelations(@PathVariable("nodeName") final String nodeName) {
        return new ResponseEntity<>(d3Service.getFirstOrderRelatedNodes(nodeName, null), HttpStatus.OK);
    }

    /**
     * <p>
     *     Get all the Nodes in the Graph
     * </p>
     * @return {@Link ResponseEntity} containing the query {@Link Result}
     */
    @CrossOrigin
    @RequestMapping(value = "allNodes", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Result> getAllNodes() {
        return new ResponseEntity<>(d3Service.getAllNodes(), HttpStatus.OK);
    }


    /**
     * <p>
     *     Get all the {@link com.johnhunsley.graph.d3domain.Link} instances and create {@Link Node} instances
     *     from the ids specified in the {@link com.johnhunsley.graph.d3domain.Link} objects
     * </p>
     * @return {@Link ResponseEntity} containing the query {@Link Result}
     */
    @CrossOrigin
    @RequestMapping(value = "allEdges", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Result> getAllEdgesAndNodeIds() {
        return new ResponseEntity<>(d3Service.getAllEdges(), HttpStatus.OK);
    }

    @CrossOrigin
    @RequestMapping(value = "path/{source}/{target}/{limit}", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<Result> getShortestPathAndNearest(@PathVariable("source") final String source,
                                                            @PathVariable("target") final String target,
                                                            @PathVariable("limit") final int limit) {
        try {
            return new ResponseEntity<>(d3Service.getShortestPathAndNearestNodes(source, target, limit), HttpStatus.OK);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
