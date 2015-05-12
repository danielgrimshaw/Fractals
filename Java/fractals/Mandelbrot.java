 /*
 * Mandelbrot.java
 * 
 * Copyright 2015  <Daniel Grimshaw>
 * 
 * Program to draw the Mandelbrot Set
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

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JComponent;
import fractals.Fractal;
import fractals.ColorSet;
import fractals.Common;

public class Mandelbrot extends JComponent implements Fractal{
	private static final int MAX_ITERATIONS = 1000;
	
	private final int maxrow, maxcol;
	private final boolean debug;
	
	private Complex c;
	private ColorSet color;
	
	public Mandelbrot(int rows, int cols) {
		this(rows, cols, ColorSet.pinkToGreen, false);
	}
	
	public Mandelbrot(int rows, int cols, ColorSet color, boolean debug) {
		this.debug = debug;
		this.maxrow = rows;
		this.maxcol = cols;
		this.color = color;
	}
	
	public void paint(Graphics g) {	
		if (debug)
			System.out.println("Painting...");
		
		int row, col; //col is column, not color
		
		double pMax = 1.0, pMin = -2.5, qMax = 1.0, qMin = -1.0;
		double deltaP = (pMax - pMin)/maxcol;
		double deltaQ = (qMax - qMin)/maxrow;
		
		for (col = 0; col < maxcol; col++) {
			for (row = 0; row < maxrow; row++) {
				double p = pMin + col*deltaP; // Scaled x-coordinate of the point
				double q = qMin + row*deltaQ; // Scaled y-coordinate of the point
				c = new Complex(p, q);
				
				Complex z = new Complex(0.0, 0.0);
				double smoothcolor = 0;
				
				for (int i = 0; i < MAX_ITERATIONS && z.abs() < 30; i++) {
					z = f(z);
                    smoothcolor = i + 1 - Math.log(Math.log(z.abs()))/Math.log(2);
				}
				
				smoothcolor = smoothcolor/MAX_ITERATIONS;
				Common.putPixel(g, col, row, 
						Color.HSBtoRGB(color.getHue() + (float)(color.getFactor()*smoothcolor),
										color.getSaturation(), 
										color.getBrightness()));
			}
		}
	}
	
	public Complex f(Complex z) {
		return z.times(z).plus(c);
	}
		
	public static void main(String [] args) {
		int frameWidth = 640;
		int frameHeight = 480;
		
		JFrame frame = new JFrame("Mandelbrot Set");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		frame.setSize(frameWidth, frameHeight/*/frame.getWidth(), frame.getHeight()*/);
		//frame.pack();
		frame.setVisible(true);
		frame.getContentPane().add(new Mandelbrot(frame.getContentPane().getHeight(), frame.getContentPane().getWidth(), ColorSet.pinkToGreen, true));
	}
}
