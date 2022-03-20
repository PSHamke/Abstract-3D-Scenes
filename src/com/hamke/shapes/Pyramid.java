package com.hamke.shapes;

import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleFanArray;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord3f;
import javax.media.j3d.Appearance;
import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;
public class Pyramid extends Shape3D{
	public Pyramid(float h, float b, Appearance app) {
		int[] stripVertexCounts= {6};
		TriangleFanArray geom = new TriangleFanArray(6,TriangleFanArray.COORDINATES
				| TriangleFanArray.NORMALS | TriangleFanArray.TEXTURE_COORDINATE_3,
				stripVertexCounts);
		
		geom.setCoordinate(0, new Point3f(0,h,0));
		geom.setCoordinate(1, new Point3f(-b,0,b));
		geom.setCoordinate(2, new Point3f(b,0,b));
		geom.setCoordinate(3, new Point3f(b,0,-b));
		geom.setCoordinate(4, new Point3f(-b,0,-b));
		geom.setCoordinate(5, new Point3f(-b,0,b));
		
		geom.setTextureCoordinate(0,0,new TexCoord3f(0.5f,1f,0.5f));
		geom.setTextureCoordinate(0,1,new TexCoord3f(0f,0f,1f));
		geom.setTextureCoordinate(0,2,new TexCoord3f(1f,0f,1f));
		geom.setTextureCoordinate(0,3,new TexCoord3f(1f,0f,0f));
		geom.setTextureCoordinate(0,4,new TexCoord3f(0f,0f,0f));
		geom.setTextureCoordinate(0,5,new TexCoord3f(0f,0f,1f));
		
		GeometryInfo gi= new GeometryInfo (geom);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		this.setGeometry(gi.getGeometryArray());
		this.setAppearance(app);
	}
	public Pyramid(float h, float b) {
		int[] stripVertexCounts= {7};
		TriangleFanArray geom = new TriangleFanArray(7,TriangleFanArray.COORDINATES
				| TriangleFanArray.NORMALS | TriangleFanArray.TEXTURE_COORDINATE_3,
				stripVertexCounts);
		
		geom.setCoordinate(0, new Point3f(0,h,0));
		geom.setCoordinate(1, new Point3f(-b,0,b));
		geom.setCoordinate(2, new Point3f((b/5)*4,0,(b/5)*4));
		geom.setCoordinate(3, new Point3f((b/5)*4,0,-(b/5)*4));
		geom.setCoordinate(4, new Point3f(-(b/5)*4,0,-(b/5)*4));
		geom.setCoordinate(5, new Point3f((b/5)*4,0,-(b/5)*4));
		geom.setCoordinate(6, new Point3f(-b,0,b));
	
		
		
		
		GeometryInfo gi= new GeometryInfo (geom);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		this.setGeometry(gi.getGeometryArray());
	
	}
}
