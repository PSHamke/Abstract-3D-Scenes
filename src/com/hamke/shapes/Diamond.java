package com.hamke.shapes;

import javax.media.j3d.Appearance;
import javax.media.j3d.Geometry;
import javax.media.j3d.IndexedGeometryArray;
import javax.media.j3d.IndexedTriangleFanArray;

import javax.media.j3d.Shape3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord3f;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

 public class Diamond extends Shape3D
 {
 public Diamond()
 {
	 int stripVertexCount[] = {6,6};
	 IndexedTriangleFanArray geom = new IndexedTriangleFanArray
	 (12, IndexedGeometryArray.COORDINATES | IndexedGeometryArray.TEXTURE_COORDINATE_3, 12, stripVertexCount);

	 geom.setCoordinate( 0, new Point3f(0f, 0.8f, 0f));
	 geom.setCoordinate( 1, new Point3f(0f, 0f, 0.5f));
	 geom.setCoordinate( 2, new Point3f(0.5f, 0f, 0f));
	 geom.setCoordinate( 3, new Point3f(0f, 0f, -0.5f));
	 geom.setCoordinate( 4, new Point3f(-0.5f, 0f, 0f));
	 geom.setCoordinate( 5, new Point3f(0f, -0.8f, 0f));

	 geom.setTextureCoordinate(0,0, new TexCoord3f(0.5f,1f,0.5f));
	 geom.setTextureCoordinate(0,1, new TexCoord3f(0f,0f,1f));
	 geom.setTextureCoordinate(0,2, new TexCoord3f(1f,0f,1f));
	 geom.setTextureCoordinate(0,3, new TexCoord3f(1f,1f,0f));
	 geom.setTextureCoordinate(0,4, new TexCoord3f(0f,0f,0f));
	 geom.setTextureCoordinate(0,5, new TexCoord3f(0.0f,0f,1f));

	 
	 //Top Diamond
	 geom.setCoordinateIndex( 0, 0); geom.setCoordinateIndex( 1, 1);
	 geom.setCoordinateIndex( 2, 2);
	 geom.setCoordinateIndex( 3, 3); geom.setCoordinateIndex( 4, 4);
	 geom.setCoordinateIndex( 5, 1);

	 //Bottom Diamond
	 geom.setCoordinateIndex( 6, 5); geom.setCoordinateIndex( 7, 4);
	 geom.setCoordinateIndex( 8, 3);
	 geom.setCoordinateIndex( 9, 2); geom.setCoordinateIndex( 10, 1);
	 geom.setCoordinateIndex( 11, 4);
	 
	GeometryInfo gi= new GeometryInfo (geom);
	NormalGenerator ng = new NormalGenerator();
	ng.generateNormals(gi);
	this.setGeometry(gi.getGeometryArray());
	
	
 }
 public Diamond(float height, float base)
 {
	 int stripVertexCount[] = {6,6};
	 IndexedTriangleFanArray geom = new IndexedTriangleFanArray
	 (12, IndexedGeometryArray.COORDINATES | IndexedGeometryArray.TEXTURE_COORDINATE_3 
			 | IndexedGeometryArray.COLOR_3, 12, stripVertexCount);

	 geom.setCoordinate( 0, new Point3f(0f, height, 0f));
	 geom.setCoordinate( 1, new Point3f(0f, 0f, base));
	 geom.setCoordinate( 2, new Point3f(base, 0f, 0f));
	 geom.setCoordinate( 3, new Point3f(0f, 0f, -base));
	 geom.setCoordinate( 4, new Point3f(-base, 0f, 0f));
	 geom.setCoordinate( 5, new Point3f(0f, -height, 0f));

	 geom.setColor(0,new Color3f(1.0f, 1.0f, 0.0f));
	 geom.setColor(1,new Color3f(0.5f, 1.0f, 0.0f));
	 geom.setColor(2,new Color3f(0f, 1.0f, 0.4f));
	 geom.setColor(3,new Color3f(1.0f, 1.0f, 0.2f));
	 geom.setColor(4,new Color3f(0.2f, 0.8f, 0.5f));
	 geom.setColor(5,new Color3f(0.5f, 0.4f, 0.9f));
	 Color3f sidecolor[] = {
				new Color3f(1.0f, 1.0f, 0.0f),
				new Color3f(1.0f, 1.0f, 0.0f),
				new Color3f(1.0f, 1.0f, 0.0f),

				new Color3f(0.0f, 1.0f, 1.0f),
				new Color3f(0.0f, 1.0f, 1.0f),
				new Color3f(0.0f, 1.0f, 1.0f),				

				new Color3f(1.0f, 0.0f, 0.0f),
				new Color3f(1.0f, 0.0f, 0.0f),
				new Color3f(1.0f, 0.0f, 0.0f),

				new Color3f(0.0f, 1.0f, 0.0f),
				new Color3f(0.0f, 1.0f, 0.0f),
				new Color3f(0.0f, 1.0f, 0.0f),

				new Color3f(0.0f, 0.0f, 1.0f),
				new Color3f(0.0f, 0.0f, 1.0f),
				new Color3f(0.0f, 0.0f, 1.0f)
	 };
	 
	 geom.setTextureCoordinate(0,0, new TexCoord3f(0.5f,1f,0.5f));
	 geom.setTextureCoordinate(0,1, new TexCoord3f(0f,0f,1f));
	 geom.setTextureCoordinate(0,2, new TexCoord3f(1f,0f,1f));
	 geom.setTextureCoordinate(0,3, new TexCoord3f(1f,1f,0f));
	 geom.setTextureCoordinate(0,4, new TexCoord3f(0f,0f,0f));
	 geom.setTextureCoordinate(0,5, new TexCoord3f(0.0f,0f,1f));

	 
	 //Top Diamond
	 geom.setCoordinateIndex( 0, 0); geom.setCoordinateIndex( 1, 1);
	 geom.setCoordinateIndex( 2, 2);
	 geom.setCoordinateIndex( 3, 3); geom.setCoordinateIndex( 4, 4);
	 geom.setCoordinateIndex( 5, 1);

	 //Bottom Diamond
	 geom.setCoordinateIndex( 6, 5); geom.setCoordinateIndex( 7, 4);
	 geom.setCoordinateIndex( 8, 3);
	 geom.setCoordinateIndex( 9, 2); geom.setCoordinateIndex( 10, 1);
	 geom.setCoordinateIndex( 11, 4);
	 
	 geom.setColorIndex(0, 0);
	 geom.setColorIndex(1, 1);
	 geom.setColorIndex(2, 2);
	 geom.setColorIndex(3, 3);
	 geom.setColorIndex(4, 4);
	 geom.setColorIndex(5, 1);
	 geom.setColorIndex(6, 5);
	 geom.setColorIndex(7, 4);
	 geom.setColorIndex(8, 3);
	 geom.setColorIndex(9, 2);
	 geom.setColorIndex(10, 1);
	 geom.setColorIndex(11, 4);
	
	GeometryInfo gi= new GeometryInfo (geom);
	NormalGenerator ng = new NormalGenerator();
	ng.generateNormals(gi);
	this.setGeometry(gi.getGeometryArray());
 }



 
 }