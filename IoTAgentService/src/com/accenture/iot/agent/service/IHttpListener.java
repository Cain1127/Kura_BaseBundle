/**
 * 
 */
package com.accenture.iot.agent.service;

/**
 * @author yong.du
 *
 */
public interface IHttpListener {
	public void onReqCompleted(String result);
	public void onReqError(String result);
}
