package com.coca.shoppingmbg.mapper;

import com.coca.shoppingmbg.model.PmsCommentReplay;
import com.coca.shoppingmbg.model.PmsCommentReplayExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface PmsCommentReplayMapper {
    long countByExample(PmsCommentReplayExample example);

    int deleteByExample(PmsCommentReplayExample example);

    int deleteByPrimaryKey(Long id);

    int insert(PmsCommentReplay row);

    int insertSelective(PmsCommentReplay row);

    List<PmsCommentReplay> selectByExample(PmsCommentReplayExample example);

    PmsCommentReplay selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") PmsCommentReplay row, @Param("example") PmsCommentReplayExample example);

    int updateByExample(@Param("row") PmsCommentReplay row, @Param("example") PmsCommentReplayExample example);

    int updateByPrimaryKeySelective(PmsCommentReplay row);

    int updateByPrimaryKey(PmsCommentReplay row);
}