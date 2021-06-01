package com.goodmap.hospital.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author 李美泉
 * @Data 2020/11/14 time
 * @Description 测试代码执行时间
 **/
public class TimeWatchUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(TimeWatchUtil.class);
    private static final ThreadLocal<TimeWatchUtil.TimeWatchInfo> TIME_WATCH_INFO = new ThreadLocal();

    protected TimeWatchUtil() {
    }

    public static void createTimeWatch(String watchName) {
        TIME_WATCH_INFO.remove();
        TIME_WATCH_INFO.set(TimeWatchUtil.TimeWatchInfo.create(watchName));
    }

    public static void addStep(String stepName) {
        TimeWatchUtil.TimeWatchInfo timeWatchInfo = (TimeWatchUtil.TimeWatchInfo)TIME_WATCH_INFO.get();
        if (Objects.isNull(timeWatchInfo)) {
            throw new RuntimeException("TimeWatchUtil未初始化");
        } else {
            timeWatchInfo.addStep(stepName);
        }
    }

    public static void printTimeWatchList() {
        TimeWatchUtil.TimeWatchInfo timeWatchInfo = (TimeWatchUtil.TimeWatchInfo)TIME_WATCH_INFO.get();
        if (!Objects.isNull(timeWatchInfo)) {
            LOGGER.info(timeWatchInfo.outputTimeList());
        }
    }

    private static class TimeWatchInfo {
        private long start;
        private List<Long> sections;
        private List<String> sectionNames;
        private String watchName;

        private TimeWatchInfo() {
        }

        public static TimeWatchUtil.TimeWatchInfo create(String watchName) {
            if (!Objects.isNull(watchName) && !watchName.equalsIgnoreCase("")) {
                TimeWatchUtil.TimeWatchInfo timeWatchInfo = new TimeWatchUtil.TimeWatchInfo();
                timeWatchInfo.sections = new ArrayList();
                timeWatchInfo.sectionNames = new ArrayList();
                timeWatchInfo.start = System.currentTimeMillis();
                timeWatchInfo.watchName = watchName;
                return timeWatchInfo;
            } else {
                throw new IllegalArgumentException("watchName不能为空");
            }
        }

        public void addStep(String stepName) {
            this.sectionNames.add(stepName);
            this.sections.add(System.currentTimeMillis());
        }

        public String outputTimeList() {
            StringBuilder outStr = new StringBuilder();
            outStr.append("[TIMEWATCH] ");
            outStr.append(this.watchName.trim());
            outStr.append(":");
            outStr.append(" [DETAILS] ");
            long last = this.start;

            for(int i = 0; i < this.sections.size(); ++i) {
                long temp = (Long)this.sections.get(i);
                outStr.append((String)this.sectionNames.get(i) + ":");
                outStr.append(temp - last);
                outStr.append(" ");
                last = temp;
            }

            long totalWaste = 0L;
            if (this.sections.size() > 0) {
                totalWaste = (Long)this.sections.get(this.sections.size() - 1) - this.start;
            }

            outStr.insert(this.watchName.length() + 13, totalWaste);
            return outStr.toString();
        }
    }

}
