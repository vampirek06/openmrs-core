package org.openmrs.scheduler;



public interface Schedulable extends Runnable {
   
   /** 
    *  Run task. 
    */
    public void run();
    
   /** 
    * Initialize task. 
    *  
    * @param config 
    */
    public void initialize(TaskConfig config);
	
}
