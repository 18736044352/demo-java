package com.java.dubbo;

import com.java.dto.DubboDto;
import com.java.interfaces.DubboInterface;
import org.springframework.stereotype.Service;

/**
 * Created by iss on 18/1/18.
 */
@Service("dubboService")
public class DubboServiceImpl implements DubboInterface {

    @Override
    public DubboDto getDubboDto(String mesage) {
        DubboDto dto = new DubboDto();
        dto.setMesage(mesage);
        return dto;
    }
}
