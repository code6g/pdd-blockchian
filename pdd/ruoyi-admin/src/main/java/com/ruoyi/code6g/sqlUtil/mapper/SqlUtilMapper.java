package com.ruoyi.code6g.sqlUtil.mapper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface SqlUtilMapper {
      
    public int insert(String sql);
    public int delete(String sql);
    public int update(String sql);
    //    public List select(SqlUtil sqlutil);
    public List<Map> list(String sql);
    public ArrayList<HashMap> listArrayList(String sql);

}