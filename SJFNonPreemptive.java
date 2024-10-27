package com.uniProcessorCPUScheduling;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class SJFNonPreemptive
{
    private static void startUserExecution(PCB[] process)
    {
        PriorityQueue<PCB>pq = new PriorityQueue<>(Comparator.comparingInt((PCB p) -> p.BT).thenComparingInt(p -> p.AT).thenComparingInt(p -> p.pid));
        int completed = 0;
        int time = 0;
        while(completed < process.length)
        {
            for(PCB currentProcess:process)
            {
                if(time>=currentProcess.AT && !pq.contains(currentProcess) && currentProcess.FT == 0)
                    pq.add(currentProcess);
            }

            if(pq.isEmpty())
            {
                time++;
            }
            else
            {
                PCB  currentProcess = pq.poll();
                currentProcess.FT = time + currentProcess.BT;
                currentProcess.TT = currentProcess.FT - currentProcess.AT;
                currentProcess.WT = currentProcess.TT -  currentProcess.BT;
                time = time + currentProcess.BT;
                completed++;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total number of processes:");
        int totalNumberOfProcesses = scanner.nextInt();

        PCB[] process = new PCB[totalNumberOfProcesses];
        for(int i = 0 ; i < totalNumberOfProcesses ; i++)
        {
            int pid = (i+1);
            System.out.println("Enter priority of process with pid "+pid+":");
            int priority = scanner.nextInt();
            System.out.println("Enter Arrival Time of process with pid "+pid+":");
            int AT = scanner.nextInt();
            System.out.println("Enter Burst Time of process with pid "+pid+":");
            int BT = scanner.nextInt();
            process[i] = new PCB(pid,priority,AT,BT);
        }

        SJFNonPreemptive.startUserExecution(process);

        for(PCB currentProcess:process)
            System.out.println(currentProcess);

    }
}