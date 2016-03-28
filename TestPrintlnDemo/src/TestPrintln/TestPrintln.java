package TestPrintln;

import java.util.List;

import javax.usb.UsbConfiguration;
import javax.usb.UsbDevice;
import javax.usb.UsbEndpoint;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbInterface;
import javax.usb.UsbPipe;
import javax.usb.UsbServices;

import org.osgi.service.component.ComponentContext;

public class TestPrintln {

	protected void activate(ComponentContext componentContext) {
		
		System.out.println("=================USB Test Activate=================");
		try
        {

            UsbServices services = UsbHostManager.getUsbServices();
            UsbHub rootHub = services.getRootUsbHub();
            traverse(rootHub);

        } catch (Exception e) {
        	
        	System.out.println("=================USB Test Activate: Get UsbServices throw exception : " + e);
        	
        }
		
	}
	
	public static void testIO(UsbDevice device)
	{

	    try
	    {

	       // Access to the active configuration of the USB device, obtain 
	       // all the interfaces available in that configuration.

	       UsbConfiguration config = device.getActiveUsbConfiguration();
	       List<?> totalInterfaces = config.getUsbInterfaces();

	       // Traverse through all the interfaces, and access the endpoints 
	       // available to that interface for I/O.

	       for (int i = 0; i < totalInterfaces.size(); i++)
	       {
	    	   
//	          UsbInterface interf = (UsbInterface) totalInterfaces.get(i);
//	          interf.claim();
//	          List<?> totalEndpoints = interf.getUsbEndpoints();

//	          for (int j=0; j<totalEndpoints.size(); j++)
//	          {

	             // Access the particular endpoint, determine the direction
	             // of its data flow, and type of data transfer, and open the 
	             // data pipe for I/O.

//	             UsbEndpoint ep = (UsbEndpoint) totalEndpoints.get(i);
//	             int direction = ep.getDirection();
//	             int type = ep.getType();
//	             
//	             System.out.println("=================UsbDevice Direction: " + direction + " type : " + type);
//	             
//	             UsbPipe pipe = ep.getUsbPipe();
//	             pipe.open();

	             // Perform I/O through the USB pipe here.
	             
//	             pipe.close();

//	          }
//
//	          interf.release();

	       }

	    } catch (Exception e) {
	    	
	    	System.out.println("=================USB Test: Test Usb I/O throw exception : " + e);
	    	
	    }
	}
	
	protected void deactivate(ComponentContext componentContext) {

		System.out.println("=================USB Test deactivate=================");

    }
	
	public static void traverse(UsbDevice device)
    {

      if (device.isUsbHub())
      {

         List<?> attachedDevices = ((UsbHub) device).getAttachedUsbDevices();
         for (int i = 0; i < attachedDevices.size(); i++)
         {

           traverse((UsbDevice) attachedDevices.get(i));

         }
      }
      else
      {

    	  System.out.println("=================USB Test traverse device :" 
    	  + device 
    	  + " idVender: " 
    	  + device.getUsbDeviceDescriptor().idVendor() 
    	  + " idProduct" 
    	  + device.getUsbDeviceDescriptor().idProduct());
//    	  testIO(device);
    	  
      }
    }
	
}
