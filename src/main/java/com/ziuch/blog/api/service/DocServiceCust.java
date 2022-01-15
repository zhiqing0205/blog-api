package com.ziuch.blog.api.service;

import com.ziuch.blog.api.mapper.ContentMapper;
import com.ziuch.blog.api.mapper.DocMapperCust;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class DocServiceCust {

    @Resource
    private DocMapperCust docMapperCust;

    @Resource
    private ContentMapper contentMapper;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(DocServiceCust.class);

    public void viewDoc(String id){
        docMapperCust.viewDoc(id);
    }

    public void voteDoc(String id){
        docMapperCust.voteDoc(id);
    }
}
