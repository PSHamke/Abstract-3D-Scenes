package com.hamke.updater;
import java.util.Enumeration;

import javax.media.j3d.Behavior;
import javax.media.j3d.Bounds;
import javax.media.j3d.Light;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnCollisionEntry;
import javax.media.j3d.WakeupOnCollisionExit;
import javax.media.j3d.WakeupOr;

import com.hamke.main.My3DScene;
import com.sun.j3d.utils.geometry.Primitive;

public class CollisionBehaviour extends Behavior {

  private Primitive testerLightShape;
  private Light testerLight;
  private Primitive testerCollisionArea;
  private WakeupCriterion[] wakeupCriterion;
  private WakeupOr wakeupOr;

  public CollisionBehaviour(Primitive testerLightShape, Light testerLight,
      Primitive testerCollisionArea, Bounds schedulingBounds) {
    this.testerLightShape = testerLightShape;
    this.testerLight = testerLight;
    this.testerCollisionArea = testerCollisionArea;
    setSchedulingBounds(schedulingBounds);
  }


  public void initialize() {
    // init collision callbacks
    wakeupCriterion = new WakeupCriterion[2];
    wakeupCriterion[0] = new WakeupOnCollisionEntry(testerCollisionArea);
    wakeupCriterion[1] = new WakeupOnCollisionExit(testerCollisionArea);
    
    wakeupOr = new WakeupOr(wakeupCriterion);
    wakeupOn(wakeupOr);
  }


  public void processStimulus(Enumeration criteria) {
    while (criteria.hasMoreElements()) {
      WakeupCriterion criterion = (WakeupCriterion) criteria.nextElement();
      if (criterion instanceof WakeupOnCollisionEntry) {
        // collision entry 
        testerLightShape.getAppearance().setMaterial(My3DScene.LIGHT_ON);
        testerLight.setEnable(true);
        System.out.println("enter");
      } else if (criterion instanceof WakeupOnCollisionExit) {
        // collision exit
        testerLightShape.getAppearance().setMaterial(My3DScene.LIGHT_OFF);
        testerLight.setEnable(false);
        System.out.println("exit");
      }
    }
    
    // continue listening for collisions
    wakeupOn(wakeupOr);
  }

}
