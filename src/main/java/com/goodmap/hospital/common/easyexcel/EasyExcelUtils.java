package com.goodmap.hospital.common.easyexcel;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Author 李美泉
 * @Data 2020/11/13 time
 * @Description
 **/
public class EasyExcelUtils {
    public static<T> AnalysisEventListener<T> getListener(Consumer<List<T>> consumer, int threshold){
        List<T> linkedList = new LinkedList<T>();

        AnalysisEventListener listener = new AnalysisEventListener<T>() {
            @Override
            public void invoke(T t, AnalysisContext analysisContext) {
                linkedList.add(t);
                if(linkedList.size() == threshold){
                    consumer.accept(linkedList);
                    linkedList.clear();
                }
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                if(linkedList.size()>0){
                    consumer.accept(linkedList);
                }
            }
        };
        return listener;
    }
    public static <T> AnalysisEventListener<T> getListener(Consumer<List<T>> consumer){
        return getListener(consumer,50);
    }
}
