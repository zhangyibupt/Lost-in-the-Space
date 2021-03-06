package org.limewire.setting;

import java.awt.Color;
import java.util.Properties;



/**
 * Provides a {@link Color} setting value. As a 
 * subclass of <code>Setting</code>, the setting has a key.
 * <p>
 * Create a <code>ColorSetting</code> object with a 
 * {@link SettingsFactory#createColorSetting(String, Color)}.
 */
public final class ColorSetting extends AbstractSetting<Color> {
    
    private Color value;

    
	/**
	 * Creates a new <tt>ColorSetting</tt> instance with the specified
	 * key and default value.
	 *
	 * @param key the constant key to use for the setting
	 * @param defaultColor the default value to use for the setting
	 */
	static ColorSetting createColorSetting(Properties defaultProps, 
										   Properties props, String key, 
                                           Color defaultColor ) { 
		return new ColorSetting(defaultProps, props, key, 
                                                 formatColor(defaultColor));
	}

	/**
	 * Creates a new <tt>ColorSetting</tt> instance with the specified 
	 * key and default value.
	 *
	 * @param defaultProps the <tt>Properties</tt> file that stores the 
	 *  defaults
	 * @param props the <tt>Properties</tt> file to store this color
	 * @param key the constant key to use for the setting
	 * @param value the default value to use for the setting
	 */
	private ColorSetting(Properties defaultProps, Properties props, String key, 
						                       String value) {
		super(defaultProps, props, key, value);
	}
        
	/**
	 * Returns the value of this setting.
	 * 
	 * @return the value of this setting
	 */
	public Color get() {
        return value;
	}

	/**
	 * Mutator for this setting.
	 *
	 * @param value the value to store
	 */
	public void set(Color value) {
	    setValueInternal(formatColor(value));
        this.value = value;
	}
	/** Returns the value of this setting. */
	@Override
    protected void loadValue(String sValue) {
	    sValue = sValue.trim();
	    try {
            int r = Integer.parseInt(sValue.substring(1, 3), 16);
            int g = Integer.parseInt(sValue.substring(3, 5), 16);
            int b = Integer.parseInt(sValue.substring(5, 7), 16);
            value = new Color(r,g,b);
        } catch(NumberFormatException nfe) {
            revertToDefault();
        } catch(StringIndexOutOfBoundsException sioobe) {
            revertToDefault();
        }
	}
    
    /**
     * Convert color to string property value.
     * @param color color
     * @return the string property value
     */
    private static String formatColor(Color color) {
		String red   = Integer.toHexString(color.getRed());
		String green = Integer.toHexString(color.getGreen());
		String blue  = Integer.toHexString(color.getBlue());	
		if(red.length() == 1)   red   = "0" + red;
		if(green.length() == 1) green = "0" + green;
		if(blue.length() == 1)  blue  = "0" + blue;
		return "#" + red + green + blue;
    }
        
}
