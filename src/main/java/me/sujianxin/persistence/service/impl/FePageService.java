package me.sujianxin.persistence.service.impl;

import me.sujianxin.persistence.model.FePage;
import me.sujianxin.persistence.repository.FePageRepository;
import me.sujianxin.persistence.service.IFePageService;
import me.sujianxin.spring.domain.FePageDomain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * <p>Created with IDEA
 * <p>Author: laudukang
 * <p>Date: 2016/3/5
 * <p>Time: 23:04
 * <p>Version: 1.0
 */
@Service
@Transactional
public class FePageService implements IFePageService {
    @Autowired
    private FePageRepository fePageRepository;

    @Override
    public void save(FePage fePage) {
        fePageRepository.save(fePage);
    }

    @Override
    public void deleteById(int id) {
        fePageRepository.delete(id);
    }

    @Override
    public void updateById(FePageDomain fePageDomain) {
        FePage tmp = fePageRepository.findOne(fePageDomain.getId());
        if (null != tmp) {
            tmp.setDownloadCode(fePageDomain.getDownloadCode());
            tmp.setMultipleCode(fePageDomain.getMultipleCode());
            fePageRepository.save(tmp);
        }
    }

    @Override
    public int updateCode(FePageDomain fePageDomain) {
        return fePageRepository.updateCode(fePageDomain.getId(), fePageDomain.getMultipleCode(), fePageDomain.getDownloadCode());
    }

    @Override
    public FePage findOne(int id) {
        return fePageRepository.findOne(id);
    }

    @Override
    public Page<FePage> findAll(Pageable pageable) {
        return fePageRepository.findAll(pageable);
    }

    @Override
    public long count() {
        return fePageRepository.count();
    }
}
