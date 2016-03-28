/**
 * 
 */

package com.accenture.iot.agent.service;


/**
 * @author yong.du
 *
 */
public interface IIotAgentService {
	boolean login(); 
	float getTemp();
	void sendTemp(double tmp, double hum);
}
