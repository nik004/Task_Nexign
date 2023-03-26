package org.example;

import data.base.subscribers.CallWithCost;

import java.util.List;

public interface ViewReport {
    boolean CreateReport(double fixCost, String tarrifIndex, String number, List<CallWithCost> list);
}
