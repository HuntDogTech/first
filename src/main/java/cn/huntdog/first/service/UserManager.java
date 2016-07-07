package cn.huntdog.first.service;

import cn.huntdog.first.dao.UserDao;
import cn.huntdog.first.entity.User;
import com.google.common.base.Optional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by JonDai on 2016/7/6.
 */
@Service
@Transactional(readOnly = true)
public class UserManager {
    @Resource
    private UserDao dao;

    /**
     * 获取所有用户
     * @return
     */
    public List<User> getAllUser(){
        return (List<User>) dao.findAll();
    }

    public User getUserByUserName(String userName){
        return dao.findByUserName(userName);
    }
    public User getUserByID(Long id){
        return dao.findOne(id);
    }

    /**
     * 用户注册简单
     * @param user
     */
    @Transactional(readOnly = false , propagation = Propagation.REQUIRED)
    public void register(User user){
        Optional.of(user);
        dao.save(user);
    }

    /**
     * 用户登录
     * @param user
     * @return
     * @throws Exception
     */
    public boolean login(User user) throws Exception {
        if(user == null){
            throw new Exception("用户名为空!");
        }else{
            User daoUser = dao.findByUserName(user.getUserName());
            if(daoUser == null){
                return false;
            }else {
                if(daoUser.getPassword().equals(user.getPassword())){
                    return true;
                }else {
                    return false;
                }
            }
        }
    }
}
