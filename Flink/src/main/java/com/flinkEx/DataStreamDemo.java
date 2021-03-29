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
        // 网站点击 Click 的数据流
        DataStream<Map<String, Long>> clicks = new DataStream(null, null);

        DataStream<Tuple2<String, Long>> result = clicks
                // 将网站点击映射为 (userId, 1) 以便计数
                .map(
                        // 实现 MapFunction 接口定义函数
                        new MapFunction<Map<String, Long>, Tuple2<String, Long>>() {
                            @Override
                            public Tuple2<String, Long> map(Map<String, Long> click) {
                                return Tuple2.of(click.get("") + "", 1L);
                            }
                        })
                // 以 userId (field 0) 作为 key
                .keyBy(0)
                // 定义 30 分钟超时的会话窗口
                .window(EventTimeSessionWindows.withGap(Time.minutes(30L)))
                // 对每个会话窗口的点击进行计数，使用 lambda 表达式定义 reduce 函数
                .reduce((a, b) -> Tuple2.of(a.f0, a.f1 + b.f1));

    }


}
