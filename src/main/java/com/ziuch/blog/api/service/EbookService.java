package com.ziuch.blog.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziuch.blog.api.domain.Ebook;
import com.ziuch.blog.api.domain.EbookExample;
import com.ziuch.blog.api.mapper.EbookMapper;
import com.ziuch.blog.api.req.EbookQueryReq;
import com.ziuch.blog.api.req.EbookSaveReq;
import com.ziuch.blog.api.resp.EbookQueryResp;
import com.ziuch.blog.api.resp.PageResp;
import com.ziuch.blog.api.util.CopyUtil;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {

    @Resource
    private EbookMapper ebookMapper;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(EbookService.class);

    public PageResp<EbookQueryResp> list(EbookQueryReq req){

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();

        if(!ObjectUtils.isEmpty(req.getName()))
            criteria.andNameLike("%" + req.getName() + "%");

        PageHelper.startPage(req.getPage(), req.getSize());
        List<Ebook> ebookList = ebookMapper.selectByExample(ebookExample);

        PageInfo<Ebook> info = new PageInfo<>(ebookList);
        LOG.info("总行数：{}", info.getTotal());
        LOG.info("总页数：{}", info.getPages());

//        List<EbookResp> ebookRespList = new ArrayList<>();

//        for(Ebook ebook : list) {
////            EbookResp ebookResp = new EbookResp();
////            BeanUtils.copyProperties(ebook, ebookResp);
//            //对象copy
//            EbookResp ebookResp = CopyUtil.copy(ebook, EbookResp.class);
//            ebookRespList.add(ebookResp);
//        }

        //列表copy
        List<EbookQueryResp> list = CopyUtil.copyList(ebookList, EbookQueryResp.class);

        PageResp<EbookQueryResp>  pageResp = new PageResp();
        pageResp.setTotal(info.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(EbookSaveReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);

        if(ObjectUtils.isEmpty(req.getId())) {
            ebook.setId(snowFlake.nextId());
            ebookMapper.insert(ebook);
        }
        else {
            ebookMapper.updateByPrimaryKey(ebook);
        }
    }
}
