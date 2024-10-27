package com.uniProcessorCPUScheduling;

public class PCB
{
    public int pid, priority, AT, BT, FT, WT, TT;

    public PCB(int pid, int priority, int AT,int BT)
    {
        this.pid = pid;
        this.priority = priority;
        this.AT = AT;
        this.BT = BT;
        this.FT = 0;
        this.WT = 0;
        this.TT = 0;
    }
    @Override
    public String toString()
    {
        return "[pid:"+pid+", priority:"+priority+", AT:"+AT+", BT:"+BT+", FT:"+FT+", TT:"+TT+", WT:"+WT+"]";
    }
}
