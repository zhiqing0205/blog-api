package com.ziuch.blog.api.controller;

import com.ziuch.blog.api.req.EbookQueryReq;
import com.ziuch.blog.api.req.EbookSaveReq;
import com.ziuch.blog.api.resp.CommonResp;
import com.ziuch.blog.api.resp.EbookQueryResp;
import com.ziuch.blog.api.resp.PageResp;
import com.ziuch.blog.api.service.EbookService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/ebook")
public class EbookController {

    @Resource
    private EbookService ebookService;

    @GetMapping("/list")
    public CommonResp list(EbookQueryReq req){
        CommonResp<PageResp<EbookQueryResp>> resp = new CommonResp<>();
        PageResp<EbookQueryResp> list = ebookService.list(req);
        resp.setContent(list);
        return resp;
    }

    @PostMapping("/save")
    public CommonResp save(@RequestBody EbookSaveReq req){
        CommonResp resp = new CommonResp<>();
        ebookService.save(req);
        return resp;
    }
}
