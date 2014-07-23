/*
 * Copyright 2011 by Alexei Kaigorodov
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.github.rfqu.df4j.core;


import java.util.Collection;
import java.util.List;
import java.util.WeakHashMap;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Contains:
 * - default executor
 * - threadfactory for context-aware executors, including default one
 * - timer 
 * Is accessed as a Threadlocal variable
 * @author rfq
 */
public class DFContext  {
    
    protected static final ThreadLocal <DFContext> currentContextKey
        = new ThreadLocal <DFContext> ()
    {
        @Override
        protected DFContext initialValue() {
            return new DFContext();
        }       
    };

    /**
     */
    public static void setCurrentContext(DFContext context) {
        currentContextKey.set(context);
    }

    /**
     * @return current context stored in thread-local variable
     */
    public static DFContext getCurrentContext() {
        return currentContextKey.get();
    }

    /**
     * removes current context
     */
    public static void removeCurrentContext() {
        currentContextKey.remove();
    }
    
    //----------------------- ----------------- Executor
    protected Executor currentExecutor;
    /**
     * @return current executor stored in thread-local variable
     */
    protected synchronized Executor _getCurrentExecutor() {
        Executor res = currentExecutor;
        if (res == null) {
            res=currentExecutor = newDefaultExecutor();
        }
        return res;
    }

    protected synchronized Executor _setCurrentExecutor(Executor executor) {
        Executor res = currentExecutor;
        currentExecutor=executor;
        return res;
    }

    /**
     * @return current executor stored in thread-local variable
     */
    public static Executor getCurrentExecutor() {
        return getCurrentContext()._getCurrentExecutor();
    }

    //---------------------------------------- context-aware thread factory

    static String dfprefix = " DF ";
    
    protected Executor newDefaultExecutor() {
        int nThreads=Runtime.getRuntime().availableProcessors();
        return newFixedThreadPool(nThreads);
    }   
    
    protected Executor newSingleThreadExecutor() {
        ContextThreadFactory tf = new ContextThreadFactory(dfprefix);
        return Executors.newSingleThreadExecutor(tf);
    }
    
    protected ThreadPoolExecutor newFixedThreadPool(int nThreads) {
        ContextThreadFactory tf = new ContextThreadFactory(dfprefix);
        return (ThreadPoolExecutor) Executors.newFixedThreadPool(nThreads, tf);
    }
    
    protected ThreadPoolExecutor newCachedThreadPool() {
        ContextThreadFactory tf = new ContextThreadFactory(dfprefix);
        return (ThreadPoolExecutor) Executors.newCachedThreadPool(tf);
    }
    
    /** 
     * Do it on your own risk.
     * Good practice is that your executor should spread this context on its threads.
     * @return old executorremoveCurrentExecutor
     */
    public static Executor setCurrentExecutor(Executor executor) {
        return getCurrentContext()._setCurrentExecutor(executor);
    }

    public static void setSingleThreadExecutor() {
        DFContext context=getCurrentContext();
        Executor executor = context.newSingleThreadExecutor();
        context._setCurrentExecutor(executor);
    }
    
    public static void setFixedThreadPool(int nThreads) {
        DFContext context=getCurrentContext();
        Executor executor = context.newFixedThreadPool(nThreads);
        context._setCurrentExecutor(executor);
    }
    
    public static void setCachedThreadPool() {
        DFContext context=getCurrentContext();
        Executor executor = context.newCachedThreadPool();
        context._setCurrentExecutor(executor);
    }
    
    class ContextThreadFactory extends ThreadGroup implements ThreadFactory {
        String prefix;

        public ContextThreadFactory(String prefix) {
            super(dfprefix);
            this.prefix=prefix;
        }

        public Thread newThread(Runnable r) {
            return new ThreadTL(r);
        }

        class ThreadTL extends Thread {
            
            public ThreadTL(Runnable r) {
                super(ContextThreadFactory.this, r);
                setDaemon(true);
            }

            @Override
            public void run() {
                setCurrentContext(DFContext.this);
                super.run();
            }
        }

        @Override
        public void uncaughtException(Thread t, Throwable e) {
            super.uncaughtException(t, e);
        }
    }

    //----------------------- ----------------- ExecutorService

    protected ExecutorService executorService;

