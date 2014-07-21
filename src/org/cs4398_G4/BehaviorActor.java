package org.cs4398_G4;

import com.github.rfqu.df4j.core.Actor;

public class BehaviorActor extends Actor {
    StringBuilder sb=new StringBuilder();
    
  
    @Override
    protected void complete(){
        System.out.println(sb.toString());
    }

	@Override
	protected void act(Object message) throws Exception {
		// TODO Auto-generated method stub
		
		
	}
}
