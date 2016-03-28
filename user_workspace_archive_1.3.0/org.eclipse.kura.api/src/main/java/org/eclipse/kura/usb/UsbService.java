/**
 * Copyright (c) 2011, 2014 Eurotech and/or its affiliates
 *
 *  All rights reserved. This program and the accompanying materials
 *  are made available under the terms of the Eclipse Public License v1.0
 *  which accompanies this distribution, and is available at
 *  http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Eurotech
 */
package org.eclipse.kura.usb;

import java.util.List;

import javax.usb.UsbServices;

import org.eclipse.kura.KuraException;

/**
 * OSGi interface for getting JSR-80 javax.usb.UsbServices currently available in the system.
 */
public interface UsbService {

	/**
	 * Gets the UsbServices currently available as specified by JSR-80 in the javax.usb
	 * 
	 * @return	The currently available javax.usb.UsbServices
	 * @throws 	KuraException
	 */
	public UsbServices getUsbServices() throws KuraException;

	/**
	 * Gets the USB devices on the gateway
	 * 
	 * @return	The currently attached USB devices
	 */
	public List<? extends UsbDevice> getUsbDevices();

	/**
	 * Gets the USB block devices on the gateway
	 * 
	 * @return	The currently attached USB block devices
	 */
	public List<UsbBlockDevice> getUsbBlockDevices();

	/**
	 * Gets the USB network devices on the gateway
	 * 
	 * @return	The currently attached USB network devices
	 */
	public List<UsbNetDevice> getUsbNetDevices();

	/**
	 * Gets the USB TTY devices on the gateway
	 * 
	 * @return	The currently attached USB TTY devices
	 */
	public List<UsbTtyDevice> getUsbTtyDevices();
	
}
