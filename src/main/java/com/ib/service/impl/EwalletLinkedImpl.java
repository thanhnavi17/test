package com.ib.service.impl;

import com.ib.entity.EwalletLinked;
import com.ib.exception.InternalServerException;
import com.ib.repository.EwalletLinkedRepository;
import com.ib.service.EwalletLinkedDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class EwalletLinkedImpl implements EwalletLinkedDao {
    @Autowired
    private EwalletLinkedRepository elRepo;

    @Override
    public List<EwalletLinked> getAll() {
        return null;
    }

    @Override
    public EwalletLinked getInfo(Integer ma) {
        return null;
    }

    @Override
    public boolean add(EwalletLinked obj) {
        try {
            elRepo.save(obj);
            return  true;
        } catch (Exception ex) {
            throw new InternalServerException("Lỗi khi cập nhật Transaction Ewallet");
        }
    }

    @Override
    public boolean update(EwalletLinked obj) {
        return false;
    }

    @Override
    public boolean delete(Integer ma) {
        return false;
    }
}
