package com.atguigu.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import kd.bos.algo.*;
import kd.bos.algo.input.CollectionInput;
import kd.bos.context.RequestContext;
import kd.bos.dataentity.OperateOption;
import kd.bos.dataentity.entity.DynamicObject;
import kd.bos.dataentity.entity.DynamicObjectCollection;
import kd.bos.dataentity.serialization.SerializationUtils;
import kd.bos.entity.datamodel.IDataModel;
import kd.bos.entity.operate.result.IOperateInfo;
import kd.bos.entity.operate.result.OperateErrorInfo;
import kd.bos.entity.operate.result.OperationResult;
import kd.bos.exception.KDBizException;
import kd.bos.form.control.EntryGrid;
import kd.bos.logging.Log;
import kd.bos.orm.ORM;
import kd.bos.orm.query.QCP;
import kd.bos.orm.query.QFilter;
import kd.bos.servicehelper.BusinessDataServiceHelper;
import kd.bos.servicehelper.QueryServiceHelper;
import kd.bos.servicehelper.operation.SaveServiceHelper;
import kd.bos.servicehelper.org.OrgUnitServiceHelper;
import kd.dfc.dfcbkl.common.constant.BosUser;
import kd.dfc.dfcbkl.common.constant.DfcbklConstant;
import kd.dfc.dfcbkl.common.constant.WorkCalendar;
import org.apache.commons.lang3.StringUtils;

import java.text.MessageFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author rd_kai_cao
 * @Description
 * @Date 2020/11/18 19:53
 */
public class DfcbklUtils {

    private static Pattern pattern = Pattern.compile("^[0-9]*$");

    /**
     * 转换小程序传送的data
     *
     * @param params
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T parseParamData(Map<String, Object> params, Class<T> clazz) {

        String data = SerializationUtils.toJsonString(params.get(DfcbklConstant.PARAMSDATA));

        JSONObject parse = (JSONObject) JSONObject.parse(data);

        T object = JSON.toJavaObject(parse, clazz);

        System.out.println(SerializationUtils.toJsonString(object));

        return object;
    }

    /**
     * 获取表格选中行
     *
     * @param grid
     */
    public static int[] getSelectIndex(EntryGrid grid) {

        int[] selectRows = grid.getSelectRows();

        return selectRows;
    }

    /**
     * 获取选中行
     */
    public static DynamicObjectCollection getSelectedDyObject(int[] selectedIndexs, EntryGrid grid) {

        IDataModel gridModel = grid.getModel();

        // 当前表格的标识
        String entryKey = grid.getKey();

        DynamicObjectCollection selectedRowsDyObject = new DynamicObjectCollection();

        // 获取选中的行数据
        for (int selectedIndex : selectedIndexs) {

            DynamicObject selectObject = gridModel.getEntryRowEntity(entryKey, selectedIndex);

            selectedRowsDyObject.add(selectObject);
        }

        return selectedRowsDyObject;
    }

    /**
     * 从小到大排列，且不重复
     *
     * @param array
     * @return
     */
    public static boolean isUniqueSorted(int[] array) {

        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] >= array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * 校验是否为数字或者小数
     *
     * @param str
     * @return
     */
    public static boolean isNumeric(String str) {

        //判断是否有小数点
        if (str.indexOf(".") > 0) {

            //判断是否只有一个小数点
            if (str.indexOf(".") == str.lastIndexOf(".") && str.split("\\.").length == 2) {

                return pattern.matcher(str.replace(".", "")).matches();
            } else {
                return false;
            }
        } else {
            return pattern.matcher(str).matches();
        }
    }

