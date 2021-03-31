package com.shb.springbootstudy.service.impl;

import com.shb.springbootstudy.domain.entity.SbTest;
import com.shb.springbootstudy.dao.SbTestMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.shb.springbootstudy.service.ISbTestService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 测试表 服务实现类
 * </p>
 *
 * @author 沈涵博
 * @since 2021-03-30
 */
@Service
public class SbTestServiceImpl extends ServiceImpl<SbTestMapper, SbTest> implements ISbTestService {

}
