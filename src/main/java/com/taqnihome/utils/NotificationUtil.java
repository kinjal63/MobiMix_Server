package com.taqnihome.utils;

import java.util.List;

import com.google.android.gcm.server.Message;
import com.google.android.gcm.server.Result;
import com.google.android.gcm.server.Sender;
import com.google.gson.JsonObject;

public class NotificationUtil {
	private static final String serverKey = "AAAAhJvdP5g:APA91bGiGkx8g0-UvlfB0iFdHPRkVhsUBeocXFpS5s-25gu11WUQYbXAL1brunCMzf7AFVmS9dO7Gae2m0TXKP2TTJKvyIE0ZGd63a_mQgSbyHuxrKTDA0-tvTfg6B_K8tjDU_bMe0QYur7hcGfSqcir9-uCnGG88g";

	public static void sendBluetoothInvitation(final String userId, final List<String> deviceTokens) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (String deviceToken : deviceTokens) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.addProperty("remote_user_id", userId);
						jsonObject.addProperty("connection_invite", 1);
						jsonObject.addProperty("message",
								"Do you want to make bluetooth connection with user " + userId);

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
	
	public static void sendWifiInvitation(final String userId, final String wifiAddress, final List<String> deviceTokens) {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					for (String deviceToken : deviceTokens) {
						JsonObject jsonObject = new JsonObject();
						jsonObject.addProperty("remote_user_id", userId);
						jsonObject.addProperty("connection_invite", 2);
						jsonObject.addProperty("wifi_address", wifiAddress);
						jsonObject.addProperty("message",
								"Do you want to make Wifi direct connection with user " + userId);

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

}
