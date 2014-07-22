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

/**
 * A kind of dataflow variable: single input, single output.
 * 
 * Distributes received value/failure among consumers.
 * Value (or failure) can only be assigned once. It is then saved, and 
 * consumers connected after the assignment still would receive it.
 * 
 * Consumers can be of two kinds: synchronous via Future.get(),
 * and asynchronous via Promise.addListener(). 
 * 
 * To avoid thread starvation when limited threadpool is used,
 * Actors and other Tasks should not use Future.get(), but only subscription.
 * Threads can use synchronous interface.
 *
 * @param <T>  type of passed value
 */
public class CompletableFuture<T>
    extends CompletableFutureBase<T, Callback<T>>
    implements ListenableFuture<T>
{

    @Override
    protected void passResult(Callback<T> listenerLoc) {
        listenerLoc.post(value);
    }

    @Override
    protected void passFailure(Callback<T> listenerLoc) {
        listenerLoc.postFailure(exc);
    }

    public Promise<T> addListener(Callback<T> sink) {
        _addListener(sink);
        return this;
    }
}
