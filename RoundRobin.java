package com.uniProcessorCPUScheduling;
import java.util.Scanner;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Arrays;


public class RoundRobin
{
    private static void startUserExecution(PCB[] processes,int[] copyOfBT,int timeQuantum)
    {
        Queue<PCB>queue = new ArrayDeque<>();
        int completed = 0, time = 0;


        while(completed<processes.length)
        {
            for(PCB currentProcess:processes)
            {
                if(currentProcess.AT<=time && !queue.contains(currentProcess) && currentProcess.BT!=0)
                    queue.add(currentProcess);
            }
            if(queue.isEmpty())
            {
                time++;
            }
            else
            {
                PCB currentProcess = queue.poll();
                int executionTime = Math.min(timeQuantum,currentProcess.BT);
                int temp = time + executionTime;
                while(time < temp)
                {
                    currentProcess.BT--;
                    time++;
                    for(PCB process:processes)
                    {
                        if(process.AT<=time && !queue.contains(process) && process.BT!=0 && currentProcess!=process)
                            queue.add(process);
                    }
                }
                if(currentProcess.BT == 0)
                {
                    currentProcess.FT = time;
                    currentProcess.TT = currentProcess.FT - currentProcess.AT;
                    completed++;
                }
                else
                {
                    queue.add(currentProcess);
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
        int totalNumberOfProcesses = scanner.nextInt();

        PCB[] processes = new PCB[totalNumberOfProcesses];

        for(int i = 0 ; i< totalNumberOfProcesses ; i++)
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

        System.out.println("Enter time quantum:");
        int timeQuantum = scanner.nextInt();

        Arrays.sort(processes, Comparator.comparingInt((PCB p) -> p.AT).thenComparingInt(p -> p.pid));

        int[] copyOfBT = new int[totalNumberOfProcesses];

        for(int i = 0; i < totalNumberOfProcesses ; i++)
        {
            copyOfBT[i]  = processes[i].BT;
        }

        RoundRobin.startUserExecution(processes,copyOfBT,timeQuantum);

        for(PCB currentProcess:processes)
            System.out.println(currentProcess);


    }
}
