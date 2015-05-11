/*
 * ColorSet.java
 * 
 * Copyright 2015  <Daniel Grimshaw>
 * 
 * A class for creating coordinated sets of colors for fractal plotting
 * 
 * This program is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 2 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA 02110-1301, USA.
 * 
 * 
 */
package fractals;

public class ColorSet {
	private float hue, saturation, brightness, factor;
	
	public static final ColorSet pinkToGreen = new ColorSet(0.95F, 10.0F, 0.6F, 1.0F);
	public static final ColorSet blueToPink = new ColorSet(0.67F, 1.0F, .85F, 1.0F);
	
	public ColorSet(float hue, float factor, float saturation, float brightness) {
		this.setHue(hue);
		this.setFactor(factor);
		this.setSaturation(saturation);
		this.setBrightness(brightness);
	}
	
	public float getHue() {
		return this.hue;
	}
	
	public float getSaturation() {
		return this.saturation;
	}
	
	public float getBrightness() {
		return this.brightness;
	}
	
	public float getFactor() {
		return this.factor;
	}
	
	public void setHue(float hue) {
		this.hue = hue;
	}
	
	public void setBrightness(float brightness) {
		this.brightness = brightness;
	}
	
	public void setSaturation(float saturation) {
		this.saturation = saturation;
	}
	
	public void setFactor(float factor) {
		this.factor = factor;
	}
	
	public String toString() {
		String ret = "ColorSet: (";
		ret += this.hue;
		ret += "F + (float)(smoothColor * ";
		ret += this.factor;
		ret += "F), ";
		ret += this.saturation;
		ret += "F, ";
		ret += this.brightness;
		return ret + "F)";
	}
}

