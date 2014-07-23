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
 * A dataflow node with one input stream port.
 * This is classic Actor type.
 * @param <M> the type of accepted messages.
 */
public abstract class ActorVariable<M> extends DataflowVariable
    implements ActorPort<M>
{
    /** place for input token(s) */
    protected final StreamInput<M> input=createInput();
    
    public ActorVariable() {
    }

    public ActorVariable(RunnableTask task) {
        super(task);
    }

    /** Override this method if another type of input queue is desired. 
     * @return storage for input tokens
     */
    protected StreamInput<M> createInput() {
//        return new StreamInput<M>(new ArrayDeque<M>());
        return new StreamInput<M>();
    }

    public void post(M m) {
        input.post(m);
    }

    public void close() {
        input.close();
    }

    public boolean isClosed() {
        return input.isClosed();
    }

    //====================== backend
    
    /** 
     * process the retrieved tokens.
     * @throws Exception 
     */
    @Override
    protected void act() throws Exception {
        M message=input.value;
        if (message==null) {
            complete();
        } else {
            act(message);
        }
    }

    /** only have sense when called from act(M message) */
    public void pushback() {
        input.pushback();
    }
    
    /**
     * processes one incoming message
     * @param message the message to process
     * @throws Exception
     */
    protected abstract void act(M message) throws Exception;

    /**
     * processes closing signal
     * @throws Exception
     */
    protected void complete() throws Exception {}

}
