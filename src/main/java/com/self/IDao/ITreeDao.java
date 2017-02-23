package com.self.IDao;

import com.self.domain.Tree;

public interface ITreeDao {

    int deleteByPrimaryKey(String id);

    int insert(Tree record);

    int insertSelective(Tree record);

    Tree selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Tree record);

    int updateByPrimaryKey(Tree record);
}