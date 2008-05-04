package org.openmrs.scheduler;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.openmrs.util.OpenmrsClassLoader;

/**
 * 
 * @author jmiranda
 */
public class SchedulableFactory {

	/**
	 *  Singleton instance of the schedulable factory
	 */
	private final static SchedulableFactory factory = new SchedulableFactory();
	
	/**
	 * Logger 
	 */
	private static Log log = LogFactory.getLog(SchedulableFactory.class);
	  
	/**
	 * Private constructor 
	 */
	private SchedulableFactory() { }
	
	/**
	 * Gets an instance of the schedulable factory
	 * @return
	 */
	public static SchedulableFactory getInstance() { 
		return factory;
	}
  
	/**
	 * Creates a new instance of Schedulable used to run tasks. 
	 * @param taskConfig
	 * @return
	 * @throws SchedulerException
	 */
	public Schedulable createInstance(TaskConfig taskConfig) throws SchedulerException {
		try {
			Class schedulableClass = OpenmrsClassLoader.getInstance().loadClass( taskConfig.getSchedulableClass() );
			Schedulable schedulable = (Schedulable) schedulableClass.newInstance();
			// TODO send taskconfig ?
			if (log.isDebugEnabled())
				log.debug("initializing " + schedulableClass.getName());
			
			schedulable.initialize(taskConfig);
			return schedulable;
		}
		catch (ClassNotFoundException cnfe) {
			log.error("OpenmrsClassLoader could not load class: " + taskConfig.getSchedulableClass() + ".  Probably due to a module not being loaded");
			
			if (log.isDebugEnabled())
				log.debug("Full error trace of ClassNotFoundException", cnfe);
			
			return null;
		}
		catch (Exception e) {
			if (log.isDebugEnabled()) {
				// don't need to log errors here necessarily.  If its needed, the calling method can log it.
				log.debug("Error creating new task for class " + taskConfig.getSchedulableClass(), e );
			}
			
			throw new SchedulerException(e);
		}
	}
}