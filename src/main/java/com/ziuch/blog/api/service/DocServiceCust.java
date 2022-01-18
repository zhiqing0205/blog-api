package com.ziuch.blog.api.service;

import com.ziuch.blog.api.domain.Doc;
import com.ziuch.blog.api.exception.BusinessException;
import com.ziuch.blog.api.exception.BusinessExceptionCode;
import com.ziuch.blog.api.mapper.DocMapper;
import com.ziuch.blog.api.mapper.DocMapperCust;
import com.ziuch.blog.api.util.RedisUtil;
import com.ziuch.blog.api.util.RequestContext;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DocServiceCust {

    @Resource
    private DocMapperCust docMapperCust;

    @Resource
    private DocMapper docMapper;

    @Resource
    private WsService wsService;

    @Resource
    private RedisUtil redisUtil;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(DocServiceCust.class);

    public void viewDoc(String id){
        docMapperCust.viewDoc(id);
    }

    public void voteDoc(String id){
        // 远程ip+文档id 24小时不重复
        String ip = RequestContext.getRemoteAddr();
        String key = "VOTE_IP_" + ip + "_DOCID_" + id;
        if(redisUtil.validateRepeat(key, 3600 * 24)){
            docMapperCust.voteDoc(id);
        } else {
            throw new BusinessException(BusinessExceptionCode.VOTE_REPEAT);
        }

        Doc docDB = docMapper.selectByPrimaryKey(Long.valueOf(id));
        String message = "【" + docDB.getName() + "】被点赞!"+ id + "!" + docDB.getEbookId();
        wsService.sendInfo(message, MDC.get("LOG_ID"));
    }

    public void updateEbookInfo(){
        docMapperCust.updateEbookInfo();
    }
}
