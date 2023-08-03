package top.mrjello.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import top.mrjello.mapper.DeptLogMapper;
import top.mrjello.pojo.DeptLog;
import top.mrjello.service.DeptLogService;

/**
 * @author jason@mrjello.top
 * @date 2023/7/3 21:06
 */
@Service
public class DeptLogServiceImpl implements DeptLogService {
    @Autowired
    private DeptLogMapper deptLogMapper;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @Override
    public void insertDeptLog(DeptLog deptLog) {
        deptLogMapper.insertDeptLog(deptLog);


    }
}
