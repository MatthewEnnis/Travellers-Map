package net.dark_roleplay.library;

import net.minecraft.client.settings.KeyBinding;

public class ChargedKeybinding extends KeyBinding {
	private boolean isDown;
	private long initPressTime;

	private Runnable chargeCallback;
	private Runnable failCallback;

	public ChargedKeybinding(String p_i45001_1_, int p_i45001_2_, String p_i45001_3_, Runnable chargeCallback, Runnable failCallback) {
		super(p_i45001_1_, p_i45001_2_, p_i45001_3_);
		this.chargeCallback = chargeCallback;
		this.failCallback = failCallback;
	}

	public void processChargedPress(){
		boolean isKeyDown = this.isKeyDown();
		if(isKeyDown && !isDown){
			isDown = true;
			initPressTime = System.currentTimeMillis();
		}else if(isKeyDown && System.currentTimeMillis() - initPressTime > 1000){
			isDown = false;
			chargeCallback.run();
		}else if(!isKeyDown && isDown){
			isDown = false;
			failCallback.run();
		}
	}

	public int getPressProgress(){
		return !isDown ? 0 : (int) (System.currentTimeMillis() - initPressTime);
	}
}
