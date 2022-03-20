package com.hamke.shapes;

import javax.media.j3d.Shape3D;
import javax.vecmath.Point3d;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class MyRamp extends Shape3D {
	public MyRamp( int smooth) {
		GeometryInfo gi = new GeometryInfo (GeometryInfo.POLYGON_ARRAY);
		   Point3d[] vertices = {
				   // 			0					1					2						3
				   new Point3d(0,0.8,0),new Point3d(0.35,0.8,0),new Point3d(0.35,0.8,0.4),new Point3d(0,0.8,0.4),
				   //				4					5
				   new Point3d(0,0,0),new Point3d(0,0,0.4),
				   //				6					7					8					9	
				   new Point3d(0.75,0,0.4),new Point3d(0.75,0,0),new Point3d(0.75,0.4,0),new Point3d(0.75,0.4,0.4)
		   };
		   int [] indices = {
				  0,4,5,3,
				   0,3,2,1, //*
				    // ** back
				   5,6,9,2,3, // 
				   4,7,6,5, //*
				   1,2,9,8, //**
				   8,9,6,7, // ** most front
				   0,1,8,7,4
			
		   };
		   	gi.setCoordinates(vertices);
		    gi.setCoordinateIndices(indices);
		    int[] stripCounts = {4,4,5,4,4,4,5};
		    gi.setStripCounts(stripCounts);
		    
		    NormalGenerator ng = new NormalGenerator();
		    ng.setCreaseAngle( (float) Math.toRadians(smooth) );
		    ng.generateNormals(gi);
		    this.setGeometry(gi.getGeometryArray());
		   }
	}

