package org.eclipse.emf.henshin.variability.configuration.ui.helpers;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.draw2d.ColorConstants;
import org.eclipse.swt.graphics.Color;

public class ColorHelper {
	
	private static final Color henshinGreen = new Color(null, 0, 200, 0);  
	private static final Color fadedRed = new Color(null, 255, 180, 180);
	private static final Color fadedGreen = new Color(null, 180, 255, 180);
	private static final Color fadedBlue = new Color(null, 255, 180, 180);

	private static final Map<Color, Color> concealColorMap;
	static {
		concealColorMap = new HashMap<Color, Color>();
		concealColorMap.put(ColorConstants.black, ColorConstants.gray);
		concealColorMap.put(ColorConstants.gray, ColorConstants.lightGray);
		concealColorMap.put(ColorConstants.blue, fadedBlue);
		concealColorMap.put(ColorConstants.red, fadedRed);
		concealColorMap.put(henshinGreen, fadedGreen);
	}

	private static final Map<Color, Color> revealColorMap;
	static {
		revealColorMap = new HashMap<Color, Color>();
		revealColorMap.put(ColorConstants.gray, ColorConstants.black);
		revealColorMap.put(ColorConstants.lightGray, ColorConstants.gray);
		revealColorMap.put(fadedBlue, ColorConstants.blue);
		revealColorMap.put(fadedRed, ColorConstants.red);
		revealColorMap.put(fadedGreen, henshinGreen);
	}
	
	public static final Color getConcealColor(Color color) {
		return concealColorMap.get(color);
	}
	
	public static final Color getRevealColor(Color color) {
		return revealColorMap.get(color);
	}
}
