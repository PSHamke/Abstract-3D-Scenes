package com.hamke.updater;


import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

public class BillboardBehavior extends Group {
  public BillboardBehavior(String text, Appearance app) {
    Appearance ap = new Appearance();
    ap.setMaterial(new Material());
    Font3D font = new Font3D(new Font("SanSerif", Font.PLAIN, 2),
                             new FontExtrusion());
    Text3D x = new Text3D(font, text);
    Shape3D xShape = new Shape3D(x, app);
    // transform for texts
    Transform3D tTr = new Transform3D();
    tTr.setTranslation(new Vector3d(-0.12, 0.6, -0.04));
    tTr.setScale(0.5);
    Bounds bounds = new BoundingSphere(new Point3d(0,0,0), 100);
    TransformGroup xTextTg = new TransformGroup(tTr);
    TransformGroup bbTg = new TransformGroup();
    bbTg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    xTextTg.addChild(bbTg);
    bbTg.addChild(xShape);
    Billboard bb = new Billboard(bbTg, Billboard.ROTATE_ABOUT_POINT, new Point3f(0,0,0));
    bb.setSchedulingBounds(bounds);
    xTextTg.addChild(bb);
    this.addChild(xTextTg);

  }
}

