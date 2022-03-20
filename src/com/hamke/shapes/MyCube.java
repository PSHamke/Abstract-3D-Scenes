package com.hamke.shapes;

import javax.media.j3d.Shape3D;
import javax.vecmath.Point3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class MyCube extends Shape3D {

	public MyCube(int smooth) {
		GeometryInfo gi = new GeometryInfo(GeometryInfo.QUAD_ARRAY);
        Point3f[] pts =  {
        		 new Point3f(-1f, 1f, 1f),
        	     new Point3f(1f, 1f, 1f),  
        	     new Point3f(1f, -1f, 1f),
        	     new Point3f(-1f, -1f, 1f),
        	     new Point3f(-1f, 1f, -1f),
        	     new Point3f(1f, 1f, -1f),
        	     new Point3f(1f, -1f, -1f),
        	     new Point3f(-1f, -1f, -1f)	
        };
       
        
        int[] indices = {
            0,4,7,3, // left face   
            3,7,6,2, // bottom face
            4,5,6,7, // back face   
            5,1,2,6, // right face  
            5,4,0,1, // top face    
            3,2,1,0 // front face
        };
        
      
        gi.setCoordinates(pts);
        gi.setCoordinateIndices(indices);
        NormalGenerator ng = new NormalGenerator();
        // smoothness of vertices 
        ng.setCreaseAngle( (float) Math.toRadians(smooth) );
        ng.generateNormals(gi);
	    this.setGeometry(gi.getGeometryArray());
	}
}
