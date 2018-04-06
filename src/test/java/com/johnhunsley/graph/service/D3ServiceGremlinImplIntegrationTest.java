package com.johnhunsley.graph.service;

import com.johnhunsley.graph.d3domain.Node;
import com.johnhunsley.graph.d3domain.Result;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author John Hunsley
 *         jphunsley@gmail.com
 *         Date : 26/03/2018
 *         Time : 16:56
 */
@SpringBootTest
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("integrationTest")
public class D3ServiceGremlinImplIntegrationTest {

    @Autowired
    private D3Service d3Service;

    @Test
    public void testGetAllEdges() {
        Result result = d3Service.getAllEdges();

        SortedSet<Node> orderedNodes = new TreeSet<>((o1, o2) -> {
            Integer int1 = Integer.parseInt(o1.getId());
            Integer int2 = Integer.parseInt(o2.getId());
            return int1.compareTo(int2);
        });

        orderedNodes.addAll(result.getNodes());

        for (Node node : orderedNodes) {
            System.out.println(node.getId());
        }

        assertTrue(result.getNodes().size() == 46);
        assertTrue(result.getLinks().size() == 1326);
    }

    @Test
    public void testGetAllVerticies() {
        Result result = d3Service.getAllNodes();

        SortedSet<Node> orderedNodes = new TreeSet<>((o1, o2) -> {
            Integer int1 = Integer.parseInt(o1.getId());
            Integer int2 = Integer.parseInt(o2.getId());
            return int1.compareTo(int2);
        });

        orderedNodes.addAll(result.getNodes());

        for (Node node : orderedNodes) {
            System.out.println(node.getName());
        }

        assertTrue(result.getNodes().size() == 47);
    }

    @Test
    public void getFirstOrderRelatedNodes() {
        Result result = d3Service.getFirstOrderRelatedNodes("Seattle-Tacoma", null);

        SortedSet<Node> orderedNodes = new TreeSet<>((o1, o2) -> {
            Integer int1 = Integer.parseInt(o1.getId());
            Integer int2 = Integer.parseInt(o2.getId());
            return int1.compareTo(int2);
        });

        orderedNodes.addAll(result.getNodes());

        for (Node node : orderedNodes) {
            System.out.println(node.getId());
        }

        assertTrue(result.getNodes().size() == 38);
        assertTrue(result.getLinks().size() == 74);

    }

    @Test
    public void testShortestPath() {
        try {
            Result result = d3Service.getShortestPath("Long Beach Airport", "Santa Fe");

            SortedSet<Node> orderedNodes = new TreeSet<>((o1, o2) -> {
                Integer int1 = Integer.parseInt(o1.getId());
                Integer int2 = Integer.parseInt(o2.getId());
                return int1.compareTo(int2);
            });

            orderedNodes.addAll(result.getNodes());

            for (Node node : orderedNodes) {
                System.out.println(node.getId());
            }

            assertTrue(result.getNodes().size() == 5);
            assertTrue(result.getLinks().size() == 7);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }

    @Test
    public void testGetShortestPathAndNearestNodes() {
        try {
            Result result = d3Service.getShortestPathAndNearestNodes("Long Beach Airport", "Santa Fe");

            SortedSet<Node> orderedNodes = new TreeSet<>((o1, o2) -> {
                Integer int1 = Integer.parseInt(o1.getId());
                Integer int2 = Integer.parseInt(o2.getId());
                return int1.compareTo(int2);
            });

            orderedNodes.addAll(result.getNodes());

            for (Node node : orderedNodes) {
                System.out.println(node.getId());
            }

            assertTrue(result.getNodes().size() == 43);
            assertTrue(result.getLinks().size() == 238);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            fail();
        }
    }


}
