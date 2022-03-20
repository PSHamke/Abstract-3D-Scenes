package com.hamke.shapes;

import javax.media.j3d.*;
import javax.vecmath.*;

public class Icosahedra extends Shape3D {

public	TriangleArray triddy;

float a = (1.0f + (float) Math.sqrt(5))/2.0f; 
		Point3f p1 = new Point3f( 0.0f, 1.0f, a);
 	   Point3f p2 = new Point3f( 0.0f, -1.0f, a);
		Point3f p3 = new Point3f( 0.0f, 1.0f, -a);
		Point3f p4 = new Point3f( 0.0f, -1.0f, -a);
		Point3f p5 = new Point3f(1.0f, a, 0.0f);
		Point3f p6 = new Point3f(1.0f, -a, 0.0f);
		Point3f p7 = new Point3f(-1.0f, a, 0.0f);
		Point3f p8 = new Point3f(-1.0f, -a, 0.0f);	
		Point3f p9 = new Point3f(a, 0.0f, 1.0f);
		Point3f p10 = new Point3f(-a, 0.0f, 1.0f);
		Point3f p11 = new Point3f(a, 0.0f, -1.0f);
		Point3f p12 = new Point3f(-a, 0.0f, -1.0f);
			


    private final Point3f[] verts = {
	p1, p2, p9, 
	p2, p1, p10,
	p9, p2, p6,
	p6, p2, p8,
	p2, p10, p8,
	p8, p10, p12,
	p8, p12, p4,
	p12, p7, p3,
	p12, p10, p7,
	p12, p3, p4,
	p4, p3, p11,
	p11, p3, p5,
	p11, p5, p9,
	p9, p5, p1,
	p1, p5, p7,
	p7, p5, p3,
	p6, p4, p11,
	p1, p7, p10,
	p6, p8, p4,
	p11, p9, p6
    };

  
    private TexCoord2f texCoord[] = {
        new TexCoord2f(0.0f, 0.0f),
		  new TexCoord2f(1.0f, 0.0f),
        new TexCoord2f(0.5f, (float) Math.sqrt(3) / 2.0f) };
		  
   public Color3f sidecolor[] = {
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
		new Color3f(0.0f, 0.0f, 1.0f),
		
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
		new Color3f(0.0f, 0.0f, 1.0f),

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
		new Color3f(0.0f, 0.0f, 1.0f),

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
		new Color3f(0.0f, 0.0f, 1.0f)};
		
 public Icosahedra() {
	int i;
int[] texCoordMap = new int[6];
	texCoordMap[0] = 	0;
	texCoordMap[1] = 	0;
	texCoordMap[2] = 	1;
   texCoordMap[3] =  0;
	texCoordMap[4] = 	1;
	texCoordMap[5] = 	1;

	triddy = new TriangleArray(60, GeometryArray.COORDINATES 
	 |	GeometryArray.NORMALS | GeometryArray.COLOR_3 | 
	 GeometryArray.TEXTURE_COORDINATE_2, 3, texCoordMap);
	triddy.setCoordinates(0, verts);


	Vector3f normal = new Vector3f();
	Vector3f v1 = new Vector3f();
	Vector3f v2 = new Vector3f();
	Point3f [] pts = new Point3f[3];
    triddy.setColors(0, sidecolor);
	for (i = 0; i < 3; i++) pts[i] = new Point3f();

	for (int face = 0; face < 20; face++) {
	    triddy.getCoordinates(face*3, pts);
	    v1.sub(pts[0], pts[1]);
	    v2.sub(pts[1], pts[2]);
	    normal.cross(v1, v2);
	    normal.normalize();
	    for (i = 0; i < 3; i++) {
		triddy.setNormal((face * 3 + i), normal);
	    }		 		 		 		 
	this.setGeometry(triddy);
	Appearance baldy = new Appearance();
	this.setAppearance(baldy);
    }
	 }
	public	TriangleArray getTriangleArray()
	 {
	 		return triddy;
	}

}