    public synchronized ExecutorService _getCurrentExecutorService() {
        if (executorService==null) {
            Executor executor=_getCurrentExecutor();
            if (executor instanceof ExecutorService) {
                executorService=(ExecutorService)executor;
            } else {
                executorService=new PrimitiveExecutorService(executor);
            }
        }
        return executorService;
    }

    public static ExecutorService getCurrentExecutorService() {
        return getCurrentContext()._getCurrentExecutorService();
    }

    /**
     * Waits for currently started tasks to finish.
     * Invoke before exiting main thread, or otherwise
     * thread pool with daemon threads would break execution
     * of the not finished tasks.
     */
    protected synchronized void _completeCurrentExecutorService() {
        if (!(currentExecutor==null)) {
            return;
        }
        executorService=null;
        if (!(currentExecutor instanceof ExecutorService)) {
            currentExecutor=null;
            return;
        }
        ExecutorService service = (ExecutorService)currentExecutor;
        currentExecutor=null;
        service.shutdown();
        try {
            service.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
        }
        service.shutdownNow();
    }
    
    /**
     * Waits for currently started tasks to finish.
     * Invoke before exiting main thread, or otherwise
     * thread pool with daemon threads would break execution
     * of the not finished tasks.
     */
    public static void completeCurrentExecutorService() {
        getCurrentContext()._completeCurrentExecutorService();
    }
    
    static class PrimitiveExecutorService implements ExecutorService {
        static final String message = "PrimitiveExecutor not a service";
        protected final Executor executor;

        public PrimitiveExecutorService(Executor executor) {
            this.executor = executor;
        }

        /**
         * Executes the given command at some time in the future.
         * 
         * @param command the runnable
         * @throws NullPointerException if command is null
         */
        public void execute(Runnable command) {
            executor.execute(command);
        }

        public void shutdown() {
            throw new UnsupportedOperationException(message);
        }

        public List<Runnable> shutdownNow() {
            throw new UnsupportedOperationException(message);
        }

        public boolean isShutdown() {
            return false;
        }

        public boolean isTerminated() {
            return false;
        }

        public boolean awaitTermination(long timeout, TimeUnit unit)
                throws InterruptedException {
            throw new UnsupportedOperationException(message);
        }

        public <T> Future<T> submit(Callable<T> task) {
            FutureTask<T> future = new FutureTask<T>(task);
            executor.execute(future);
            return future;
        }

        public <T> Future<T> submit(Runnable task, T result) {
            FutureTask<T> future = new FutureTask<T>(task, result);
            executor.execute(future);
            return future;
        }

        public Future<?> submit(Runnable task) {
            FutureTask<Void> future = new FutureTask<Void>(task, null);
            executor.execute(future);
            return future;
        }

        public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks)
                throws InterruptedException {
            throw new UnsupportedOperationException(message);
        }

        public <T> List<Future<T>> invokeAll(
                Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)
                throws InterruptedException {
            throw new UnsupportedOperationException(message);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks)
                throws InterruptedException, ExecutionException {
            throw new UnsupportedOperationException(message);
        }

        public <T> T invokeAny(Collection<? extends Callable<T>> tasks,
                long timeout, TimeUnit unit) throws InterruptedException,
                ExecutionException, TimeoutException {
            throw new UnsupportedOperationException(message);
        }

    }

    //================================== Storage for additional context items
    
    WeakHashMap<ItemKey<?>, Object> itemMap=new WeakHashMap<ItemKey<?>, Object>();

    private static AtomicInteger nextHashCode = new AtomicInteger();
    
    public class ItemKey<V> {
        private final int hashCode = nextHashCode.incrementAndGet();

        @Override
        public int hashCode() {
            return hashCode;
        }
        
        public V get() {
            V res;
            synchronized (itemMap) {
                res=(V) itemMap.get(this);
                if (res==null) {
                    res=initialValue(DFContext.this);
                    itemMap.put(this, res);
                }
            }
            return res;
        }
        
        public void remove() {
            synchronized (itemMap) {
                itemMap.remove(this);
            }            
        }
        
        protected V initialValue(DFContext context) {
            return null;
        }

        public void set(V value) {
            synchronized (itemMap) {
                itemMap.put(this, value);
            }
        }
        
    }
}