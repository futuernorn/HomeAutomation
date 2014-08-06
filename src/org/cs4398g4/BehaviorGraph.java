package org.cs4398g4;

import java.util.ArrayList;

import org.apache.commons.collections15.Factory;

import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;

public class BehaviorGraph {
    Graph<Integer, String> g;
    public Graph<Integer, String> getG() {
		return g;
	}
	public void setG(Graph<Integer, String> g) {
		this.g = g;
	}
	int nodeCount, edgeCount;
    Factory <Integer> vertexFactory;

	Factory<String> edgeFactory;

	private ArrayList<Integer> selectedBehaviors;
    public BehaviorGraph() {
    	selectedBehaviors = new ArrayList<Integer>();
    g = new SparseMultigraph<Integer, String>();
    nodeCount = 0; edgeCount = 0;
    
    vertexFactory = new Factory<Integer>() { // My vertex factory
        public Integer create() {
            return nodeCount++;
//        	return -1;
        }
    };
    edgeFactory = new Factory<String>() { // My edge factory
    

		public String create() {
//        	System.out.println(pickedState);
        	selectedBehaviors = new ArrayList<Integer>();
            return "E"+edgeCount++;
        }
    };
    }
    
    public void AddSelectedVertex(Integer newVert) {
    	selectedBehaviors.add(newVert);
    }
    
    public Factory<Integer> getVertexFactory() {
		return vertexFactory;
	}
	public Factory<String> getEdgeFactory() {
		return edgeFactory;
	}

}
