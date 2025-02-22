package org.unibl.etf.activitylog;

public class TransportActivityLog extends ActivityLog {
    public TransportActivityLog() {
    }

    public TransportActivityLog(int userId, int activityId) {
        super(userId, activityId);
    }

    @Override
    public boolean verifyActivity() {
        return false; // TODO: zavrsiti implementaciju
    }
}
