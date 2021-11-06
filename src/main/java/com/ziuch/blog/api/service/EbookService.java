package com.ziuch.blog.api.service;

import com.ziuch.blog.api.domain.Ebook;
import com.ziuch.blog.api.domain.EbookExample;
import com.ziuch.blog.api.mapper.EbookMapper;
import com.ziuch.blog.api.req.EbookReq;
import com.ziuch.blog.api.resp.EbookResp;
import com.ziuch.blog.api.util.CopyUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    public List<EbookResp> list(EbookReq req){
        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        criteria.andNameLike("%" + req.getName() + "%");

        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);
//        List<EbookResp> ebookRespList = new ArrayList<>();

//        for(Ebook ebook : list) {
////            EbookResp ebookResp = new EbookResp();
////            BeanUtils.copyProperties(ebook, ebookResp);
//            //对象copy
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//            ebookRespList.add(ebookResp);
//        }

        //列表copy

        return CopyUtil.copyList(ebookList, EbookResp.class);
    }
}
