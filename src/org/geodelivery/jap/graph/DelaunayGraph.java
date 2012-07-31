package org.geodelivery.jap.graph;

import org.geodelivery.jap.core.GeometryToGraph;

import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;
import com.vividsolutions.jts.operation.linemerge.LineMergeGraph;
import com.vividsolutions.jts.planargraph.PlanarGraph;
import com.vividsolutions.jts.triangulate.DelaunayTriangulationBuilder;

public class DelaunayGraph implements GeometryToGraph {
	
	public DelaunayGraph() {
		super();
	}

	public PlanarGraph computeGraph(Geometry geom) {

		DelaunayTriangulationBuilder triangulator = new DelaunayTriangulationBuilder();
		triangulator.setSites(geom);
		Geometry edges = triangulator.getEdges(new GeometryFactory());	

		LineMergeGraph graph = new LineMergeGraph();
		for (int i = 0; i < edges.getNumGeometries(); i++) {
			LineString ls = (LineString) edges.getGeometryN(i);
			graph.addEdge(ls);
		}
		return graph;
	}
	
}
