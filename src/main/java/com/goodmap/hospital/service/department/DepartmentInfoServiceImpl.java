package com.goodmap.hospital.service.department;

import com.goodmap.hospital.common.utils.PinYinUtil;
import com.goodmap.hospital.mapper.DepartmentInfoMapper;
import com.goodmap.hospital.pojo.DepartmentInfo;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.security.acl.WorldGroupImpl;

import java.util.*;

/**
 * @author 刘智强
 * @date 2021/5/12
 * @Description
 */
@Service
public class DepartmentInfoServiceImpl extends BaseServiceImpl<DepartmentInfo> implements DepartmentInfoService {

    @Autowired
    private DepartmentInfoMapper departmentInfoMapper;

    @Override
    public List<DepartmentInfo> selcetAll() {
        return departmentInfoMapper.selectAll();
    }


    @Override
    public Map<String,List<DepartmentInfo>> getDepartmentInfoPinYinMap() {
        List<DepartmentInfo> departmentInfoList = departmentInfoMapper.selectAll();

        Map<String, List<DepartmentInfo>> map = new HashMap<>();

        for (int i = 1; i <= 26; i++) {
            String word = String.valueOf((char) (96 + i)).toUpperCase();

            List<DepartmentInfo> departmentInfos1 = new ArrayList<>();
            Iterator<DepartmentInfo> it = departmentInfoList.iterator();
            while (it.hasNext()) {
                DepartmentInfo dInfo = it.next();
                if(word.equalsIgnoreCase(PinYinUtil.getPinYin(dInfo.getName()))){
                    departmentInfos1.add(dInfo);
                    it.remove();
                }
            }
            map.put(word,departmentInfos1);
        }
        return map;
    }
}
