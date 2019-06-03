package com.gmail.max.airplane.utils;

import com.sun.j3d.utils.image.TextureLoader;

import javax.media.j3d.Appearance;
import javax.media.j3d.Material;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.vecmath.Color3f;
import javax.vecmath.Color4f;
import java.awt.*;

public class AppearanceUtils {
    public static Appearance getAppearance(Color color, String texturePath) {
        Appearance ap = new Appearance();

        Color3f emissive = new Color3f(Color.BLACK);
        Color3f ambient = new Color3f(color);
        Color3f diffuse = new Color3f(color);
        Color3f specular = new Color3f(color);

        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 64.0f));

        if (texturePath != null && !texturePath.isEmpty()) {
            TextureLoader loader = new TextureLoader(texturePath, "LUMINANCE", new Container());
            Texture texture = loader.getTexture();

            texture.setBoundaryModeS(Texture.WRAP);
            texture.setBoundaryModeT(Texture.WRAP);
            texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));
            TextureAttributes texAttr = new TextureAttributes();
            texAttr.setTextureMode(TextureAttributes.MODULATE);
            ap.setTexture(texture);
            ap.setTextureAttributes(texAttr);
        }

        return ap;
    }

    public static Appearance getGreyAppearance() {
        return getAppearance(new Color(106, 106, 106), "resource\\images\\airplane-texture.jpg");
    }

    public static Appearance getEmissiveAppearance(Color color) {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(color);
        Color3f ambient = new Color3f(Color.BLACK);
        Color3f diffuse = new Color3f(Color.BLACK);
        Color3f specular = new Color3f(Color.BLACK);

        Material material = new Material(ambient, emissive, diffuse, specular, 64.0f);

        ap.setMaterial(material);

        return ap;
    }

	public static Appearance getPencilBodyAppearence() {
        TextureLoader loader = new TextureLoader("source_folder\\pencilbody.jpg", "LUMINANCE", new Container());
        Texture texture = loader.getTexture();
        
        texture.setBoundaryModeS(Texture.WRAP);
        texture.setBoundaryModeT(Texture.WRAP);
        texture.setBoundaryColor(new Color4f(0.0f, 1.0f, 1.0f, 0.0f));

        TextureAttributes texAttr = new TextureAttributes();
        texAttr.setTextureMode(TextureAttributes.MODULATE);

        Appearance ap = new Appearance();
        ap.setTexture(texture);
        ap.setTextureAttributes(texAttr);

        Color3f emissive = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(100,38, 38));
        Color3f diffuse = new Color3f(new Color(100,38, 38));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
	}

	public static Appearance getPencilGraphiteAppearence() {
        Appearance ap = new Appearance();
        Color3f emissive = new Color3f(new Color(1.0f,1.0f, 1.0f));
        Color3f ambient = new Color3f(new Color(0, 0, 0));
        Color3f diffuse = new Color3f(new Color(0, 0, 0));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emissive, diffuse, specular, 1.0f));
        return ap;
	}
	


	public static Appearance getPencilEdgeAppearence() {
        Appearance ap = new Appearance();
        Color3f emission = new Color3f(new Color(0,0, 0));
        Color3f ambient = new Color3f(new Color(100, 88, 59));
        Color3f diffuse = new Color3f(new Color(255, 156, 41));
        Color3f specular = new Color3f(new Color(0,0, 0));
        ap.setMaterial(new Material(ambient, emission, diffuse, specular, 1.0f));
        return ap;
	}
}
