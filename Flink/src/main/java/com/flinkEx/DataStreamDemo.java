package com.flinkEx;

import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.assigners.EventTimeSessionWindows;
import org.apache.flink.streaming.api.windowing.time.Time;

import java.util.Map;

public class DataStreamDemo {

    public static void main(String[] args) {
        // ��վ��� Click ��������
        DataStream<Map<String, Long>> clicks = new DataStream(null, null);

        DataStream<Tuple2<String, Long>> result = clicks
                // ����վ���ӳ��Ϊ (userId, 1) �Ա����
                .map(
                        // ʵ�� MapFunction �ӿڶ��庯��
                        new MapFunction<Map<String, Long>, Tuple2<String, Long>>() {
                            @Override
                            public Tuple2<String, Long> map(Map<String, Long> click) {
                                return Tuple2.of(click.get("") + "", 1L);
                            }
                        })
                // �� userId (field 0) ��Ϊ key
                .keyBy(0)
                // ���� 30 ���ӳ�ʱ�ĻỰ����
                .window(EventTimeSessionWindows.withGap(Time.minutes(30L)))
                // ��ÿ���Ự���ڵĵ�����м�����ʹ�� lambda ���ʽ���� reduce ����
                .reduce((a, b) -> Tuple2.of(a.f0, a.f1 + b.f1));

    }


}
