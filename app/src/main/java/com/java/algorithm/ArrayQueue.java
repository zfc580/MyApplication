package com.java.algorithm;

import java.util.Arrays;

// 用数组实现的队列
public class ArrayQueue {
    // 数组：items，数组大小：n
    private String[] items;
    private int n = 0;
    // head 表示队头下标，tail 表示队尾下标
    private int head = 0;
    private int tail = 0;

    // 申请一个大小为 capacity 的数组
    public ArrayQueue(int capacity) {
        items = new String[capacity];
        n = capacity;
    }

    // 入队操作，将 item 放入队尾
    public boolean enqueue(String item) {
        // tail == n 表示队列末尾没有空间了
        if (tail == n) {
            // tail ==n && head==0，表示整个队列都占满了
            if (head == 0) return false;
            System.out.println("enqueue head = " + head);
            // 数据搬移
            for (int i = head; i < tail; ++i) {
                System.out.println("loop enqueue i = " + head);
                items[i-head] = items[i];
            }
            // 搬移完之后重新更新 head 和 tail
            tail -= head;
            head = 0;
        }

        items[tail] = item;
        ++tail;
        return true;
    }


    // 出队
    public String dequeue() {
        // 如果 head == tail 表示队列为空
        if (head == tail) return null;
        // 为了让其他语言的同学看的更加明确，把 -- 操作放到单独一行来写了
        String ret = items[head];
        items[head] = null;
        ++head;
        return ret;
    }


    @Override
    public String toString() {
        return Arrays.toString(items);
    }

    public static void main(String[] args) {
//        ArrayQueue queue = new ArrayQueue(6);
//        queue.enqueue("a");
//        queue.enqueue("b");
//        queue.enqueue("c");
//        queue.enqueue("d");
//        queue.enqueue("e");
//        System.out.println("queue = " + queue);
//        queue.dequeue();
//        System.out.println("queue = " + queue);
//        queue.enqueue("f");
//        System.out.println("queue = " + queue);
//        queue.enqueue("g");
        //疑问：++i后值已经增加，为什么打印出来i仍为原值；
        String[] array = new String[]{"a", "b"};
        int i = 0;
        ++i;
        System.out.println("i = " + i);
        for (i = 0; i < array.length; ++i) {
            System.out.println("loop enqueue array[" + i + "] = " + array[i]);
        }
    }
}
