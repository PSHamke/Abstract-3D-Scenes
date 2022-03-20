package com.hamke.shapes;

import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class CustomGeom extends Shape3D {
	
	public CustomGeom() {
			// geometry for the cow plow
			final Point3f[] Vertices = {
	        // base triangle 1
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 1.0f),
	
	        // base triangle 2
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 1.0f), new Point3f(0.0f, 0.0f, 1.0f),
	
	        // left side
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(0.0f, 0.0f, 1.0f), new Point3f(0.0f, 1.0f, 0.0f),
	
	        // right side
	        new Point3f(1.0f, 0.0f, 0.0f), new Point3f(1.0f, 1.0f, 0.0f), new Point3f(1.0f, 0.0f, 1.0f),
	
	        // back triangle 1
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(0.0f, 1.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f),
	
	        // back triangle 2
	        new Point3f(1.0f, 1.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f), new Point3f(0.0f, 1.0f, 0.0f),
	
	        // front triangle 1
	        new Point3f(0.0f, 1.0f, 0.0f), new Point3f(0.0f, 0.0f, 1.0f), new Point3f(1.0f, 0.0f, 1.0f),
	
	        // front triangle 2
	        new Point3f(1.0f, 1.0f, 0.0f), new Point3f(0.0f, 1.0f, 0.0f),
	        new Point3f(1.0f, 0.0f, 1.0f)};
	
		
			final Color3f[] colors = {
			        // base triangle 1
			        new Color3f(0.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 1.0f),
			
			        // base triangle 2
			        new Color3f(0.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 1.0f), new Color3f(0.0f, 0.0f, 1.0f),
			
			        // left side
			        new Color3f(0.0f, 0.0f, 0.0f), new Color3f(0.0f, 0.0f, 1.0f), new Color3f(0.0f, 1.0f, 0.0f),
			
			        // right side
			        new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 1.0f, 0.0f), new Color3f(1.0f, 0.0f, 1.0f),
			
			        // back triangle 1
			        new Color3f(0.0f, 0.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f),
			
			        // back triangle 2
			        new Color3f(1.0f, 1.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f),
			
			        // front triangle 1
			        new Color3f(0.0f, 1.0f, 0.0f), new Color3f(0.0f, 0.0f, 1.0f), new Color3f(1.0f, 0.0f, 1.0f),
			
			        // front triangle 2
			        new Color3f(1.0f, 1.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f),
			        new Color3f(1.0f, 0.0f, 1.0f)};
			
			
	    GeometryInfo geometryInfo = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);
	    geometryInfo.setCoordinates(Vertices);
	    
	    geometryInfo.setColors(colors);
	    NormalGenerator normalGen = new NormalGenerator();
	    normalGen.generateNormals(geometryInfo);
	
	    // create shape from custom geometry
	    this.setGeometry(geometryInfo.getGeometryArray());

	}
	
	public CustomGeom(int Col) {
			// geometry for the cow plow
			final Point3f[] Vertices = {
	        // base triangle 1
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 1.0f),
	
	        // base triangle 2
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(1.0f, 0.0f, 1.0f), new Point3f(0.0f, 0.0f, 1.0f),
	
	        // left side
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(0.0f, 0.0f, 1.0f), new Point3f(0.0f, 1.0f, 0.0f),
	
	        // right side
	        new Point3f(1.0f, 0.0f, 0.0f), new Point3f(1.0f, 1.0f, 0.0f), new Point3f(1.0f, 0.0f, 1.0f),
	
	        // back triangle 1
	        new Point3f(0.0f, 0.0f, 0.0f), new Point3f(0.0f, 1.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f),
	
	        // back triangle 2
	        new Point3f(1.0f, 1.0f, 0.0f), new Point3f(1.0f, 0.0f, 0.0f), new Point3f(0.0f, 1.0f, 0.0f),
	
	        // front triangle 1
	        new Point3f(0.0f, 1.0f, 0.0f), new Point3f(0.0f, 0.0f, 1.0f), new Point3f(1.0f, 0.0f, 1.0f),
	
	        // front triangle 2
	        new Point3f(1.0f, 1.0f, 0.0f), new Point3f(0.0f, 1.0f, 0.0f),
	        new Point3f(1.0f, 0.0f, 1.0f)};
	
		
			final Color3f[] colors = {
			        // base triangle 1
			        new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f),
			
			        // base triangle 2
			        new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f), new Color3f(1.0f, 0.0f, 0.0f),
					
			        // left side
			        new Color3f(0.0f, 1.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f), new Color3f(0.0f, 1.0f, 0.0f),
			
			        // right side
			        new Color3f(0.2f, 0.3f, 0.4f), new Color3f(0.2f, 0.3f, 0.4f),new Color3f(0.2f, 0.3f, 0.4f),
			
			        // back triangle 1
			        new Color3f(0.8f, 0.6f, 0.4f), new Color3f(0.8f, 0.6f, 0.4f), new Color3f(0.8f, 0.6f, 0.4f),
			
			        // back triangle 2
			        new Color3f(0.8f, 0.6f, 0.4f), new Color3f(0.8f, 0.6f, 0.4f), new Color3f(0.8f, 0.6f, 0.4f),
					
			        // front triangle 1
			        new Color3f(0.0f, 1.0f, 1.0f), new Color3f(0.0f, 1.0f, 1.0f), new Color3f(0.0f, 1.0f, 1.0f),
			
			        // front triangle 2
			        new Color3f(0.0f, 1.0f, 1.0f), new Color3f(0.0f, 1.0f, 1.0f), new Color3f(0.0f, 1.0f, 1.0f)
					
			};
			
	    GeometryInfo geometryInfo = new GeometryInfo(GeometryInfo.TRIANGLE_ARRAY);
	    geometryInfo.setCoordinates(Vertices);
	    
	    geometryInfo.setColors(colors);
	    NormalGenerator normalGen = new NormalGenerator();
	    normalGen.generateNormals(geometryInfo);
	
	    // create shape from custom geometry
	    this.setGeometry(geometryInfo.getGeometryArray());
	
}
}
  

