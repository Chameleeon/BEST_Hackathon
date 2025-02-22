package org.unibl.etf.activitylog;

public class GarbageActivityLog extends ActivityLog {
    public GarbageActivityLog() {
    }

    public GarbageActivityLog(int userId, int activityId) {
        super(userId, activityId);
    }

    @Override
    public boolean verifyActivity() {
        return false; // TODO: zavrsiti implementaciju
    }
}
