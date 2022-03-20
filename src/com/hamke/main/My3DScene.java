package com.hamke.main;


// Import Section

import javax.vecmath.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.*;

import javax.imageio.ImageIO;
import javax.media.j3d.*;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import com.sun.j3d.utils.universe.*;


import com.sun.j3d.loaders.Scene;

import com.sun.j3d.loaders.objectfile.ObjectFile;

import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;

import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import com.sun.j3d.utils.picking.PickTool;
import com.sun.j3d.utils.picking.behaviors.PickRotateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickTranslateBehavior;
import com.sun.j3d.utils.picking.behaviors.PickZoomBehavior;
import com.sun.j3d.utils.behaviors.interpolators.*;
import com.sun.j3d.utils.behaviors.keyboard.KeyNavigatorBehavior;
import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.behaviors.mouse.MouseTranslate;
import com.sun.j3d.utils.behaviors.mouse.MouseZoom;
import com.sun.j3d.utils.behaviors.vp.OrbitBehavior;

import java.applet.*;
import java.net.*;

import com.hamke.shapes.CustomGeom;
import com.hamke.shapes.Diamond;

import com.hamke.shapes.Icosahedra;
import com.hamke.shapes.MyCube;
import com.hamke.shapes.MyRamp;
import com.hamke.shapes.Pyramid;
import com.hamke.shapes.RectPrism;
import com.hamke.shapes.Supress;
import com.hamke.shapes.Tepren;
import com.hamke.updater.BGBehavior;
import com.hamke.updater.BillboardBehavior;
import com.hamke.updater.MorphBehavior;
import com.hamke.updater.CollisionBehaviour;
import com.hamke.shapes.GullCG;
import com.sun.j3d.audioengines.javasound.JavaSoundMixer;
import com.sun.j3d.utils.applet.MainFrame;


public class My3DScene extends Frame {
	
