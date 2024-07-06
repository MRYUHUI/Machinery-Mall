package com.mall.mechmall.service;

import com.mall.mechmall.domain.ShippingAddress;

import java.util.List;

/**
 * @Author: thh
 * @Date: 2024/7/3
 * @Description:
 */
public interface ShippingAddressService {

    List<ShippingAddress> getShippingAddressMapper(int userId);

    int countAddress(int userId);

    Boolean addShippingAddress(ShippingAddress shippingAddress);

    Boolean updateShippingAddress(ShippingAddress shippingAddress);

    Boolean deleteShippingAddress(int id);

    Boolean updateDfault(int id);

    Boolean resetDfault(int userId);
}
