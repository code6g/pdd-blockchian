package com.ruoyi.code6g.sqlUtil.service;

//import com.demo.pojo.Fsxx;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface SqlUtilService {

	//List<Fsxx> list();
    //int add(Fsxx fsxx);

    public int insert(String sql);
    public int delete(String sql);
    public int update(String sql);
    List<Map> list(String sql);
    ArrayList<HashMap> listArrayList(String sql);

}