	public static int width = 900;
	public static int height = width / 16*9;
	private static final BoundingSphere BOUNDS = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 2.0);
	private Texture skyTex = new TextureLoader(getClass().getResource("/resources/sky.jpg"), this).getTexture();
	private Texture grassTex = new TextureLoader(getClass().getResource("/resources/grass.jpg"), this).getTexture();
	private Texture glassTex = new TextureLoader(getClass().getResource("/resources/glass.jpg"), this).getTexture();
	private Texture woodTex = new TextureLoader(getClass().getResource("/resources/wood.jpg"), this).getTexture();
	private Texture metalTex = new TextureLoader(getClass().getResource("/resources/metal1.jpg"), this).getTexture();
	private static final double TRACK_RADIUS = 45.0;
	
	static Canvas3D cv;
	static OrbitBehavior orbit;
	Panel panel;
	static View view = null;
	static Canvas3D offScreenCanvas;
	static GraphicsConfiguration gc;
	static SimpleUniverse su;
	static BackgroundSound bSound;
	static Cone myCone;
	
	  private static TransformGroup tspin = null;
	  private static Transform3D tshadowProj = null;
	  private static GeometryArray tgeom = null;
	  private static GeometryArray tshadowGeom = null;
	  private static ShadowUpdater tupdater = null;
	
  private static RotationInterpolator rotator2Anim = null;
  private static RotationInterpolator rotator2Anim2 = null;
  private static PositionInterpolator translator2Anim = null;
  private static KeyNavigatorBehavior behavior = null;
  private static MouseRotate mRotator = null;
  private static MouseTranslate translator = null;
  private static MouseZoom zoom =null;
  
  public static final Material LIGHT_OFF = new Material(new Color3f(0.1f, 0.1f, 0.1f),
	      new Color3f(), new Color3f(0.1f, 0.1f, 0.1f), new Color3f(1.0f, 1.0f, 1.0f), 20.0f);
  public static final Material LIGHT_ON = new Material(new Color3f(0.1f, 0.1f, 0.1f), new Color3f(1.0f, 0.0f, 0.0f),
	          new Color3f(0.1f, 0.1f, 0.1f), new Color3f(1.0f, 1.0f, 1.0f), 20.0f);

	
	public static void main(String[] args) {
		Frame frame = new My3DScene();
		Dimension dim = new Dimension(width,height);
		frame.setPreferredSize(dim);
		
		Panel panel = new Panel();
	    panel.setLayout(new GridLayout(6,1));
	    
	    frame.add(panel, BorderLayout.EAST);
	    buttons(panel);
	   
		frame.setTitle("My3DScene");
		frame.pack();
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
	
	}
	public static void buttons(Panel panel) {
		 	Button button;
		    button = new Button("OrbitBehaviour");
		    
		    button.addActionListener(new ActionListener() {
		    	int currentFactor = 0;
		    	@Override
				
				public void actionPerformed(ActionEvent e) {
					System.out.println("Colorr");
					if(currentFactor==0) {
						currentFactor=1;
					}else {
						currentFactor=0;
					}
					orbit.setRotXFactor(currentFactor);//or any other value
					orbit.setRotYFactor(currentFactor);
					orbit.setZoomFactor((float)currentFactor);
				}
			});
		    panel.add(button);
		    button = new Button("Audio");
		    
		    button.addActionListener(new ActionListener() {
		    	int isPlay = 0;
		    	@Override
				
				public void actionPerformed(ActionEvent e) {
					if(isPlay==0) {
						bSound.setEnable(true);
						isPlay=1;
					}else {
						isPlay=0;
						bSound.setEnable(false);
					}
					
				}
			});
		    panel.add(button);
		    button = new Button("Animate");
		    button.addActionListener(new ActionListener() {
		    	int isAnim = 0;
		    	@Override
				public void actionPerformed(ActionEvent e) {
					if(isAnim==0) {
						rotator2Anim.setEnable(true);
						translator2Anim.setEnable(true);
						isAnim=1;
					}else {
						isAnim=0;
						rotator2Anim.setEnable(false);
						translator2Anim.setEnable(false);
					}
					
				}
			});
		    panel.add(button);
		
		    button = new Button("KeyControl");
		    button.addActionListener(new ActionListener() {
		    	int isPress = 0;
		    	@Override
				public void actionPerformed(ActionEvent e) {
					if(isPress==0) {
						behavior.setEnable(true);
						isPress=1;
					}else {
						isPress=0;
						behavior.setEnable(false);
	
					}
					
				}
			});
		    panel.add(button);
		    
		    button = new Button("MouseControl");
		    button.addActionListener(new ActionListener() {
		    	int isPress = 0;
		    	@Override
				public void actionPerformed(ActionEvent e) {
					if(isPress==0) {
						mRotator.setEnable(true);
						translator.setEnable(true);
						zoom.setEnable(true);
						isPress=1;
					}else {
						isPress=0;
						mRotator.setEnable(false);
						translator.setEnable(false);
						zoom.setEnable(false);
					}
					
				}
			});
		    panel.add(button);
		    
		    
		    
		    
		    button = new Button("Save Image");
			panel.add(button, BorderLayout.SOUTH);
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					BufferedImage bi = capture();
					save(bi);
				}
			});
			
			
			view = su.getViewer().getView();
			offScreenCanvas = new Canvas3D(gc, true);
			Screen3D screenOn = cv.getScreen3D();
			Screen3D screenOff = offScreenCanvas.getScreen3D();
			Dimension dim = screenOn.getSize();
			screenOff.setSize(dim);
			screenOff.setPhysicalScreenWidth(screenOn.getPhysicalScreenWidth());
			screenOff.setPhysicalScreenHeight(screenOn.getPhysicalScreenHeight());
			Point loc = cv.getLocationOnScreen();
			offScreenCanvas.setOffScreenLocation(loc);
			
	}
	
	
	
	public My3DScene() {
		 	
		gc= SimpleUniverse.getPreferredConfiguration();
		cv = new Canvas3D(gc);
	    setLayout(new BorderLayout());
		add(cv);
		su = new SimpleUniverse(cv);
		// Create initial view position
		Transform3D locator = new Transform3D();
		locator.lookAt(new Point3d(7.5, 1.5,9), new Point3d(0, 0, 0), new Vector3d(0, 1, 0));
		locator.invert();
		tupdater = new ShadowUpdater();
		su.getViewingPlatform().getViewPlatformTransform().setTransform(locator);
		BranchGroup bg = createSceneGraph();
		bg.compile();

		su.addBranchGraph(bg);
		
		Point3d myPoint = new Point3d(); 
		// Create orbit behavior
		orbit = new OrbitBehavior(cv);
		orbit.setSchedulingBounds(BOUNDS);
		orbit.getRotationCenter(myPoint);
		orbit.setRotXFactor(0);//or any other value
		orbit.setRotYFactor(0);
		orbit.setZoomFactor(0f);
		su.getViewingPlatform().setViewPlatformBehavior(orbit);
		

		
		// To perfom close operation
		WindowListener wListener = new WindowAdapter() {
			public void windowClosing(WindowEvent ev) {
				System.exit(0);
			}
		};
		addWindowListener(wListener);
		AudioDevice audioDev = new JavaSoundMixer(su.getViewer().getPhysicalEnvironment());
		audioDev.initialize();
	}
	
	
	 
	private BranchGroup mainScene() {
		BranchGroup root = new BranchGroup();
	    //------------------------------------------------//
		// Main Transform Groups
	    TransformGroup spin = new TransformGroup(); // To make spin
	    spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    TransformGroup primTg = new TransformGroup(); // Transform group for Java 3D primitives
	    TransformGroup cusTg = new TransformGroup(); // Transform group for Custom Geometries
	    TransformGroup key = new TransformGroup();
	    TransformGroup mouse = new TransformGroup();
	    
	    
	    //------------------------------------------------//
	    // Add Main Transform Groups into root
	    root.addChild(spin);
	    root.addChild(primTg);
	    root.addChild(cusTg);
	    root.addChild(mouse);
	    root.addChild(key);
	    //------------------------------------------------//
	    // Custom Materials
	    	final Material bodyPaintMat = new Material(new Color3f(1.0f, 0.5f, 0.2f), new Color3f(),
	            new Color3f(1.0f, 0.5f, 0.2f), new Color3f(1.0f, 1.0f, 1.0f), 25.0f);
	        final Material bodyMetalMat = new Material(new Color3f(0.3f, 0.3f, 0.3f), new Color3f(),
	            new Color3f(0.3f, 0.3f, 0.3f), new Color3f(1.0f, 1.0f, 1.0f), 20.0f);
	        final Material windowMat = new Material(new Color3f(0.4f, 1.0f, 1.0f), new Color3f(),
	            new Color3f(0.4f, 1.0f, 1.0f), new Color3f(1.0f, 1.0f, 1.0f), 10.0f);
	        final Material wheelMetalMat = new Material(new Color3f(0.6f, 0.6f, 0.6f), new Color3f(),
	            new Color3f(0.6f, 0.6f, 0.6f), new Color3f(1.0f, 1.0f, 1.0f), 35.0f);
	        final Material customMat = new Material(new Color3f(0.7f, 0.6f, 0.3f), new Color3f(0.1f,0.2f,0.6f),
		            new Color3f(0.1f, 0.6f, 0.3f), new Color3f(0.1f, 0.6f, 0.2f), 35.0f);

	    //------------------------------------------------//
	    // Custom Appearances
	    Appearance basicApp = new Appearance();
	    basicApp.setMaterial(new Material());
	    Appearance glassApp= new Appearance();
	    glassApp.setMaterial(windowMat);
	    glassApp.setTexture(glassTex);
	    Appearance grassApp= new Appearance();
	    grassApp.setTexture(grassTex);
	    Appearance woodApp= new Appearance();
	    woodApp.setTexture(woodTex);
	    Appearance metalApp= new Appearance();
	    metalApp.setMaterial(bodyMetalMat);
	    metalApp.setTexture(metalTex);
	    Appearance customApp = new Appearance();
	    customApp.setMaterial(customMat);
	    Appearance glassApp2 = createTextureAppearance("resources/glass.jpg");
	    Appearance metalApp2 = createTextureAppearance("resources/metal1.jpg");
	    Appearance grassApp2 = createTextureAppearance("resources/grass.jpg");
	    Appearance woodApp2 = createTextureAppearance("resources/wood.jpg");
	    //------------------------------------------------//
	    //object
	    Appearance ap = new Appearance();
	    ap.setMaterial(new Material());
	    Appearance app = new Appearance();
		app.setColoringAttributes(new ColoringAttributes(new Color3f(Color.RED), ColoringAttributes.FASTEST));
		app.setPointAttributes(new PointAttributes(10f, true));
		app.setLineAttributes(new LineAttributes(5f, LineAttributes.PATTERN_SOLID, true));
	    //------------------------------------------------//
	    // Java 3D Primitives
	    Box pBox;
	    Sphere pSphere;
	    //------------------------------------------------//
	    // Custom Geometries
	    RectPrism rectP;
	    Icosahedra icosa;
	    CustomGeom cust = new CustomGeom();
	    MyCube myCube = new MyCube(50);
	    MyRamp myRamp = new MyRamp(100);
	    MyRamp myRamp2 = new MyRamp(100);
	    Diamond diamond = new Diamond(1.5f,2.0f);
	    //------------------------------------------------//
	    // Advanced Geometries
	    
	    
	    
	    
	    
	    //------------------------------------------------//
	    // Fonts and 3D texts
	    

	    
	    Font font = new Font("Serif", Font.BOLD, 2);
	    FontExtrusion extrusion = new FontExtrusion();
	    Font3D font3d = new Font3D(font, extrusion);
	    Text3D text = new Text3D(font3d, "My Scene");
	    Shape3D shape1 = new Shape3D(text, woodApp);
	    Transform3D tr1 = new Transform3D();;
	    tr1.setScale(0.1);
	    tr1.setTranslation(new Vector3f(-0.1f, 1f, 0f));
	    TransformGroup tg1 = new TransformGroup(tr1);
	    root.addChild(tg1);
	    tg1.addChild(shape1);
	    
	    
	    Transform3D tr = new Transform3D();
	    tr.setScale(0.3);
	    tr.setTranslation(new Vector3f(-0.1f, 0.6f, 0f));
	    TransformGroup tg = new TransformGroup(tr);
	    root.addChild(tg);
	    BillboardBehavior bill = new BillboardBehavior("Hamke",metalApp2);
	    tg.addChild(bill);
	        //------------------------------------------------//
	    // Appearance With simple Attributes
	    
	    //------------------------------------------------//
	    // Appearance with material configuration 
	    
	    //------------------------------------------------//
	    // Geometric Transformations
	    //------------------------------------------------//
	    // View Configurations
	    //------------------------------------------------//
	    // Picking
	    //------------------------------------------------//
	    // Lightning
	    //------------------------------------------------//
	    // 2D Textures
	    //------------------------------------------------//
	    // Interaction Behaviors applied to objects

	   
	    
	    // Mouse
	  
	
	 
	    
	    ////////////////////////////
	    BoundingSphere bounds3 = new BoundingSphere();
	    
	    Transform3D cusTr3= new Transform3D();
	    icosa = new Icosahedra();
	    cusTr3.setScale(0.1);
	    cusTr3.setTranslation ( new Vector3f (0.2f,0.0f,1f));
	    TransformGroup tgDiamond3= new TransformGroup(cusTr3);
	    tgDiamond3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tgDiamond3.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tgDiamond3.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
	    root.addChild(tgDiamond3);
	    
	    

	    
	    Transform3D diamondTrans = new Transform3D();
	    diamondTrans.setRotation(new AxisAngle4d(0.0, 1.0, 0.0, 90));
	    diamondTrans.setScale(new Vector3d(0.25f, 0.25f, 0.25f));
	    diamondTrans.setTranslation(new Vector3f(0.0f, -1f, 2.00f));
	    TransformGroup diamondTg = new TransformGroup(diamondTrans);
	    diamond.setAppearance(app);
	    diamondTg.addChild(diamond);
	    spin.addChild(diamondTg);
	    
	    Transform3D rampTrans = new Transform3D();
	    //rampTrans.setRotation(new AxisAngle4d(0.0, 1.0, 0.0, 90));
	    rampTrans.setScale(0.5);
	    rampTrans.setTranslation(new Vector3f(0.425f, -1f, 0.20f));
	    TransformGroup rampTg = new TransformGroup(rampTrans);
	    myRamp.setAppearance(app);
	    rampTg.addChild(myRamp);
	    root.addChild(rampTg);
	    
	    rampTrans = new Transform3D();
	    rampTrans.setRotation(new AxisAngle4d(0.0, 0.0, 1.0, Math.PI/2));
	    rampTrans.setScale(0.5);
	    rampTrans.setTranslation(new Vector3f(0f, -1f, 0.20f));
	    rampTg = new TransformGroup(rampTrans);
	    myRamp.setAppearance(app);
	    rampTg.addChild(myRamp2);
	    root.addChild(rampTg);
	    
	    
	    Transform3D cubeTrans = new Transform3D();
	    cubeTrans.setRotation(new AxisAngle4d(0.0, 1.0, 0.0, 90));
	    cubeTrans.setScale(new Vector3d(0.25f, 0.25f, 0.25f));
	    cubeTrans.setTranslation(new Vector3f(0.0f, -1f, 2.00f));
	    TransformGroup cubeTg = new TransformGroup(cubeTrans);
	    myCube.setAppearance(app);
	    cubeTg.addChild(myCube);
	    spin.addChild(cubeTg);
	  
	    
	  
	    //tr = new Transform3D();
	    //tr.setIdentity();
	    //------------------------------------------------//
	    //Transform for Java 3D Primitives
	    Transform3D primTr = new Transform3D();
	    primTr.setScale(0.05);
	    // BOX
	    for (int i=0; i<5; i++) {
	    	for (int y=0; y<5; y++) {
	    		for (int k=0; k<5; k++) {
	    			pBox = new Box (1f,1f,1f,Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,glassApp2);
			    	primTr.setTranslation(new Vector3f((float)(0f+y*0.1),(float) (-1f+i*0.1),(float)(0f+k*0.1)));
				    TransformGroup tgBox= new TransformGroup(primTr);
				    primTg.addChild(tgBox);
				    tgBox.addChild(pBox);
	    		}
	    	
	    	}
	    	
	    }

	    
	    // SPHERE
	    pSphere = new Sphere(1f,Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,
	    		  glassApp);
	    primTr.setScale(0.1);
	    primTr.setTranslation(new Vector3f(0.15f,-0.45f,0.1f));
	    TransformGroup tgSphere = new TransformGroup(primTr);
	    primTg.addChild(tgSphere);
	    tgSphere.addChild(pSphere);
	    
	    pSphere = new Sphere(1f,Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,
	    		  glassApp);
	    primTr.setScale(0.1);
	    primTr.setTranslation(new Vector3f(0.30f,-0.45f,0.35f));
	    tgSphere = new TransformGroup(primTr);
	    primTg.addChild(tgSphere);
	    tgSphere.addChild(pSphere);
	    
	    //------------------------------------------------//
	    //Transform for Custom Geometries
	    
	    Transform3D cusTr= new Transform3D();
	    rectP = new RectPrism(1.8f,2.2f,3.3f);
	    cusTr.setScale(0.1);
	    cusTr.setTranslation(new Vector3f(0.2f,-1f,-0.375f));
	    TransformGroup tgRectP= new TransformGroup(cusTr);
	    cusTg.addChild(tgRectP);
	    tgRectP.addChild(rectP);
	    
	    
	    //------------------------------------------------//

	    Alpha alpha = new Alpha(-1, 10000);
	    RotationInterpolator rotator = new RotationInterpolator(alpha, spin);
	    BoundingSphere bounds = new BoundingSphere();
	    rotator.setSchedulingBounds(bounds);
	    spin.addChild(rotator);

	    //background and light
	    Background background = new Background(0.0f, 1.0f, 1.0f);
	    background.setApplicationBounds(bounds);
	    root.addChild(background);
	    AmbientLight light = new AmbientLight(true, new Color3f(Color.red));
	    light.setInfluencingBounds(bounds);
	    root.addChild(light);
	    PointLight ptlight = new PointLight(new Color3f(Color.green), 
	      new Point3f(3f,3f,3f), new Point3f(1f,0f,0f));
	    ptlight.setInfluencingBounds(bounds);
	    root.addChild(ptlight);
	    PointLight ptlight2 = new PointLight(new Color3f(Color.orange), 
	      new Point3f(-2f,2f,2f), new Point3f(1f,0f,0f));
	    ptlight2.setInfluencingBounds(bounds);
	    root.addChild(ptlight2);
	    
	    
	    // BGM SOUND
	    bSound = new BackgroundSound();
		URL url = this.getClass().getClassLoader().getResource("resources/LevelAudio.wav");
		MediaContainer mContainer = new MediaContainer(url);
		bSound.setCapability(BackgroundSound.ALLOW_ENABLE_READ | BackgroundSound.ALLOW_ENABLE_WRITE);
		bSound.setSoundData(mContainer);
		bSound.setLoop(Sound.INFINITE_LOOPS);
		bSound.setSchedulingBounds(BOUNDS);
		bSound.setInitialGain(0.01f);
		root.addChild(bSound);
	    
	    
	    return root;
	}
	
	private TransformGroup tg = null;
	private Transform3D t3d = null;
	private Transform3D t3dstep = new Transform3D();
	private TransformGroup tg2 = null;
	private Transform3D t3d2 = null;
	private Transform3D t3dstep2 = new Transform3D();
	private Matrix4d matrix = new Matrix4d();
	private BranchGroup createStar() {

		BranchGroup objRoot = new BranchGroup();
		tg = new TransformGroup();
		t3d = new Transform3D();

		tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		t3d.setTranslation(new Vector3d(0.2, 0.05, 0.40));
		t3d.setRotation(new AxisAngle4d(0.0, 0.0, 1.0, 30.0));
		t3d.setRotation(new AxisAngle4d(1.0, 0.0, 0.0, 30.0));
	
		t3d.setScale(0.2);

		tg.setTransform(t3d);
		
		tg2 = new TransformGroup();
		t3d2 = new Transform3D();

		tg2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		t3d2.setTranslation(new Vector3d(0.2, -0.75, 0.50));
		t3d2.setRotation(new AxisAngle4d(0.0, 0.0, 1.0, 30.0));
		t3d2.setRotation(new AxisAngle4d(1.0, 0.0, 0.0, 30.0));
	
		t3d2.setScale(0.2);

		tg2.setTransform(t3d2);

		ObjectFile loader = new ObjectFile(ObjectFile.RESIZE);
		Scene s = null;
		Scene s2 = null;
		File file = new java.io.File("C:\\Users\\hayre\\eclipse-workspace\\3DSession\\src\\resources\\estrellica.obj");

		try {
			s = loader.load(file.toURI().toURL());
			s2 = loader.load(file.toURI().toURL());
		} catch (Exception e) {
			System.err.println(e);
			System.exit(1);
		}
		
		
		tg.addChild(s.getSceneGroup());
		tg2.addChild(s2.getSceneGroup());
		
		objRoot.addChild(tg);
		objRoot.addChild(tg2);
		//objRoot.addChild(createLight());
		
		return objRoot;

	}
	
	private Light createLight() {
		DirectionalLight light = new DirectionalLight(true, new Color3f(1.0f, 1.0f, 1.0f),
				new Vector3f(-0.3f, 0.2f, -1.0f));

		light.setInfluencingBounds(new BoundingSphere(new Point3d(), 10000.0));

		return light;
	}
	
	private BranchGroup collisionTest() {
	    BranchGroup root = new BranchGroup();

	    final Material metalMat = new Material(new Color3f(0.3f, 0.3f, 0.3f), new Color3f(),
	        new Color3f(0.3f, 0.3f, 0.3f), new Color3f(1.0f, 1.0f, 1.0f), 30.0f);

	    Appearance metalApp = new Appearance();
	    metalApp.setMaterial(metalMat);

	    Appearance collisionApp = new Appearance();
	    RenderingAttributes collisionRender = new RenderingAttributes();
	    collisionRender.setVisible(false);
	    collisionApp.setRenderingAttributes(collisionRender);

	    Appearance lightOffApp = new Appearance();
	    lightOffApp.setMaterial(LIGHT_OFF);
	    lightOffApp.setCapability(Appearance.ALLOW_MATERIAL_WRITE);


	    TransformGroup lightTg = new TransformGroup();
	    Transform3D lightTrans = new Transform3D();
	    lightTrans.setTranslation(new Vector3d(0.10, -0.5, 1));
	    lightTg.setTransform(lightTrans);
	    Sphere lightShape = new Sphere(0.15f, lightOffApp);
	    lightTg.addChild(lightShape);

	    // create the pole point light
	    PointLight light = new PointLight(false, new Color3f(1.0f, 0.3f, 0.3f),
	        new Point3f(0.10f, -0.5f, 1f), new Point3f(1.5f, 0.0f, 0.0f));
	    light.setCapability(Light.ALLOW_STATE_WRITE);
	    light.setInfluencingBounds(BOUNDS);

	    // create collision checking area
	    TransformGroup collisionTg = new TransformGroup();
	    Transform3D collisionTrans = new Transform3D();
	    collisionTrans.setTranslation(new Vector3d(0.20, -1, -1.5));
	    collisionTg.setTransform(collisionTrans);
	    Box collision = new Box(0.30f, 0.1f, 0.2f, collisionApp);
	    collisionTg.addChild(collision);

	    // create the collision checking behaviour
	    CollisionBehaviour collisionBehaviour =
	        new CollisionBehaviour(lightShape, light, collision, BOUNDS);

	    root.addChild(lightTg);
	    root.addChild(light);
	    root.addChild(collisionTg);
	    root.addChild(collisionBehaviour);
	    return root;
	  }
	
	private BranchGroup birdsScene() {
		BranchGroup birds = new BranchGroup();
		TransformGroup tg = new TransformGroup();
	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tg.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    birds.addChild(tg);
	    // switch node
	    // appearance
	    Appearance ap = new Appearance();
	    Material material = new Material();
	    // gull
	    Shape3D shape = new Shape3D(new GullCG(),ap );
	    Transform3D trans = new Transform3D();
	    trans.setScale(0.1);
	    trans.setRotation(new AxisAngle4d(0, 0, 1, Math.PI/4));
	    trans.setTranslation(new Vector3f(-0.6f, 1f, 0f));
	    TransformGroup tgScale = new TransformGroup(trans);
	    tg.addChild(tgScale);
	    tgScale.addChild(shape);
	    
	    shape = new Shape3D(new GullCG(),ap );
	    trans = new Transform3D();
	    trans.setScale(0.1);
	    trans.setRotation(new AxisAngle4d(0, 0, 1, Math.PI/6));
	    trans.setTranslation(new Vector3f(-0.2f, 1f, 0f));
	    tgScale = new TransformGroup(trans);
	    tg.addChild(tgScale);
	    tgScale.addChild(shape);
	    
	    shape = new Shape3D(new GullCG(),ap );
	    trans = new Transform3D();
	    trans.setScale(0.1);
	    trans.setRotation(new AxisAngle4d(0, 0, 1, Math.PI/8));
	    trans.setTranslation(new Vector3f(-1f, 1f, 0f));
	    tgScale = new TransformGroup(trans);
	    tg.addChild(tgScale);
	    tgScale.addChild(shape);
	    
	
	 // interpolators    
	    BoundingSphere bounds = new BoundingSphere(new Point3d(0,0,0),100);
	    Alpha alpha = new Alpha(-1, 6000);
	    alpha.setMode(Alpha.INCREASING_ENABLE | Alpha.DECREASING_ENABLE);
	    alpha.setDecreasingAlphaDuration(6000);
	    
	    // translation
	    translator2Anim = new PositionInterpolator(alpha, tg);
	    translator2Anim.setSchedulingBounds(bounds);
	    translator2Anim.setEnable(false);
	    birds.addChild(translator2Anim);
		return birds;
	}

	private BranchGroup skyScene() {
		BranchGroup sky = new BranchGroup();

	    // create a textured spherical sky (background)
	    Background skyBg = new Background(new Color3f(1.0f, 1.0f, 1.0f));
	    skyBg.setApplicationBounds(BOUNDS);

	    BranchGroup skyGeometry = new BranchGroup();
	    Appearance skyApp = new Appearance();
	    skyApp.setCapability(Texture.ALLOW_ENABLE_WRITE);
	    skyApp.setTexture(skyTex);
	    //BGBehavior bg = new BGBehavior(skyApp);
	    //bg.setSchedulingBounds(BOUNDS);
	    Sphere skySphere = new Sphere(1.0f,
	        Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD , skyApp);
	    skyGeometry.addChild(skySphere);
	    
	    skyBg.setGeometry(skyGeometry);
	    
	    // create the sky light (ambient)
	    AmbientLight skyLight = new AmbientLight(new Color3f(0.4f, 0.4f, 0.4f));
	    skyLight.setInfluencingBounds(BOUNDS);

	    // create the sun light (directional)
	    DirectionalLight sunLight =
	        new DirectionalLight(new Color3f(1.0f, 0.98f, 0.96f), new Vector3f(4.0f, -7.0f, -12.0f));
	    sunLight.setInfluencingBounds(BOUNDS);

	    // add sky parts to sky group
	    sky.addChild(skyBg);
	    //sky.addChild(bg);
	    sky.addChild(skyLight);
	    sky.addChild(sunLight);
	    return sky;
	}
	  private BranchGroup pickBranch() {
		    BranchGroup root = new BranchGroup();
		    TransformGroup terrainBaseTg = new TransformGroup();
		    Transform3D terrainBaseTrans = new Transform3D();
		    terrainBaseTrans.setTranslation(new Vector3f(-1.0f, -0.5f, 0.0f));
		    terrainBaseTg.setTransform(terrainBaseTrans);
		    terrainBaseTg.addChild(createObject());
		
		    BoundingSphere bounds = new BoundingSphere();
		    // rotation
		    PickRotateBehavior rotator = new PickRotateBehavior(root, cv, bounds, 
		      PickTool.GEOMETRY);
		    rotator.setEnable(true);
		    root.addChild(rotator);
		    // translation
		    PickTranslateBehavior translator = new PickTranslateBehavior(root, cv,
		      bounds, PickTool.GEOMETRY);
		    root.addChild(translator);
		    // zoom
		    PickZoomBehavior zoom = new PickZoomBehavior(root, cv, bounds,
		      PickTool.GEOMETRY);
		    root.addChild(zoom);
		    //light
		    AmbientLight light = new AmbientLight(true, new Color3f(Color.blue));
		    light.setInfluencingBounds(bounds);
		    root.addChild(light);
		    PointLight ptlight = new PointLight(new Color3f(Color.white),
		      new Point3f(0f,0f,2f), new Point3f(1f,0.3f,0f));
		    ptlight.setInfluencingBounds(bounds);
		    //root.addChild(ptlight);
		    //background
		    Background background = new Background(1.0f, 1.0f, 1.0f);
		    background.setApplicationBounds(bounds);
		    root.addChild(background);
		    root.addChild(terrainBaseTg);
		    return root;
		  }
		  
		  private Node createObject() {
		    // transform
		    Transform3D trans = new Transform3D();
		    trans.setTranslation(new Vector3d(2f,0.5f,0));
		    trans.setScale(0.1);
		    TransformGroup spin = new TransformGroup(trans);
		    spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    spin.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		    spin.setCapability(TransformGroup.ENABLE_PICK_REPORTING);
		    // visual objects
		    Appearance ap = new Appearance();
		    ap.setMaterial(new Material());
		    Shape3D shape = new Icosahedra();
		    PickTool.setCapabilities(shape, PickTool.INTERSECT_FULL);
		    spin.addChild(shape);
		    return spin;
		  }  
	  private BranchGroup createTerrain() {
		    BranchGroup terrain = new BranchGroup();

		    TransformGroup terrainBaseTg = new TransformGroup();
		    Transform3D terrainBaseTrans = new Transform3D();
		    terrainBaseTrans.setTranslation(new Vector3f(0.0f, -5.1f, 0.0f));
		    terrainBaseTg.setTransform(terrainBaseTrans);

		    // create terrain base to support the track
		    final Material grassMat = new Material(new Color3f(0.0f, 0.58f, 0.15f), new Color3f(),
		        new Color3f(0.0f, 0.58f, 0.15f), new Color3f(), 1.0f);
		    Appearance terrainApp = new Appearance();
		    terrainApp.setMaterial(grassMat);
		    terrainApp.setTexture(grassTex);
		    Cylinder terrainBase = new Cylinder((float) TRACK_RADIUS + 10.0f, 1.0f,
		        Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS, terrainApp);
		    terrainBaseTg.addChild(terrainBase);

		    terrain.addChild(terrainBaseTg);
		    return terrain;
		  }
	  
	  private BranchGroup keyScene() {
		  BranchGroup root = new BranchGroup();
		  TransformGroup key = new TransformGroup();
		  
		  key.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		  key.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		    // key behavior
		  behavior = new KeyNavigatorBehavior(key);
		  behavior.setSchedulingBounds(BOUNDS);
		  behavior.setEnable(false);  
		  key.addChild(behavior);
		  TransformGroup primTg = new TransformGroup();
		  Transform3D primTr= new Transform3D();
		  root.addChild(key);
		  key.addChild(primTg);
			 
			    Appearance metalApp2 = createTextureAppearance("resources/metal1.jpg");
			    Cone pCone;
			    Cylinder pCylinder;
			    // CONE
			    pCone = new Cone( 2f, 1.5f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,
			    	     metalApp2);
			    primTr.setScale(0.1);
			    primTr.setTranslation(new Vector3f(-.1f,-0.375f,0.6f));
			    TransformGroup tgCone = new TransformGroup(primTr);
			    primTg.addChild(tgCone);
			    tgCone.addChild(pCone);
			    
			    pCone = new Cone( 2f, 1.5f, Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,
			    	     metalApp2);
			    primTr.setScale(0.1);
			    primTr.setTranslation(new Vector3f(0.50f,-0.375f,0.6f));
			    tgCone = new TransformGroup(primTr);
			    primTg.addChild(tgCone);
			    tgCone.addChild(pCone);
			    
			    
			    // CYLINDER
			    pCylinder = new Cylinder(1.2f, 5f,Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,
			    		metalApp2);
			    primTr.setScale(0.1);
			    primTr.setTranslation(new Vector3f(-.10f,-0.70f,0.6f));
			    TransformGroup tgCylinder = new TransformGroup(primTr);
			    primTg.addChild(tgCylinder);
			    tgCylinder.addChild(pCylinder);
			
			    
			    pCylinder = new Cylinder(1.2f, 5f,Primitive.GENERATE_TEXTURE_COORDS | Primitive.GENERATE_NORMALS_INWARD,
			    		metalApp2);
			    primTr.setScale(0.1);
			    primTr.setTranslation(new Vector3f(0.50f,-0.70f,0.6f));
			    tgCylinder = new TransformGroup(primTr);
			    primTg.addChild(tgCylinder);
			    tgCylinder.addChild(pCylinder);
			 
		  return root;
	  }
	  private BranchGroup mouseScene() {
		  	BranchGroup root = new BranchGroup();
		    TransformGroup mouse = new TransformGroup();
		    root.addChild(mouse);
		    mouse.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    mouse.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
		    mRotator = new MouseRotate(mouse);
		    BoundingSphere bounds2 = new BoundingSphere();
		    mRotator.setSchedulingBounds(bounds2);
		    mRotator.setEnable(false);
		    mouse.addChild(mRotator);
		    // translation
		    translator = new MouseTranslate(mouse);
		    translator.setSchedulingBounds(bounds2);
		    translator.setEnable(false);
		    mouse.addChild(translator);
		    // zoom
		    zoom = new MouseZoom(mouse);
		    zoom.setSchedulingBounds(bounds2);
		    zoom.setEnable(false);
		    mouse.addChild(zoom);
		    CustomGeom cust = new CustomGeom(1);
		    Transform3D customGeomTrans = new Transform3D();
		    customGeomTrans.rotX(Math.PI/2);
		    customGeomTrans.setScale(new Vector3d(0.75f, 0.75f, 0.75f));
		    customGeomTrans.setTranslation(new Vector3f(-0.175f, -1f, -0.00f));
		    TransformGroup customGeomTg = new TransformGroup(customGeomTrans);
		    //cust.setAppearance(customApp);
		    customGeomTg.addChild(cust);
		    
		    
		    mouse.addChild(customGeomTg);
		    return root;
	  }
	private BranchGroup animatedScene() {
		 BranchGroup root = new BranchGroup();
		 
		 
		 
		    TransformGroup spin = new TransformGroup();
		    spin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    
		    TransformGroup spinInverse = new TransformGroup();
		    spinInverse.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		    Shape3D shape = new Icosahedra();
		    Transform3D tr = new Transform3D();
		    tr.setScale(0.1f);
		    tr.setTranslation(new Vector3f(-1.0f, 0f, 0.0f));
		    TransformGroup tg = new TransformGroup(tr);
		    root.addChild(tg);
		    tg.addChild(spin);
		    
		    spin.addChild(shape);

		    
		    // rotator
		    Alpha alpha = new Alpha(-1, 2000);
		    rotator2Anim = new RotationInterpolator(alpha, spin);
		    rotator2Anim.setEnable(false);
		    BoundingSphere bounds = new BoundingSphere();
		    rotator2Anim.setSchedulingBounds(bounds);
		    spin.addChild(rotator2Anim);
		    
		    alpha = new Alpha(-1, 2000);
		    rotator2Anim2 = new RotationInterpolator(alpha, spinInverse);
		    rotator2Anim2.setEnable(false);
		 
		    rotator2Anim2.setSchedulingBounds(bounds);
		    spinInverse.addChild(rotator2Anim2);
		    
		    
		 return root;
	}
	private BranchGroup shadowScene() {
	    BranchGroup root = new BranchGroup();
	    //object
	    Appearance ap = new Appearance();
	    ap.setMaterial(new Material());
	    Shape3D shape = new Diamond(1f,1f);
	    shape.setAppearance(ap);
	    tgeom = (GeometryArray)shape.getGeometry();
	    tgeom.setCapability(GeometryArray.ALLOW_COORDINATE_READ);
	    //transform
	    Transform3D tr = new Transform3D();
	    tr.rotY(-0.2);
	    tr.setTranslation(new Vector3d(0.25,-2,0));
	    tr.setScale(0.2);
	    TransformGroup tg = new TransformGroup(tr);
	    root.addChild(tg);
	    tspin = new TransformGroup();
	    tspin.setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
	    tspin.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	    tg.addChild(tspin);
	    tspin.addChild(shape);
	    //rotator
	    Alpha alpha = new Alpha(-1, 8000);
	    RotationInterpolator rotator = new RotationInterpolator(alpha, tspin);
	    BoundingSphere bounds = new BoundingSphere();
	    rotator.setSchedulingBounds(bounds);
	    tspin.addChild(rotator);
	    //light and background
	    Background background = new Background(1.0f, 1.0f, 1.0f);
	    background.setApplicationBounds(bounds);
	    root.addChild(background);
	    AmbientLight light = new AmbientLight(true, new Color3f(Color.red));
	    light.setInfluencingBounds(bounds);
	    root.addChild(light);
	    Point3f lightPos = new Point3f(5f,5f,2f);
	    PointLight ptlight = new PointLight(new Color3f(Color.green),
	    lightPos, new Point3f(1f,0f,0f));
	    ptlight.setInfluencingBounds(bounds);
	    tg.addChild(ptlight);
	    // wall

	    // shadow
	    tshadowGeom = createShadow(tgeom, lightPos, new Point3f(-5f, -6.5f,8f));
	    ap = new Appearance();
	    ColoringAttributes colorAttr = new ColoringAttributes(0.1f, 0.1f, 0.1f, 
	      ColoringAttributes.FASTEST);
	    ap.setColoringAttributes(colorAttr);
	    TransparencyAttributes transAttr = new TransparencyAttributes(
	      TransparencyAttributes.BLENDED,0.35f);
	    ap.setTransparencyAttributes(transAttr);
	    PolygonAttributes polyAttr = new PolygonAttributes();
	    polyAttr.setCullFace(PolygonAttributes.CULL_NONE);
	    ap.setPolygonAttributes(polyAttr);
	    shape = new Shape3D(tshadowGeom, ap);
	    tg.addChild(shape);
	    // shadow update
	    ShadowBehavior sb = new ShadowBehavior();
	    sb.setSchedulingBounds(bounds);
	    tg.addChild(sb);
	    return root;
	}
	
	private BranchGroup morphScene() {
	    BranchGroup root = new BranchGroup();
	    // geometry
	    GeometryArray[] geoms = new GeometryArray[4];
	    Tepren tpr= new Tepren(0.2);
	    Tepren tpr2= new Tepren(0.8);
	    Supress supress= new Supress(0.6);
	    Supress supress2= new Supress(0.9);
	    geoms[0] = tpr.createGeometry();
	    geoms[1] = tpr2.createGeometry();
	    geoms[2] = supress.createGeometry();
	    geoms[3] = supress2.createGeometry();
	    Appearance appear = new Appearance();
	    appear.setMaterial(new Material());
	    Morph morph = new Morph(geoms, appear);
	    morph.setCapability(Morph.ALLOW_WEIGHTS_READ);
	    morph.setCapability(Morph.ALLOW_WEIGHTS_WRITE);
	    Transform3D tr = new Transform3D();
	    tr.rotX(Math.PI);
	    tr.setScale(0.8);
	    tr.setTranslation(new Vector3f(0.1f,0.0f,0.3f));
	    TransformGroup tg = new TransformGroup(tr);
	    tg.addChild(morph);
	    root.addChild(tg);
	    // Behavior node
	    Alpha alpha = new Alpha(-1, Alpha.INCREASING_ENABLE|Alpha.DECREASING_ENABLE,
	    0,0, 8000,0,0,8000,0,0);
	    MorphBehavior mb = new MorphBehavior(morph, alpha);
	    BoundingSphere bounds = new BoundingSphere();
	    mb.setSchedulingBounds(bounds);
	    root.addChild(mb);
	    
	    PointLight ptlight = new PointLight(new Color3f(Color.white),
	      new Point3f(0.7f,0.7f,2f), new Point3f(1f,0f,0f));
	    ptlight.setInfluencingBounds(bounds);
	    root.addChild(ptlight);
	    
	    return root;
	  }
	
	
	  private void addEnvironmentToScene(BranchGroup sceneRoot) {
		    // add different objects to scene
		    sceneRoot.addChild(skyScene());
		    sceneRoot.addChild(mainScene());
		    sceneRoot.addChild(createTerrain());
		    sceneRoot.addChild(keyScene());
		    sceneRoot.addChild(mouseScene());
		    sceneRoot.addChild(pickBranch());
		    sceneRoot.addChild(birdsScene());
		    sceneRoot.addChild(animatedScene());
		    sceneRoot.addChild(shadowScene());
		    sceneRoot.addChild(morphScene());
		    sceneRoot.addChild(createStar());
		    sceneRoot.addChild(collisionTest());
		  }
	  
	private BranchGroup createSceneGraph() {
	    BranchGroup root = new BranchGroup();
	    addEnvironmentToScene(root);
	    return root;
		
	}
	
	public static BufferedImage capture() {
		Dimension dim = cv.getSize();
		BufferedImage bi = new BufferedImage(dim.width, dim.height, BufferedImage.TYPE_INT_RGB);

		view.stopView();
		view.addCanvas3D(offScreenCanvas);

		ImageComponent2D buffer = new ImageComponent2D(ImageComponent.FORMAT_RGB, bi);
		offScreenCanvas.setOffScreenBuffer(buffer);

		view.startView();

		offScreenCanvas.renderOffScreenBuffer();
		offScreenCanvas.waitForOffScreenRendering();

		bi = offScreenCanvas.getOffScreenBuffer().getImage();

		view.removeCanvas3D(offScreenCanvas);
		
		return bi;
	}
	
	public static void save(BufferedImage bi) {
		JFileChooser chooser = new JFileChooser();
		
		if(chooser.showSaveDialog(chooser) == JFileChooser.APPROVE_OPTION) {
			File file = chooser.getSelectedFile();
			
			try {
				ImageIO.write(bi, "jpeg", file);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	  private GeometryArray createShadow(GeometryArray ga, Point3f light, Point3f plane) {
		    GeometryInfo gi = new GeometryInfo(ga);
		    gi.convertToIndexedTriangles();
		    IndexedTriangleArray ita = (IndexedTriangleArray)gi.getIndexedGeometryArray();
		    Vector3f v = new Vector3f();
		    v.sub(plane, light);
		    double[] mat = new double[16];
		    for (int i = 0; i < 16; i++) {
		      mat[i] = 0;
		    }
		    mat[0] = 1;
		    mat[5] = 1;
		    mat[10] = 1-0.001;
		    mat[14] = -1/v.length();
		    Transform3D proj = new Transform3D();
		    proj.set(mat);
		    Transform3D u = new Transform3D();
		    u.lookAt(new Point3d(light), new Point3d(plane), new Vector3d(0,1,0));
		    proj.mul(u);
		    tshadowProj = new Transform3D();
		    u.invert();
		    tshadowProj.mul(u, proj);
		    int n = ita.getVertexCount();
		    int count = ita.getIndexCount();
		    IndexedTriangleArray shadow = new IndexedTriangleArray(n,
		    GeometryArray.COORDINATES | GeometryArray.BY_REFERENCE, count);
		    shadow.setCapability(GeometryArray.ALLOW_REF_DATA_READ);
		    shadow.setCapability(GeometryArray.ALLOW_REF_DATA_WRITE);
		    double[] vert = new double[3*n];
		    Point3d p = new Point3d();
		    for (int i = 0; i < n; i++) {
		      ga.getCoordinate(i, p);
		      Vector4d v4 = new Vector4d(p);
		      v4.w = 1;
		      tshadowProj.transform(v4);
		      Point4d p4 = new Point4d(v4);
		      p.project(p4);
		      vert[3*i] = p.x;
		      vert[3*i+1] = p.y;
		      vert[3*i+2] = p.z;
		    }
		    shadow.setCoordRefDouble(vert);
		    int[] indices = new int[count];
		    ita.getCoordinateIndices(0, indices);
		    shadow.setCoordinateIndices(0, indices);
		    return shadow;
		  }
	  class ShadowUpdater implements GeometryUpdater {    
		    public void updateData(Geometry geometry) {
		      double[] vert = ((GeometryArray)geometry).getCoordRefDouble();
		      int n = vert.length/3;
		      Transform3D rot = new Transform3D();
		      tspin.getTransform(rot);
		      Transform3D tr = new Transform3D(tshadowProj);
		      tr.mul(rot);
		      Point3d p = new Point3d();
		      for (int i = 0; i < n; i++) {
		        tgeom.getCoordinate(i, p);
		        Vector4d v4 = new Vector4d(p);
		        v4.w = 1;
		        tr.transform(v4);
		        Point4d p4 = new Point4d(v4);
		        p.project(p4);
		        vert[3*i] = p.x;
		        vert[3*i+1] = p.y;
		        vert[3*i+2] = p.z;
		      }
		    }
		  }
		  
		  class ShadowBehavior extends Behavior {
		    WakeupOnElapsedFrames wakeup = null;
		    
		    public ShadowBehavior() {
		      wakeup = new WakeupOnElapsedFrames(0);
		    }
		    
		    public void initialize() {
		      wakeupOn(wakeup);
		    }
		    
		    public void processStimulus(java.util.Enumeration enumeration) {
		      tshadowGeom.updateData(tupdater);
		      wakeupOn(wakeup);
		    }    
		  }
		  
		  public Appearance createTextureAppearance(String path){    
			    Appearance ap = new Appearance();
			    URL filename = 
			        getClass().getClassLoader().getResource(path);
			    TextureLoader loader = new TextureLoader(filename, this);
			    ImageComponent2D image = loader.getImage();
			    if(image == null) {
			      System.out.println("Cannot find texture file.");
			    }
			    Texture2D texture = new Texture2D(Texture.BASE_LEVEL, Texture.RGBA,
			    image.getWidth(), image.getHeight());
			    texture.setImage(0, image);
			    texture.setEnable(true);
			    texture.setMagFilter(Texture.BASE_LEVEL_LINEAR);
			    texture.setMinFilter(Texture.BASE_LEVEL_LINEAR);
			    ap.setTexture(texture);
			    return ap;
			  }
}
