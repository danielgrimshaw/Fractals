/*
 * Common.java
 * 
 * Copyright 2015  <Daniel Grimshaw>
 * 
 * Common code for fractals.
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
 
 import java.awt.Graphics;
 import java.awt.Color;
 import java.util.Scanner;
 
 public class Common {
    private Common() {} // NO COMMON OBJECTS
    
    public static double linearInterpolate(double v0, double v1, double t) {
		// This is precise
		return (1-t)*v0 + t*v1;
	}
	
	public static void putPixel(Graphics g, int col, int row, int color) {
		g.setColor(new Color(color));
		g.drawLine(col,row,col,row);
	}
	
	public static void pause() {
        Scanner s = new Scanner(System.in);
        s.next();
    }
    
    public static void setBackground(Graphics g, int col, int row, Color bk) {
        g.setColor(bk);
        g.fillRect(0,0,col,row);
    }
 }