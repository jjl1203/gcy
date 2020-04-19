package com.example.gcy.service;

import com.example.gcy.pojo.LabTime;

import java.util.Date;
import java.util.List;

public interface LabTimeService {

    LabTime save(LabTime labTime);

    LabTime findById(Long id);

    LabTime updateStartTime(Date startTime);

    LabTime updateLabTime(Long id, LabTime labTime);

    LabTime updateEndTime(Date endTime);

    List<LabTime> labTimeList();
}
