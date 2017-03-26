package com.taqnihome.utils;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.JsonObject;
import com.taqnihome.request.custom.CustomRequest;

public class NotificationUtil {
	private static final String serverKey = "AAAAdVay44I:APA91bHog7dyl-BBHqP9_RsgptmG00vyQ7wnTBMGzR1j80kH2SwEaa9SpuRCYviT3GcOGJZKJOOjfj6wToYbmwLO2X_2QVzN_MOHc5qjykOi2KGbpfCJL2FC64CZDySZeshPUrxGCvc-";
	
	public static void sendBluetoothInvitation(final String userId, final List<String> deviceTokens, final String bluetoothAddress) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (String deviceToken : deviceTokens) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.addProperty("remote_user_id", userId);
						jsonObject.addProperty("connection_invite", 1);
						jsonObject.addProperty("bluetooth_address", bluetoothAddress);

						Sender sender = new FCMSender(serverKey);
						Message message = new Message.Builder().collapseKey("message").timeToLive(3)
								.delayWhileIdle(true).addData("message", jsonObject.toString()).build();

						// Use the same token(or registration id) that was
						// earlier
						// used to send the message to the client directly from
						// Firebase Console's Notification tab.
						Result result = sender.send(message, deviceToken, 1);
						System.out.println("Result: " + result.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}
	}
	
	public static void sendWifiInvitation(final String userId, final List<String> deviceTokens, final String wifiAddress) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (String deviceToken : deviceTokens) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.addProperty("remote_user_id", userId);
						jsonObject.addProperty("connection_invite", 2);
						jsonObject.addProperty("wifi_address", wifiAddress);

						Sender sender = new FCMSender(serverKey);
						Message message = new Message.Builder().collapseKey("message").timeToLive(3)
								.delayWhileIdle(true).addData("message", jsonObject.toString()).build();

						// Use the same token(or registration id) that was
						// earlier
						// used to send the message to the client directly from
						// Firebase Console's Notification tab.
						Result result = sender.send(message, deviceToken, 1);
						System.out.println("Result: " + result.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}
	}

	public static void sendBluetoothAddress(final String userId, final String deviceToken,
			final String bluetoothAddress) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					JsonObject jsonObject = new JsonObject();
					jsonObject.addProperty("bluetooth_address", bluetoothAddress);
					jsonObject.addProperty("remote_user_id", userId);
					jsonObject.addProperty("connection_invite", 0);

					Sender sender = new FCMSender(serverKey);
					Message message = new Message.Builder().collapseKey("message").timeToLive(3).delayWhileIdle(true)
							.addData("message", jsonObject.toString()).build();

					// Use the same token(or registration id) that was earlier
					// used to send the message to the client directly from
					// Firebase Console's Notification tab.
					Result result = sender.send(message, deviceToken, 1);
					System.out.println("Result: " + result.toString());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}
	}

	public static void notifyOtherUsers(final String userId, final List<String> deviceTokens) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (String deviceToken : deviceTokens) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.addProperty("remote_user_id", userId);
						jsonObject.addProperty("notification_message", 1);

						Sender sender = new FCMSender(serverKey);
						Message message = new Message.Builder().collapseKey("message").timeToLive(3)
								.delayWhileIdle(true).addData("message", jsonObject.toString()).build();

						// Use the same token(or registration id) that was
						// earlier
						// used to send the message to the client directly from
						// Firebase Console's Notification tab.
						Result result = sender.send(message, deviceToken, 1);
						System.out.println("Result: " + result.toString());
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}

		});
		t.start();
		try {
			t.join();
		} catch (InterruptedException iex) {
			iex.printStackTrace();
		}
	}
	
	public static String sendNotification()
	{
		final String uri = "https://api.instapush.im/v1/post";
	     
		CustomRequest.NullHostnameVerifier verifier = new CustomRequest.NullHostnameVerifier(); 
		CustomRequest.MySimpleClientHttpRequestFactory requestFactory = new  CustomRequest.MySimpleClientHttpRequestFactory(verifier,null);
		
	    RestTemplate restTemplate = new RestTemplate();
	    restTemplate.setRequestFactory(requestFactory);
	    
		HttpHeaders headers = new HttpHeaders();
	    headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	    headers.set("x-instapush-appid", "581c19e0a4c48ac98ab0390b");
	    headers.set("x-instapush-appsecret", "8ae91fa6799a86b2d8e00183892fb234");
	    headers.set("Content-Type", "application/json");
	    
	    String str = "{\"event\":\"test\",\"trackers\":{\"tracker\":\"Data is submitted for user. You can track it here... http://www.google.com/\"}}";
//	    		+ "Transmitted bytes: + transmitted_bytes + \", Received bytes:\" + received_bytes + \"}}";
	    HttpEntity<String> entity = new HttpEntity(str, headers);
	    entity.getBody();
	     
	    ResponseEntity<String> result = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
	     
	    return result.getBody();
	}


}
