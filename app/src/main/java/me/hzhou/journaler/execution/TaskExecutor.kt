package me.hzhou.journaler.execution

import java.util.concurrent.BlockingQueue
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

/**
 * Created by hzhou on 4/4/18.
 * Email: hzhou.me@gmail.com
 */
class TaskExecutor private constructor(corePoolSize: Int, maxPoolSize: Int, workQueue: BlockingQueue<Runnable>)
    : ThreadPoolExecutor(corePoolSize, maxPoolSize, 0L, TimeUnit.SECONDS, workQueue) {
    companion object {
        fun getInstance(capacity: Int): TaskExecutor {
            return TaskExecutor(capacity, 2 * capacity, LinkedBlockingQueue<Runnable>())
        }
    }
}