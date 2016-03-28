package com.accenture.iot.app;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.accenture.iot.agent.service.IIotAgentService;


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
		System.out.println("hello world app"); 
		IIotAgentService hello1 = 
		        (IIotAgentService) context.getService( 
		        context.getServiceReference(IIotAgentService.class.getName()));
		        System.out.println("Start to login");
		        hello1.login();
		        hello1.sendTemp(29.0,20.0);
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
