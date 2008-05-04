package org.openmrs.scheduler;

import org.openmrs.api.context.Context;


/**
 *  Stateful task 
 *
 */
public abstract class StatefulTask extends TaskConfig { 

	// Private context that provides some state
	private Context context;

	/**
	 *  Public no-arg constructor
	 */
	public StatefulTask() { }

	/**
	 *  Public constructor
	 */
	public StatefulTask(Context context) { 
	  this.context = context;
	}
	
	/**
	 *  Set the context for this task.
	 */
	public void setContext(Context context) { 
	  this.context = context;
	}
	

}