    /**
     * 根据人员IDSet获取对应的信息
     *
     * @param operatorIdSet
     * @return
     */
    public static Map<Long, String> getUserPositionMap(Set<Long> operatorIdSet) {

        String BOSUSERFIELD =
                BosUser.ID + "," +
                        BosUser.NAME + "," +
                        BosUser.ENTRYENTITY + "." + BosUser.E_DPT + "," +
                        BosUser.ENTRYENTITY + "." + BosUser.E_ISPARTJOB;

        QFilter bosUserIdFilter = new QFilter(BosUser.ID, QCP.in, operatorIdSet);

        DynamicObject[] bosUserArray = BusinessDataServiceHelper
                .load(DfcbklConstant.BOS_USER, BOSUSERFIELD, bosUserIdFilter.toArray());

        Map<Long, String> userPositionMap = Maps.newHashMapWithExpectedSize(operatorIdSet.size());

        for (DynamicObject bosUser : bosUserArray) {

            DynamicObjectCollection userPositionColl = bosUser.getDynamicObjectCollection(BosUser.ENTRYENTITY);

            List<DynamicObject> userPositionList = userPositionColl.stream()
                    .filter(up -> up.getDynamicObject(BosUser.E_DPT) != null
                            && !up.getBoolean(BosUser.E_ISPARTJOB))
                    .collect(Collectors.toList());

            if (userPositionList.size() > 0) {

                DynamicObject userPosition = userPositionList.get(0);

                long operatorID = bosUser.getLong(BosUser.ID);

                String dpt = userPosition.getDynamicObject(BosUser.E_DPT)
                        .getLocaleString(BosUser.NAME).getLocaleValue_zh_CN();

                userPositionMap.put(operatorID, dpt);
            }
        }
        return userPositionMap;
    }

    /**
     * 获取按照组织分组的工作日历实体
     *
     * @return
     */
    public static Map<String, DynamicObject> getAllOrgWorkCalendarMap() {

        DynamicObject[] workCalendar = getAllOrgWorkCalendar();

        return Arrays.stream(workCalendar)
                .filter(wc -> wc.getDynamicObject(WorkCalendar.ORG) != null)
                .collect(Collectors.toMap(
                        wc -> wc.getDynamicObject(WorkCalendar.ORG).getString(WorkCalendar.ID),
                        wc -> wc));
    }

    /**
     * 获取所有组织工作日历实体不包含表体具体日历
     *
     * @return
     */
    public static DynamicObject[] getAllOrgWorkCalendar() {

        List<QFilter> filterList = Lists.newArrayList();

        QFilter isIndividuation = new QFilter(WorkCalendar.ISINDIVIDUATION, QCP.equals, "0");

        filterList.add(isIndividuation);

        DynamicObject[] workCalendarArray = BusinessDataServiceHelper.load(DfcbklConstant.WORKCALENDAR,
                WorkCalendar.SELECT_FIELD, filterList.toArray(new QFilter[0]));

        return workCalendarArray;
    }


    /**
     * 获取工作根组织工作日
     *
     * @return
     */
    public static Boolean[] getWorkDays() {

        //获取根组织工作日历
        DynamicObject workCalendar = validatorAndGetRootOrgWorkCalendar();

        return DateUtils.getWorkDays(workCalendar);
    }


    /**
     * 校验和获取根组织工作日历集合
     *
     * @return
     */
    public static DynamicObject validatorAndGetRootOrgWorkCalendar() {

        validatorCalindar();

        DynamicObjectCollection workCalendarColl = getRootOrgWorkCalendarColl();

        DynamicObject workCalendar = workCalendarColl.get(0);

        return workCalendar;
    }

    /**
     * 获取根组织工作日历集合
     *
     * @return
     */
    private static DynamicObjectCollection getRootOrgWorkCalendarColl() {

        List<QFilter> filterList = Lists.newArrayList();

        QFilter isIndividuation = new QFilter(WorkCalendar.ISINDIVIDUATION, QCP.equals, "0");

        filterList.add(isIndividuation);

        QFilter rootOrgIdFilter = new QFilter(WorkCalendar.ORG, QCP.equals, OrgUnitServiceHelper.getRootOrgId());

        filterList.add(rootOrgIdFilter);

        return ORM.create().query(DfcbklConstant.WORKCALENDAR, filterList.toArray(new QFilter[0]));
    }

