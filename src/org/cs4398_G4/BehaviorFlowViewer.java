package org.cs4398_G4;


/*
 * EditingGraphViewer.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */



import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

import org.apache.commons.collections15.Factory;
public class BehaviorFlowViewer extends JPanel {


	    Graph<Integer, String> g;
	    int nodeCount, edgeCount;
	    Factory <Integer> vertexFactory;
	    Factory<String> edgeFactory;
		private VisualizationViewer<Integer,String> vv;
	    
	    /** Creates a new instance of SimpleGraphView */
	    public BehaviorFlowViewer() {
	        // Graph<V, E> where V is the type of the vertices and E is the type of the edges
	        g = new SparseMultigraph<Integer, String>();
	        nodeCount = 0; edgeCount = 0;
	        vertexFactory = new Factory<Integer>() { // My vertex factory
	            public Integer create() {
	                return nodeCount++;
	            }
	        };
	        edgeFactory = new Factory<String>() { // My edge factory
	            public String create() {
	                return "E"+edgeCount++;
	            }
	        };
	        
	        Layout<Integer, String> layout = new StaticLayout(this.g);
	        layout.setSize(new Dimension(300,300));
	        vv = new VisualizationViewer<Integer,String>(layout);
	        vv.setPreferredSize(new Dimension(350,350));
	        // Show vertex and edge labels
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller());
	        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	        // Create a graph mouse and add it to the visualization viewer
	        // Our Vertices are going to be Integer objects so we need an Integer factory
	        EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), 
	        		this.vertexFactory, this.edgeFactory); 
	        vv.setGraphMouse(gm);
	        
	        
	        

	        this.add(vv);
	        
	        updateRooms();
	    }

		private void updateRooms() {
			// TODO Auto-generated method stub
			g.addVertex(0);
//			g.
//			g.getVertices().toArray()[0].
		
			
		}
		
		private class RoomVertextLabeller extends ToStringLabeller<Object> {
			
		}
	    

	    
	}