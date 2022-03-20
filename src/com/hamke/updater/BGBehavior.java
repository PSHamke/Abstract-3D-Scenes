package com.hamke.updater;

import java.util.*;
import javax.media.j3d.*;
import com.sun.j3d.utils.image.TextureLoader;

public class BGBehavior extends Behavior {
  Appearance BGapp;
  //Texture grassTex = new TextureLoader(getClass().getResource("/resources/grass.jpg"), null).getTexture();
	
  public BGBehavior(Appearance app) {
	BGapp = app;  
  }
  
  public void initialize() {
	  
    wakeupOn(new WakeupOnElapsedTime(500));
  }
  
  public void processStimulus(java.util.Enumeration enumeration) {
	//BGapp.setTexture(grassTex);
    wakeupOn(new WakeupOnElapsedTime(10000));
  }
}