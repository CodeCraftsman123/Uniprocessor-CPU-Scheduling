package com.uniProcessorCPUScheduling;
import java.util.Scanner;
import java.util.PriorityQueue;
import java.util.Comparator;


public class SJFPreemptive
{
    private static void startUserExecution(PCB[] processes,int[] copyOfBT)
    {
        int time = 0, completed = 0;
        PriorityQueue<PCB>pq = new PriorityQueue<>(Comparator.comparingInt((PCB p) -> p.BT).thenComparingInt(p -> p.AT).thenComparingInt(p -> p.pid));
//        PCB prev = null;

        while(completed < processes.length)
        {
            for(PCB currentProcess:processes)
            {
                if(!pq.contains(currentProcess) && time>=currentProcess.AT && currentProcess.BT!=0)
                    pq.add(currentProcess);
            }

            if(pq.isEmpty())
            {
                time++;
//                prev = null;
            }
            else
            {
                PCB currentProcess = pq.poll();
                time++;
//                if(prev!=null && prev.BT == currentProcess.BT && prev != currentProcess)
//                {
//                    pq.add(currentProcess);
//                    currentProcess = prev;
//                }
                currentProcess.FT = time;
                currentProcess.BT--;
                if(currentProcess.BT == 0)
                {
                    currentProcess.TT = currentProcess.FT - currentProcess.AT;
                    completed++;
//                    prev = null;
                }
                else
                {
                    pq.add(currentProcess);
//                    prev = currentProcess;
                }
            }
        }

        for(int i = 0 ; i < processes.length ; i++)
        {
            processes[i].BT = copyOfBT[i];
            processes[i].WT = processes[i].TT - processes[i].BT;
        }
    }

    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter total number of processes:");
        int totalNumberOfProcess = scanner.nextInt();

        PCB[] processes = new PCB[totalNumberOfProcess];
        for(int i = 0 ; i < totalNumberOfProcess ; i++)
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
        int[] copyOfBT = new int[totalNumberOfProcess];

        for(int i = 0 ; i < totalNumberOfProcess ;i++)
        {
            copyOfBT[i] = processes[i].BT;
        }

        SJFPreemptive.startUserExecution(processes,copyOfBT);

        for(PCB currentProcess:processes)
            System.out.println(currentProcess);
    }
}