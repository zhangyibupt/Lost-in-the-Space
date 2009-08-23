package org.limewire.setting;

import java.util.Properties;

import org.limewire.util.StringUtils;


/**
 * Provides a <code>String</code> setting value. As a
 * subclass of <code>Setting</code>, the setting has a key. 
 * <p>
 * When you add items to the setting, you can only add an entire array at once, 
 * as opposed to per array element. In the same vein, you retrieve the entire 
 * array value with the <code>getValue</code> method.
 * <p>
 * Create a <code>StringArraySetting</code> object with a 
 * {@link SettingsFactory#createStringArraySetting(String, String[])}.
 */
 
public class StringArraySetting extends AbstractSetting<String[]> {
    
    public static final char SEPARATOR = ';';
    
    private String[] value;

    /**
     * Creates a new <tt>StringArraySetting</tt> instance with the specified
     * key and default value.
     * @param key the constant key to use for the setting
     * @param defaultValue the default value to use for the setting
     */
    StringArraySetting(Properties defaultProps, Properties props, String key, 
                                                       String[] defaultValue) {
        super(defaultProps, props, key, encode(defaultValue));
    }
        
    /**
     * @return the value of this setting
     */
    public String[] get() {
        return value;
    }

    /**
     * Mutator for this setting.
     *
     * @param value the value to store
     */
    public void set(String[] value) {
        setValueInternal(encode(value));
    }
    
    /** Load value from property string value.
     * @param sValue property string value
     *
     */
    @Override
    protected void loadValue(String sValue) {
        value = decode(sValue);
    }
    
    /**
     * Splits the string into an Array
     */
    public static String[] decode(String src) {
        
        if (src == null || src.length()==0) {
            return (new String[0]);
        }
        
        return StringUtils.split(src, SEPARATOR);
    }
    
    /**
     * Separates each field of the array by a semicolon.
     */
    public static String encode(String[] src) {
        
        if (src == null || src.length==0) {
            return "";
        }
        
        StringBuilder buffer = new StringBuilder();
        for(String str : src) {
            buffer.append(str).append(SEPARATOR);
        }
        
        if (buffer.length() > 0) {
            buffer.setLength(buffer.length()-1);
        }
        return buffer.toString();
    }

    @Override
    public String toString() {
        return encode(get());
    }
}
