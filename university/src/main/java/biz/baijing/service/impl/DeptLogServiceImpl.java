package biz.baijing.service.impl;


import biz.baijing.DeptLog;
import biz.baijing.mapper.DeptLogMapper;
import biz.baijing.service.DeptLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DeptLogServiceImpl implements DeptLogService {


    @Autowired
    private DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)    // 默认值 .REQUIRES 会和前面的 Transactional 一致；_NEW 会创建新事物
    @Override
    public void insert(DeptLog deptLog) {
        deptLogMapper.insert(deptLog);
    }
}
