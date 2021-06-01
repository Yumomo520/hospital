package com.goodmap.hospital.service.poi.Impl;

import com.goodmap.hospital.common.result.EntityResult;
import com.goodmap.hospital.common.result.ResultStatus;
import com.goodmap.hospital.mapper.MessageMapper;
import com.goodmap.hospital.pojo.ErroreM;
import com.goodmap.hospital.pojo.Message;
import com.goodmap.hospital.service.base.impl.BaseServiceImpl;
import com.goodmap.hospital.service.poi.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author 李美泉
 * @Data 2020/12/22 time
 * @Description
 **/
@Service
public class MessageServiceImpl extends BaseServiceImpl<ErroreM> implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public EntityResult<ErroreM> addM(ErroreM erroreM) {
        List<Message> data = erroreM.getData();
        if(data!=null){
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < data.size(); i++) {
                s.append(data.get(i).getMess()+";");
            }
            erroreM.setPoiname(s.toString());
        }
        int insert = messageMapper.add(erroreM.getMessage(),erroreM.getPoiname(),erroreM.getTextbox());
        if(insert==1)
        return new EntityResult<>(ResultStatus.SUCCESS.getCode(),ResultStatus.SUCCESS.getMessage(),erroreM);
        return new EntityResult<>(ResultStatus.ADD_ENTITY_ERROR.getCode(),ResultStatus.ADD_ENTITY_ERROR.getMessage(),null);

    }
}
