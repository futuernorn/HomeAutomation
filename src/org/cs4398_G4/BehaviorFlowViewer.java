package org.cs4398_G4;


/*
 * EditingGraphViewer.java
 *
 * Created on March 8, 2007, 7:49 PM; Updated May 29, 2007
 *
 * Copyright March 8, 2007 Grotto Networking
 */



import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.GraphElementAccessor;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.algorithms.layout.StaticLayout;
import edu.uci.ics.jung.graph.Graph;
import edu.uci.ics.jung.graph.SparseMultigraph;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.AbstractPopupGraphMousePlugin;
import edu.uci.ics.jung.visualization.control.EditingModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.picking.PickedState;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;

import org.apache.commons.collections15.Factory;
public class BehaviorFlowViewer extends JPanel {



	    LocalInterface view;
	    BehaviorGraph graph;
		private VisualizationViewer<Integer,String> vv;
		private Layout<Integer, String> layout;
		final PickedState<Integer> pickedState;
		

	    /** Creates a new instance of SimpleGraphView */
	    public BehaviorFlowViewer(final LocalInterface view, final BehaviorGraph graph) {
	    	this.view = view;
	    	this.graph = graph;
	    	
	        // Graph<V, E> where V is the type of the vertices and E is the type of the edges

	        
	        layout = new CircleLayout(graph.getG());
	        layout.setSize(new Dimension(300,300));
	        vv = new VisualizationViewer<Integer,String>(layout);
	        vv.setPreferredSize(new Dimension(350,350));
	        System.out.println(view.getFrmHomeAutomationSystem().getContentPane().getSize());
	        vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller() {
	            @Override
	            public String transform(Object v) {
	            	return view.getBaseStation().getBehvaiorName((Integer)v);
	            }});
	        vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller());
	        // Create a graph mouse and add it to the visualization viewer
	        // Our Vertices are going to be Integer objects so we need an Integer factory
	        EditingModalGraphMouse gm = new EditingModalGraphMouse(vv.getRenderContext(), 
	        		graph.vertexFactory, graph.edgeFactory);
	        
	        gm.getPopupEditingPlugin();
	        

	        final PickedState<String> pickedEdgeState =  vv.getPickedEdgeState();
	   
	        
	     // Attach the listener that will print when the vertices selection changes.


	        pickedState = vv.getPickedVertexState();
	     // Attach the listener that will print when the vertices selection changes.
	        pickedState.addItemListener(new ItemListener() {

	            public void itemStateChanged(ItemEvent e) {
	                Object subject = e.getItem();
	                // The graph uses Integers for vertices.
	                if (subject instanceof Integer) {
	                    Integer vertex = (Integer) subject;
	                    graph.AddSelectedVertex(vertex);
	                    if (pickedState.isPicked(vertex)) {
	                        System.out.println("Vertex " + vertex
	                            + " is now selected");
	                    } else {
	                        System.out.println("Vertex " + vertex
	                            + " no longer selected");
	                    }
	                }
	            }
	        });
//	        gm.add(new PopupGraphMousePlugin());
	        vv.setGraphMouse(gm);

	        
//	        gm.setMode(ModalGraphMouse.Mode.EDITING); // Start off in editing mode
	        gm.setMode(ModalGraphMouse.Mode.PICKING);

	        this.add(vv);
	        
			this.addAncestorListener ( new AncestorListener () {
		        public void ancestorAdded ( AncestorEvent event )
		        {
		            // Component added somewhere
		        	System.out.println("BehaviorFlowViewer added.");
		        	updateBehaviors();
		        }
		        
		        public void ancestorRemoved ( AncestorEvent event )
		        {
		            // Component removed from container
		        }

		        public void ancestorMoved ( AncestorEvent event )
		        {
		            // Component container moved
		        }
		        
		    } );
	        
	        
	    }

		private void updateBehaviors() {
			// TODO Auto-generated method stub
//			System.out.println(view.getBaseStation());
			for (Behavior behavior: view.getBaseStation().getBehvaiors()) {
//				System.out.println(view.getBaseStation().getBehvaiors() + " => " + behavior.hashCode());
				graph.getG().addVertex(behavior.hashCode());
//				vertexFactory.create();
				
//				layout.
			     layout = new CircleLayout(graph.getG());
//			        System.out.println(view.getFrmHomeAutomationSystem().getContentPane().getSize());
			        layout.setSize(view.getFrmHomeAutomationSystem().getContentPane().getSize());
			        vv.setPreferredSize(view.getFrmHomeAutomationSystem().getContentPane().getSize());
			        vv.setGraphLayout(layout);
				 vv.repaint();
//				 layout = new CircleLayout(this.g);
//				 vv = new VisualizationViewer<Integer,String>(layout);
				 
			}
//			g.
//			g.getVertices().toArray()[0].
		
			
		}

//		protected class MyPopupGraphMousePlugin implements ItemListener {
//
//			public void itemStateChanged(ItemEvent arg0) {
//				// TODO Auto-generated method stub
////				System.out.println(g.getEdges());
//				
//			}
//
//
//		}
		
		/**
		 * a GraphMousePlugin that offers popup
		 * menu support
		 */
		protected class PopupGraphMousePlugin extends AbstractPopupGraphMousePlugin
		implements MouseListener {

		    public PopupGraphMousePlugin() {
		        this(MouseEvent.BUTTON3_MASK);
		    }
		    public PopupGraphMousePlugin(int modifiers) {
		        super(modifiers);
		    }

		    /**
		     * If this event is over a station (vertex), pop up a menu to
		     * allow the user to perform a few actions; else, pop up a menu over the layout/canvas
		     *
		     * @param e
		     */
		    protected void handlePopup(MouseEvent e) {

		    	JPopupMenu popup = new JPopupMenu();

		            popup.add(new AbstractAction("Add Connection") {
		                public void actionPerformed(ActionEvent e) {
		                    
		                }
		            });


	
		        

		    }
		}

	    
	}