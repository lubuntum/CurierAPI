package com.example.curierplus.enities;

import java.io.Serializable;

public class WorkActivity implements Serializable {
    public String activityDate;
    public int ordersComplete = 0;

    public WorkActivity(String activityDate, int ordersComplete) {
        this.activityDate = activityDate;
        this.ordersComplete = ordersComplete;
    }

    public String getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(String activityDate) {
        this.activityDate = activityDate;
    }

    public int getOrdersComplete() {
        return ordersComplete;
    }

    public void setOrdersComplete(int ordersComplete) {
        this.ordersComplete = ordersComplete;
    }
}