    /**
     * 验证是否存在工作日历
     */
    public static void validatorCalindar() {

        if (!isExistCalendar()) {
            throw new KDBizException("没有工作日历数据");
        }
    }

    /**
     * 获取当前组织工作日历
     *
     * @return
     */
    public static DynamicObjectCollection getCurrentOrgWorkCalendar() {


        List<QFilter> filterList = Lists.newArrayList();

        QFilter isIndividuation = new QFilter(WorkCalendar.ISINDIVIDUATION, QCP.equals, "0");
        filterList.add(isIndividuation);

        long orgId = RequestContext.get().getOrgId();
        QFilter orgFilter = new QFilter(WorkCalendar.ORG, QCP.equals, orgId);
        filterList.add(orgFilter);

        return ORM.create().query(DfcbklConstant.WORKCALENDAR, filterList.toArray(new QFilter[0]));
    }

    /**
     * 判断是否定制了工作日历
     *
     * @return
     */
    public static boolean isExistCalendar() {

        long rootOrgId = OrgUnitServiceHelper.getRootOrgId();

        QFilter[] qf = new QFilter[]{new QFilter(WorkCalendar.ORG, QCP.equals, rootOrgId),
                new QFilter(WorkCalendar.ISINDIVIDUATION, QCP.equals, "0")};

        return QueryServiceHelper.exists(DfcbklConstant.WORKCALENDAR, qf);
    }

    /**
     * 普通保存操作，抛出异常
     *
     * @param dynamicObjects
     * @param metadataName
     * @param message
     * @param log
     */
    public static void saveOperatorUnLocked(DynamicObject[] dynamicObjects, String metadataName, String message, Log log) {

        OperationResult operationResult = SaveServiceHelper.saveOperate(DfcbklConstant.SAVE_UNLOCKED,
                metadataName, dynamicObjects, OperateOption.create());

        if (!operationResult.isSuccess()) {
            log.error(MessageFormat.format("{0},error:{1}", message, operationResult.getMessage()));
            throw new KDBizException(StringUtils.isBlank(message) ? operationResult.getMessage() : message);
        }
    }


    /**
     * 保存操作，抛出异常
     *
     * @param dynamicObjects
     * @param metadataName
     * @param message
     * @param log
     */
    public static OperationResult saveOperator(DynamicObject[] dynamicObjects, String metadataName, String message, Log log) {

        OperationResult operationResult = SaveServiceHelper.saveOperate(DfcbklConstant.OP_SAVE,
                metadataName, dynamicObjects, OperateOption.create());

        if (!operationResult.isSuccess()) {

            StringBuilder stringBuilder = new StringBuilder();

            stringBuilder.append(message).append(";");

            List<IOperateInfo> validateInfo = operationResult.getAllErrorOrValidateInfo();
            for (IOperateInfo info : validateInfo) {
                stringBuilder.append(info.getMessage()).append(";");
            }

            List<OperateErrorInfo> allErrorInfo = operationResult.getAllErrorInfo();

            for (OperateErrorInfo operateErrorInfo : allErrorInfo) {
                stringBuilder.append(operateErrorInfo.getMessage()).append(";");
            }

            log.error(MessageFormat.format("{0},message:{1}.", message, stringBuilder.toString()));
            throw new KDBizException(StringUtils.isBlank(stringBuilder.toString()) ? stringBuilder.toString() : message);
        }
        return operationResult;
    }

    /**
     * map根据value从大到小排列
     *
     * @param map
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {

        Map<K, V> result = new LinkedHashMap<>();

        map.entrySet().stream()
                .sorted(Map.Entry.<K, V>comparingByValue().reversed())
                .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        return result;
    }


    public static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {

        Map<Object, Boolean> seen = new ConcurrentHashMap<>();

        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
