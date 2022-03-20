package com.hamke.shapes;

import javax.media.j3d.Shape3D;
import javax.media.j3d.TriangleFanArray;
import javax.vecmath.Color3f;
import javax.vecmath.Point3f;
import javax.vecmath.TexCoord3f;
import javax.media.j3d.Appearance;
import javax.media.j3d.QuadArray;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;


public class RectPrism extends Shape3D {
	// Constructor with predefined colors
    public RectPrism(float x, float y, float z) {
    	float[] vertices = {
    			// FRONT
        		x,-y,z,
        		x,y,z,
        		-x,y,z,
        		-x,-y,z,
        		// BACK
        		-x,-y,-z,
        		-x,y,-z,
        		x,y,-z,
        		x,-y,-z,
        		// RIGHT
        		x,-y,-z,
        		x,y,-z,
        		x,y,z,
        		x,-y,z,
        		// LEFT
        		-x,-y,z,
        		-x,y,z,
        		-x,y,-z,
        		-x,-y,-z,
        		// TOP
        		x,y,z,
        		x,y,-z,
        		-x,y,-z,
        		-x,y,z,
        		// BOTTOM
        		-x,-y,z,
        		-x,-y,-z,
        		x,-y,-z,
        		z,-y,z	
        };
    	Color3f[] colors = new Color3f[6];
    	colors[0] = new Color3f(1,0.5f,0.5f);
    	colors[1] = new Color3f(0,0,1);
    	colors[2] = new Color3f(0,1,0);
    	colors[3] = new Color3f(1,0,0);
    	colors[4] = new Color3f(1,1,0);
    	colors[5] = new Color3f(0,1,1);
    	QuadArray geom = new QuadArray(24, QuadArray.COORDINATES |
            QuadArray.COLOR_3);
     
        geom.setCoordinates(0, vertices);
        for (int i=0 ; i<24; i++) {
        	geom.setColor(i, colors[i/4]);
        }
        //geom.setColors(0, colors);
        this.setGeometry(geom);
        }
    
    
 // Constructor with predefined colors
    public RectPrism(float x, float y, float z, Appearance app) {
    	float[] vertices = {
    			// FRONT
        		x,-y,z,
        		x,y,z,
        		-x,y,z,
        		-x,-y,z,
        		// BACK
        		-x,-y,-z,
        		-x,y,-z,
        		x,y,-z,
        		x,-y,-z,
        		// RIGHT
        		x,-y,-z,
        		x,y,-z,
        		x,y,z,
        		x,-y,z,
        		// LEFT
        		-x,-y,z,
        		-x,y,z,
        		-x,y,-z,
        		-x,-y,-z,
        		// TOP
        		x,y,z,
        		x,y,-z,
        		-x,y,-z,
        		-x,y,z,
        		// BOTTOM
        		-x,-y,z,
        		-x,-y,-z,
        		x,-y,-z,
        		z,-y,z	
        };
    	QuadArray geom = new QuadArray(24, QuadArray.COORDINATES |
            QuadArray.NORMALS | QuadArray.TEXTURE_COORDINATE_3);
    	
    	TexCoord3f[] texCoord = new TexCoord3f[6];
    	texCoord[0] = new TexCoord3f(1,0.5f,0.5f);
    	texCoord[1] = new TexCoord3f(0,0,1);
    	texCoord[2] = new TexCoord3f(0,1,0);
    	texCoord[3] = new TexCoord3f(1,0,0);
    	texCoord[4] = new TexCoord3f(1,1,0);
    	texCoord[5] = new TexCoord3f(0,1,1);
        geom.setCoordinates(0, vertices);
        for(int i=0; i<24;i++) {
            //geom.setTextureCoordinate(0,i/4,texCoord[i/4]);
        		geom.setTextureCoordinate(0,i/4,new TexCoord3f(1,0,0));
        }
        
    	geom.setCoordinates(0, vertices);
    	GeometryInfo gi= new GeometryInfo (geom);
		NormalGenerator ng = new NormalGenerator();
		ng.generateNormals(gi);
		this.setGeometry(gi.getGeometryArray());
        this.setAppearance(app);
    }
    
    
    
    
    
    
}