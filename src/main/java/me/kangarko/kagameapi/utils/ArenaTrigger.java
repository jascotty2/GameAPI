package me.kangarko.kagameapi.utils;

import java.util.Set;

public class ArenaTrigger {

	// Mode one: Trigger when match numbers here
	private final Set<Integer> triggers;

	// Mode two: Trigger each time on the trigger
	private final int trigger;
	private int count = 0;

	public ArenaTrigger(Set<Integer> triggers) {
		this(-1, triggers);
	}

	public ArenaTrigger(int trigger) {
		this(trigger, null);
	}

	private ArenaTrigger(int trigger, Set<Integer> triggers) {
		this.trigger = trigger;
		this.triggers = triggers;
	}

	public final boolean trigger(int phase) {
		if (triggers != null)
			return triggers.contains(phase);

		if (trigger != -1) {
			if (++count >= trigger) {
				count = 0;

				return true;
			}

			return false;
		}

		throw new RuntimeException("NO BUENO");
	}
}
