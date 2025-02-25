package org.unibl.etf.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    public void saveActivity(Activity activity) {
        activityRepository.save(activity);
    }

    public Activity findById(int id) {
        return activityRepository.findById(id);
    }

}