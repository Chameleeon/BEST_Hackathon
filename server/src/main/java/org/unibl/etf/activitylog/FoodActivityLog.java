package org.unibl.etf.activitylog;

public class FoodActivityLog extends ActivityLog {
    public FoodActivityLog() {
    }

    public FoodActivityLog(int userId, int activityId) {
        super(userId, activityId);
    }

    @Override
    public boolean verifyActivity() {
        return false; // TODO: zavrsiti implementaciju
    }
}
