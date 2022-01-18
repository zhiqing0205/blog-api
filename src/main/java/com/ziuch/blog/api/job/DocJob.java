package com.ziuch.blog.api.job;

import com.ziuch.blog.api.service.DocServiceCust;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class DocJob {

    private static final Logger LOG = LoggerFactory.getLogger(DocJob.class);

    @Resource
    private DocServiceCust docServiceCust;

    @Resource
    private SnowFlake snowFlake;

    /**
     * 每一小时更新电子书信息
     */
    @Scheduled(cron = "5/30 * * * * ?")
//    @Scheduled(cron = "0 0 */1 * * ?")
    public void cron() {
        // 增加日志流水号
        MDC.put("LOG_ID", String.valueOf(snowFlake.nextId()));
        LOG.info("更新电子书下的文档数据开始");
        long start = System.currentTimeMillis();
        docServiceCust.updateEbookInfo();
        LOG.info("更新电子书下的文档数据结束，耗时：{}毫秒", System.currentTimeMillis() - start);
    }

}
