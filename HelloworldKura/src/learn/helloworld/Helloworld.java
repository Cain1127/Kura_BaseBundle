package learn.helloworld;

import org.osgi.service.component.ComponentContext;

import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.eclipse.kura.usb.*;

public class Helloworld {
//	private static final Logger s_logger = LoggerFactory.getLogger(Helloworld.class);

//    private static final String APP_ID = "org.eclipse.kura.example.helloworld_kura";

    protected void activate(ComponentContext componentContext) throws SecurityException, UsbException {

//        System.out.println("Bundle " + APP_ID + " has started!");
//        System.out.println("Bundle " + APP_ID + " has started");
//        System.out.println(APP_ID + ": This is a debug message.");
    	System.out.println("=================activate Start=================");
        
        UsbServices services = UsbHostManager.getUsbServices();
        UsbHub rootHub = services.getRootUsbHub();
        System.out.println(rootHub.getAttachedUsbDevices());
        
        System.out.println("=================activate End=================");

    }

	protected void deactivate(ComponentContext componentContext) {

		System.out.println("=================deactivate=================");
//    	System.out.println("Bundle " + APP_ID + " has stopped!");

    }
}
