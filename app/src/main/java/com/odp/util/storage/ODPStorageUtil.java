package com.odp.util.storage;

import android.text.TextUtils;

import com.tencent.mmkv.MMKV;

import java.util.Arrays;
import java.util.List;

import androidx.collection.SimpleArrayMap;

/**
 * @author ChenHh
 * @time 2018/10/16 11:31
 * @des 数据存储工具类
 **/
public class ODPStorageUtil {
    private static final String STORAGE_SUFFIX = "odp_storage";
    private static SimpleArrayMap<String, ODPStorageUtil> STORAGE_UTIL_MAP = new SimpleArrayMap<>();

    public static ODPStorageUtil getInstance(String fileName) {
        String name = STORAGE_SUFFIX;
        if (!TextUtils.isEmpty(fileName)) {
            name = STORAGE_SUFFIX + fileName;
        }
        ODPStorageUtil util = STORAGE_UTIL_MAP.get(name);

        if (util == null) {
            util = new ODPStorageUtil(name);
            STORAGE_UTIL_MAP.put(name, util);
        }
        return util;
    }

    public static ODPStorageUtil getDefault() {
        return getInstance(null);
    }

    private ODPStorageUtil(String name) {
        mmkv = MMKV.mmkvWithID(name);
    }

    private MMKV mmkv;

    public void push(String key, int value) {
        mmkv.encode(key, value);
    }

    public void push(String key, String value) {
        mmkv.encode(key, value);
    }

    public void push(String key, long value) {
        mmkv.encode(key, value);
    }

    public void push(String key, boolean value) {
        mmkv.encode(key, value);
    }

    public void push(String key, float value) {
        mmkv.encode(key, value);
    }

    public void push(String key, double value) {
        mmkv.encode(key, value);
    }

    public void push(String key, byte[] value) {
        mmkv.encode(key, value);
    }

    public byte[] push(String key) {
        return mmkv.decodeBytes(key);
    }

    public String getString(String key) {
        return mmkv.decodeString(key, "");
    }

    public String getString(String key, String def) {
        return mmkv.decodeString(key, def);
    }

    public long getLong(String key) {
        return mmkv.decodeLong(key);
    }

    public float getFloat(String key) {
        return mmkv.decodeFloat(key);
    }

    public double getDouble(String key) {
        return mmkv.decodeDouble(key);
    }

    public boolean getBoolean(String key) {
        return mmkv.decodeBool(key);
    }

    public int getInteger(String key) {
        return mmkv.decodeInt(key);
    }

    public void remove(String... keys) {
        if (keys == null || keys.length == 0) {
            return;
        }
        mmkv.removeValuesForKeys(keys);
    }

    public void removeIgnore(String... keys) {
        if (keys == null || keys.length == 0) {
            return;
        }
        String[] allKeys = mmkv.allKeys();
        List<String> list = Arrays.asList(keys);
        for (String key : allKeys) {
            if (!list.contains(key)) {
                mmkv.remove(key);
            }
        }
    }

}
