package org.unibl.etf.activitylog;

public class EnergyActivityLog extends ActivityLog {
    public EnergyActivityLog() {
    }

    public EnergyActivityLog(int userId, int activityId) {
        super(userId, activityId);
    }

    @Override
    public boolean verifyActivity() {
        return false; // TODO: zavrsiti implementaciju
    }
}
