package com.coca.shoppinguserservice.service.impl;

import com.coca.shoppingmodel.entity.ums.UmsMemberProductCategoryRelation;
import com.coca.shoppinguserservice.mapper.UmsMemberProductCategoryRelationMapper;
import com.coca.shoppinguserservice.service.IUmsMemberProductCategoryRelationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员与产品分类关系表（用户喜欢的分类） 服务实现类
 * </p>
 *
 * @author coca
 * @since 2023-06-27
 */
@Service
public class UmsMemberProductCategoryRelationServiceImpl extends ServiceImpl<UmsMemberProductCategoryRelationMapper, UmsMemberProductCategoryRelation> implements IUmsMemberProductCategoryRelationService {

}
