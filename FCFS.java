package com.uniProcessorCPUScheduling;
import java.util.Scanner;
import java.util.Arrays;
import java.util.Comparator;

public class FCFS
{

    private static  void startExecution(PCB[] process)
    {
        int time = process[0].AT;
        int completed = 0;
        while(completed<process.length)
        {
            while(time<process[completed].AT)
                time++;
            time += process[completed].BT;
            process[completed].FT = time;
            process[completed].TT = process[completed].FT - process[completed].AT;
            process[completed].WT = process[completed].TT - process[completed].BT;
            completed++;
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total Number Of Process:");
        int totalNumberOfProcess = scanner.nextInt();
        PCB[] process =  new PCB[totalNumberOfProcess];

        for(int i = 0 ; i < totalNumberOfProcess ; i++)
        {
            int pid = (i+1);
            System.out.println("Enter the priority of process with Pid "+pid+":");
            int priority = scanner.nextInt();
            System.out.println("Enter the Arrival Time of process with Pid "+pid+":");
            int AT = scanner.nextInt();
            System.out.println("Enter the Burst Time Of Process with Pid "+pid+":");
            int BT = scanner.nextInt();
            process[i] = new PCB(pid,priority,AT,BT);
        }

        Arrays.sort(process,Comparator.comparingInt((PCB p) -> p.AT).thenComparingInt(p -> p.pid));

        FCFS.startExecution(process);

        for(PCB element:process)
            System.out.println(element);

    }
}