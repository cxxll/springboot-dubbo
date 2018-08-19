package com.cxl.springboot.service.impl;

import com.cxl.springboot.mapper.UserMapper;
import com.cxl.springboot.model.User;
import com.cxl.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserMapper userMapper;

//  注入springboot自动配置好的RedisTemplate
    @Autowired
    private RedisTemplate<Object,Object> redisTemplate;

/*    //穿透代码
    @Override
    public List<User> getAllUser() {

        List<User> userList = (List<User>) redisTemplate.opsForValue().get("alluser");

                userList = (List<User>) redisTemplate.opsForValue().get("allUsers");
                //从redis获取一下
                if (null == userList) {
                    System.out.println("查询的数据库。。。。。");
                    //缓存为空，查询一遍数据库
                    userList = userMapper.getAllUser();
                    //把数据库查询出来的数据，放入redis中
                    redisTemplate.opsForValue().set("allUsers", userList);
                }else{
                    System.out.println("查询的缓存。。。。。");
                }

        return userList;
    }*/

    @Override
    public /*synchronized*/ List<User> getAllUser() {
        //字符串的序列化器
         RedisSerializer redisSerializer = new StringRedisSerializer();

        //高并发条件下，此处有点问题：缓存穿透
        //查询缓存
        List<User> userList = (List<User>) redisTemplate.opsForValue().get("alluser");

        //双重检测锁
        if(null == userList) {
            synchronized (this) {
                userList = (List<User>) redisTemplate.opsForValue().get("allUsers");
                //从redis获取一下
                if (null == userList) {
                    System.out.println("查询的数据库。。。。。");
                    //缓存为空，查询一遍数据库
                    userList = userMapper.getAllUser();
                    //把数据库查询出来的数据，放入redis中
                    redisTemplate.opsForValue().set("allUsers", userList);
                }else{
                    System.out.println("查询的缓存。。。。。");
                }
            }
        }else{
            System.out.println("查询的缓存。。。...。");
        }
        return userList;
    }

    @Transactional
    @Override
    public int update(){
        User user = new User();
        user.setId(1);
        user.setName("123s3");
        int update = userMapper.updateByPrimaryKey(user);
        int a = 10/0;
        return update;
    }

    @Override
    public User findByName(String name) {
        return userMapper.findByName(name);
    }

    @Override
    public User findById(Integer id) {
        return userMapper.findById(id);
    }
}
