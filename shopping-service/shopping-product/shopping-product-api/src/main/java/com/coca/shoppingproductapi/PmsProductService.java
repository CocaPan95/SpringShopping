package com.coca.shoppingproductapi;

import com.coca.shoppingmodel.dto.PmsProductParam;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PmsProductService {

    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int create(PmsProductParam productParam);
    @Transactional(isolation = Isolation.DEFAULT,propagation = Propagation.REQUIRED)
    int update(Long id, PmsProductParam productParam);
    int updatePublishStatus(List<Long> ids, Integer publishStatus);
    int updateRecommendStatus(List<Long> ids, Integer recommendStatus);
    int updateNewStatus(List<Long> ids, Integer newStatus);
    int updateDeleteStatus(List<Long> ids, Integer deleteStatus);

}
