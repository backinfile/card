package com.backinfile.card.manager;

import com.backinfile.card.gen.GameMessageHandler.DTargetInfo;
import com.backinfile.card.gen.GameMessageHandler.ETargetSlotAimType;
import com.backinfile.card.gen.GameMessageHandler.ETargetType;

public class GameUtils {
	public static DTargetInfo newTargetInfo(ETargetType type, String tip) {
		return newTargetInfo(type, 0, -1, tip);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, int number, String tip) {
		return newTargetInfo(type, ETargetSlotAimType.None, number, tip);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, int minNumber, int maxNumber, String tip) {
		DTargetInfo newTargetInfo = newTargetInfo(type, ETargetSlotAimType.Occupy, 1, tip);
		newTargetInfo.setMinNumber(minNumber);
		newTargetInfo.setMaxNumber(maxNumber);
		return newTargetInfo;
	}

	public static DTargetInfo newTargetInfo(ETargetType type, ETargetSlotAimType slotAimType, int number, String tip) {
		return newTargetInfo(type, slotAimType, number, tip, false, false, false, false);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, int number, String tip, boolean onlyReady,
			boolean exceptPlan) {
		return newTargetInfo(type, ETargetSlotAimType.None, number, tip, onlyReady, exceptPlan, false, false);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, ETargetSlotAimType slotAimType, int number, String tip,
			boolean onlyReady, boolean exceptPlan) {
		return newTargetInfo(type, slotAimType, number, tip, onlyReady, exceptPlan, false, false);
	}

	public static DTargetInfo newTargetInfo(ETargetType type, ETargetSlotAimType slotAimType, int number, String tip,
			boolean onlyReady, boolean exceptPlan, boolean opponent, boolean exceptHand) {
		DTargetInfo targetInfo = new DTargetInfo();
		targetInfo.setType(type);
		targetInfo.setSlotAimType(slotAimType);
		targetInfo.setTip(tip);
		targetInfo.setMinNumber(number);
		targetInfo.setMaxNumber(number);
		targetInfo.setOnlyReady(onlyReady);
		targetInfo.setExceptPlan(exceptPlan);
		targetInfo.setOpponent(opponent);
		targetInfo.setExceptHand(exceptHand);
		return targetInfo;
	}

	public static void setNumber(DTargetInfo targetInfo, int minNumber, int maxNumber) {
		targetInfo.setMinNumber(minNumber);
		targetInfo.setMaxNumber(maxNumber);
	}
}
