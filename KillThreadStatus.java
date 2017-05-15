package com.company;
// класс флаг. если тру то остановить все потоки. если фолс то норма
/**
 * Created by admin on 07.04.2017.
 */
public class KillThreadStatus {
    private boolean status;

    public boolean isStatus()
    {
        return status;
    }

    public void setStatus(boolean status)
    {
        this.status = status;
    }
}
