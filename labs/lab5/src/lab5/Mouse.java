package lab5;

import com.sun.j3d.loaders.Scene;
import com.sun.j3d.loaders.objectfile.ObjectFile;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.ViewingPlatform;

import javax.media.j3d.*;
import javax.swing.*;
import javax.vecmath.*;
import java.awt.*;
import java.io.FileReader;
import java.io.IOException;
import java.util.Map;


class Mouse extends JFrame {

    private Canvas3D canvas;
    private SimpleUniverse universe;
    private BranchGroup root;

    private TransformGroup mouse;

    private Map<String, Shape3D> shapeMap;

    Mouse() throws IOException {

        configureWindow();
        configureCanvas();
        configureUniverse();

        root = new BranchGroup();
        root.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);

        addImageBackground("source_folder/Drive.jpg");
        addLightToUniverse();

        changeViewAngle();

        mouse = getMouseGroup();

        TransformGroup room = new TransformGroup();
        room.addChild(mouse);

        root.addChild(room);

        addAppearance();

        MouseAnimation mouseAnimation = new MouseAnimation(this);
        canvas.addKeyListener(mouseAnimation);

        root.compile();
        universe.addBranchGraph(root);
    }

    private void configureWindow() {
        setTitle("Mouse Animation");
        setSize(1000, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void configureCanvas() {
        canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
        canvas.setDoubleBufferEnable(true);
        canvas.setFocusable(true);
        add(canvas, BorderLayout.CENTER);
    }

    private void configureUniverse() {
        universe = new SimpleUniverse(canvas);
        universe.getViewingPlatform().setNominalViewingTransform();
    }

    private void addImageBackground(String imagePath) {
        TextureLoader t = new TextureLoader(imagePath, canvas);
        Background background = new Background(t.getImage());
        background.setImageScaleMode(Background.SCALE_FIT_ALL);
        BoundingSphere bounds = new BoundingSphere(new Point3d(0.0, 0.0, 0.0), 100.0);
        background.setApplicationBounds(bounds);
        root.addChild(background);
    }

    private void addLightToUniverse() {
        BoundingSphere bounds = new BoundingSphere();
        bounds.setRadius(1000);

        DirectionalLight directionalLight = new DirectionalLight(
                new Color3f(new Color(255, 255, 255)),
                new Vector3f(0, -0.5f, -0.5f));
        directionalLight.setInfluencingBounds(bounds);

        AmbientLight ambientLight = new AmbientLight(
                new Color3f(new Color(255, 255, 245)));
        ambientLight.setInfluencingBounds(bounds);

        root.addChild(directionalLight);
        root.addChild(ambientLight);
    }

    private TransformGroup getMouseGroup() throws IOException {
        Transform3D scale = new Transform3D();
        scale.setScale(new Vector3d(1, 1, 1));

        Transform3D yRotation = new Transform3D();
        yRotation.rotY(Math.PI);

        Transform3D zRotation = new Transform3D();
        zRotation.rotZ(.06);

        Transform3D xRotation = new Transform3D();
        xRotation.rotX(-.5);

        zRotation.mul(xRotation);
        yRotation.mul(zRotation);
        scale.mul(yRotation);

        TransformGroup group = getModelGroup("source_folder/X5.obj");
        group.setTransform(scale);
        return group;
    }

    private TransformGroup getModelGroup(String path) throws IOException {
        Scene scene = getSceneFromFile(path);
        shapeMap = scene.getNamedObjects();

        printModelElementsList(shapeMap);

        TransformGroup group = new TransformGroup();

        for (String shapeName: shapeMap.keySet()) {
            Shape3D shape = shapeMap.get(shapeName);

            scene.getSceneGroup().removeChild(shape);
            group.addChild(shape);
        }

        group.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

        return group;
    }

    private void printModelElementsList(Map<String, Shape3D> shapeMap) {
        for (String name : shapeMap.keySet()) {
            System.out.printf("Name: %s\n", name);
        }
    }

    private void addAppearance() {
        shapeMap.get("wheel_004").setAppearance(getAppearance(new Color(0,0,0)));
        shapeMap.get("wheel_003").setAppearance(getAppearance(new Color(0,0,0)));
        shapeMap.get("wheel_002").setAppearance(getAppearance(new Color(0,0,0)));
        shapeMap.get("wheel_001").setAppearance(getAppearance(new Color(0,0,0)));       shapeMap.get("door_rr_ok_001").setAppearance(getAppearance(new Color(86, 1 ,0)));
       shapeMap.get("door_rf_ok_001").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("door_rr_ok_001").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("door_lf_ok_001").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("door_lr_ok_001").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("steklo_001").setAppearance(getAppearance(new Color(0,0,1)));
       shapeMap.get("windscre01_001").setAppearance(getAppearance(new Color(0, 1, 0)));
       shapeMap.get("group_004").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("group_002").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("group_001").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("bonnet_ok_001").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("plate_08_003").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("mtk_1_002").setAppearance(getAppearance(new Color(50, 49, 49)));
       shapeMap.get("3d_object__32_007").setAppearance(getAppearance(new Color(0, 0, 0))); //diski
       shapeMap.get("3d_object__32_005").setAppearance(getAppearance(new Color(0, 0, 0))); //diski
       shapeMap.get("3d_object__32_003").setAppearance(getAppearance(new Color(0, 0, 0))); //diski
       shapeMap.get("3d_object__32_001").setAppearance(getAppearance(new Color(0, 0, 0))); //diski
       shapeMap.get("m_3_001").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
       shapeMap.get("m_3_002").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
       shapeMap.get("915_1_001").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
       shapeMap.get("915_1_002").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
       shapeMap.get("boot_ok_001").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
       shapeMap.get("without_pe_001").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
       shapeMap.get("d_1_001").setAppearance(getAppearance(new Color(50, 49, 49))); //diski
    }

    private Appearance getAppearance(Color materialColor){
        Appearance appearance = new Appearance();
        appearance.setMaterial(getMaterial(materialColor));
        return appearance;
    }

    private Material getMaterial(Color defaultColor) {
        Material material = new Material();
        material.setEmissiveColor(new Color3f(Color.BLACK));
        material.setAmbientColor(new Color3f(defaultColor));
        material.setDiffuseColor(new Color3f(defaultColor));
        material.setSpecularColor(new Color3f(defaultColor));
        material.setShininess(64);
        material.setLightingEnable(true);
        return material;
    }

    private void changeViewAngle() {
        ViewingPlatform vp = universe.getViewingPlatform();
        TransformGroup vpGroup = vp.getMultiTransformGroup().getTransformGroup(0);
        Transform3D vpTranslation = new Transform3D();
        vpTranslation.setTranslation(new Vector3f(0, 0, 13));
        vpGroup.setTransform(vpTranslation);
    }

    private static Scene getSceneFromFile(String location) throws IOException {
        ObjectFile file = new ObjectFile(ObjectFile.RESIZE);
        file.setFlags(ObjectFile.RESIZE | ObjectFile.TRIANGULATE | ObjectFile.STRIPIFY);
        return file.load(new FileReader(location));
    }

    TransformGroup getMouseTransformGroup() {
        return mouse;
    }
}
