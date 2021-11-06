package com.ziuch.blog.api.service;

import com.ziuch.blog.api.domain.Ebook;
import com.ziuch.blog.api.domain.EbookExample;
import com.ziuch.blog.api.mapper.EbookMapper;
import com.ziuch.blog.api.req.EbookReq;
import com.ziuch.blog.api.resp.EbookResp;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        criteria.andNameLike("%" + req.getName() + "%");

        List<Ebook> list = ebookMapper.selectByExample(ebookExample);
        List<EbookResp> ebookRespList = new ArrayList<>();

        for(Ebook ebook : list) {
            EbookResp ebookResp = new EbookResp();
            BeanUtils.copyProperties(ebook, ebookResp);
            ebookResp.setId(111L);
            ebookRespList.add(ebookResp);
        }
        
        return ebookRespList;
    }
}
