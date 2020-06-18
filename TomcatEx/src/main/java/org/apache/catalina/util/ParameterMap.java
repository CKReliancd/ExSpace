package org.apache.catalina.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Administrator
 * @Description
 * @Date 2020/6/18 16:59
 */
public class ParameterMap extends HashMap {

    /**
     * 构造器初始化创建一个默认的hashmap
     */
    public ParameterMap(){super();}

    /**
     * 构造器初始化，创造默认加载因子指定容量的hashmap
     * @param initialCapacity
     */
    public ParameterMap(int initialCapacity){super(initialCapacity);}

    /**
     * Construct a new, empty map with the specified initial capacity and load factor.
     * @param initialCapacity
     * @param loadFactor
     */
    public ParameterMap(int initialCapacity,float loadFactor){super(initialCapacity, loadFactor);}

    /**
     * Construct a new map with the same mappings as the given map.
     *
     * @param map Map whose contents are dupliated in the new map
     */
    public ParameterMap(Map map) { super(map); }

    /**
     * The current lock state of this parameter map.
     */
    private boolean locked = false;

    /**
     * Return the locked state of this parameter map.
     */
    public boolean isLocked() {

        return (this.locked);

    }

    /**
     * Set the locked state of this parameter map.
     *
     * @param locked The new locked state
     */
    public void setLocked(boolean locked) {

        this.locked = locked;

    }

    /**
     * The string manager for this package.
     */
    private static final StringManager sm =
            StringManager.getManager("org.apache.catalina.util");

    /**
     * Remove all mappings from this map.
     *
     * @exception IllegalStateException if this map is currently locked
     */
    public void clear(){
        if (locked){
            throw new IllegalStateException(sm.getString("propertyMap.locked"));
        }
        super.clear();
    }

    /**
     * Associate the specified value with the specified key in this map.  If
     * the map previously contained a mapping for this key, the old value is
     * replaced.
     *
     * @param key Key with which the specified value is to be associated
     * @param value Value to be associated with the specified key
     *
     * @return The previous value associated with the specified key, or
     *  <code>null</code> if there was no mapping for key
     *
     * @exception IllegalStateException if this map is currently locked
     */
    public Object put(Object key, Object value) {

        if (locked)
            throw new IllegalStateException
                    (sm.getString("parameterMap.locked"));
        return (super.put(key, value));

    }

    /**
     * Remove the mapping for this key from the map if present.
     *
     * @param key Key whose mapping is to be removed from the map
     *
     * @return The previous value associated with the specified key, or
     *  <code>null</code> if there was no mapping for that key
     *
     * @exception IllegalStateException if this map is currently locked
     */
    public Object remove(Object key) {

        if (locked)
            throw new IllegalStateException
                    (sm.getString("parameterMap.locked"));
        return (super.remove(key));

    }

}
