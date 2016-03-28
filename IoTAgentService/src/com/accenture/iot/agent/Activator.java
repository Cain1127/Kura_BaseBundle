package com.accenture.iot.agent;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.accenture.iot.agent.service.IIotAgentService;
import com.accenture.iot.agent.service.IotAgentServiceImp;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
		System.out.println("==========Debug log begin==========");
		IotAgentServiceImp obj = new IotAgentServiceImp();
		System.out.println("==========Debug log end==========");
//	    context.registerService( 
//	            IIotAgentService.class.getName(), 
//	            new IotAgentServiceImp(), 
//	            null);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
