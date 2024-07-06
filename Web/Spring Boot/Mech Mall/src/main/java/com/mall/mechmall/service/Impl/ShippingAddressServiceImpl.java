package com.mall.mechmall.service.Impl;

import com.mall.mechmall.domain.ShippingAddress;
import com.mall.mechmall.mapper.ShippingAddressMapper;
import com.mall.mechmall.service.ShippingAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/7/3
 * @Description:
 */
@Service
public class ShippingAddressServiceImpl implements ShippingAddressService {
   @Autowired
    private ShippingAddressMapper shippingAddressMapper;

    @Override
    public  List<ShippingAddress> getShippingAddressMapper(int userId) {
        return shippingAddressMapper.getAddressesByUserId(userId);
    }
    @Override
    public int countAddress(int userId){
        return shippingAddressMapper.countAddress(userId);
    }

    @Override
    public Boolean addShippingAddress(ShippingAddress shippingAddress){
        return shippingAddressMapper.addShippingAddress(shippingAddress)>0;
    }
    @Override
    public Boolean updateShippingAddress(ShippingAddress shippingAddress){
        return shippingAddressMapper.updateShippingAddress(shippingAddress)>0;
    }

    @Override
    public Boolean deleteShippingAddress(int id){
        return shippingAddressMapper.deleteProduct(id)>0;
    }

    @Override
    public Boolean updateDfault(int id){
        return shippingAddressMapper.updateDfault(id)>0;
    }

    @Override
    public Boolean resetDfault(int userId){
        return shippingAddressMapper.resetDfault(userId)>0;
    }

}
