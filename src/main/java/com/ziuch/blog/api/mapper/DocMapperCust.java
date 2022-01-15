package com.ziuch.blog.api.mapper;

import org.apache.ibatis.annotations.Param;

public interface DocMapperCust {

    void viewDoc(@Param("id") String id);

    void voteDoc(@Param("id") String id);
}