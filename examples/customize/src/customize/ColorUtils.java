package customize;

import java.awt.Color;
import java.lang.reflect.Field;

public class ColorUtils {
	public static String getColorName(Color color) {
		try {
	        for (Field field : Class.forName("java.awt.Color").getFields()) {
	        	if ((Color) field.get(null) == color) {
	        		return field.getName();
	        	}
	        }
	    } catch (Exception e) {
	    }

	    return null;
	}
	
	public static Color getColor(String colorName) {
		try {
	        Field field = Class.forName("java.awt.Color").getField(colorName);
	        return (Color) field.get(null);
	    } catch (Exception e) {
	        return null;
	    }
	}
}
