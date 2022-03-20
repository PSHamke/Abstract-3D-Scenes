package com.hamke.shapes;

import javax.media.j3d.GeometryArray;
import javax.media.j3d.IndexedQuadArray;
import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.GeometryInfo;
import com.sun.j3d.utils.geometry.NormalGenerator;

public class Tepren {
	double h;
	public Tepren(double h){
		this.h=h;
	}
public GeometryArray createGeometry() {
    double r1 = 0.1;
    double r2 = 0.5;
    int m = 20;
    int n = 40;
    Point3d[] pts = new Point3d[m];
    pts[0] = new Point3d(r1+r2, 0, 0);
    double theta = 2.0 * Math.PI / m;
    double c = Math.cos(theta);
    double s = Math.sin(theta);
    double[] mat = {c, -s, 0, r2*(1-c),
                    s, c, 0, -r2*s,
                    0, 0, 1, 0,
                    0, 0, 0, 1};
    Transform3D rot1 = new Transform3D(mat);
    for (int i = 1; i < m; i++) {
      pts[i] = new Point3d();
      rot1.transform(pts[i-1], pts[i]);
    }

    Transform3D rot2 = new Transform3D();
    rot2.set(new Vector3d(0,0,-h/n));
    IndexedQuadArray qa = new IndexedQuadArray(m*n, IndexedQuadArray.COORDINATES, 
      4*m*(n-1));
    int quadIndex = 0;
    for (int i = 0; i < n; i++) {
      qa.setCoordinates(i*m, pts);
      for (int j = 0; j < m; j++) {
        rot2.transform(pts[j]);
        int[] quadCoords = {i*m+j, ((i+1)%n)*m+j, ((i+1)%n)*m+((j+1)%m), 
          i*m+((j+1)%m)};
        if (i < n-1)
        qa.setCoordinateIndices(quadIndex, quadCoords);
        quadIndex += 4;
      }
    }
    GeometryInfo gi = new GeometryInfo(qa);
    NormalGenerator ng = new NormalGenerator();
    ng.generateNormals(gi);
    return gi.getGeometryArray();
  }
}
