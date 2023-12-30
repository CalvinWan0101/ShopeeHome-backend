package com.calvinwan.shopeehomebackend.service.implementation;

import com.calvinwan.shopeehomebackend.dao.ShopDao;
import com.calvinwan.shopeehomebackend.dto.shop.ShopDto;
import com.calvinwan.shopeehomebackend.dto.shop.ShopLoginDto;
import com.calvinwan.shopeehomebackend.model.Shop;
import com.calvinwan.shopeehomebackend.service.ShopService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.web.server.ResponseStatusException;

@Service
public class ShopServiceImplementation implements ShopService {

    private final Logger log = LoggerFactory.getLogger(ShopServiceImplementation.class);

    @Autowired
    private ShopDao shopDao;

    @Override
    public String insert(ShopDto shopDto) {
        Shop shop = shopDao.getByEmail(shopDto.getEmail());
        if (shop != null) {
            log.warn("Email {} already exists", shopDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(shopDto.getPassword().getBytes());
        shopDto.setPassword(hashedPassword);
        shopDto.setDeleted(false);

        return shopDao.insert(shopDto);
    }

    @Override
    public Shop getById(String id) {
        return shopDao.getById(id);
    }

    @Override
    public void updateById(String id, ShopDto shopDto) {
        String hashedPassword = DigestUtils.md5DigestAsHex(shopDto.getPassword().getBytes());
        shopDto.setPassword(hashedPassword);
        shopDao.updateById(id, shopDto);
    }

    @Override
    public void deleteById(String id) {
        Shop shop = shopDao.getById(id);
        if (shop != null) {
            ShopDto shopDto = new ShopDto(shop.getEmail(), shop.getPassword(), shop.getName(), shop.getPhoneNumber(), shop.getAddress(), shop.getDescription(), shop.getAvatar(), shop.getBackground(), shop.getCreaterId(), shop.getDeleterId(), true);
            shopDao.updateById(id, shopDto);
        }
    }

    @Override
    public Shop login(ShopLoginDto shopLoginDto) {
        Shop shop = shopDao.getByEmail(shopLoginDto.getEmail());
        if (shop == null) {
            log.warn("Email {} not found", shopLoginDto.getEmail());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        String hashedPassword = DigestUtils.md5DigestAsHex(shopLoginDto.getPassword().getBytes());
        if (!hashedPassword.equals(shop.getPassword())) {
            log.warn("Password not match");
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        return shop;
    }
}
