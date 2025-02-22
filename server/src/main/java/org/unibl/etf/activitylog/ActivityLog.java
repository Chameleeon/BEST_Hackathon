package org.unibl.etf.activitylog;

import org.unibl.etf.activity.Activity;

abstract public class ActivityLog {
    protected int userId;
    protected int activityId;

    public ActivityLog(int userId, int activityId) {
        this.userId = userId;
        this.activityId = activityId;
    }

    public ActivityLog() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getActivityId() {
        return activityId;
    }

    public void setActivityId(int activityId) {
        this.activityId = activityId;
    }

    public abstract boolean verifyActivity();
}
