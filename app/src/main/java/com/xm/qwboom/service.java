package com.xm.qwboom;

import android.accessibilityservice.*;
import android.app.*;
import android.content.*;
import android.os.*;
import android.view.accessibility.*;
import android.widget.*;
import java.util.*;
/*

 时间：2018-09-09 12:08
 作者：炫明_3510088586
 QQ群：2414496466

 */
public class service extends AccessibilityService
{
	private int num=0;
	private String str="",PackageName="";
	@Override
	public void onStart(Intent intent, int startId)
	{
		// TODO: Implement this method
		super.onStart(intent, startId);
		str = intent.getStringExtra("body");
	    num = intent.getIntExtra("num", 0);
		System.out.println(str + num);
	}


	@Override
	public void onAccessibilityEvent(AccessibilityEvent event)
	{
		// TODO: Implement this method
		int type=event.getEventType();
		PackageName = event.getPackageName().toString();
		//System.out.println(event);
		switch (type)
		{
			case AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED:		
				SendMessage();
				break;
			case AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED:
				//SendMessage();
				break;
	    }
	}

	public void SendMessage()
	{

		for (int i=1;i <= num;i++)
		{
			String strr=str.replaceAll("/n", i + "");
			inputText(strr);
			findText("发送");
		}
	}

	public void inputText(String str)
	{
		ClipboardManager clipboard = (ClipboardManager)getSystemService(Context.CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText("text", str);
		clipboard.setPrimaryClip(clip);
		AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
		if (nodeInfo != null)
		{
			List<AccessibilityNodeInfo> list = null;
			if (PackageName.equals("com.tencent.mm"))
			{
				list = nodeInfo.findAccessibilityNodeInfosByViewId("com.tencent.mm:id/aep");
				for (AccessibilityNodeInfo lists:list)
				{
                    lists.performAction(AccessibilityNodeInfo.ACTION_FOCUS);
       				lists.performAction(AccessibilityNodeInfo.ACTION_PASTE);
				}
			}
			else
			{
				list = nodeInfo.findAccessibilityNodeInfosByText("发送");
				if (!list.isEmpty())
				{
					for (AccessibilityNodeInfo lists:list)
					{
						lists.getParent().getChild(0).performAction(AccessibilityNodeInfo.ACTION_FOCUS);
						lists.getParent().getChild(0).performAction(AccessibilityNodeInfo.ACTION_PASTE);
					}
				}
			}
		}
	}



	private void findText(String text)
	{
        AccessibilityNodeInfo nodeInfo = getRootInActiveWindow();
		if (nodeInfo != null)
		{
            List<AccessibilityNodeInfo> list = nodeInfo.findAccessibilityNodeInfosByText(text);
			if (!list.isEmpty())
			{
				for (AccessibilityNodeInfo lists:list)
				{
					lists.performAction(AccessibilityNodeInfo.ACTION_CLICK);
	     		}
			}
        }
    }



	@Override
	public void onInterrupt()
	{
		// TODO: Implement this method
	}

}

