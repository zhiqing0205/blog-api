package com.ziuch.blog.api.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ziuch.blog.api.domain.User;
import com.ziuch.blog.api.domain.UserExample;
import com.ziuch.blog.api.exception.BusinessException;
import com.ziuch.blog.api.exception.BusinessExceptionCode;
import com.ziuch.blog.api.mapper.UserMapper;
import com.ziuch.blog.api.req.UserQueryReq;
import com.ziuch.blog.api.req.UserResetPasswordReq;
import com.ziuch.blog.api.req.UserSaveReq;
import com.ziuch.blog.api.resp.PageResp;
import com.ziuch.blog.api.resp.UserQueryResp;
import com.ziuch.blog.api.util.CopyUtil;
import com.ziuch.blog.api.util.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private SnowFlake snowFlake;

    private static final Logger LOG = LoggerFactory.getLogger(UserService.class);

    public PageResp<UserQueryResp> list(UserQueryReq req){

        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();

        if(!ObjectUtils.isEmpty(req.getLoginName()))
            criteria.andLoginNameLike('%' + req.getLoginName() + '%');

        PageHelper.startPage(req.getPage(), req.getSize());
        List<User> userList = userMapper.selectByExample(userExample);

        PageInfo<User> info = new PageInfo<>(userList);
        LOG.info("总行数：{}", info.getTotal());
        LOG.info("总页数：{}", info.getPages());

//        List<UserResp> userRespList = new ArrayList<>();

//        for(User user : list) {
////            UserResp userResp = new UserResp();
////            BeanUtils.copyProperties(user, userResp);
//            //对象copy
//            UserResp userResp = CopyUtil.copy(user, UserResp.class);
//            userRespList.add(userResp);
//        }

        //列表copy
        List<UserQueryResp> list = CopyUtil.copyList(userList, UserQueryResp.class);

        PageResp<UserQueryResp>  pageResp = new PageResp();
        pageResp.setTotal(info.getTotal());
        pageResp.setList(list);
        return pageResp;
    }

    public void save(UserSaveReq req) {
        User user = CopyUtil.copy(req, User.class);

        if(ObjectUtils.isEmpty(req.getId())) {

            User userDB = selectByLoginName(req.getLoginName());
            if(ObjectUtils.isEmpty(userDB)) {
                user.setId(snowFlake.nextId());

                userMapper.insert(user);
            } else {
                throw new BusinessException(BusinessExceptionCode.USER_LOGIN_NAME_EXIST);
            }
        }
        else {
            user.setLoginName(null);
            user.setPassword(null);
            userMapper.updateByPrimaryKeySelective(user);
        }
    }

    public void delete(Long id){
        userMapper.deleteByPrimaryKey(id);
    }

    public User selectByLoginName(String LoginName){
        UserExample userExample = new UserExample();
        UserExample.Criteria criteria = userExample.createCriteria();
        criteria.andLoginNameEqualTo(LoginName);

        List<User> list = userMapper.selectByExample(userExample);
        if(ObjectUtils.isEmpty(list)){
            return null;
        }
        return list.get(0);
    }

    public void resetPassword(UserResetPasswordReq req) {
        User user = CopyUtil.copy(req, User.class);
        userMapper.updateByPrimaryKeySelective(user);
    }
}
