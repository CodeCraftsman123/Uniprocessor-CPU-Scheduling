package com.uniProcessorCPUScheduling;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;

public class PriorityNonPreemptive
{
    private static void startUserExecution(PCB[] processes)
    {
        int time = 0,completed = 0;
        PriorityQueue<PCB>pq = new PriorityQueue<>(Comparator.comparingInt((PCB p) -> p.priority).thenComparingInt(p -> p.AT).thenComparingInt(p -> p.pid));

        while(completed < processes.length)
        {
            for(PCB currentProcess:processes)
            {
                if(currentProcess.AT<=time && !pq.contains(currentProcess) && currentProcess.FT == 0)
                    pq.add(currentProcess);
            }
            if(pq.isEmpty())
            {
                time++;
            }
            else
            {
                PCB currentProcess = pq.poll();
                currentProcess.FT = time + currentProcess.BT;
                time += currentProcess.BT;
                currentProcess.TT = currentProcess.FT - currentProcess.AT;
                currentProcess.WT = currentProcess.TT - currentProcess.BT;
                completed++;
            }
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total number of processes:");
        int totalNumberOfProcess = scanner.nextInt();
        PCB[] processes = new PCB[totalNumberOfProcess];

        for(int i = 0; i < totalNumberOfProcess ;i++)
        {
            int pid = (i+1);
            System.out.println("Enter priority of process with pid "+pid+":");
            int priority = scanner.nextInt();
            System.out.println("Enter Arrival Time of process with pid "+pid+":");
            int AT = scanner.nextInt();
            System.out.println("Enter Burst Time of process with pid "+pid+":");
            int BT = scanner.nextInt();
            processes[i] = new PCB(pid,priority,AT,BT);
        }

        PriorityNonPreemptive.startUserExecution(processes);

        for(PCB element:processes)
            System.out.println(element);
    }
}
