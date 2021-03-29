package com.atguigu.utils;

import kd.bos.context.RequestContext;
import kd.bos.mutex.DataMutex;
import kd.bos.mutex.MutexFactory;

import java.io.IOException;
import java.util.Collections;
import java.util.Map;

/**
 * @author rd_kai_cao
 * @Description 互斥操作
 * @Date 2020/11/30 18:45
 */
public class MutexUtils {

    public static String separation = "/";

    public static long LOCK_KEEP_TIME_MS = 6000;

    public static String ENTITY_KEY;
//
//    public static void checkLock(String templatenumber, Long model) {
//
//        Map<String, String> lockinfo = getLockInfo(templatenumber, model);
//
//        if (lockinfo == null || lockinfo.size() == 0) return;
//
//        if (!RequestContext.get().getUserId().equals(lockinfo.get("userid"))) {
//
//            String lockTime = (String) lockinfo.get("lockedTime");
//
//            if (System.currentTimeMillis() - Long.parseLong(lockTime) > LOCK_KEEP_TIME_MS) {
//
//                unlock(templatenumber, model);
//
//            } else {
//                showTips(lockinfo, templatenumber);
//            }
//        }
//    }
//
//    public static Map<String, String> getLockInfo(String templatenumber, Long model) {
//
//        try (DataMutex dataMutex = MutexFactory.createDataMutex()) {
//
//            Map<String, String> lockinfo =
//                    dataMutex.getLockInfo(templatenumber + separation + model, String.valueOf(model), ENTITY_KEY);
//
//            lockinfo = lockinfo == null ? Collections.emptyMap() : lockinfo;
//
//            return lockinfo;
//        } catch (IOException ex) {
//            throw new RuntimeException("IOException when closing data mutex!", ex);
//        }
//    }
//
//    public static boolean unlock(String templatenumber, Long model) {
//
//        Map<String, String> lockinfo = getLockInfo(templatenumber, model);
//
//        String userid = lockinfo.get("userid");
//
//        if (userid != null && userid.equals(RequestContext.get().getUserId())) {
//
//            try (DataMutex dataMutex = MutexFactory.createDataMutex()) {
//
//                return dataMutex.release(templatenumber + separation + model, ENTITY_KEY, OPERATION_KEY);
//
//            } catch (IOException ex) {
//
//                throw new RuntimeException("IOException when closing data mutex!", ex);
//            }
//        }
//        return false;
//    }

}
