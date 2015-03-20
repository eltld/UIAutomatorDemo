package com.company;

import java.util.ArrayList;

import com.android.uiautomator.core.UiObject;
import com.android.uiautomator.core.UiObjectNotFoundException;
import com.android.uiautomator.core.UiSelector;
import com.android.uiautomator.testrunner.UiAutomatorTestCase;


public class WidgetSuite extends UiAutomatorTestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		getUiDevice().pressHome();
	}

	public void testCallContactWidget() throws UiObjectNotFoundException, InterruptedException {
		
		Thread.sleep(1000);
		UiObject contactWidget = new UiObject(new UiSelector().text("Contact A"));
		contactWidget.clickAndWaitForNewWindow();
		
		Thread.sleep(1000);
		UiObject dialing = new UiObject(new UiSelector().text("Dialing").packageName("com.android.dialer"));
		assertTrue("Unable to Start calling contact A", dialing.exists());
		Logger.printMessage("Dialing contact");
		
		Thread.sleep(3000);
		UiObject endCallButton = new UiObject(new UiSelector().description("End"));
		endCallButton.click();
		Logger.printMessage("Stop dialing");
	}
	
	public void testMusicWidget() throws UiObjectNotFoundException, InterruptedException {
		UiObject playButton = new UiObject(new UiSelector().resourceId("com.android.music:id/control_play"));
		playButton.click();
		Logger.printMessage("Start the song");
		Thread.sleep(10000);
		
		UiObject songTitle = new UiObject(new UiSelector().resourceId("com.android.music:id/title"));
		String firstTitle = songTitle.getText();
		
		UiObject nextSongButton = new UiObject(new UiSelector().resourceId("com.android.music:id/control_next"));
		nextSongButton.click();
		Logger.printMessage("Start the next song");
		Thread.sleep(10000);
				
		assertFalse("The first song keeps playing", songTitle.getText().equals(firstTitle));
		playButton.click();
		Logger.printMessage("Stop the song");
	}
	
	public void testDraggingWidget() throws UiObjectNotFoundException {
		UiObject calendarWidget = new UiObject(new UiSelector().description("Calendar")); //.className("android.appwidget.AppWidgetHostView")); //by class name and content description
		
		int startX = calendarWidget.getVisibleBounds().centerX();
	    int startY = calendarWidget.getVisibleBounds().centerY();

	    getUiDevice().drag(startX, startY, 0, 0, 0);
	    Logger.printMessage("StartX = " + startX);
	    Logger.printMessage("StartY = " + startY);
	    
	    Logger.printMessage("Move the widget");
	    
	    //getUiDevice().drag(startX, startY, 0, 0, 0);
		
		assertTrue("Calendar widget hasn't moved", calendarWidget.getVisibleBounds().centerX()!= startX);
		assertTrue("Calendar widget hasn't moved", calendarWidget.getVisibleBounds().centerY()!= startY);
	}
	
	public void testHostViewWidget() throws UiObjectNotFoundException {
		UiObject hostViewWidget = new UiObject(new UiSelector().description("Power control"));
		assertTrue("Host view widget does not exist", hostViewWidget.exists());
		
		ArrayList<String> controls = new ArrayList<String>();
		controls.add("com.android.settings:id/img_wifi");
		controls.add("com.android.settings:id/img_bluetooth");
		controls.add("com.android.settings:id/img_location");
		controls.add("com.android.settings:id/img_sync");
		controls.add("com.android.settings:id/img_brightness");
		
		for (int i = 0; i< controls.size(); i++){
			UiObject object = new UiObject(new UiSelector().resourceId(controls.get(i)));
			assertTrue((controls.get(i)).split("_")[1] + "button does not exist", object.exists());
			object.click();
		}
	}
}












