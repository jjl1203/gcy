package com.example.gcy.service;

import com.example.gcy.pojo.LabTime;
import com.example.gcy.repository.LabTimeRepository;
import com.example.gcy.util.UpdateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LabTimeServiceImpl implements LabTimeService {


    @Autowired
    private LabTimeRepository labTimeRepository;

    @Override
    public LabTime save(LabTime labTime) {
        return labTimeRepository.save(labTime);
    }

    @Override
    public LabTime findById(Long id) {
        return labTimeRepository.findById(id).get();
    }

    @Override
    public LabTime updateStartTime(Date startTime) {
        return null;
    }

    @Override
    public LabTime updateEndTime(Date endTime) {
        return null;
    }

    @Override
    public LabTime updateLabTime(Long id, LabTime labTime) {

        LabTime l = labTimeRepository.findById(id).get();
        if (l!=null) {
            UpdateUtil.copyNullProperties(labTime, l);
            return labTimeRepository.save(l);
        }
        return null;
    }

    @Override
    public List<LabTime> labTimeList() {
        return labTimeRepository.findAll();
    }
}
