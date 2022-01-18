package com.ziuch.blog.api.job;

import com.ziuch.blog.api.service.EbookSnapshotService;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class EbookSnapshotJob {

    private static final Logger LOG = LoggerFactory.getLogger(EbookSnapshotJob.class);

    @Resource
    private EbookSnapshotService ebookSnapshotService;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 每一小时更新电子书信息
     */
    @Scheduled(cron = "18/30 * * * * ?")
//    @Scheduled(cron = "0 0 */1 * * ?")
    public void cron() {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("更新电子书快照数据开始");
        long start = System.currentTimeMillis();
        ebookSnapshotService.genSnapshot();
        LOG.info("更新电子书快照数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }

}
